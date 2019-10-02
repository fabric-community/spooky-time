package com.fabriccommunity.spookytime.mixin;

import com.fabriccommunity.spookytime.SpookyConfig;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.level.LevelProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.level.LevelProperties;

import java.util.Random;

/**
 * @author Indigo Amann
 */
@Mixin(ServerWorld.class)
public class ServerWorldLightningMixin {
    @Redirect(method = "tickChunk", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I", ordinal = 0, remap = false))
    private int makeItThunderrr(Random random, int bound) {
        return random.nextInt(bound) / SpookyConfig.SpookyWeather.thunderModifier;
    }
    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/LevelProperties;getClearWeatherTime()I", ordinal = 0))
    private int ofcWeAllLoveThunderstorms(LevelProperties levelProperties) {
        return SpookyConfig.SpookyWeather.lessClearSkies ? levelProperties.getClearWeatherTime() > 1 ? levelProperties.getClearWeatherTime() - 50 : 0 : levelProperties.getClearWeatherTime();
    }
}
