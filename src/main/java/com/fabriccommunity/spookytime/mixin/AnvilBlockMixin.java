package com.fabriccommunity.spookytime.mixin;

import com.fabriccommunity.spookytime.common.SpookyItems;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.AnvilBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PumpkinBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

@Mixin(AnvilBlock.class)
public class AnvilBlockMixin {


    @Inject(method = "onLanding", at = @At("HEAD"))
    protected void onLanding(World world, BlockPos upPosition, BlockState blockState_1, BlockState blockState_2, CallbackInfo info) {
        BlockPos downPosition = upPosition.offset(Direction.DOWN);
        Block checkBlock = world.getBlockState(downPosition).getBlock();
        Block anvilBlock = world.getBlockState(upPosition).getBlock();
        if (checkBlock instanceof PumpkinBlock) {
            Block.dropStack(world, upPosition, anvilBlock.getPickStack(world, upPosition, world.getBlockState(downPosition)));
            world.clearBlockState(upPosition, true);
            Block.dropStack(world, downPosition, new ItemStack(SpookyItems.TINY_PUMPKIN));
            world.clearBlockState(downPosition, true);
        }
    }
}