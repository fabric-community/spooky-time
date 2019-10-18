package com.fabriccommunity.thehallow.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import com.mojang.blaze3d.platform.GlStateManager;

import com.fabriccommunity.thehallow.block.entity.InfusionPillarBlockEntity;

@Environment(EnvType.CLIENT)
public class InfusionPillarBlockEntityRenderer extends BlockEntityRenderer<InfusionPillarBlockEntity> {
	public double rotation = 0;
	
	private boolean rotationMode = false;
	
	private long nanosA = 0;
	private long nanosB = 0;
	
	@Override
	public void render(InfusionPillarBlockEntity pillar, double x, double y, double z, float delta, int breakingStage) {
		ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
		
		if (!pillar.storedStack.isEmpty()) {
			GlStateManager.pushMatrix();
			GlStateManager.translated(x + 0.5d, y + 1.1 + Math.sin(rotation) / 32, z + 0.5d);
			GlStateManager.rotated(rotation * 2, 0, 1, 0);
			GlStateManager.scaled(0.5, 0.5, 0.5);
			itemRenderer.renderItem(pillar.storedStack, ModelTransformation.Type.FIXED);
			GlStateManager.popMatrix();
		}
		
		nanosA = System.nanoTime();
		if ((nanosA - nanosB) / 1000000 > 16) {
			nanosB = System.nanoTime();
			if (rotationMode) {
				rotation += 0.16;
				if (rotation >= 360) {
					rotationMode = !rotationMode;
				}
			} else {
				rotation += 0.16;
				if (rotation <= 0) {
					rotationMode = !rotationMode;
				}
			}
		}
		
	}
}
