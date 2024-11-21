//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package me.akaishin.cracked.features.modules.render;

import net.minecraft.util.math.*;
import net.minecraft.tileentity.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;

import java.awt.*;
import java.util.*;

import me.akaishin.cracked.event.events.*;
import me.akaishin.cracked.features.modules.*;
import me.akaishin.cracked.features.modules.client.*;
import me.akaishin.cracked.features.setting.*;
//import me.akaishin.cracked.util.*;
import me.akaishin.cracked.util.storageesp.*;
import net.minecraft.item.*;

public class StorageESP extends Module
{
    private final Setting<Float> range;
    private final Setting<Boolean> colorSync;
    private final Setting<Boolean> chest;
    private final Setting<Boolean> dispenser;
    private final Setting<Boolean> shulker;
    private final Setting<Boolean> echest;
    private final Setting<Boolean> furnace;
    private final Setting<Boolean> hopper;
    private final Setting<Boolean> cart;
    private final Setting<Boolean> frame;
    private final Setting<Boolean> box;
    private final Setting<Integer> boxAlpha;
    private final Setting<Boolean> outline;
    private final Setting<Float> lineWidth;
    
    public StorageESP() {
        super("StorageESP", "Highlights Containers.", Module.Category.RENDER, false, false, false);
        this.range = (Setting<Float>)this.register(new Setting("Range", 50.0f, 1.0f, 300.0f));
        this.colorSync = (Setting<Boolean>)this.register(new Setting("Speed", "Sync", 0.0, 0.0,false, 0));
        this.chest = (Setting<Boolean>)this.register(new Setting("Speed", "Chest", 0.0, 0.0, true, 0));
        this.dispenser = (Setting<Boolean>)this.register(new Setting("Speed", "Dispenser", 0.0, 0.0, false, 0));
        this.shulker = (Setting<Boolean>)this.register(new Setting("Speed", "Shulker", 0.0, 0.0, true, 0));
        this.echest = (Setting<Boolean>)this.register(new Setting("Speed", "Ender Chest", 0.0, 0.0, true, 0));
        this.furnace = (Setting<Boolean>)this.register(new Setting("Speed", "Furnace", 0.0, 0.0, false, 0));
        this.hopper = (Setting<Boolean>)this.register(new Setting("Speed", "Hopper", 0.0, 0.0, false, 0));
        this.cart = (Setting<Boolean>)this.register(new Setting("Speed", "Minecart", 0.0, 0.0, false, 0));
        this.frame = (Setting<Boolean>)this.register(new Setting("Speed", "Item Frame", 0.0, 0.0, false, 0));
        this.box = (Setting<Boolean>)this.register(new Setting("Speed", "Box", 0.0, 0.0, false, 0));
        this.boxAlpha = (Setting<Integer>)this.register(new Setting("BoxAlpha", 125, 0, 255, v -> this.box.getValue()));
        this.outline = (Setting<Boolean>)this.register(new Setting("Speed", "Outline", 0.0, 0.0, true, 0));
        this.lineWidth = (Setting<Float>)this.register(new Setting("LineWidth", 1.0f, 0.1f, 5.0f, v -> this.outline.getValue()));
    }
    
    public void onRender3D(final Render3DEvent event) {
        final HashMap<BlockPos, Integer> positions = new HashMap<BlockPos, Integer>();
        for (final TileEntity tileEntity : StorageESP.mc.world.loadedTileEntityList) {
            final BlockPos pos;
            if (((tileEntity instanceof TileEntityChest && this.chest.getValue()) || (tileEntity instanceof TileEntityDispenser && this.dispenser.getValue()) || (tileEntity instanceof TileEntityShulkerBox && this.shulker.getValue()) || (tileEntity instanceof TileEntityEnderChest && this.echest.getValue()) || (tileEntity instanceof TileEntityFurnace && this.furnace.getValue()) || (tileEntity instanceof TileEntityHopper && this.hopper.getValue())) && StorageESP.mc.player.getDistanceSq(pos = tileEntity.getPos()) <= MathUtil.square(this.range.getValue())) {
                final int color;
                if ((color = this.getTileEntityColor(tileEntity)) == -1) {
                    continue;
                }
                positions.put(pos, color);
            }
        }
        for (final Entity entity : StorageESP.mc.world.loadedEntityList) {
            final BlockPos pos;
            if (((entity instanceof EntityItemFrame && this.frame.getValue()) || (entity instanceof EntityMinecartChest && this.cart.getValue())) && StorageESP.mc.player.getDistanceSq(pos = entity.getPosition()) <= MathUtil.square(this.range.getValue())) {
                final int color;
                if ((color = this.getEntityColor(entity)) == -1) {
                    continue;
                }
                positions.put(pos, color);
            }
        }
        for (final Map.Entry entry : positions.entrySet()) {
            final BlockPos blockPos = (BlockPos) entry.getKey();
            final int color = (Integer) entry.getValue();
            RenderUtil.drawBoxESP(blockPos, ((boolean)this.colorSync.getValue()) ? Colors.INSTANCE.getCurrentColor() : new Color(color), false, new Color(color), this.lineWidth.getValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), false);
        }
    }
    
    private int getTileEntityColor(final TileEntity tileEntity) {
        if (tileEntity instanceof TileEntityChest) {
            return ColorUtil.Colors.BLUE;
        }
        if (tileEntity instanceof TileEntityShulkerBox) {
            return ColorUtil.Colors.RED;
        }
        if (tileEntity instanceof TileEntityEnderChest) {
            return ColorUtil.Colors.PURPLE;
        }
        if (tileEntity instanceof TileEntityFurnace) {
            return ColorUtil.Colors.GRAY;
        }
        if (tileEntity instanceof TileEntityHopper) {
            return ColorUtil.Colors.DARK_RED;
        }
        if (tileEntity instanceof TileEntityDispenser) {
            return ColorUtil.Colors.ORANGE;
        }
        return -1;
    }
    
    private int getEntityColor(final Entity entity) {
        if (entity instanceof EntityMinecartChest) {
            return ColorUtil.Colors.ORANGE;
        }
        if (entity instanceof EntityItemFrame && ((EntityItemFrame)entity).getDisplayedItem().getItem() instanceof ItemShulkerBox) {
            return ColorUtil.Colors.YELLOW;
        }
        if (entity instanceof EntityItemFrame && !(((EntityItemFrame)entity).getDisplayedItem().getItem() instanceof ItemShulkerBox)) {
            return ColorUtil.Colors.ORANGE;
        }
        return -1;
    }
}
