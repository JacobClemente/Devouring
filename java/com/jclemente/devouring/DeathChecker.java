package com.jclemente.devouring;

import java.util.List;

import com.jclemente.devouring.capabilities.Capabilities;
import com.jclemente.devouring.entities.DevouringEssenceEntity;

import net.minecraft.entity.Entity;

public class DeathChecker {
	boolean isFound;
	List<? extends DevouringEssenceEntity> list;
	Entity entity;

	public DeathChecker(List<? extends DevouringEssenceEntity> list, Entity entity) {
		this.isFound = false;
		this.list = list;
		this.entity = entity;
	}

	public void checkDeath() {
		for (DevouringEssenceEntity devouring : list) {
			devouring.getCapability(Capabilities.DEVOURING_CAPABILITY).ifPresent(capability -> {
				if (!entity.getEntity().level.isClientSide) {
					isFound = capability.readEntityDeath(entity, devouring);
				}
			});
			if (isFound)
				break;
		}
	}
}
