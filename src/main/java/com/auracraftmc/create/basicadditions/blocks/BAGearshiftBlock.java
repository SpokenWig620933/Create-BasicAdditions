package com.auracraftmc.create.basicadditions.blocks;

import java.util.function.Supplier;
import javax.annotation.Nonnull;

import com.simibubi.create.content.kinetics.transmission.GearshiftBlock;
import com.simibubi.create.content.kinetics.transmission.SplitShaftBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class BAGearshiftBlock extends GearshiftBlock {
	
	private final Supplier<? extends BlockEntityType<? extends SplitShaftBlockEntity>> blockEntityType;
	
	public BAGearshiftBlock(@Nonnull Properties properties, @Nonnull Supplier<? extends BlockEntityType<? extends SplitShaftBlockEntity>> blockEntityType) {
		super(properties);
		
		this.blockEntityType = blockEntityType;
	}
	
	@Nonnull
	@Override
	public BlockEntityType<? extends SplitShaftBlockEntity> getBlockEntityType() {
		return this.blockEntityType.get();
	}
}