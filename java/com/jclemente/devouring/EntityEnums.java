package com.jclemente.devouring;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

public enum EntityEnums {
	
	BLAZE("Blaze", EntityType.BLAZE, 10.0D, 20),
	CAVE_SPIDER("Cave Spider", EntityType.CAVE_SPIDER, 10.0D, 30),
	CREEPER("Creeper", EntityType.CREEPER, 10.0D, 30),
	DROWNED("Drowned", EntityType.DROWNED, 10.0D, 30),
	ENDERMAN("Enderman", EntityType.ENDERMAN, 10.0D, 20),
	GHAST("Ghast", EntityType.GHAST, 100.0D, 5),
	GUARDIAN("Guardian", EntityType.GUARDIAN, 20.0D, 5),
	HOGLIN("Hoglin", EntityType.HOGLIN, 10.0D, 20),
	MAGMA_CUBE("Magma Cube", EntityType.MAGMA_CUBE, 10.0D, 20),
	PHANTOM("Phantom", EntityType.PHANTOM, 20.0D, 10),
	PIGLIN("Piglin", EntityType.PIGLIN, 10.0D, 30),
	PILLAGER("Pillager", EntityType.PILLAGER, 10.0D, 20),
	RAVAGER("Ravager", EntityType.RAVAGER, 20.0D, 3),
	SHULKER("Shulker", EntityType.SHULKER, 50.0D, 30),
	SILVERFISH("Silverfish", EntityType.SILVERFISH, 10.0D, 30),
	SKELETON("Skeleton", EntityType.SKELETON, 20.0D, 30),
	SLIME("Slime", EntityType.SLIME, 10.0D, 80),
	SPIDER("Spider", EntityType.SPIDER, 10.0D, 30),
	WITCH("Witch", EntityType.WITCH, 15.0D, 10),
	WITHER_SKELETON("Wither Skeleton", EntityType.WITHER_SKELETON, 10.0D, 30),
	ZOGLIN("Zoglin", EntityType.ZOGLIN, 10.0D, 10),
	ZOMBIE("Zombie", EntityType.ZOMBIE, 10.0D, 30),
	ZOMBIFIED_PIGLIN("Zombified Piglin", EntityType.ZOMBIFIED_PIGLIN, 10.0D, 40);
	
	public final String name;
	public final EntityType<? extends Entity> entityType;
	public final double radius;
	public final int goal;
	
	private EntityEnums(String name, EntityType<? extends Entity> entityType, double radius, int goal) {
		this.name = name;
		this.entityType = entityType;
		this.radius = radius;
		this.goal = goal;
	}

}
