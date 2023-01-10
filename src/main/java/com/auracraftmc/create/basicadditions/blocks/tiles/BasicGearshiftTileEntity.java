package com.auracraftmc.create.basicadditions.blocks.tiles;

import com.simibubi.create.content.contraptions.relays.encased.SplitShaftTileEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BasicGearshiftTileEntity extends SplitShaftTileEntity {

	public BasicGearshiftTileEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
	}

	@Override
	public float getRotationSpeedModifier(Direction face) {
		if(hasSource() && face != getSourceFacing()) return -1;
		
		return 1;
	}
	
}