package com.jclemente.devouring;

import java.util.function.ToIntFunction;

import com.jclemente.devouring.blocks.AltarBlock;
import com.jclemente.devouring.entities.AltarTileEntity;
import com.jclemente.devouring.entities.DevouringEssenceEntity;
import com.jclemente.devouring.entities.SoulEntity;
import com.jclemente.devouring.items.ChargedEssenceItem;
import com.jclemente.devouring.items.DevouringEssenceItem;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registry {
	public static final DeferredRegister<GlobalLootModifierSerializer<?>> GLM = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, Main.MOD_ID);
	public static final RegistryObject<DevouringEssenceLootModifier.Serializer> DUNGEON_LOOT = GLM.register("devouring_essence", DevouringEssenceLootModifier.Serializer::new);
	// Registers
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MOD_ID);
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Main.MOD_ID);
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Main.MOD_ID);
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Main.MOD_ID);
	// Blocks
	public static final RegistryObject<Block> ALTAR_BLOCK = BLOCKS.register("altar", () -> new AltarBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().harvestLevel(1).strength(4.0F, 6.0F).lightLevel(getLightValueLit(15))));
	public static final RegistryObject<Block> EMPTY_SPAWNER = BLOCKS.register("empty_spawner", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(5.0F).sound(SoundType.METAL).noOcclusion()));
	// Block Items
	public static final RegistryObject<Item> ALTAR_ITEM = ITEMS.register("altar", () -> new BlockItem(ALTAR_BLOCK.get(), new Item.Properties().tab(ItemGroup.TAB_DECORATIONS)));
	public static final RegistryObject<Item> EMPTY_SPAWNER_ITEM = ITEMS.register("empty_spawner", () -> new BlockItem(EMPTY_SPAWNER.get(), new Item.Properties().tab(ItemGroup.TAB_DECORATIONS)));
	// Items
	public static final RegistryObject<Item> DEVOURING_ESSENCE = ITEMS.register("devouring_essence", () -> new DevouringEssenceItem(new Item.Properties().tab(ItemGroup.TAB_MISC), null, null));
	public static final RegistryObject<Item> CHARGED_ESSENCE = ITEMS.register("charged_essence", () -> new ChargedEssenceItem(new Item.Properties().tab(ItemGroup.TAB_MISC), null));
	// Tile Entities
	public static final RegistryObject<TileEntityType<AltarTileEntity>> ALTAR_TILE_ENTITY = TILE_ENTITIES.register("altar_tile_entity", () -> TileEntityType.Builder.of(AltarTileEntity::new, ALTAR_BLOCK.get()).build(null));
	// Entities
	public static final RegistryObject<EntityType<DevouringEssenceEntity>> DEVOURING_ESSENCE_ENTITY = ENTITIES.register("devouring_essence_entity", () -> EntityType.Builder.of(DevouringEssenceEntity::new, EntityClassification.MISC).sized(0.35F, 0.35F).build(new ResourceLocation(Main.MOD_ID, "devouring_essence_entity").toString()));
	public static final RegistryObject<EntityType<SoulEntity>> SOUL_ENTITY = ENTITIES.register("soul_entity", () -> EntityType.Builder.of(SoulEntity::new, EntityClassification.MISC).sized(0.25F, 0.25F).build(new ResourceLocation(Main.MOD_ID, "soul_entity").toString()));
	// Sound Events
	public static final RegistryObject<SoundEvent> DEVOURING_AMBIENT = SOUNDS.register("devouring_ambient", () -> new SoundEvent(new ResourceLocation(Main.MOD_ID, "devouring_ambient")));
	public static final RegistryObject<SoundEvent> DEVOURING_CONSUME = SOUNDS.register("devouring_consume", () -> new SoundEvent(new ResourceLocation(Main.MOD_ID, "devouring_consume")));
	public static final RegistryObject<SoundEvent> DEVOURING_ASCEND = SOUNDS.register("devouring_ascend", () -> new SoundEvent(new ResourceLocation(Main.MOD_ID, "devouring_ascend")));
	// Devouring Essence Variations
	public static final RegistryObject<Item> DEVOURING_ESSENCE_BLAZE = ITEMS.register("devouring_essence_blaze", () -> new DevouringEssenceItem(new Item.Properties(), EntityType.BLAZE, EntityEnums.BLAZE.goal));
	public static final RegistryObject<Item> DEVOURING_ESSENCE_CAVE_SPIDER = ITEMS.register("devouring_essence_cave_spider", () -> new DevouringEssenceItem(new Item.Properties(), EntityType.CAVE_SPIDER, EntityEnums.CAVE_SPIDER.goal));
	public static final RegistryObject<Item> DEVOURING_ESSENCE_CREEPER = ITEMS.register("devouring_essence_creeper", () -> new DevouringEssenceItem(new Item.Properties(), EntityType.CREEPER, EntityEnums.CREEPER.goal));
	public static final RegistryObject<Item> DEVOURING_ESSENCE_DROWNED = ITEMS.register("devouring_essence_drowned", () -> new DevouringEssenceItem(new Item.Properties(), EntityType.DROWNED, EntityEnums.DROWNED.goal));
	public static final RegistryObject<Item> DEVOURING_ESSENCE_ENDERMAN = ITEMS.register("devouring_essence_enderman", () -> new DevouringEssenceItem(new Item.Properties(), EntityType.ENDERMAN, EntityEnums.ENDERMAN.goal));
	public static final RegistryObject<Item> DEVOURING_ESSENCE_GHAST = ITEMS.register("devouring_essence_ghast", () -> new DevouringEssenceItem(new Item.Properties(), EntityType.GHAST, EntityEnums.GHAST.goal));
	public static final RegistryObject<Item> DEVOURING_ESSENCE_GUARDIAN = ITEMS.register("devouring_essence_guardian", () -> new DevouringEssenceItem(new Item.Properties(), EntityType.GUARDIAN, EntityEnums.GUARDIAN.goal));
	public static final RegistryObject<Item> DEVOURING_ESSENCE_HOGLIN = ITEMS.register("devouring_essence_hoglin", () -> new DevouringEssenceItem(new Item.Properties(), EntityType.HOGLIN, EntityEnums.HOGLIN.goal));
	public static final RegistryObject<Item> DEVOURING_ESSENCE_MAGMA_CUBE = ITEMS.register("devouring_essence_magma_cube", () -> new DevouringEssenceItem(new Item.Properties(), EntityType.MAGMA_CUBE, EntityEnums.MAGMA_CUBE.goal));
	public static final RegistryObject<Item> DEVOURING_ESSENCE_PHANTOM = ITEMS.register("devouring_essence_phantom", () -> new DevouringEssenceItem(new Item.Properties(), EntityType.PHANTOM, EntityEnums.PHANTOM.goal));
	public static final RegistryObject<Item> DEVOURING_ESSENCE_PIGLIN = ITEMS.register("devouring_essence_piglin", () -> new DevouringEssenceItem(new Item.Properties(), EntityType.PIGLIN, EntityEnums.PIGLIN.goal));
	public static final RegistryObject<Item> DEVOURING_ESSENCE_PILLAGER = ITEMS.register("devouring_essence_pillager", () -> new DevouringEssenceItem(new Item.Properties(), EntityType.PILLAGER, EntityEnums.PILLAGER.goal));
	public static final RegistryObject<Item> DEVOURING_ESSENCE_RAVAGER = ITEMS.register("devouring_essence_ravager", () -> new DevouringEssenceItem(new Item.Properties(), EntityType.RAVAGER, EntityEnums.RAVAGER.goal));
	public static final RegistryObject<Item> DEVOURING_ESSENCE_SHULKER = ITEMS.register("devouring_essence_shulker", () -> new DevouringEssenceItem(new Item.Properties(), EntityType.SHULKER, EntityEnums.SHULKER.goal));
	public static final RegistryObject<Item> DEVOURING_ESSENCE_SILVERFISH = ITEMS.register("devouring_essence_silverfish", () -> new DevouringEssenceItem(new Item.Properties(), EntityType.SILVERFISH, EntityEnums.SILVERFISH.goal));
	public static final RegistryObject<Item> DEVOURING_ESSENCE_SKELETON = ITEMS.register("devouring_essence_skeleton", () -> new DevouringEssenceItem(new Item.Properties(), EntityType.SKELETON, EntityEnums.SKELETON.goal));
	public static final RegistryObject<Item> DEVOURING_ESSENCE_SLIME = ITEMS.register("devouring_essence_slime", () -> new DevouringEssenceItem(new Item.Properties(), EntityType.SLIME, EntityEnums.SLIME.goal));
	public static final RegistryObject<Item> DEVOURING_ESSENCE_SPIDER = ITEMS.register("devouring_essence_spider", () -> new DevouringEssenceItem(new Item.Properties(), EntityType.SPIDER, EntityEnums.SPIDER.goal));
	public static final RegistryObject<Item> DEVOURING_ESSENCE_WITCH = ITEMS.register("devouring_essence_witch", () -> new DevouringEssenceItem(new Item.Properties(), EntityType.WITCH, EntityEnums.WITCH.goal));
	public static final RegistryObject<Item> DEVOURING_ESSENCE_WITHER_SKELETON = ITEMS.register("devouring_essence_wither_skeleton", () -> new DevouringEssenceItem(new Item.Properties(), EntityType.WITHER_SKELETON, EntityEnums.WITHER_SKELETON.goal));
	public static final RegistryObject<Item> DEVOURING_ESSENCE_ZOGLIN = ITEMS.register("devouring_essence_zoglin", () -> new DevouringEssenceItem(new Item.Properties(), EntityType.ZOGLIN, EntityEnums.ZOGLIN.goal));
	public static final RegistryObject<Item> DEVOURING_ESSENCE_ZOMBIE = ITEMS.register("devouring_essence_zombie", () -> new DevouringEssenceItem(new Item.Properties(), EntityType.ZOMBIE, EntityEnums.ZOMBIE.goal));
	public static final RegistryObject<Item> DEVOURING_ESSENCE_ZOMBIFIED_PIGLIN = ITEMS.register("devouring_essence_zombified_piglin", () -> new DevouringEssenceItem(new Item.Properties(), EntityType.ZOMBIFIED_PIGLIN, EntityEnums.ZOMBIFIED_PIGLIN.goal));
	// Charged Essence Variations
	public static final RegistryObject<Item> CHARGED_ESSENCE_BLAZE = ITEMS.register("charged_essence_blaze", () -> new ChargedEssenceItem(new Item.Properties(), EntityType.BLAZE));
	public static final RegistryObject<Item> CHARGED_ESSENCE_CAVE_SPIDER = ITEMS.register("charged_essence_cave_spider", () -> new ChargedEssenceItem(new Item.Properties(), EntityType.CAVE_SPIDER));
	public static final RegistryObject<Item> CHARGED_ESSENCE_CREEPER = ITEMS.register("charged_essence_creeper", () -> new ChargedEssenceItem(new Item.Properties(), EntityType.CREEPER));
	public static final RegistryObject<Item> CHARGED_ESSENCE_DROWNED = ITEMS.register("charged_essence_drowned", () -> new ChargedEssenceItem(new Item.Properties(), EntityType.DROWNED));
	public static final RegistryObject<Item> CHARGED_ESSENCE_ENDERMAN = ITEMS.register("charged_essence_enderman", () -> new ChargedEssenceItem(new Item.Properties(), EntityType.ENDERMAN));
	public static final RegistryObject<Item> CHARGED_ESSENCE_GHAST = ITEMS.register("charged_essence_ghast", () -> new ChargedEssenceItem(new Item.Properties(), EntityType.GHAST));
	public static final RegistryObject<Item> CHARGED_ESSENCE_GUARDIAN = ITEMS.register("charged_essence_guardian", () -> new ChargedEssenceItem(new Item.Properties(), EntityType.GUARDIAN));
	public static final RegistryObject<Item> CHARGED_ESSENCE_HOGLIN = ITEMS.register("charged_essence_hoglin", () -> new ChargedEssenceItem(new Item.Properties(), EntityType.HOGLIN));
	public static final RegistryObject<Item> CHARGED_ESSENCE_MAGMA_CUBE = ITEMS.register("charged_essence_magma_cube", () -> new ChargedEssenceItem(new Item.Properties(), EntityType.MAGMA_CUBE));
	public static final RegistryObject<Item> CHARGED_ESSENCE_PHANTOM = ITEMS.register("charged_essence_phantom", () -> new ChargedEssenceItem(new Item.Properties(), EntityType.PHANTOM));
	public static final RegistryObject<Item> CHARGED_ESSENCE_PIGLIN = ITEMS.register("charged_essence_piglin", () -> new ChargedEssenceItem(new Item.Properties(), EntityType.PIGLIN));
	public static final RegistryObject<Item> CHARGED_ESSENCE_PILLAGER = ITEMS.register("charged_essence_pillager", () -> new ChargedEssenceItem(new Item.Properties(), EntityType.PILLAGER));
	public static final RegistryObject<Item> CHARGED_ESSENCE_RAVAGER = ITEMS.register("charged_essence_ravager", () -> new ChargedEssenceItem(new Item.Properties(), EntityType.RAVAGER));
	public static final RegistryObject<Item> CHARGED_ESSENCE_SHULKER = ITEMS.register("charged_essence_shulker", () -> new ChargedEssenceItem(new Item.Properties(), EntityType.SHULKER));
	public static final RegistryObject<Item> CHARGED_ESSENCE_SILVERFISH = ITEMS.register("charged_essence_silverfish", () -> new ChargedEssenceItem(new Item.Properties(), EntityType.SILVERFISH));
	public static final RegistryObject<Item> CHARGED_ESSENCE_SKELETON = ITEMS.register("charged_essence_skeleton", () -> new ChargedEssenceItem(new Item.Properties(), EntityType.SKELETON));
	public static final RegistryObject<Item> CHARGED_ESSENCE_SLIME = ITEMS.register("charged_essence_slime", () -> new ChargedEssenceItem(new Item.Properties(), EntityType.SLIME));
	public static final RegistryObject<Item> CHARGED_ESSENCE_SPIDER = ITEMS.register("charged_essence_spider", () -> new ChargedEssenceItem(new Item.Properties(), EntityType.SPIDER));
	public static final RegistryObject<Item> CHARGED_ESSENCE_WITCH = ITEMS.register("charged_essence_witch", () -> new ChargedEssenceItem(new Item.Properties(), EntityType.WITCH));
	public static final RegistryObject<Item> CHARGED_ESSENCE_WITHER_SKELETON = ITEMS.register("charged_essence_wither_skeleton", () -> new ChargedEssenceItem(new Item.Properties(), EntityType.WITHER_SKELETON));
	public static final RegistryObject<Item> CHARGED_ESSENCE_ZOGLIN = ITEMS.register("charged_essence_zoglin", () -> new ChargedEssenceItem(new Item.Properties(), EntityType.ZOGLIN));
	public static final RegistryObject<Item> CHARGED_ESSENCE_ZOMBIE = ITEMS.register("charged_essence_zombie", () -> new ChargedEssenceItem(new Item.Properties(), EntityType.ZOMBIE));
	public static final RegistryObject<Item> CHARGED_ESSENCE_ZOMBIFIED_PIGLIN = ITEMS.register("charged_essence_zombified_piglin", () -> new ChargedEssenceItem(new Item.Properties(), EntityType.ZOMBIFIED_PIGLIN));

	private static ToIntFunction<BlockState> getLightValueLit(int lightValue) {
		return (state) -> {
			return state.getValue(BlockStateProperties.LIT) ? lightValue : 0;
		};
	}
}