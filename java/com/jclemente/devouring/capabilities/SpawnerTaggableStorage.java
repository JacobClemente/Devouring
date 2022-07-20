package com.jclemente.devouring.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public class SpawnerTaggableStorage implements Capability.IStorage<SpawnerTaggable>{

	@Override
	public INBT writeNBT(Capability<SpawnerTaggable> capability, SpawnerTaggable instance, Direction side) {
		CompoundNBT tag = new CompoundNBT();
		tag.putString("entity_id", instance.getTag());
		return tag;
	}

	@Override
	public void readNBT(Capability<SpawnerTaggable> capability, SpawnerTaggable instance, Direction side, INBT nbt) {
		CompoundNBT tag = (CompoundNBT) nbt;
		instance.setTag(tag.getString("entity_id"));
	}

}
