package com.auracraftmc.create.basicadditions.blocks;

import java.util.function.Supplier;
import javax.annotation.Nonnull;

import com.simibubi.create.content.contraptions.relays.encased.GearshiftBlock;
import com.simibubi.create.content.contraptions.relays.encased.SplitShaftTileEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class BAGearshiftBlock extends GearshiftBlock {
	
	private final Supplier<? extends BlockEntityType<? extends SplitShaftTileEntity>> tileEntityType;
	
	public BAGearshiftBlock(@Nonnull Properties properties, @Nonnull Supplier<? extends BlockEntityType<? extends SplitShaftTileEntity>> tileEntityType) {
		super(properties);
		
		this.tileEntityType = tileEntityType;
	}
	
	@Nonnull
	@Override
	public BlockEntityType<? extends SplitShaftTileEntity> getTileEntityType() {
		return this.tileEntityType.get();
	}
}