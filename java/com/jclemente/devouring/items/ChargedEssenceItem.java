package com.jclemente.devouring.items;

import java.util.List;

import javax.annotation.Nullable;

import com.jclemente.devouring.Registry;

import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class ChargedEssenceItem extends Item {

	EntityType<? extends Entity> targetEntity;

	public ChargedEssenceItem(Properties p_i48487_1_, @Nullable EntityType<? extends Entity> entityType) {
		super(p_i48487_1_);
		this.targetEntity = entityType;
	}

	@Override
	public ActionResultType useOn(ItemUseContext item) {
		World world = item.getLevel();
		if (!(world instanceof ServerWorld)) {
			return ActionResultType.SUCCESS;
		} else {
			BlockPos blockpos = item.getClickedPos();
			if (world.getBlockState(blockpos).getBlock() == Registry.EMPTY_SPAWNER.get() && this.targetEntity != null) {
				world.setBlock(blockpos, Blocks.SPAWNER.defaultBlockState(), 2);
				MobSpawnerTileEntity spawnerTileEntity = (MobSpawnerTileEntity) world.getBlockEntity(blockpos);
				spawnerTileEntity.getSpawner().setEntityId(targetEntity);
				item.getItemInHand().shrink(1);
				return ActionResultType.CONSUME;
			} else
				return ActionResultType.FAIL;
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {
		tooltip.add(this.getDisplayName().withStyle(TextFormatting.GRAY).withStyle(TextFormatting.ITALIC));
	}

	public IFormattableTextComponent getDisplayName() {
		return new TranslationTextComponent(this.getDescriptionId() + ".desc");
	}

}
