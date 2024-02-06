package com.auracraftmc.create.basicadditions.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import javax.annotation.Nonnull;

import com.auracraftmc.create.basicadditions.registries.BlockEntities;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedShaftBlock;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class BAEncasedShaftBlock extends EncasedShaftBlock {

	private static final List<NonNullSupplier<? extends Block>> encasedBlocks = new ArrayList<>();
	
	public static NonNullSupplier<List<NonNullSupplier<? extends Block>>> getEncasedBlocks() {
		return () -> encasedBlocks;
	}
	
	public BAEncasedShaftBlock(@Nonnull Properties properties, @Nonnull Supplier<Block> casing) {
		super(properties, casing);
		
		encasedBlocks.add(() -> this);
	}
	
	@Override
	public BlockEntityType<? extends KineticBlockEntity> getBlockEntityType() {
		return BlockEntities.ENCASED_SHAFT.get();
	}
}