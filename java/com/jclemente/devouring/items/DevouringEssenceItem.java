package com.jclemente.devouring.items;

import java.util.List;

import javax.annotation.Nullable;

import com.jclemente.devouring.Registry;
import com.jclemente.devouring.capabilities.Capabilities;
import com.jclemente.devouring.entities.DevouringEssenceEntity;

import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class DevouringEssenceItem extends Item {
	EntityType<? extends Entity> targetEntity;
	int killGoal;

	public DevouringEssenceItem(Properties p_i48487_1_, @Nullable EntityType<? extends Entity> entityType, @Nullable Integer killGoal) {
		super(p_i48487_1_);
		if (entityType != null)
			this.targetEntity = entityType;
		if (killGoal != null)
			this.killGoal = killGoal;
	}

	@Override
	public ActionResultType useOn(ItemUseContext item) {
		World world = item.getLevel();
		if (!(world instanceof ServerWorld)) {
			return ActionResultType.SUCCESS;
		} else {
			BlockPos blockpos = item.getClickedPos();
			BlockPos blockposAbove = new BlockPos(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
			if (world.getBlockState(blockpos).getBlock() == Registry.ALTAR_BLOCK.get() && world.getBlockState(blockposAbove).getBlock() == Blocks.AIR && world.getEntitiesOfClass(DevouringEssenceEntity.class, new AxisAlignedBB(blockposAbove)).isEmpty() && this.targetEntity != null) {
				DevouringEssenceEntity spawnedEntity = (DevouringEssenceEntity) Registry.DEVOURING_ESSENCE_ENTITY.get().spawn((ServerWorld) world, null, null, blockposAbove.offset(new Vector3i(0, 1, 0)), SpawnReason.SPAWN_EGG, true, true);
				spawnedEntity.getCapability(Capabilities.DEVOURING_CAPABILITY).ifPresent(devouringEntity -> {
					devouringEntity.setTarget(targetEntity);
					devouringEntity.setGoal(killGoal);
				});
				item.getItemInHand().shrink(1);
				return ActionResultType.CONSUME;
			} else
				return ActionResultType.FAIL;
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(this.getDisplayName().withStyle(TextFormatting.GRAY).withStyle(TextFormatting.ITALIC));
	}

	public IFormattableTextComponent getDisplayName() {
		return new TranslationTextComponent(this.getDescriptionId() + ".desc");
	}
}
