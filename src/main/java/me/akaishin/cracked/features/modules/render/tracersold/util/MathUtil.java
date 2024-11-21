package me.akaishin.cracked.features.modules.render.tracersold.util;

import net.minecraft.util.math.Vec3d;


public class MathUtil
        implements Util {
            public static double square(double input) {
                return input * input;
            }
        
            public static double square(float input) {
                return input * input;
            }
            public static double lengthSQ(Vec3d vec3d) {
                return MathUtil.square(vec3d.x) + MathUtil.square(vec3d.y) + MathUtil.square(vec3d.z);
            }
        
}

