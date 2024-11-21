//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\akais\Desktop\Deobfuscador Mapping 1.12.2\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

package me.akaishin.cracked.features.future.guinew.gui.util;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class MathUtil implements Util {
   private static final Random random = new Random();

   public static int getRandom(int min, int max) {
      return min + random.nextInt(max - min + 1);
   }

   public static double getRandom(double min, double max) {
      return MathHelper.clamp(min + random.nextDouble() * max, min, max);
   }

   public static Vec3d getInterpolatedRenderPos(Entity entity, float ticks) {
      return interpolateEntity(entity, ticks).subtract(Minecraft.getMinecraft().getRenderManager().renderPosX, Minecraft.getMinecraft().getRenderManager().renderPosY, Minecraft.getMinecraft().getRenderManager().renderPosZ);
   }

   public static Vec3d interpolateEntity(Entity entity, float time) {
      return new Vec3d(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)time, entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)time, entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)time);
   }

   public static float getRandom(float min, float max) {
      return MathHelper.clamp(min + random.nextFloat() * max, min, max);
   }

   public static int clamp(int num, int min, int max) {
      return num < min ? min : Math.min(num, max);
   }

   public static float clamp(float num, float min, float max) {
      return num < min ? min : Math.min(num, max);
   }

   public static double clamp(double num, double min, double max) {
      return num < min ? min : Math.min(num, max);
   }

   public static float sin(float value) {
      return MathHelper.sin(value);
   }

   public static float cos(float value) {
      return MathHelper.cos(value);
   }

   public static float wrapDegrees(float value) {
      return MathHelper.wrapDegrees(value);
   }

   public static double wrapDegrees(double value) {
      return MathHelper.wrapDegrees(value);
   }

   public static Vec3d roundVec(Vec3d vec3d, int places) {
      return new Vec3d(round(vec3d.x, places), round(vec3d.y, places), round(vec3d.z, places));
   }

   public static double angleBetweenVecs(Vec3d vec3d, Vec3d other) {
      double angle = Math.atan2(vec3d.x - other.x, vec3d.z - other.z);
      angle = -(angle / 3.141592653589793D) * 360.0D / 2.0D + 180.0D;
      return angle;
   }

   public static double lengthSQ(Vec3d vec3d) {
      return square(vec3d.x) + square(vec3d.y) + square(vec3d.z);
   }

   public static double length(Vec3d vec3d) {
      return Math.sqrt(lengthSQ(vec3d));
   }

   public static double dot(Vec3d vec3d, Vec3d other) {
      return vec3d.x * other.x + vec3d.y * other.y + vec3d.z * other.z;
   }

   public static double square(double input) {
      return input * input;
   }

   public static double square(float input) {
      return (double)(input * input);
   }

   public static double round(double value, int places) {
      if (places < 0) {
         throw new IllegalArgumentException();
      } else {
         BigDecimal bd = BigDecimal.valueOf(value);
         bd = bd.setScale(places, RoundingMode.FLOOR);
         return bd.doubleValue();
      }
   }

   public static float wrap(float valI) {
      float val = valI % 360.0F;
      if (val >= 180.0F) {
         val -= 360.0F;
      }

      if (val < -180.0F) {
         val += 360.0F;
      }

      return val;
   }

   public static Vec3d direction(float yaw) {
      return new Vec3d(Math.cos(degToRad((double)(yaw + 90.0F))), 0.0D, Math.sin(degToRad((double)(yaw + 90.0F))));
   }

   public static float round(float value, int places) {
      if (places < 0) {
         throw new IllegalArgumentException();
      } else {
         BigDecimal bd = BigDecimal.valueOf((double)value);
         bd = bd.setScale(places, RoundingMode.FLOOR);
         return bd.floatValue();
      }
   }

   public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map, boolean descending) {
      LinkedList<Entry<K, V>> list = new LinkedList(map.entrySet());
      if (descending) {
         list.sort(Entry.comparingByValue(Comparator.reverseOrder()));
      } else {
         list.sort(Entry.comparingByValue());
      }

      LinkedHashMap<K, V> result = new LinkedHashMap();
      Iterator var4 = list.iterator();

      while(var4.hasNext()) {
         Entry<K, V> entry = (Entry)var4.next();
         result.put(entry.getKey(), entry.getValue());
      }

      return result;
   }

   public static String getTimeOfDay() {
      Calendar c = Calendar.getInstance();
      int timeOfDay = c.get(11);
      if (timeOfDay < 12) {
         return "Good Morning ";
      } else if (timeOfDay < 16) {
         return "Good Afternoon ";
      } else {
         return timeOfDay < 21 ? "Good Evening " : "Good Night ";
      }
   }

   public static double radToDeg(double rad) {
      return rad * 57.295780181884766D;
   }

   public static double degToRad(double deg) {
      return deg * 0.01745329238474369D;
   }

   public static double getIncremental(double val, double inc) {
      double one = 1.0D / inc;
      return (double)Math.round(val * one) / one;
   }

   public static double[] directionSpeed(double speed) {
      float forward = mc.player.movementInput.moveForward;
      float side = mc.player.movementInput.moveStrafe;
      float yaw = mc.player.prevRotationYaw + (mc.player.rotationYaw - mc.player.prevRotationYaw) * mc.getRenderPartialTicks();
      if (forward != 0.0F) {
         if (side > 0.0F) {
            yaw += (float)(forward > 0.0F ? -45 : 45);
         } else if (side < 0.0F) {
            yaw += (float)(forward > 0.0F ? 45 : -45);
         }

         side = 0.0F;
         if (forward > 0.0F) {
            forward = 1.0F;
         } else if (forward < 0.0F) {
            forward = -1.0F;
         }
      }

      double sin = Math.sin(Math.toRadians((double)(yaw + 90.0F)));
      double cos = Math.cos(Math.toRadians((double)(yaw + 90.0F)));
      double posX = (double)forward * speed * cos + (double)side * speed * sin;
      double posZ = (double)forward * speed * sin - (double)side * speed * cos;
      return new double[]{posX, posZ};
   }


   public static boolean areVec3dsAligned(Vec3d vec3d1, Vec3d vec3d2) {
      return areVec3dsAlignedRetarded(vec3d1, vec3d2);
   }

   public static boolean areVec3dsAlignedRetarded(Vec3d vec3d1, Vec3d vec3d2) {
      BlockPos pos1 = new BlockPos(vec3d1);
      BlockPos pos2 = new BlockPos(vec3d2.x, vec3d1.y, vec3d2.z);
      return pos1.equals(pos2);
   }

   public static float[] calcAngle(Vec3d from, Vec3d to) {
      double difX = to.x - from.x;
      double difY = (to.y - from.y) * -1.0D;
      double difZ = to.z - from.z;
      double dist = (double)MathHelper.sqrt(difX * difX + difZ * difZ);
      return new float[]{(float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(difZ, difX)) - 90.0D), (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(difY, dist)))};
   }

   public static float[] calcAngleNoY(Vec3d from, Vec3d to) {
      double difX = to.x - from.x;
      double difZ = to.z - from.z;
      return new float[]{(float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(difZ, difX)) - 90.0D)};
   }

   public static Vec3d calculateLine(Vec3d x1, Vec3d x2, double distance) {
      double length = Math.sqrt(multiply(x2.x - x1.x) + multiply(x2.y - x1.y) + multiply(x2.z - x1.z));
      double unitSlopeX = (x2.x - x1.x) / length;
      double unitSlopeY = (x2.y - x1.y) / length;
      double unitSlopeZ = (x2.z - x1.z) / length;
      double x = x1.x + unitSlopeX * distance;
      double y = x1.y + unitSlopeY * distance;
      double z = x1.z + unitSlopeZ * distance;
      return new Vec3d(x, y, z);
   }

   public static double multiply(double one) {
      return one * one;
   }

   public static Vec3d extrapolatePlayerPosition(EntityPlayer player, int ticks) {
      Vec3d lastPos = new Vec3d(player.lastTickPosX, player.lastTickPosY, player.lastTickPosZ);
      Vec3d currentPos = new Vec3d(player.posX, player.posY, player.posZ);
      double distance = multiply(player.motionX) + multiply(player.motionY) + multiply(player.motionZ);
      Vec3d tempVec = calculateLine(lastPos, currentPos, distance * (double)ticks);
      return new Vec3d(tempVec.x, player.posY, tempVec.z);
   }

   public static double[] differentDirectionSpeed(double speed) {
      Minecraft mc = Minecraft.getMinecraft();
      float forward = mc.player.movementInput.moveForward;
      float side = mc.player.movementInput.moveStrafe;
      float yaw = mc.player.prevRotationYaw + (mc.player.rotationYaw - mc.player.prevRotationYaw) * mc.getRenderPartialTicks();
      if (forward != 0.0F) {
         if (side > 0.0F) {
            yaw += (float)(forward > 0.0F ? -45 : 45);
         } else if (side < 0.0F) {
            yaw += (float)(forward > 0.0F ? 45 : -45);
         }

         side = 0.0F;
         if (forward > 0.0F) {
            forward = 1.0F;
         } else if (forward < 0.0F) {
            forward = -1.0F;
         }
      }

      double sin = Math.sin(Math.toRadians((double)(yaw + 90.0F)));
      double cos = Math.cos(Math.toRadians((double)(yaw + 90.0F)));
      double posX = (double)forward * speed * cos + (double)side * speed * sin;
      double posZ = (double)forward * speed * sin - (double)side * speed * cos;
      return new double[]{posX, posZ};
   }

   public static String getDirectionFromPlayer(double x, double z) {
      double angle = Math.toDegrees(Math.atan2(-(mc.player.posX - x), -(mc.player.posZ - z)));
      double r = angle + (double)mc.player.rotationYaw;
      if (r < 0.0D) {
         r += 360.0D;
      }

      if (!(r > 315.0D) && !(r <= 45.0D)) {
         if (r > 45.0D && r <= 135.0D) {
            return "to your left";
         } else if (r > 135.0D && r <= 225.0D) {
            return "behind you";
         } else {
            return r > 225.0D && r <= 315.0D ? "to your right" : ChatFormatting.OBFUSCATED + "living in your walls";
         }
      } else {
         return "in front of you";
      }
   }
}
