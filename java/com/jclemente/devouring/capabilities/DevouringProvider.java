package com.jclemente.devouring.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class DevouringProvider implements ICapabilitySerializable<CompoundNBT> {
	private final DevouringDefault devouringDefault = new DevouringDefault();
	private final LazyOptional<Devouring> devouringOptional = LazyOptional.of(() -> devouringDefault);

	public void invalidate() {
		devouringOptional.invalidate();
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return devouringOptional.cast();
	}

	@Override
	public CompoundNBT serializeNBT() {
		if (Capabilities.DEVOURING_CAPABILITY == null)
			return new CompoundNBT();
		else
			return (CompoundNBT) Capabilities.DEVOURING_CAPABILITY.writeNBT(devouringDefault, null);
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		if (Capabilities.DEVOURING_CAPABILITY != null)
			Capabilities.DEVOURING_CAPABILITY.readNBT(devouringDefault, null, nbt);
	}
}
