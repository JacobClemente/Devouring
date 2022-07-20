package com.jclemente.devouring;

import javax.annotation.Nonnull;

import com.jclemente.devouring.capabilities.Capabilities;
import com.jclemente.devouring.entities.DevouringEssenceRenderer;
import com.jclemente.devouring.entities.SoulRenderer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("devouring")
public class Main {
	public static final String MOD_ID = "devouring";

	public Main() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
		MinecraftForge.EVENT_BUS.register(this);
		Registry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
		Registry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		Registry.SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
		Registry.TILE_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
		Registry.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

	private void setup(final FMLCommonSetupEvent event) {
		Capabilities.register();
		MinecraftForge.EVENT_BUS.register(EventHandler.class);
	}

	private void doClientStuff(final FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(Registry.DEVOURING_ESSENCE_ENTITY.get(), DevouringEssenceRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(Registry.SOUL_ENTITY.get(), SoulRenderer::new);
		RenderTypeLookup.setRenderLayer(Registry.EMPTY_SPAWNER.get(), RenderType.cutout());
	}

	private void enqueueIMC(final InterModEnqueueEvent event) {
	}

	private void processIMC(final InterModProcessEvent event) {
	}

	@SubscribeEvent
	public void onServerStarting(FMLServerStartingEvent event) {
	}

	@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class LootModifierEvents {
		@SubscribeEvent
		public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
			event.getRegistry().register(new DevouringEssenceLootModifier.Serializer().setRegistryName(new ResourceLocation(Main.MOD_ID, "devouring_essence")));
		}
	}
}
