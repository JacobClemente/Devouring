package com.jclemente.devouring.entities;

import java.util.List;

import com.jclemente.devouring.Registry;

import net.minecraft.state.BooleanProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3i;

public class AltarTileEntity extends TileEntity	implements ITickableTileEntity{

	private List<DevouringEssenceEntity> list;
	private AxisAlignedBB bbAbove;
	public static final BooleanProperty LIT = BlockStateProperties.LIT;
	
	public AltarTileEntity(TileEntityType<?> p_i48289_1_) {
		super(p_i48289_1_);
	}
	
	public AltarTileEntity() {
		this(Registry.ALTAR_TILE_ENTITY.get());
	}

	@Override
	public void tick() {
		bbAbove = new AxisAlignedBB(this.getBlockPos().offset(new Vector3i(0, 1, 0)));
		list = level.getEntitiesOfClass(DevouringEssenceEntity.class, bbAbove);
		if (!list.isEmpty()) {
			level.setBlock(this.getBlockPos(), this.getBlockState().setValue(LIT, Boolean.valueOf(true)), 3);
		}
		else {
			level.setBlock(this.getBlockPos(), this.getBlockState().setValue(LIT, Boolean.valueOf(false)), 3);
		}
	}
}
