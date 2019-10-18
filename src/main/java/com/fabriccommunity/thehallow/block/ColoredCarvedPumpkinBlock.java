package com.fabriccommunity.thehallow.block;

import net.minecraft.block.CarvedPumpkinBlock;

public class ColoredCarvedPumpkinBlock extends CarvedPumpkinBlock {
	private final ColoredPumpkinBlock.PumpkinColor color;

	public ColoredCarvedPumpkinBlock(Settings settings, ColoredPumpkinBlock.PumpkinColor color) {
		super(settings);
		this.color = color;
	}

	public ColoredPumpkinBlock.PumpkinColor getColor() {
		return this.color;
	}
}
