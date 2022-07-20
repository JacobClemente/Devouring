package com.jclemente.devouring.capabilities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

public class SoulSeekingDefault implements SoulSeeking {
	EntityType<? extends Entity> entityType;

	@Override
	public void setEntityType(EntityType<? extends Entity> entityType) {
		this.entityType = entityType;
	}

	@Override
	public EntityType<? extends Entity> getEntityType() {
		return this.entityType;
	}
}
