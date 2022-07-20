package com.jclemente.devouring;

import java.util.List;

import com.jclemente.devouring.capabilities.Capabilities;
import com.jclemente.devouring.capabilities.DevouringProvider;
import com.jclemente.devouring.capabilities.SoulSeekingProvider;
import com.jclemente.devouring.entities.DevouringEssenceEntity;
import com.jclemente.devouring.entities.SoulEntity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteractSpecific;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EventHandler {

	@SubscribeEvent
	public static void onAttachCapabilitiesEntitiesEvent(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof DevouringEssenceEntity) {
			DevouringProvider provider = new DevouringProvider();
			event.addCapability(new ResourceLocation(Main.MOD_ID, "devouring"), provider);
			event.addListener(provider::invalidate);
		} else if (event.getObject() instanceof SoulEntity) {
			SoulSeekingProvider provider = new SoulSeekingProvider();
			event.addCapability(new ResourceLocation(Main.MOD_ID, "soul_seeking"), provider);
			event.addListener(provider::invalidate);
		}
	}

	@SubscribeEvent
	public static void onEntityDeathEvent(LivingDeathEvent event) {
		double radius = -1.0D;
		try {
			radius = EntityTypeParser.parseTypeStringToEnum(event.getEntity().getType().getDescriptionId()).radius;
		} catch (NullPointerException exception) {
		}
		if (radius != -1.0D) {
			List<DevouringEssenceEntity> nearbyDevourers = event.getEntity().getCommandSenderWorld().getEntitiesOfClass(DevouringEssenceEntity.class, new AxisAlignedBB(event.getEntity().getX() - radius, event.getEntity().getY() - radius, event.getEntity().getZ() - radius, event.getEntity().getX() + radius, event.getEntity().getY() + radius, event.getEntity().getZ() + radius));
			DeathChecker checker = new DeathChecker(nearbyDevourers, event.getEntity());
			checker.checkDeath();
		}
	}

	@SubscribeEvent
	public static void onEntityClickEvent(EntityInteractSpecific event) {
		if (event.getTarget() instanceof DevouringEssenceEntity && !event.getWorld().isClientSide) {
			event.getTarget().getCapability(Capabilities.DEVOURING_CAPABILITY).ifPresent(devouring -> {
				devouring.displayProgress((PlayerEntity) event.getEntity());
			});
		}
	}
}
