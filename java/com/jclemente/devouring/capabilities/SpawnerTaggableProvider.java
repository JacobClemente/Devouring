package com.jclemente.devouring.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class SpawnerTaggableProvider implements ICapabilitySerializable<CompoundNBT> {

	private final SpawnerTaggableDefault spawnerTaggableDefault = new SpawnerTaggableDefault();
	private final LazyOptional<SpawnerTaggable> spawnerTaggableOptional = LazyOptional.of(() -> spawnerTaggableDefault);

	public void invalidate() {
		spawnerTaggableOptional.invalidate();
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return spawnerTaggableOptional.cast();
	}

	@Override
	public CompoundNBT serializeNBT() {
		if (Capabilities.SPAWNER_TAGGABLE_CAPABILITY == null)
			return new CompoundNBT();
		else
			return (CompoundNBT) Capabilities.SPAWNER_TAGGABLE_CAPABILITY.writeNBT(spawnerTaggableDefault, null);
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		if (Capabilities.SPAWNER_TAGGABLE_CAPABILITY != null)
			Capabilities.SPAWNER_TAGGABLE_CAPABILITY.readNBT(spawnerTaggableDefault, null, nbt);
	}
}
