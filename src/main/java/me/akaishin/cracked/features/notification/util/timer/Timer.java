package me.akaishin.cracked.features.notification.util.timer;

public class Timer implements Passable {
    private long time = -1L;
	private long current;

    public boolean passedS(double s) {
        return this.getMs(System.nanoTime() - this.time) >= (long) (s * 1000.0);
    }

    public boolean passedM(double m) {
        return this.getMs(System.nanoTime() - this.time) >= (long) (m * 1000.0 * 60.0);
    }

    public boolean passedDms(double dms) {
        return this.getMs(System.nanoTime() - this.time) >= (long) (dms * 10.0);
    }

    public boolean passedDs(double ds) {
        return this.getMs(System.nanoTime() - this.time) >= (long) (ds * 100.0);
    }

    public boolean passedMs(double ms) {
        return this.getMs(System.nanoTime() - this.time) >=(long) ms;
    }
	
	public boolean hasReached(long passedTime) {
        return System.currentTimeMillis() - this.current >= passedTime;
    }

    public boolean passedNS(long ns) {
        return System.nanoTime() - this.time >= ns;
    }

    public void setMs(long ms) {
        this.time = System.nanoTime() - ms * 1000000L;
    }

    public long getPassedTimeMs() {
        return this.getMs(System.nanoTime() - this.time);
    }

    public void reset() {
        this.time = System.nanoTime();
    }

    public long getMs(long time) {
        return time / 1000000L;
    }

    public boolean passed( final long delay, final boolean reset ) {
        if ( reset ) this.reset( );
        return System.currentTimeMillis( ) - this.time >= delay;
    }
    public long getTime() {
        return System.nanoTime() - this.time;
    }

    public void adjust(int by) {
        time += by;
    }

    @Override
    public boolean passed(long delay) {
        return this.getMs(System.nanoTime() - this.time) >= delay;
    }
}