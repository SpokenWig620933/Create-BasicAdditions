package com.auracraftmc.create.basicadditions.blocks;

import java.util.List;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.simibubi.create.content.contraptions.base.RotatedPillarKineticBlock;
import com.simibubi.create.content.contraptions.relays.gearbox.GearboxTileEntity;
import com.simibubi.create.foundation.block.ITE;
import net.fabricmc.fabric.api.block.BlockPickInteractionAware;
import net.minecraft.core.*;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.LootContext.Builder;
import net.minecraft.world.phys.HitResult;

public class BAGearboxBlock extends RotatedPillarKineticBlock implements ITE<GearboxTileEntity>, BlockPickInteractionAware {

	private final Supplier<? extends BlockItem> verticalGearboxItem;
	private final Supplier<? extends BlockEntityType<GearboxTileEntity>> tileEntityType;
	
	public BAGearboxBlock(@Nonnull Properties properties, @Nonnull Supplier<? extends BlockItem> verticalGearboxItem, @Nonnull Supplier<? extends BlockEntityType<GearboxTileEntity>> tileEntityType) {
		super(properties);
		
		this.verticalGearboxItem = verticalGearboxItem;
		this.tileEntityType = tileEntityType;
	}

	@SuppressWarnings("deprecation")
	@Nonnull
	@Override
	public PushReaction getPistonPushReaction(@Nonnull BlockState state) {
		return PushReaction.PUSH_ONLY;
	}

	@Override
	public void fillItemCategory(@Nonnull CreativeModeTab group, @Nonnull NonNullList<ItemStack> items) {
		super.fillItemCategory(group, items);
		items.add(new ItemStack(this.verticalGearboxItem.get()));
	}

	@SuppressWarnings("deprecation")
	@Nonnull
	@Override
	public List<ItemStack> getDrops(@Nonnull BlockState state, @Nonnull Builder builder) {
		if(state.getValue(AXIS).isVertical()) return super.getDrops(state, builder);
		
		return List.of(new ItemStack(this.verticalGearboxItem.get()));
	}
	
	@Nonnull
	@Override
	
	public ItemStack getPickedStack(@Nonnull BlockState state, @Nonnull BlockGetter view, @Nonnull BlockPos pos, @Nullable Player player, @Nullable HitResult result) {
		if(state.getValue(AXIS).isVertical()) return super.getCloneItemStack(view, pos, state);
		
		return new ItemStack(this.verticalGearboxItem.get());
	}
	
	@Nonnull
	@Override
	public BlockState getStateForPlacement(@Nonnull BlockPlaceContext context) {
		return defaultBlockState().setValue(AXIS, Axis.Y);
	}

	// IRotate:

	@Override
	public boolean hasShaftTowards(@Nonnull LevelReader world, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull Direction face) {
		return face.getAxis() != state.getValue(AXIS);
	}
	
	@Nonnull
	@Override
	public Axis getRotationAxis(@Nonnull BlockState state) {
		return state.getValue(AXIS);
	}
	
	@Nonnull
	@Override
	public Class<GearboxTileEntity> getTileEntityClass() {
		return GearboxTileEntity.class;
	}
	
	@Nonnull
	@Override
	public BlockEntityType<? extends GearboxTileEntity> getTileEntityType() {
		return this.tileEntityType.get();
	}
}