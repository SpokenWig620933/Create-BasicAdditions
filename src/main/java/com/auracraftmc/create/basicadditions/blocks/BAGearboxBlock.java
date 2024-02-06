package com.auracraftmc.create.basicadditions.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import javax.annotation.Nonnull;

import com.simibubi.create.content.kinetics.base.RotatedPillarKineticBlock;
import com.simibubi.create.content.kinetics.gearbox.GearboxBlockEntity;
import com.simibubi.create.foundation.block.IBE;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.HitResult;

public class BAGearboxBlock extends RotatedPillarKineticBlock implements IBE<GearboxBlockEntity> {
	
	private static final List<NonNullSupplier<? extends Block>> encasedBlocks = new ArrayList<>();
	
	public static NonNullSupplier<List<NonNullSupplier<? extends Block>>> getEncasedBlocks() {
		return () -> encasedBlocks;
	}
	
	private final Supplier<? extends BlockItem> verticalGearboxItem;
	private final Supplier<? extends BlockEntityType<GearboxBlockEntity>> gearboxBlockEntity;
	
	public BAGearboxBlock(@Nonnull Properties properties, @Nonnull Supplier<? extends BlockEntityType<GearboxBlockEntity>> gearboxBlockEntity, @Nonnull Supplier<? extends BlockItem> verticalGearboxItem) {
		super(properties);
		
		encasedBlocks.add(() -> this);
		
		this.verticalGearboxItem = verticalGearboxItem;
		this.gearboxBlockEntity = gearboxBlockEntity;
	}

	@Nonnull
	@Override
	public PushReaction getPistonPushReaction(@Nonnull BlockState state) {
		return PushReaction.PUSH_ONLY;
	}

	@SuppressWarnings("deprecation")
	@Nonnull
	@Override
	public List<ItemStack> getDrops(@Nonnull BlockState state, @Nonnull LootParams.Builder builder) {
		if(state.getValue(AXIS).isVertical()) return super.getDrops(state, builder);
		
		return List.of(new ItemStack(this.verticalGearboxItem.get()));
	}
	
	@Nonnull
	@Override
	public ItemStack getCloneItemStack(@Nonnull BlockState state, @Nonnull HitResult target, @Nonnull BlockGetter world, @Nonnull BlockPos pos, @Nonnull Player player) {
		if(state.getValue(AXIS).isVertical()) return super.getCloneItemStack(state, target, world, pos, player);
		
		return new ItemStack(this.verticalGearboxItem.get());
	}
	
	@Nonnull
	@Override
	public BlockState getStateForPlacement(@Nonnull BlockPlaceContext context) {
		return defaultBlockState().setValue(AXIS, Axis.Y);
	}

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
	public Class<GearboxBlockEntity> getBlockEntityClass() {
		return GearboxBlockEntity.class;
	}
	
	@Nonnull
	@Override
	public BlockEntityType<? extends GearboxBlockEntity> getBlockEntityType() {
		return this.gearboxBlockEntity.get();
	}
}