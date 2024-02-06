package com.auracraftmc.create.basicadditions.blocks;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import javax.annotation.Nonnull;

import com.auracraftmc.create.basicadditions.registries.BlockEntities;
import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogwheelBlock;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class BAEncasedCogwheelBlock extends EncasedCogwheelBlock {
	
	private static final Map<Boolean, List<NonNullSupplier<? extends Block>>> encasedBlocks = new ConcurrentHashMap<>(Map.of(false, new ArrayList<>(), true, new ArrayList<>()));
	
	public static NonNullSupplier<List<NonNullSupplier<? extends Block>>> getEncasedBlocks(boolean large) {
		return () -> encasedBlocks.get(large);
	}
	
	public BAEncasedCogwheelBlock(@Nonnull Properties properties, boolean large, @Nonnull Supplier<Block> casing) {
		super(properties, large, casing);
		
		encasedBlocks.get(large).add(() -> this);
	}
	
	@Override
	public BlockEntityType<? extends SimpleKineticBlockEntity> getBlockEntityType() {
		return isLargeCog() ? BlockEntities.ENCASED_LARGE_COGWHEEL.get() : BlockEntities.ENCASED_COGWHEEL.get();
	}
}