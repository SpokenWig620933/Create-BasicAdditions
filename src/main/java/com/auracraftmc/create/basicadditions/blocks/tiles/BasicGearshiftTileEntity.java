package com.auracraftmc.create.basicadditions.blocks.tiles;

import javax.annotation.Nonnull;

import com.simibubi.create.content.contraptions.relays.encased.SplitShaftTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BasicGearshiftTileEntity extends SplitShaftTileEntity {

	public BasicGearshiftTileEntity(@Nonnull BlockEntityType<?> type, @Nonnull BlockPos pos, @Nonnull BlockState state) {
		super(type, pos, state);
	}

	@Override
	public float getRotationSpeedModifier(@Nonnull Direction face) {
		return (hasSource() && face != getSourceFacing()) ? -1 : 1;
	}
}