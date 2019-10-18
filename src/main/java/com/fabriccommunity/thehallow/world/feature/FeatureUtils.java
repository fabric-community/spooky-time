package com.fabriccommunity.thehallow.world.feature;

import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

import java.util.Random;

public interface FeatureUtils {
	
	default void setSpawner(IWorld world, BlockPos pos, EntityType<?> entity) {
		world.setBlockState(pos, Blocks.SPAWNER.getDefaultState(), 2);
		BlockEntity be = world.getBlockEntity(pos);
		if (be instanceof MobSpawnerBlockEntity) {
			((MobSpawnerBlockEntity) be).getLogic().setEntityId(entity);
		}
	}
	
	default void setLootChest(IWorld world, BlockPos pos, Identifier lootTable, Random rand) {
		world.setBlockState(pos, Blocks.CHEST.getDefaultState(), 2);
		
		BlockEntity entity = world.getBlockEntity(pos);
		if (entity instanceof ChestBlockEntity) {
			((ChestBlockEntity) entity).setLootTable(lootTable, rand.nextLong());
		}
	}
}
