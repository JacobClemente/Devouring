package com.jclemente.devouring.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class SoulSeekingProvider implements ICapabilitySerializable<CompoundNBT> {
	private final SoulSeekingDefault soulSeekingDefault = new SoulSeekingDefault();
	private final LazyOptional<SoulSeeking> soulSeekingOptional = LazyOptional.of(() -> soulSeekingDefault);

	public void invalidate() {
		soulSeekingOptional.invalidate();
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return soulSeekingOptional.cast();
	}

	@Override
	public CompoundNBT serializeNBT() {
		if (Capabilities.SOUL_SEEKING_CAPABILITY == null)
			return new CompoundNBT();
		else
			return (CompoundNBT) Capabilities.SOUL_SEEKING_CAPABILITY.writeNBT(soulSeekingDefault, null);
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		if (Capabilities.SOUL_SEEKING_CAPABILITY != null)
			Capabilities.SOUL_SEEKING_CAPABILITY.readNBT(soulSeekingDefault, null, nbt);
	}
}
