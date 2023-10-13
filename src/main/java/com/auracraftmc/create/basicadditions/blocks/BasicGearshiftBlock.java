package com.auracraftmc.create.basicadditions.blocks;

import java.util.Random;
import java.util.function.Supplier;
import javax.annotation.Nonnull;

import com.simibubi.create.content.contraptions.RotationPropagator;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.relays.encased.AbstractEncasedShaftBlock;
import com.simibubi.create.content.contraptions.relays.encased.SplitShaftTileEntity;
import com.simibubi.create.foundation.block.ITE;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.ticks.TickPriority;

public class BasicGearshiftBlock extends AbstractEncasedShaftBlock implements ITE<SplitShaftTileEntity> {

	private final Supplier<BlockEntityType<? extends SplitShaftTileEntity>> tileEntityType;
	
	public BasicGearshiftBlock(@Nonnull Properties properties, @Nonnull Supplier<BlockEntityType<? extends SplitShaftTileEntity>> tileEntityType) {
		super(properties);
		
		this.tileEntityType = tileEntityType;
	}
	
	@Nonnull
	@Override
	public Class<SplitShaftTileEntity> getTileEntityClass() {
		return SplitShaftTileEntity.class;
	}
	
	@Nonnull
	@Override
	public BlockEntityType<? extends SplitShaftTileEntity> getTileEntityType() {
		return this.tileEntityType.get();
	}

	public void detachKinetics(@Nonnull Level worldIn, @Nonnull BlockPos pos, boolean reAttachNextTick) {
		BlockEntity te = worldIn.getBlockEntity(pos);
		
		if(te instanceof KineticTileEntity kte) {
			RotationPropagator.handleRemoved(worldIn, pos, kte);
			
			if(reAttachNextTick) worldIn.scheduleTick(pos, this, 0, TickPriority.EXTREMELY_HIGH);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void tick(@Nonnull BlockState state, @Nonnull ServerLevel worldIn, @Nonnull BlockPos pos, @Nonnull Random random) {
		BlockEntity te = worldIn.getBlockEntity(pos);
		
		if(te instanceof KineticTileEntity kte) RotationPropagator.handleAdded(worldIn, pos, kte);
	}
}