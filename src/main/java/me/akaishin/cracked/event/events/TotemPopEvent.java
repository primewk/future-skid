package me.akaishin.cracked.event.events;

import me.akaishin.cracked.event.EventStage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.*;

public class TotemPopEvent extends EventStage
{
    private final EntityPlayer entity;

    public TotemPopEvent(EntityPlayer entity) {
        this.entity = entity;
    }

    public EntityPlayer getEntity() {
        return this.entity;
    }
}
