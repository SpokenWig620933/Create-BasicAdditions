package com.auracraftmc.create.basicadditions.utils;

import java.util.function.Supplier;
import javax.annotation.Nonnull;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.decoration.encasing.EncasedCTBehaviour;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.content.kinetics.base.RotatedPillarKineticBlock;
import com.simibubi.create.content.kinetics.simpleRelays.encased.*;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.data.*;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction.AxisDirection;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.state.BlockBehaviour;

import static com.simibubi.create.foundation.data.BlockStateGen.axisBlock;

public class BABuilderTransformers {
	
	public static <B extends EncasedShaftBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> encasedShaft(@Nonnull String casing, @Nonnull Supplier<CTSpriteShiftEntry> casingShift) {
		return builder -> encasedBase(builder, AllBlocks.SHAFT::get)
				.onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCTBehaviour(casingShift.get())))
		        .onRegister(CreateRegistrate.casingConnectivity((block, cc) -> cc.make(block, casingShift.get(), (s, f) -> f.getAxis() != s.getValue(EncasedShaftBlock.AXIS))))
		        .blockstate((c, p) -> axisBlock(c, p, blockState -> p.models().getExistingFile(p.modLoc("block/encased_shaft/" + casing + "/block")), true))
		        .item()
		        .model(AssetLookup.customBlockItemModel("encased_shaft", casing, "item"))
		        .build();
	}
	
	public static <B extends EncasedCogwheelBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> encasedCogwheel(@Nonnull String casing, @Nonnull Supplier<CTSpriteShiftEntry> casingShift) {
		return b -> encasedCogwheelBase(b, casing, casingShift, AllBlocks.COGWHEEL::get, false);
	}
	
	public static <B extends EncasedCogwheelBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> encasedLargeCogwheel(@Nonnull String casing, @Nonnull Supplier<CTSpriteShiftEntry> casingShift) {
		return b -> encasedCogwheelBase(b, casing, casingShift, AllBlocks.LARGE_COGWHEEL::get, true).onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCogCTBehaviour(casingShift.get())));
	}
	
	@SuppressWarnings("removal")
	private static <B extends EncasedCogwheelBlock, P> BlockBuilder<B, P> encasedCogwheelBase(@Nonnull BlockBuilder<B, P> b, @Nonnull String casing, @Nonnull Supplier<CTSpriteShiftEntry> casingShift, @Nonnull Supplier<ItemLike> drop, boolean large) {
		String blockFolder = (large ? "encased_large_cogwheel" : "encased_cogwheel") + "/" + casing;
		
		return encasedBase(b, drop)
		        .addLayer(() -> RenderType::cutoutMipped)
		        .onRegister(CreateRegistrate.casingConnectivity((block, cc) -> cc.make(block, casingShift.get(), (s, f) -> f.getAxis() == s.getValue(EncasedCogwheelBlock.AXIS) && !s.getValue(f.getAxisDirection() == AxisDirection.POSITIVE ? EncasedCogwheelBlock.TOP_SHAFT : EncasedCogwheelBlock.BOTTOM_SHAFT))))
		        .blockstate((c, p) -> axisBlock(c, p, blockState -> p.models().getExistingFile(p.modLoc("block/" + blockFolder + "/block" + (blockState.getValue(EncasedCogwheelBlock.TOP_SHAFT) ? "_top" : "") + (blockState.getValue(EncasedCogwheelBlock.BOTTOM_SHAFT) ? "_bottom" : ""))), false))
		        .item()
		        .model((c, p) -> p.getExistingFile(p.modLoc("block/" + blockFolder + "/item")))
		        .build();
	}
	
	private static <B extends RotatedPillarKineticBlock, P> BlockBuilder<B, P> encasedBase(@Nonnull BlockBuilder<B, P> b, @Nonnull Supplier<ItemLike> drop) {
		return b.initialProperties(SharedProperties::stone)
		        .properties(BlockBehaviour.Properties::noOcclusion)
		        .transform(BlockStressDefaults.setNoImpact())
		        .loot((p, lb) -> p.dropOther(lb, drop.get()));
	}
}