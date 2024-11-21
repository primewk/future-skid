package me.akaishin.cracked.util.jumprender;

import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static me.akaishin.cracked.features.Feature.nullCheck;

public class Timer {

    private long time;
    private long ticks;
    private long current;

    public Timer() {
        time = -1;
        ticks = -1;
    }

    @SubscribeEvent
    public void onUpdate(LivingEvent.LivingUpdateEvent event) {
        // update ticks
        if (nullCheck()) {
            ticks++;
        } else {
            // reset time
            ticks = -1;
        }
    }

    public boolean passedS(double s) {
        return getMs(System.nanoTime() - this.time) >= ((long) (s * 1000.0));
    }

    public boolean passedDms(double dms) {
        return getMs(System.nanoTime() - this.time) >= ((long) (dms * 10.0));
    }

    public boolean passedDs(double ds) {
        return getMs(System.nanoTime() - this.time) >= ((long) (ds * 100.0));
    }

    public boolean passedMs(long ms) {
        return getMs(System.nanoTime() - this.time) >= ms;
    }

    public boolean passedTicks(long time) {
        return ticks >= time;
    }

    public boolean passedNS(long ns) {
        return System.nanoTime() - this.time >= ns;
    }

    public boolean passed(double ms) {
        return (double) (System.currentTimeMillis() - this.current) >= ms;
    }

    public long getPassedTimeMs() {
        return getMs(System.nanoTime() - this.time);
    }

    public void reset() {
        this.time = System.nanoTime();
        this.ticks = 0;
    }

    public long getMs(long time) {
        return time / 1000000;
    }

    public boolean passed(long delay) {
        return System.currentTimeMillis() - this.current >= delay;
    }
}