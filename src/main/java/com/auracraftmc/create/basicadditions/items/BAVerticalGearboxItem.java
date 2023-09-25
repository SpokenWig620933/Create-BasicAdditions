package com.auracraftmc.create.basicadditions.items;

import java.util.Map;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.foundation.utility.Iterate;
import net.minecraft.core.*;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class BAVerticalGearboxItem extends BlockItem {

	public BAVerticalGearboxItem(@Nonnull Properties properties, @Nonnull Supplier<? extends Block> gearboxBlock) {
		super(gearboxBlock.get(), properties);
	}

	@Override
	public void fillItemCategory(@Nonnull CreativeModeTab tab, @Nonnull NonNullList<ItemStack> stacks) {
	}
	
	@Nonnull
	@Override
	public String getDescriptionId() {
		return "item.create_basic_additions.vertical_brass_gearbox";
	}

	@Override
	public void registerBlocks(@Nonnull Map<Block, Item> map, @Nonnull Item item) {
	}

	@Override
	protected boolean updateCustomBlockEntityTag(@Nonnull BlockPos pos, @Nonnull Level world, @Nullable Player player, @Nonnull ItemStack stack, @Nonnull BlockState state) {
		Axis prefferedAxis = null;
		
		for(Direction side : Iterate.horizontalDirections) {
			BlockState blockState = world.getBlockState(pos.relative(side));
			
			if(blockState.getBlock() instanceof IRotate) {
				if(((IRotate) blockState.getBlock()).hasShaftTowards(world, pos.relative(side), blockState, side.getOpposite())) {
					if(prefferedAxis != null && prefferedAxis != side.getAxis()) {
						prefferedAxis = null;
						break;
					} else prefferedAxis = side.getAxis();
				}
			}
		}

		Axis axis = prefferedAxis == null && player != null ? player.getDirection().getClockWise().getAxis() : prefferedAxis == Axis.X ? Axis.Z : Axis.X;
		
		world.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.AXIS, axis));
		
		return super.updateCustomBlockEntityTag(pos, world, player, stack, state);
	}
}