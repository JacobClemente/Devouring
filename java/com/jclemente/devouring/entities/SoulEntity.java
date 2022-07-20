package com.jclemente.devouring.entities;

import java.util.List;

import com.jclemente.devouring.EntityTypeParser;
import com.jclemente.devouring.Registry;
import com.jclemente.devouring.capabilities.Capabilities;
import com.jclemente.devouring.capabilities.SoulSeeking;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkHooks;

public class SoulEntity extends Entity {
	DevouringEssenceEntity devouring;
	LazyOptional<SoulSeeking> soulSeekingCapability;

	public void setDevouring(DevouringEssenceEntity devouring) {
		this.devouring = devouring;
	}

	public SoulEntity(EntityType<?> p_i48580_1_, World p_i48580_2_) {
		super(p_i48580_1_, p_i48580_2_);
		this.noPhysics = true;
		soulSeekingCapability = this.getCapability(Capabilities.SOUL_SEEKING_CAPABILITY);
	}

	@Override
	public void tick() {
		super.tick();
		if (this.devouring != null && devouring.getCapability(Capabilities.DEVOURING_CAPABILITY).isPresent() && !level.isClientSide) {
			if ((Math.abs((double) this.devouring.getX() - this.getX()) >= 0.25) || (Math.abs((double) this.devouring.getY() - this.getY()) >= 0.25) || (Math.abs((double) this.devouring.getZ() - this.getZ()) >= 0.25)) {
				Vector3d vector3d = new Vector3d(this.devouring.getX() - this.getX(), this.devouring.getY() - this.getY(), this.devouring.getZ() - this.getZ());
				this.setDeltaMovement(this.getDeltaMovement().add(vector3d.normalize().scale(0.01)));
			} else {
				this.remove();
				soulSeekingCapability.invalidate();
				devouring.getCapability(Capabilities.DEVOURING_CAPABILITY).ifPresent(cap -> {
					cap.incrementKillCount();
					devouring.setProgress(cap.getKillCount());
					if (cap.getKillCount() < cap.getGoal())
						this.level.playSound(null, new BlockPos(devouring.getX(), devouring.getY(), devouring.getZ()), Registry.DEVOURING_CONSUME.get(), SoundCategory.AMBIENT, 5.0F, 1.0F);
					else if (cap.getKillCount() == cap.getGoal()) {
						this.level.playSound(null, new BlockPos(devouring.getX(), devouring.getY(), devouring.getZ()), Registry.DEVOURING_ASCEND.get(), SoundCategory.AMBIENT, 5.0F, 1.0F);
					}
				});
			}
		} else if (soulSeekingCapability.isPresent() && !level.isClientSide) {
			this.devouring = null;
			soulSeekingCapability.ifPresent(soulSeeking -> {
				double radius;
				try {
					radius = EntityTypeParser.parseTypeStringToEnum(soulSeeking.getEntityType().getDescriptionId()).radius;
				} catch (NullPointerException exception) {
					radius = -1.0D;
				}
				if (radius != -1.0D) {
					List<DevouringEssenceEntity> nearbyDevourers = level.getEntitiesOfClass(DevouringEssenceEntity.class, new AxisAlignedBB(this.getX() - radius, this.getY() - radius, this.getZ() - radius, this.getX() + radius, this.getY() + radius, this.getZ() + radius));
					if (!nearbyDevourers.isEmpty()) {
						for (DevouringEssenceEntity entity : nearbyDevourers) {
							entity.getCapability(Capabilities.DEVOURING_CAPABILITY).ifPresent(devouring -> {
								if (devouring.getTarget() == soulSeeking.getEntityType()) {
									this.devouring = entity;
									this.setDeltaMovement(new Vector3d(0, 0, 0));
								}
							});
						}
						if (this.devouring == null)
							this.remove();
					} else
						this.remove();
				} else
					this.remove();
			});
		} else if (!this.level.isClientSide)
			this.remove();
		this.move(MoverType.SELF, this.getDeltaMovement());
	}

	@Override
	protected boolean isMovementNoisy() {
		return false;
	}

	@Override
	protected void defineSynchedData() {
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
