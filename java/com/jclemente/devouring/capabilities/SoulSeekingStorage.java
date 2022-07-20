package com.jclemente.devouring.capabilities;

import com.jclemente.devouring.EntityTypeParser;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public class SoulSeekingStorage implements Capability.IStorage<SoulSeeking>{
	@Override
	public INBT writeNBT(Capability<SoulSeeking> capability, SoulSeeking instance, Direction side) {
		CompoundNBT tag = new CompoundNBT();
		tag.putString("target", instance.getEntityType().getDescriptionId());
		return tag;
	}

	@Override
	public void readNBT(Capability<SoulSeeking> capability, SoulSeeking instance, Direction side, INBT nbt) {
		CompoundNBT tag = (CompoundNBT) nbt;
		instance.setEntityType(EntityTypeParser.parseTypeStringToEnum(tag.getString("target")).entityType);
	}
}
