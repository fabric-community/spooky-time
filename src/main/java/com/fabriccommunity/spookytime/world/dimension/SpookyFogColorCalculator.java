package com.fabriccommunity.spookytime.world.dimension;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import com.fabriccommunity.spookytime.SpookyConfig;
import com.fabriccommunity.spookytime.api.SpookyBiomeInfo;
import com.github.draylar.worldtraveler.api.dimension.utils.FogColorCalculator;

public class SpookyFogColorCalculator implements FogColorCalculator {
	@Override
	@Environment(EnvType.CLIENT)
	public Vec3d calculate(float v, float v1) {
		World world = MinecraftClient.getInstance().world;
		PlayerEntity player = MinecraftClient.getInstance().player;
		double totalR = 0;
		double totalG = 0;
		double totalB = 0;
		int count = 0;
		int radius = SpookyConfig.SpookyFog.fogSmoothingRadius;
		
		for (int x = 0; x < radius; x++) {
			for (int z = 0; z < radius; z++) {
				BlockPos pos = player.getBlockPos().add(x - (radius / 2), 0, z - (radius / 2));
				
				if (world.getBiome(pos) instanceof SpookyBiomeInfo) {
					SpookyBiomeInfo biomeInfo = (SpookyBiomeInfo) world.getBiome(pos);
					
					totalR += Math.pow(biomeInfo.getFogColor().x, 2);
					totalG += Math.pow(biomeInfo.getFogColor().y, 2);
					totalB += Math.pow(biomeInfo.getFogColor().z, 2);
				}
				count++;
			}
		}
		
		return new Vec3d(Math.sqrt(totalR / count), Math.sqrt(totalG / count), Math.sqrt(totalB / count));
	}
}
