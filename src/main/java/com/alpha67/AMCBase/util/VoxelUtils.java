package com.alpha67.AMCBase.util;

import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

public class VoxelUtils {
    public VoxelUtils() {
    }

    public static VoxelShape combine(VoxelShape... shapes) {
        if (shapes.length <= 0) {
            return VoxelShapes.empty();
        } else {
            VoxelShape combined = shapes[0];

            for(int i = 1; i < shapes.length; ++i) {
                combined = VoxelShapes.or(combined, shapes[i]);
            }

            return combined;
        }
    }
}
