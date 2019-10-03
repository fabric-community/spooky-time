package com.fabriccommunity.spookytime.mixin;

import com.fabriccommunity.spookytime.registry.SpookyBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Makes deceased soil count as "natural" for tree spawning
 * @author Indigo Amann
 */
@Mixin(AbstractTreeFeature.class)
public class AbstractTreeFeatureMixin {
    @Inject(method = "isNaturalDirtOrGrass", at = @At(value = "HEAD"), cancellable = true)
    private static void isNaturalDeceased(TestableWorld testableWorld, BlockPos blockPos, CallbackInfoReturnable<Boolean> cir) {
        if (testableWorld.testBlockState(blockPos, (blockState) -> {
            Block block = blockState.getBlock();
            return block == SpookyBlocks.DECEASED_GRASS_BLOCK || block == SpookyBlocks.DECEASED_DIRT;
        })) cir.setReturnValue(true);
    }
}
