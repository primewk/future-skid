package me.akaishin.cracked.features.modules.combat.antiburrow.Utils;

import me.akaishin.cracked.Cracked;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;

public class EntityUtil
implements Wrapper {
    public static final Vec3d[] doubleLegOffsetList = new Vec3d[]{new Vec3d(-1.0, 0.0, 0.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -1.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-2.0, 0.0, 0.0), new Vec3d(2.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -2.0), new Vec3d(0.0, 0.0, 2.0)};

    public static boolean isntValid(Entity entity, double d) {
        return entity == null || EntityUtil.isDead(entity) || entity.equals(EntityUtil.mc.player) || entity instanceof EntityPlayer && Cracked.friendManager.isFriend(entity.getName()) || EntityUtil.mc.player.getDistanceSq(entity) > MathUtil.square(d);
    }
    public static boolean isDead(Entity entity) {
        return !EntityUtil.isAlive(entity);
    }
    public static boolean isAlive(Entity entity) {
        return EntityUtil.isLiving(entity) && !entity.isDead && ((EntityLivingBase)entity).getHealth() > 0.0f;
    }
    public static boolean isLiving(Entity entity) {
        return entity instanceof EntityLivingBase;
    }

}

