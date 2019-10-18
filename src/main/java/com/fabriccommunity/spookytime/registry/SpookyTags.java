package com.fabriccommunity.spookytime.registry;

import net.fabricmc.fabric.api.tag.TagRegistry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;

import com.fabriccommunity.spookytime.SpookyTime;

public class SpookyTags {
	public static final Tag<Item> COSTUMES = registerItem("costumes");
	public static final Tag<Item> PUMPKIN_ITEMS = registerItem("pumpkin");
	public static final Tag<Item> CARVED_PUMPKIN_ITEMS = registerItem("carved_pumpkin");
	public static final Tag<Item> JACK_O_LANTERN_ITEMS = registerItem("jack_o_lantern");

	public static final Tag<Block> PUMPKIN_BLOCKS = registerBlock("pumpkin");
	public static final Tag<Block> CARVED_PUMPKIN_BLOCKS = registerBlock("carved_pumpkin");
	public static final Tag<Block> JACK_O_LANTERN_BLOCKS = registerBlock("jack_o_lantern");


	private SpookyTags() {
		// NO-OP
	}
	
	public static void init() {
		// NO-OP
	}
	
	public static Tag<Item> registerItem(String name) {
		return TagRegistry.item(SpookyTime.id(name));
	}

	public static Tag<Block> registerBlock(String name) {
		return TagRegistry.block(SpookyTime.id(name));
	}
}
