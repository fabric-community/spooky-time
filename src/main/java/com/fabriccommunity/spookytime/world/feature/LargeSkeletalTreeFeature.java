package com.fabriccommunity.spookytime.world.feature;

import com.fabriccommunity.spookytime.registry.SpookyBlocks;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

/**
 * @author Indigo Amann
 */
public class LargeSkeletalTreeFeature extends AbstractTreeFeature<DefaultFeatureConfig> {
    private static final BlockState LOG = Blocks.BONE_BLOCK.getDefaultState();

    public LargeSkeletalTreeFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function, boolean boolean1) {
        super(function, boolean1);
    }

    private void addLog(Set<BlockPos> set, ModifiableTestableWorld modifiableTestableWorld, BlockPos blockPos, Direction.Axis axis, MutableIntBoundingBox mutableIntBoundingBox) {
        if (canTreeReplace(modifiableTestableWorld, blockPos)) {
            this.setBlockState(set, modifiableTestableWorld, blockPos, LOG.with(PillarBlock.AXIS, axis), mutableIntBoundingBox);
        }
    }

    private static boolean isPlantableOn(TestableWorld testableWorld, BlockPos blockPos) {
        return testableWorld.testBlockState(blockPos, (blockState) -> {
            Block block = blockState.getBlock();
            return Block.isNaturalDirt(block) || block == Blocks.GRASS_BLOCK || block == SpookyBlocks.DECEASED_GRASS_BLOCK || block == Blocks.BONE_BLOCK;
        });
    }

    private static boolean isSurroundedByAir(TestableWorld testableWorld, BlockPos blockPos, Direction ignore) {
        for (Iterator<Direction> it = Direction.Type.HORIZONTAL.iterator(); it.hasNext(); ) {
            Direction direction = it.next();
            if (direction == ignore) continue;
            if (!isAir(testableWorld, blockPos.offset(direction))) return false;
        }
        return true;
    }

    @Override
    protected boolean generate(Set<BlockPos> set, ModifiableTestableWorld world, Random random, BlockPos pos, MutableIntBoundingBox mibb) {
        if (!isPlantableOn(world, pos.down())) return false;
        generateBranch(set, world, random, pos, mibb, random.nextInt(4), null);
        return true;
    }

    protected boolean generateBranch(Set<BlockPos> set, ModifiableTestableWorld world, Random random, BlockPos pos, MutableIntBoundingBox mibb, int maxBranchouts, Direction lastDir) {
        int baseHeight = random.nextInt(4) + 2;

        for (int int_4 = 0; int_4 < baseHeight; ++int_4) {
            BlockPos blockPos = pos.up(int_4 + 1);
            if (!isSurroundedByAir(world, blockPos, null)) {
                return true;
            }
            addLog(set, world, blockPos, Direction.Axis.Y, mibb);
        }

        int int_5 = random.nextInt(4) + 1;
        int int_1 = 8;

        if (maxBranchouts > 0)
            for (int int_6 = 0; int_6 < int_5; ++int_6) {
                Direction direction_1 = Direction.Type.HORIZONTAL.random(random);
                if (direction_1 == lastDir) continue;
                BlockPos blockPos_4 = pos.up(baseHeight).offset(direction_1);
                if (Math.abs(blockPos_4.getX() - pos.getX()) < int_1 && Math.abs(blockPos_4.getZ() - pos.getZ()) < int_1 && isAir(world, blockPos_4) && isAir(world, blockPos_4.down()) && isSurroundedByAir(world, blockPos_4, direction_1.getOpposite())) {
                    addLog(set, world, blockPos_4, direction_1.getAxis(), mibb);
                    generateBranch(set, world, random, blockPos_4, mibb, maxBranchouts - 1, direction_1.getOpposite());
                }
            }
        return true;
    }
}
