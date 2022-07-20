package com.jclemente.devouring.capabilities;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class Capabilities {
	@CapabilityInject(Devouring.class)
	public static Capability<Devouring> DEVOURING_CAPABILITY = null;
	@CapabilityInject(SpawnerTaggable.class)
	public static Capability<SpawnerTaggable> SPAWNER_TAGGABLE_CAPABILITY = null;
	@CapabilityInject(SoulSeeking.class)
	public static Capability<SoulSeeking> SOUL_SEEKING_CAPABILITY = null;

	public static void register() {
		CapabilityManager.INSTANCE.register(Devouring.class, new DevouringStorage(), DevouringDefault::new);
		CapabilityManager.INSTANCE.register(SpawnerTaggable.class, new SpawnerTaggableStorage(), SpawnerTaggableDefault::new);
		CapabilityManager.INSTANCE.register(SoulSeeking.class, new SoulSeekingStorage(), SoulSeekingDefault::new);
	}
}
