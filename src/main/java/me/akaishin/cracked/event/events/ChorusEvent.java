package me.akaishin.cracked.event.events;

import me.akaishin.cracked.event.EventStage;

public class ChorusEvent
        extends EventStage {
    private final double chorusX;
    private final double chorusY;
    private final double chorusZ;

    public ChorusEvent(double x, double y, double z) {
        this.chorusX = x;
        this.chorusY = y;
        this.chorusZ = z;
    }

    public double getChorusX() {
        return this.chorusX;
    }

    public double getChorusY() {
        return this.chorusY;
    }

    public double getChorusZ() {
        return this.chorusZ;
    }
}

