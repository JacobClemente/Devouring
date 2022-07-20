package com.jclemente.devouring.capabilities;

import com.jclemente.devouring.EntityTypeParser;
import com.jclemente.devouring.Registry;
import com.jclemente.devouring.entities.DevouringEssenceEntity;
import com.jclemente.devouring.entities.SoulEntity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;

public class DevouringDefault implements Devouring {
	int killCount;
	int goal;
	EntityType<? extends Entity> target;

	public DevouringDefault() {
		this.killCount = 0;
		this.goal = 1;
		this.target = EntityType.PIG;
	}

	@Override
	public void setKillCount(int killCount) {
		this.killCount = killCount;
	}

	@Override
	public int getKillCount() {
		return this.killCount;
	}

	@Override
	public void incrementKillCount() {
		this.killCount++;
	}

	@Override
	public void setGoal(int goal) {
		this.goal = goal;
	}

	@Override
	public int getGoal() {
		return this.goal;
	}

	@Override
	public void setTarget(EntityType<? extends Entity> target) {
		this.target = target;
	}

	@Override
	public EntityType<? extends Entity> getTarget() {
		return this.target;
	}

	@Override
	public boolean readEntityDeath(Entity entity, DevouringEssenceEntity devouring) {
		if (entity.getType() == this.target) {
			SoulEntity soulEntity = (SoulEntity) Registry.SOUL_ENTITY.get().spawn((ServerWorld) entity.level, null, null, entity.blockPosition().offset(0, entity.getEyeHeight() + 0.5F, 0), SpawnReason.TRIGGERED, true, true);
			soulEntity.setDevouring(devouring);
			soulEntity.getCapability(Capabilities.SOUL_SEEKING_CAPABILITY).ifPresent(soulSeeking -> {
				soulSeeking.setEntityType(target);
			});
			return true;
		} else
			return false;
	}

	@Override
	public void displayProgress(PlayerEntity player) {
		player.displayClientMessage(new TranslationTextComponent("Target: " + EntityTypeParser.parseTypeStringToEnum(target.getDescriptionId()).name + ", Progress: " + this.killCount + "/" + this.goal + "."), true);
	}
}
