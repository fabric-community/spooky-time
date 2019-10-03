package com.fabriccommunity.spookytime.world.biome;

import com.fabriccommunity.spookytime.registry.SpookyBlocks;
import com.google.common.collect.Lists;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.DiskFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public class SpookyBiomeFeatures {
	public static TernarySurfaceConfig SPOOKY_FOREST = new TernarySurfaceConfig(/*SpookyBlocks.DECEASED_GRASS_BLOCK.getDefaultState()*/Blocks.GRASS_BLOCK.getDefaultState(), SpookyBlocks.DECEASED_DIRT.getDefaultState(), SpookyBlocks.TAINTED_GRAVEL.getDefaultState());
	
	public static void addSpookyForestTrees(Biome biome) {
		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.DARK_OAK_TREE, FeatureConfig.DEFAULT, Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(12, 0.1F, 1)));
		//Still needs work
	}
	
	public static void addDisks(Biome biome) {
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(net.minecraft.world.gen.feature.Feature.DISK, new DiskFeatureConfig(SpookyBlocks.TAINTED_SAND.getDefaultState(), 7, 2, Lists.newArrayList(new BlockState[]{SpookyBlocks.DECEASED_DIRT.getDefaultState(), SpookyBlocks.DECEASED_GRASS_BLOCK.getDefaultState()})), Decorator.COUNT_TOP_SOLID, new CountDecoratorConfig(3)));
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(net.minecraft.world.gen.feature.Feature.DISK, new DiskFeatureConfig(SpookyBlocks.TAINTED_GRAVEL.getDefaultState(), 6, 2, Lists.newArrayList(new BlockState[]{SpookyBlocks.DECEASED_DIRT.getDefaultState(), SpookyBlocks.DECEASED_GRASS_BLOCK.getDefaultState()})), Decorator.COUNT_TOP_SOLID, new CountDecoratorConfig(1)));
	}
}