package com.fabriccommunity.spookytime.client.render;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.ZombieEntityRenderer;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.Identifier;
import com.mojang.blaze3d.platform.GlStateManager;

import com.fabriccommunity.spookytime.SpookyTime;

public class MummyEntityRenderer extends ZombieEntityRenderer {
	private static final Identifier SKIN = SpookyTime.id("textures/entity/mummy.png");
	
	public MummyEntityRenderer(EntityRenderDispatcher erd) {
		super(erd);
	}
	
	protected void scale(ZombieEntity entity, float f1) {
		float f2 = 1.2F;
		GlStateManager.scalef(f2, f2, f2);
		super.scale(entity, f1);
	}
	
	protected Identifier method_4163(ZombieEntity entity) {
		return SKIN;
	}
}
