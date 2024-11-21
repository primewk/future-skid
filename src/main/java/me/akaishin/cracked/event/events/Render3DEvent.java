package me.akaishin.cracked.event.events;

import me.akaishin.cracked.event.EventStage;

public class Render3DEvent
        extends EventStage {
    private final float partialTicks;

    public Render3DEvent(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public float getPartialTicks() {
        return this.partialTicks;
    }
   public float getAspect() {
     return this.partialTicks;
   }
}

