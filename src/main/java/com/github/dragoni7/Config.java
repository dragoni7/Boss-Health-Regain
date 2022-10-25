package com.github.dragoni7;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber
public class Config {
	public static final String CATEGORY_GENERAL = "Bosses";
	public static ForgeConfigSpec.ConfigValue<List<? extends String>> BOSSES;
	public static ForgeConfigSpec.IntValue RADIUS;
	public static ForgeConfigSpec.DoubleValue HEALTH_FACTOR;
	public static ForgeConfigSpec COMMON_CONFIG;
	
	static {
		ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
		COMMON_BUILDER.comment("General Settings").push(CATEGORY_GENERAL);
		BOSSES = COMMON_BUILDER.comment("List of entities that should regain health whenever a play dies nearby.").define("boss entities:", Lists.newArrayList("minecraft:wither", "minecraft:ender_dragon", "minecraft:elder_guardian", "meetyourfight:swampjaw", "meetyourfight:bellringer", "meetyourfight:dame_fortuna"));
		
		RADIUS = COMMON_BUILDER.comment("Radius to check around player who died for bosses to regain health. Default 16. Value between 0 and 256").defineInRange("radius:", 16, 0, 256);
		COMMON_BUILDER.push("Health Percent");
		HEALTH_FACTOR = COMMON_BUILDER.comment("How much should the boss heal whenever a player dies nearby? Setting this to 1 will make it heal to full, 0.5 will heal it by half its max HP. Value between 0.0 and 1.0").defineInRange("heal factor:", 0.5, 0.0, 1.0);
		COMMON_BUILDER.pop();
		
		COMMON_CONFIG = COMMON_BUILDER.build();
	}
	
	@SubscribeEvent
	public static void onLoad(final ModConfigEvent.Loading configEvent) {
		
	}
	
	@SubscribeEvent
	public static void onReload(final ModConfigEvent.Reloading configEvent) {
		
	}
}
