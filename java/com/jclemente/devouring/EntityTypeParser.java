package com.jclemente.devouring;

public class EntityTypeParser {

	public static EntityEnums parseTypeStringToEnum(String s) {
		switch (s) {
		case "entity.minecraft.blaze":
			return EntityEnums.BLAZE;
		case "entity.minecraft.cave_spider":
			return EntityEnums.CAVE_SPIDER;
		case "entity.minecraft.creeper":
			return EntityEnums.CREEPER;
		case "entity.minecraft.drowned":
			return EntityEnums.DROWNED;
		case "entity.minecraft.enderman":
			return EntityEnums.ENDERMAN;
		case "entity.minecraft.ghast":
			return EntityEnums.GHAST;
		case "entity.minecraft.guardian":
			return EntityEnums.GUARDIAN;
		case "entity.minecraft.hoglin":
			return EntityEnums.HOGLIN;
		case "entity.minecraft.magma_cube":
			return EntityEnums.MAGMA_CUBE;
		case "entity.minecraft.phantom":
			return EntityEnums.PHANTOM;
		case "entity.minecraft.piglin":
			return EntityEnums.PIGLIN;
		case "entity.minecraft.pillager":
			return EntityEnums.PILLAGER;
		case "entity.minecraft.ravager":
			return EntityEnums.RAVAGER;
		case "entity.minecraft.shulker":
			return EntityEnums.SHULKER;
		case "entity.minecraft.silverfish":
			return EntityEnums.SILVERFISH;
		case "entity.minecraft.skeleton":
			return EntityEnums.SKELETON;
		case "entity.minecraft.slime":
			return EntityEnums.SLIME;
		case "entity.minecraft.spider":
			return EntityEnums.SPIDER;
		case "entity.minecraft.witch":
			return EntityEnums.WITCH;
		case "entity.minecraft.wither_skeleton":
			return EntityEnums.WITHER_SKELETON;
		case "entity.minecraft.zoglin":
			return EntityEnums.ZOGLIN;
		case "entity.minecraft.zombie":
			return EntityEnums.ZOMBIE;
		case "entity.minecraft.zombified_piglin":
			return EntityEnums.ZOMBIFIED_PIGLIN;
		default:
			return null;
		}
	}
}
