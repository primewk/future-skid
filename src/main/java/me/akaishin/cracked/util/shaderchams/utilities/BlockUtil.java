package me.akaishin.cracked.util.shaderchams.utilities;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
public class BlockUtil
implements Wrapper {
    private static IBlockState getState(BlockPos blockPos) {
        return BlockUtil.mc.world.getBlockState(blockPos);
    }

    public static Block getBlock(BlockPos blockPos) {
        return BlockUtil.getState(blockPos).getBlock();
    }
}

