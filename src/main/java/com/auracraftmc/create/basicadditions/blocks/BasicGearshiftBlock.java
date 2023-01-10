package com.auracraftmc.create.basicadditions.blocks;

import java.util.Random;

import com.auracraftmc.create.basicadditions.registries.TileEntities;
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

	public BasicGearshiftBlock(Properties properties) {
		super(properties);
	}

	@Override
	public Class<SplitShaftTileEntity> getTileEntityClass() {
		return SplitShaftTileEntity.class;
	}
	
	@Override
	public BlockEntityType<? extends SplitShaftTileEntity> getTileEntityType() {
		return TileEntities.BASIC_GEARSHIFT.get();
	}

	public void detachKinetics(Level worldIn, BlockPos pos, boolean reAttachNextTick) {
		BlockEntity te = worldIn.getBlockEntity(pos);
		
		if(te == null || !(te instanceof KineticTileEntity)) return;
		
		RotationPropagator.handleRemoved(worldIn, pos, (KineticTileEntity) te);

		if(reAttachNextTick) worldIn.scheduleTick(pos, this, 0, TickPriority.EXTREMELY_HIGH);
	}

	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random random) {
		BlockEntity te = worldIn.getBlockEntity(pos);
		
		if(te == null || !(te instanceof KineticTileEntity)) return;
		
		KineticTileEntity kte = (KineticTileEntity) te;
		RotationPropagator.handleAdded(worldIn, pos, kte);
	}
}
