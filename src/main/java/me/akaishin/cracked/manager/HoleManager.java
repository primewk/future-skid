package me.akaishin.cracked.manager;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import me.akaishin.cracked.features.Feature;
import me.akaishin.cracked.features.modules.combat.HoleFiller;

import me.akaishin.cracked.features.modules.render.HoleESP;
import me.akaishin.cracked.util.BlockUtil;
import me.akaishin.cracked.util.EntityUtil;
import me.akaishin.cracked.util.Timer;

public class HoleManager
        extends Feature
        implements Runnable {
    private static final BlockPos[] surroundOffset = BlockUtil.toBlockPos(EntityUtil.getOffsets(0, true, true));
    private final List<BlockPos> midSafety = new ArrayList<BlockPos>();
    private final Timer syncTimer = new Timer();
    private final AtomicBoolean shouldInterrupt = new AtomicBoolean(false);
    private final Timer holeTimer = new Timer();
    private List<BlockPos> holes = new ArrayList<BlockPos>();
    private ScheduledExecutorService executorService;
    private int lastUpdates = 0;
    private Thread thread;

    public void update() {
        if (HoleESP.getInstance().holeThread.getValue() == HoleESP.ThreadMode.WHILE) {
            if (this.thread == null || this.thread.isInterrupted() || !this.thread.isAlive() || this.syncTimer.passedMs(HoleESP.getInstance().holeSync.getValue().intValue())) {
                if (this.thread == null) {
                    this.thread = new Thread(this);
                } else if (this.syncTimer.passedMs(HoleESP.getInstance().holeSync.getValue().intValue()) && !this.shouldInterrupt.get()) {
                    this.shouldInterrupt.set(true);
                    this.syncTimer.reset();
                    return;
                }
                if (this.thread != null && (this.thread.isInterrupted() || !this.thread.isAlive())) {
                    this.thread = new Thread(this);
                }
                if (this.thread != null && this.thread.getState() == Thread.State.NEW) {
                    try {
                        this.thread.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    this.syncTimer.reset();
                }
            }
        } else if (HoleESP.getInstance().holeThread.getValue() == HoleESP.ThreadMode.WHILE) {
            if (this.executorService == null || this.executorService.isTerminated() || this.executorService.isShutdown() || this.syncTimer.passedMs(10000L) || this.lastUpdates != HoleESP.getInstance().holeUpdates.getValue()) {
                this.lastUpdates = HoleESP.getInstance().holeUpdates.getValue();
                if (this.executorService != null) {
                    this.executorService.shutdown();
                }
                this.executorService = this.getExecutor();
            }
        } else if (this.holeTimer.passedMs(HoleESP.getInstance().holeUpdates.getValue().intValue()) && !HoleManager.fullNullCheck() && (HoleESP.getInstance().isOn() || HoleFiller.getInstance().isOn())) {
            this.holes = this.calcHoles();
            this.holeTimer.reset();
        }
    }

    public void settingChanged() {
        if (this.executorService != null) {
            this.executorService.shutdown();
        }
        if (this.thread != null) {
            this.shouldInterrupt.set(true);
        }
    }

    private ScheduledExecutorService getExecutor() {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(this, 0L, HoleESP.getInstance().holeUpdates.getValue().intValue(), TimeUnit.MILLISECONDS);
        return service;
    }

    @Override
    public void run() {
        if (HoleESP.getInstance().holeThread.getValue() == HoleESP.ThreadMode.WHILE) {
            while (true) {
                if (this.shouldInterrupt.get()) {
                    this.shouldInterrupt.set(false);
                    this.syncTimer.reset();
                    Thread.currentThread().interrupt();
                    return;
                }
                if (!HoleManager.fullNullCheck() && (HoleESP.getInstance().isOn() || HoleFiller.getInstance().isOn())) {
                    this.holes = this.calcHoles();
                }
                try {
                    Thread.sleep(HoleESP.getInstance().holeUpdates.getValue().intValue());
                } catch (InterruptedException e) {
                    this.thread.interrupt();
                    e.printStackTrace();
                }
            }
        }
        if (HoleESP.getInstance().holeThread.getValue() == HoleESP.ThreadMode.POOL && !HoleManager.fullNullCheck() && (HoleESP.getInstance().isOn() || HoleFiller.getInstance().isOn())) {
            this.holes = this.calcHoles();
        }
    }

    public List<BlockPos> getHoles() {
        return this.holes;
    }

    public List<BlockPos> getMidSafety() {
        return this.midSafety;
    }

    public List<BlockPos> getSortedHoles() {
        this.holes.sort(Comparator.comparingDouble(hole -> HoleManager.mc.player.getDistanceSq(hole)));
        return this.getHoles();
    }

    public List<BlockPos> calcHoles() {
        ArrayList<BlockPos> safeSpots = new ArrayList<BlockPos>();
        this.midSafety.clear();
        List<BlockPos> positions = BlockUtil.getSphere(EntityUtil.getPlayerPos(HoleManager.mc.player), HoleESP.getInstance().holeRange.getValue().floatValue(), HoleESP.getInstance().holeRange.getValue().intValue(), false, true, 0);
        for (BlockPos pos : positions) {
            if (!HoleManager.mc.world.getBlockState(pos).getBlock().equals(Blocks.AIR) || !HoleManager.mc.world.getBlockState(pos.add(0, 1, 0)).getBlock().equals(Blocks.AIR) || !HoleManager.mc.world.getBlockState(pos.add(0, 2, 0)).getBlock().equals(Blocks.AIR))
                continue;
            boolean isSafe = true;
            boolean midSafe = true;
            for (BlockPos offset : surroundOffset) {
                Block block = HoleManager.mc.world.getBlockState(pos.add(offset)).getBlock();
                if (BlockUtil.isBlockUnSolid(block)) {
                    midSafe = false;
                }
                if (block == Blocks.BEDROCK || block == Blocks.OBSIDIAN || block == Blocks.ENDER_CHEST || block == Blocks.ANVIL)
                    continue;
                isSafe = false;
            }
            if (isSafe) {
                safeSpots.add(pos);
            }
            if (!midSafe) continue;
            this.midSafety.add(pos);
        }
        return safeSpots;
    }

    public boolean isSafe(BlockPos pos) {
        boolean isSafe = true;
        for (BlockPos offset : surroundOffset) {
            Block block = HoleManager.mc.world.getBlockState(pos.add(offset)).getBlock();
            if (block == Blocks.BEDROCK) continue;
            isSafe = false;
            break;
        }
        return isSafe;
    }
}

