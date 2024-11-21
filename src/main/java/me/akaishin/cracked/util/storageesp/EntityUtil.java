package me.akaishin.cracked.util.storageesp;

import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.Entity;

public class EntityUtil implements IUtil
{
        public static Vec3d interpolateEntity(final Entity entity, final float time) {
        return new Vec3d(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * time, entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * time, entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * time);
    }
}
