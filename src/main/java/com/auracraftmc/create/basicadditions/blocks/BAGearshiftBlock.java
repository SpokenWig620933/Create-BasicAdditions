package com.auracraftmc.create.basicadditions.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import javax.annotation.Nonnull;

import com.simibubi.create.content.kinetics.RotationPropagator;
import com.simibubi.create.content.kinetics.base.AbstractEncasedShaftBlock;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.transmission.SplitShaftBlockEntity;
import com.simibubi.create.foundation.block.IBE;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BAGearshiftBlock extends AbstractEncasedShaftBlock implements IBE<SplitShaftBlockEntity> {
	
	private static final List<NonNullSupplier<? extends Block>> encasedBlocks = new ArrayList<>();
	
	public static NonNullSupplier<List<NonNullSupplier<? extends Block>>> getEncasedBlocks() {
		return () -> encasedBlocks;
	}
	
	private final Supplier<? extends BlockEntityType<? extends SplitShaftBlockEntity>> blockEntityType;
	
	public BAGearshiftBlock(@Nonnull Properties properties, @Nonnull Supplier<? extends BlockEntityType<? extends SplitShaftBlockEntity>> blockEntityType) {
		super(properties);
		
		encasedBlocks.add(() -> this);
		
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
	
	@SuppressWarnings("deprecation")
	@Override
	public void tick(@Nonnull BlockState state, @Nonnull ServerLevel worldIn, @Nonnull BlockPos pos, @Nonnull RandomSource random) {
		if(worldIn.getBlockEntity(pos) instanceof KineticBlockEntity kbe) RotationPropagator.handleAdded(worldIn, pos, kbe);
	}
}