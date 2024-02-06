package com.auracraftmc.create.basicadditions.blocks;

import java.util.function.Supplier;
import javax.annotation.Nonnull;

import com.simibubi.create.content.kinetics.RotationPropagator;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.transmission.SplitShaftBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.ticks.TickPriority;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.POWERED;

public class BAGearshiftRCBlock extends BAGearshiftBlock {
	
	public BAGearshiftRCBlock(@Nonnull Properties properties, @Nonnull Supplier<? extends BlockEntityType<? extends SplitShaftBlockEntity>> blockEntityType) {
		super(properties, blockEntityType);
		
		registerDefaultState(defaultBlockState().setValue(POWERED, false));
	}
	
	@Override
	protected void createBlockStateDefinition(@Nonnull Builder<Block, BlockState> builder) {
		builder.add(POWERED);
		
		super.createBlockStateDefinition(builder);
	}
	
	@Nonnull
	@Override
	public BlockState getStateForPlacement(@Nonnull BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(POWERED, context.getLevel().hasNeighborSignal(context.getClickedPos()));
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void neighborChanged(@Nonnull BlockState state, @Nonnull Level worldIn, @Nonnull BlockPos pos, @Nonnull Block blockIn, @Nonnull BlockPos fromPos, boolean isMoving) {
		if(worldIn.isClientSide) return;
		
		if(state.getValue(POWERED) != worldIn.hasNeighborSignal(pos)) {
			detachKinetics(worldIn, pos, true);
			
			worldIn.setBlock(pos, state.cycle(POWERED), 2);
		}
	}
	
	public void detachKinetics(@Nonnull Level worldIn, @Nonnull BlockPos pos, boolean reAttachNextTick) {
		if(!(worldIn.getBlockEntity(pos) instanceof KineticBlockEntity kbe)) return;
		
		RotationPropagator.handleRemoved(worldIn, pos, kbe);
		
		// Re-attach next tick
		if(reAttachNextTick) worldIn.scheduleTick(pos, this, 0, TickPriority.EXTREMELY_HIGH);
	}
}