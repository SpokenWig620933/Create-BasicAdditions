package com.auracraftmc.create.basicadditions.blocks.tiles;

import javax.annotation.Nonnull;

import com.simibubi.create.content.kinetics.transmission.SplitShaftBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.POWERED;

public class BAGearshiftBlockEntity extends SplitShaftBlockEntity {
	
	public BAGearshiftBlockEntity(@Nonnull BlockEntityType<?> type, @Nonnull BlockPos pos, @Nonnull BlockState state) {
		super(type, pos, state);
	}
	
	@Override
	public float getRotationSpeedModifier(@Nonnull Direction face) {
		return (hasSource() && face != getSourceFacing() && (!(getBlockState().hasProperty(POWERED)) || getBlockState().getValue(POWERED))) ? -1 : 1;
	}
}