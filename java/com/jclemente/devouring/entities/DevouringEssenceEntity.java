package com.jclemente.devouring.entities;

import java.util.Random;

import com.jclemente.devouring.ChargedEssenceRetreiver;
import com.jclemente.devouring.Registry;
import com.jclemente.devouring.capabilities.Capabilities;
import com.jclemente.devouring.capabilities.Devouring;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkHooks;

public class DevouringEssenceEntity extends Entity {
	public int innerRotation;
	World world;
	BlockPos blockposBelow;
	LazyOptional<Devouring> devouringCapability;
	Random random;
	private int progress = -1;
	private int goal = -1;
	static final DataParameter<Integer> progressData = EntityDataManager.defineId(DevouringEssenceEntity.class, DataSerializers.INT);
	static final DataParameter<Integer> goalData = EntityDataManager.defineId(DevouringEssenceEntity.class, DataSerializers.INT);

	public int getProgress() {
		return entityData.get(progressData);
	}

	public void setProgress(int progress) {
		this.progress = progress;
		this.entityData.set(progressData, progress);
	}

	public int getGoal() {
		return entityData.get(goalData);
	}

	public void setGoal(int goal) {
		this.goal = goal;
		this.entityData.set(goalData, goal);
	}

	@Override
	public boolean isPickable() {
		return true;
	}

	@Override
	public void tick() {
		blockposBelow = new BlockPos(this.blockPosition().getX(), this.blockPosition().getY() - 1, this.blockPosition().getZ());
		if (!world.getBlockState(blockposBelow).getBlock().is(Registry.ALTAR_BLOCK.get())) {
			this.remove();
		}
		innerRotation++;
		if (!level.isClientSide)
			devouringCapability.ifPresent(devouringEntity -> {
				if (progress == -1 || goal == -1) {
					this.setProgress(devouringEntity.getKillCount());
					this.setGoal(devouringEntity.getGoal());
				}
				if (devouringEntity.getKillCount() >= devouringEntity.getGoal()) {
					this.spawnAtLocation(ChargedEssenceRetreiver.retrieveItemFromEntityTypeId(devouringEntity.getTarget().getDescriptionId()));
					this.remove();
					devouringCapability.invalidate();
				}
			});
		if (random.nextInt(300) == 0) {
			world.playSound(null, this.blockPosition(), Registry.DEVOURING_AMBIENT.get(), SoundCategory.AMBIENT, 1.0F, 1.0F);
		}
		super.tick();
	}

	public DevouringEssenceEntity(EntityType<?> p_i48580_1_, World p_i48580_2_) {
		super(p_i48580_1_, p_i48580_2_);
		this.world = p_i48580_2_;
		this.blocksBuilding = true;
		this.noPhysics = true;
		this.setNoGravity(true);
		if (!level.isClientSide)
			devouringCapability = this.getCapability(Capabilities.DEVOURING_CAPABILITY, null);
		random = new Random();
	}

	@Override
	protected boolean isMovementNoisy() {
		return false;
	}

	@Override
	protected void defineSynchedData() {
		this.entityData.define(progressData, progress);
		this.entityData.define(goalData, goal);
	}

	@Override
	protected void readAdditionalSaveData(CompoundNBT p_70037_1_) {
	}

	@Override
	protected void addAdditionalSaveData(CompoundNBT p_213281_1_) {
	}

	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
