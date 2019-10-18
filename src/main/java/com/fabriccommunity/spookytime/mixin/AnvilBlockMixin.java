package com.fabriccommunity.spookytime.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.fabriccommunity.spookytime.block.ColoredPumpkinBlock;
import com.fabriccommunity.spookytime.registry.SpookyBlocks;

import net.minecraft.block.AnvilBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PumpkinBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

/**
 * Turns pumpkins into a tiny pumpkin when an anvil is dropped on top.
 *
 * @author vini2003
 */
@Mixin(AnvilBlock.class)
public class AnvilBlockMixin {
	@Inject(method = "onLanding(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/block/BlockState;)V", at = @At("HEAD"))
	protected void onLanding(World world, BlockPos upPosition, BlockState blockState_1, BlockState blockState_2, CallbackInfo info) {
		BlockPos downPosition = upPosition.offset(Direction.DOWN);
		Block checkBlock = world.getBlockState(downPosition).getBlock();
		Block anvilBlock = world.getBlockState(upPosition).getBlock();
		if (checkBlock instanceof PumpkinBlock && !(checkBlock instanceof ColoredPumpkinBlock) || checkBlock.equals(SpookyBlocks.WITCHED_PUMPKIN)) { // TODO add colored tiny pumpkins later

			Block.dropStack(world, upPosition, anvilBlock.getPickStack(world, upPosition, world.getBlockState(downPosition)));
			world.clearBlockState(upPosition, true);
			Block.dropStack(world, downPosition, checkBlock.equals(SpookyBlocks.WITCHED_PUMPKIN) ? new ItemStack(SpookyBlocks.TINY_WITCHED_PUMPKIN) : new ItemStack(SpookyBlocks.TINY_PUMPKIN));
			world.clearBlockState(downPosition, true);
		}
	}
}
