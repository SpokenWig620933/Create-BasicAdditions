package com.auracraftmc.create.basicadditions.blocks;

import java.util.Random;
import java.util.function.Supplier;
import javax.annotation.Nonnull;

import com.simibubi.create.content.kinetics.RotationPropagator;
import com.simibubi.create.content.kinetics.base.AbstractEncasedShaftBlock;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.transmission.SplitShaftBlockEntity;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.ticks.TickPriority;

public class BasicGearshiftBlock extends AbstractEncasedShaftBlock implements IBE<SplitShaftBlockEntity> {

	private final Supplier<BlockEntityType<? extends SplitShaftBlockEntity>> blockEntityType;
	
	public BasicGearshiftBlock(@Nonnull Properties properties, @Nonnull Supplier<BlockEntityType<? extends SplitShaftBlockEntity>> blockEntityType) {
		super(properties);
		
		this.blockEntityType = blockEntityType;
	}
	
	@Nonnull
	@Override
	public Class<SplitShaftBlockEntity> getBlockEntityClass() {
		return SplitShaftBlockEntity.class;
	}
	
	@Nonnull
	@Override
	public BlockEntityType<? extends SplitShaftBlockEntity> getBlockEntityType() {
		return this.blockEntityType.get();
	}

	public void detachKinetics(@Nonnull Level worldIn, @Nonnull BlockPos pos, boolean reAttachNextTick) {
		BlockEntity te = worldIn.getBlockEntity(pos);
		
		if(te instanceof KineticBlockEntity kte) {
			RotationPropagator.handleRemoved(worldIn, pos, kte);
			
			if(reAttachNextTick) worldIn.scheduleTick(pos, this, 0, TickPriority.EXTREMELY_HIGH);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void tick(@Nonnull BlockState state, @Nonnull ServerLevel worldIn, @Nonnull BlockPos pos, @Nonnull Random random) {
		BlockEntity te = worldIn.getBlockEntity(pos);
		
		if(te instanceof KineticBlockEntity kte) RotationPropagator.handleAdded(worldIn, pos, kte);
	}
}