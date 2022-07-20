package com.jclemente.devouring;

import net.minecraft.item.Item;

public class ChargedEssenceRetreiver {
	public static Item retrieveItemFromEntityTypeId(String entityId) {
		switch (entityId) {
		case "entity.minecraft.blaze":
			return Registry.CHARGED_ESSENCE_BLAZE.get();
		case "entity.minecraft.cave_spider":
			return Registry.CHARGED_ESSENCE_CAVE_SPIDER.get();
		case "entity.minecraft.creeper":
			return Registry.CHARGED_ESSENCE_CREEPER.get();
		case "entity.minecraft.drowned":
			return Registry.CHARGED_ESSENCE_DROWNED.get();
		case "entity.minecraft.enderman":
			return Registry.CHARGED_ESSENCE_ENDERMAN.get();
		case "entity.minecraft.ghast":
			return Registry.CHARGED_ESSENCE_GHAST.get();
		case "entity.minecraft.guardian":
			return Registry.CHARGED_ESSENCE_GUARDIAN.get();
		case "entity.minecraft.hoglin":
			return Registry.CHARGED_ESSENCE_HOGLIN.get();
		case "entity.minecraft.magma_cube":
			return Registry.CHARGED_ESSENCE_MAGMA_CUBE.get();
		case "entity.minecraft.phantom":
			return Registry.CHARGED_ESSENCE_PHANTOM.get();
		case "entity.minecraft.piglin":
			return Registry.CHARGED_ESSENCE_PIGLIN.get();
		case "entity.minecraft.pillager":
			return Registry.CHARGED_ESSENCE_PILLAGER.get();
		case "entity.minecraft.ravager":
			return Registry.CHARGED_ESSENCE_RAVAGER.get();
		case "entity.minecraft.shulker":
			return Registry.CHARGED_ESSENCE_SHULKER.get();
		case "entity.minecraft.silverfish":
			return Registry.CHARGED_ESSENCE_SILVERFISH.get();
		case "entity.minecraft.skeleton":
			return Registry.CHARGED_ESSENCE_SKELETON.get();
		case "entity.minecraft.slime":
			return Registry.CHARGED_ESSENCE_SLIME.get();
		case "entity.minecraft.spider":
			return Registry.CHARGED_ESSENCE_SPIDER.get();
		case "entity.minecraft.witch":
			return Registry.CHARGED_ESSENCE_WITCH.get();
		case "entity.minecraft.wither_skeleton":
			return Registry.CHARGED_ESSENCE_WITHER_SKELETON.get();
		case "entity.minecraft.zoglin":
			return Registry.CHARGED_ESSENCE_ZOGLIN.get();
		case "entity.minecraft.zombie":
			return Registry.CHARGED_ESSENCE_ZOMBIE.get();
		case "entity.minecraft.zombified_piglin":
			return Registry.CHARGED_ESSENCE_ZOMBIFIED_PIGLIN.get();
		default:
			return null;
		}
	}
}
