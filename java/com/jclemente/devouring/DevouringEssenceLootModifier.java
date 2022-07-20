package com.jclemente.devouring;

import java.util.List;

import javax.annotation.Nonnull;

import com.google.gson.JsonObject;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

public class DevouringEssenceLootModifier extends LootModifier {
	private final Item devouring;

	public DevouringEssenceLootModifier(ILootCondition[] conditionsIn, Item devouring) {
		super(conditionsIn);
		this.devouring = devouring;
	}

	@Nonnull
	@Override
	public List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		generatedLoot.add(new ItemStack(this.devouring, 1));
		return generatedLoot;
	}

	public static class Serializer extends GlobalLootModifierSerializer<DevouringEssenceLootModifier> {
		@Override
		public DevouringEssenceLootModifier read(ResourceLocation location, JsonObject object, ILootCondition[] ailootcondition) {
			final Item devouring = ForgeRegistries.ITEMS.getValue(new ResourceLocation((JSONUtils.getAsString(object, "devouring"))));
			return new DevouringEssenceLootModifier(ailootcondition, devouring);
		}

		@Override
		public JsonObject write(DevouringEssenceLootModifier instance) {
			JsonObject json = makeConditions(instance.conditions);
			json.addProperty("devouring", ForgeRegistries.ITEMS.getKey(Registry.DEVOURING_ESSENCE.get()).toString());
			return json;
		}
	}
}
