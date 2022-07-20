package com.jclemente.devouring.blocks;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import com.jclemente.devouring.Registry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class AltarBlock extends Block {
	public static final BooleanProperty LIT = BlockStateProperties.LIT;
	public static final DirectionProperty FACING = HorizontalBlock.FACING;
	private static final Map<Direction, VoxelShape> SHAPES = new HashMap<Direction, VoxelShape>();
	private static VoxelShape shape = Stream.of(Block.box(4, 15, 3, 12, 16, 4), Block.box(7, 0, 7, 9, 12, 9), Block.box(5, 0, 5, 11, 2, 11), Block.box(5, 12, 5, 11, 13, 11), Block.box(5, 13, 4, 11, 14, 5), Block.box(5, 13, 11, 11, 14, 12), Block.box(4, 13, 5, 5, 14, 11), Block.box(11, 13, 5, 12, 14, 11), Block.box(4, 14, 4, 12, 15, 5), Block.box(4, 14, 11, 12, 15, 12), Block.box(4, 14, 5, 5, 15, 11), Block.box(11, 14, 5, 12, 15, 11), Block.box(12, 15, 4, 13, 16, 12), Block.box(3, 15, 4, 4, 16, 12), Block.box(4, 15, 12, 12, 16, 13)).reduce((v1, v2) -> {
		return VoxelShapes.join(v1, v2, IBooleanFunction.OR);
	}).get();

	public AltarBlock(Properties p_i48440_1_) {
		super(p_i48440_1_);
		runCalculation(AltarBlock.shape);
		this.registerDefaultState(this.stateDefinition.any().setValue(LIT, Boolean.valueOf(false)));
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader worldIn, BlockPos blockPos, ISelectionContext context) {
		return SHAPES.get(blockState.getValue(FACING));
	}

	private static void calculateShapes(Direction to, VoxelShape shape) {
		VoxelShape[] buffer = { shape, VoxelShapes.empty() };
		int times = (to.get2DDataValue() - Direction.NORTH.get2DDataValue() + 4) % 4;
		for (int i = 0; i < times; i++) {
			buffer[0].forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = VoxelShapes.or(buffer[1], VoxelShapes.box(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX)));
			buffer[0] = buffer[1];
			buffer[1] = VoxelShapes.empty();
		}
		SHAPES.put(to, buffer[0]);
	}

	private void runCalculation(VoxelShape shape) {
		for (Direction direction : Direction.values())
			calculateShapes(direction, shape);
	}

	public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
		return this.defaultBlockState().setValue(FACING, p_196258_1_.getHorizontalDirection().getOpposite());
	}

	public BlockState rotate(BlockState p_185499_1_, Rotation p_185499_2_) {
		return p_185499_1_.setValue(FACING, p_185499_2_.rotate(p_185499_1_.getValue(FACING)));
	}

	@SuppressWarnings("deprecation")
	public BlockState mirror(BlockState p_185471_1_, Mirror p_185471_2_) {
		return p_185471_1_.rotate(p_185471_2_.getRotation(p_185471_1_.getValue(FACING)));
	}

	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
		p_206840_1_.add(FACING, LIT);
	}

	@Override
	public BlockRenderType getRenderShape(BlockState p_149645_1_) {
		return BlockRenderType.MODEL;
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return Registry.ALTAR_TILE_ENTITY.get().create();
	}
}
