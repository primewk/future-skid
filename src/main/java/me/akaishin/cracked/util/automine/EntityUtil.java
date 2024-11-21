package me.akaishin.cracked.util.automine;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

import me.akaishin.cracked.Cracked;


public class EntityUtil implements Util {

    public static boolean isntValid(Entity entity, double range) {
        return entity == null || isDead(entity) || entity.equals(mc.player) || (entity instanceof EntityPlayer && Cracked.friendManager.isFriend(entity.getName())) || mc.player.getDistanceSq(entity) > Math.pow(range, 2);
    }
    public static boolean isDead(Entity entity) {
        return !isAlive(entity);
    }
    public static boolean isLiving(Entity entity) {
        return entity instanceof EntityLivingBase;
    }

    public static boolean isAlive(Entity entity) {
        return isLiving(entity) && !entity.isDead && ((EntityLivingBase) (entity)).getHealth() > 0;
    }

}
