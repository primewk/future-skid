package me.akaishin.cracked.event.events;

import me.akaishin.cracked.event.EventStage;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class BlockCollisionBoundingBoxEvent
        extends EventStage {
    private final BlockPos pos;
    private AxisAlignedBB _boundingBox;

    public
    BlockCollisionBoundingBoxEvent ( BlockPos pos ) {
        this.pos = pos;
    }

    public
    BlockPos getPos ( ) {
        return pos;
    }

    public
    AxisAlignedBB getBoundingBox ( ) {
        return _boundingBox;
    }

    public
    void setBoundingBox ( AxisAlignedBB boundingBox ) {
        this._boundingBox = boundingBox;
    }
}