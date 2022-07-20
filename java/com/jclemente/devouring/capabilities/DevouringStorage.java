package com.jclemente.devouring.capabilities;

import com.jclemente.devouring.EntityTypeParser;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public class DevouringStorage implements Capability.IStorage<Devouring> {
	@Override
	public INBT writeNBT(Capability<Devouring> capability, Devouring instance, Direction side) {
		CompoundNBT tag = new CompoundNBT();
		tag.putInt("killCount", instance.getKillCount());
		tag.putInt("goal", instance.getGoal());
		tag.putString("target", instance.getTarget().toString());
		return tag;
	}

	@Override
	public void readNBT(Capability<Devouring> capability, Devouring instance, Direction side, INBT nbt) {
		CompoundNBT tag = (CompoundNBT) nbt;
		instance.setKillCount(tag.getInt("killCount"));
		instance.setGoal(tag.getInt("goal"));
		instance.setTarget(EntityTypeParser.parseTypeStringToEnum(tag.getString("target")).entityType);
	}
}
