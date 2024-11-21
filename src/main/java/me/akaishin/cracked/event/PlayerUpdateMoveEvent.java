package me.akaishin.cracked.event;

import me.akaishin.cracked.event.EventStage;
import net.minecraft.util.MovementInput;

public class PlayerUpdateMoveEvent
        extends EventStage {
    public MovementInput movementInput;

    public PlayerUpdateMoveEvent(MovementInput movementInput) {
        this.movementInput = movementInput;
    }
}