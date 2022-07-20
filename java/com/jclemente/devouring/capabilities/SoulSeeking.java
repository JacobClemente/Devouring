package com.jclemente.devouring.capabilities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

public interface SoulSeeking {
	public void setEntityType(EntityType<? extends Entity> entityType);

	public EntityType<? extends Entity> getEntityType();
}
