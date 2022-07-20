package com.jclemente.devouring.capabilities;

import com.jclemente.devouring.entities.DevouringEssenceEntity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;

public interface Devouring {
	void setKillCount(int killCount);

	int getKillCount();

	void incrementKillCount();

	void setGoal(int goal);

	int getGoal();

	void setTarget(EntityType<? extends Entity> target);

	EntityType<? extends Entity> getTarget();

	boolean readEntityDeath(Entity entity, DevouringEssenceEntity devouringEntity);

	void displayProgress(PlayerEntity player);
}
