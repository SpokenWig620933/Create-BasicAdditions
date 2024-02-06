package com.auracraftmc.create.basicadditions.registries;

import java.util.function.Supplier;
import javax.annotation.Nonnull;

import com.auracraftmc.create.basicadditions.CreateBasicAdditionsMod;
import com.auracraftmc.create.basicadditions.blocks.*;
import com.auracraftmc.create.basicadditions.blocks.tiles.BAGearshiftBlockEntity;
import com.auracraftmc.create.basicadditions.items.BAVerticalGearboxItem;
import com.auracraftmc.create.basicadditions.utils.BABuilderTransformers;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllSpriteShifts;
import com.simibubi.create.content.decoration.encasing.*;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.content.kinetics.gearbox.GearboxBlock;
import com.simibubi.create.content.kinetics.gearbox.GearboxBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogCTBehaviour;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.data.*;
import com.simibubi.create.foundation.utility.Couple;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;

@SuppressWarnings("removal")
public class Blocks extends net.minecraft.world.level.block.Blocks {
	
	private static final CreateRegistrate REGISTRATE = CreateBasicAdditionsMod.REGISTRATE;
	
	public static BlockEntry<BAEncasedShaftBlock> COPPER_ENCASED_SHAFT = createEncasedShaft("copper", "Copper", AllBlocks.COPPER_CASING, MapColor.TERRACOTTA_LIGHT_GRAY, () -> AllSpriteShifts.COPPER_CASING);
	public static BlockEntry<BAEncasedShaftBlock> RAILWAY_ENCASED_SHAFT = createEncasedShaft("railway", "Train", AllBlocks.RAILWAY_CASING, MapColor.TERRACOTTA_CYAN, () -> AllSpriteShifts.RAILWAY_CASING);
	
	public static BlockEntry<BAEncasedCogwheelBlock> COPPER_ENCASED_COGWHEEL = createEncasedCogwheel("copper", "Copper", AllBlocks.COPPER_CASING, MapColor.TERRACOTTA_LIGHT_GRAY, () -> AllSpriteShifts.COPPER_CASING, () -> SpriteShifts.COPPER_ENCASED_COGWHEEL_SIDE, () -> SpriteShifts.COPPER_ENCASED_COGWHEEL_OTHERSIDE);
	public static BlockEntry<BAEncasedCogwheelBlock> RAILWAY_ENCASED_COGWHEEL = createEncasedCogwheel("railway", "Train", AllBlocks.RAILWAY_CASING, MapColor.TERRACOTTA_CYAN, () -> AllSpriteShifts.RAILWAY_CASING, () -> SpriteShifts.RAILWAY_ENCASED_COGWHEEL_SIDE, () -> SpriteShifts.RAILWAY_ENCASED_COGWHEEL_OTHERSIDE);
	
	public static BlockEntry<BAEncasedCogwheelBlock> COPPER_ENCASED_LARGE_COGWHEEL = createEncasedLargeCogwheel("copper", "Copper", AllBlocks.COPPER_CASING, MapColor.TERRACOTTA_LIGHT_GRAY, () -> AllSpriteShifts.COPPER_CASING);
	public static BlockEntry<BAEncasedCogwheelBlock> RAILWAY_ENCASED_LARGE_COGWHEEL = createEncasedLargeCogwheel("railway", "Train", AllBlocks.RAILWAY_CASING, MapColor.TERRACOTTA_CYAN, () -> AllSpriteShifts.RAILWAY_CASING);
	
	public static final BlockEntry<BAGearboxBlock> BRASS_GEARBOX = createGearbox("brass", "Brass", BlockEntities.GEARBOX, Items.VERTICAL_BRASS_GEARBOX, SharedProperties::softMetal, MapColor.TERRACOTTA_BROWN, () -> AllSpriteShifts.BRASS_CASING);
	public static final BlockEntry<BAGearboxBlock> COPPER_GEARBOX = createGearbox("copper", "Copper", BlockEntities.GEARBOX, Items.VERTICAL_COPPER_GEARBOX, SharedProperties::copperMetal, MapColor.TERRACOTTA_LIGHT_GRAY, () -> AllSpriteShifts.COPPER_CASING);
	public static final BlockEntry<BAGearboxBlock> RAILWAY_GEARBOX = createGearbox("railway", "Train", BlockEntities.GEARBOX, Items.VERTICAL_RAILWAY_GEARBOX, SharedProperties::softMetal, MapColor.TERRACOTTA_CYAN, () -> AllSpriteShifts.RAILWAY_CASING);
	
	public static final BlockEntry<BAGearshiftRCBlock> BRASS_GEARSHIFT = createGearshift("brass", "Brass", BlockEntities.GEARSHIFT, SharedProperties::softMetal, MapColor.TERRACOTTA_BROWN);
	public static final BlockEntry<BAGearshiftRCBlock> COPPER_GEARSHIFT = createGearshift("copper", "Copper", BlockEntities.GEARSHIFT, SharedProperties::copperMetal, MapColor.TERRACOTTA_LIGHT_GRAY);
	public static final BlockEntry<BAGearshiftRCBlock> RAILWAY_GEARSHIFT = createGearshift("railway", "Train", BlockEntities.GEARSHIFT, SharedProperties::softMetal, MapColor.TERRACOTTA_CYAN);
	
	public static final BlockEntry<BAGearshiftBlock> BASIC_GEARSHIFT = createSimpleGearshift("basic", "Basic", BlockEntities.GEARSHIFT, SharedProperties::stone, MapColor.PODZOL);
	public static final BlockEntry<BAGearshiftBlock> BASIC_BRASS_GEARSHIFT = createSimpleGearshift("basic_brass", "Basic Brass", BlockEntities.GEARSHIFT, SharedProperties::softMetal, MapColor.TERRACOTTA_BROWN);
	public static final BlockEntry<BAGearshiftBlock> BASIC_COPPER_GEARSHIFT = createSimpleGearshift("basic_copper", "Basic Copper", BlockEntities.GEARSHIFT, SharedProperties::copperMetal, MapColor.TERRACOTTA_LIGHT_GRAY);
	public static final BlockEntry<BAGearshiftBlock> BASIC_RAILWAY_GEARSHIFT = createSimpleGearshift("basic_railway", "Basic Train", BlockEntities.GEARSHIFT, SharedProperties::softMetal, MapColor.TERRACOTTA_CYAN);
	
	public static BlockEntry<BAEncasedShaftBlock> createEncasedShaft(@Nonnull String id, @Nonnull String title, @Nonnull Supplier<CasingBlock> casing, @Nonnull MapColor mapColor, @Nonnull Supplier<CTSpriteShiftEntry> sprite) {
		return REGISTRATE.block(id + "_encased_shaft", p -> new BAEncasedShaftBlock(p, casing::get))
		       .lang(title + " Encased Shaft")
		       .properties(p -> p.mapColor(mapColor))
		       .transform(BABuilderTransformers.encasedShaft(id, sprite))
		       .transform(EncasingRegistry.addVariantTo(AllBlocks.SHAFT))
		       .transform(axeOrPickaxe())
		       .register();
	}
	
	public static BlockEntry<BAEncasedCogwheelBlock> createEncasedCogwheel(@Nonnull String id, @Nonnull String title, @Nonnull Supplier<CasingBlock> casing, @Nonnull MapColor mapColor, @Nonnull Supplier<CTSpriteShiftEntry> sprite, @Nonnull Supplier<CTSpriteShiftEntry> spriteSide, @Nonnull Supplier<CTSpriteShiftEntry> spriteOtherside) {
		return REGISTRATE.block(id + "_encased_cogwheel", p -> new BAEncasedCogwheelBlock(p, false, casing::get))
		       .lang(title + " Encased Cogwheel")
		       .properties(p -> p.mapColor(mapColor))
		       .transform(BABuilderTransformers.encasedCogwheel(id, sprite))
		       .transform(EncasingRegistry.addVariantTo(AllBlocks.COGWHEEL))
		       .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCogCTBehaviour(sprite.get(), Couple.create(spriteSide.get(), spriteOtherside.get()))))
		       .transform(axeOrPickaxe())
		       .register();
	}
	
	public static BlockEntry<BAEncasedCogwheelBlock> createEncasedLargeCogwheel(@Nonnull String id, @Nonnull String title, @Nonnull Supplier<CasingBlock> casing, @Nonnull MapColor mapColor, @Nonnull Supplier<CTSpriteShiftEntry> sprite) {
		return REGISTRATE.block(id + "_encased_large_cogwheel", p -> new BAEncasedCogwheelBlock(p, true, casing::get))
		       .lang(title + " Encased Large Cogwheel")
		       .properties(p -> p.mapColor(mapColor))
		       .transform(BABuilderTransformers.encasedLargeCogwheel(id, sprite))
		       .transform(EncasingRegistry.addVariantTo(AllBlocks.LARGE_COGWHEEL))
		       .transform(axeOrPickaxe())
		       .register();
	}
	
	public static BlockEntry<BAGearboxBlock> createGearbox(@Nonnull String id, @Nonnull String title, @Nonnull Supplier<BlockEntityType<GearboxBlockEntity>> blockEntity, @Nonnull Supplier<BAVerticalGearboxItem> verticalGearbox, @Nonnull Supplier<Block> sharedProperties, @Nonnull MapColor mapColor, @Nonnull Supplier<CTSpriteShiftEntry> sprite) {
		return REGISTRATE.block(id + "_gearbox", (prop) -> new BAGearboxBlock(prop, blockEntity, verticalGearbox))
		       .lang(title + " Gearbox")
		       .initialProperties(sharedProperties::get)
		       .properties(BlockBehaviour.Properties::noOcclusion)
		       .properties(p -> p.mapColor(mapColor))
		       .transform(BlockStressDefaults.setNoImpact())
		       .transform(TagGen.axeOrPickaxe())
		       .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCTBehaviour(sprite.get())))
		       .onRegister(CreateRegistrate.casingConnectivity((block, cc) -> cc.make(block, sprite.get(), (s, f) -> f.getAxis() == s.getValue(GearboxBlock.AXIS))))
		       .blockstate((c, p) -> BlockStateGen.axisBlock(c, p, $ -> AssetLookup.partialBaseModel(c, p), true))
		       .item()
		       .transform(ModelGen.customItemModel())
		       .register();
	}
	
	public static BlockEntry<BAGearshiftRCBlock> createGearshift(@Nonnull String id, @Nonnull String title, @Nonnull Supplier<BlockEntityType<BAGearshiftBlockEntity>> blockEntity, @Nonnull Supplier<Block> sharedProperties, @Nonnull MapColor mapColor) {
		return REGISTRATE.block(id + "_gearshift", (prop) -> new BAGearshiftRCBlock(prop, blockEntity))
		       .lang(title + " Gearshift")
		       .initialProperties(sharedProperties::get)
		       .properties(BlockBehaviour.Properties::noOcclusion)
		       .properties(p -> p.mapColor(mapColor))
		       .addLayer(() -> RenderType::cutoutMipped)
		       .transform(BlockStressDefaults.setNoImpact())
		       .transform(TagGen.axeOrPickaxe())
		       .blockstate((c, p) -> BlockStateGen.axisBlock(c, p, AssetLookup.forPowered(c, p)))
		       .item()
		       .transform(ModelGen.customItemModel())
		       .register();
	}
	
	public static BlockEntry<BAGearshiftBlock> createSimpleGearshift(@Nonnull String id, @Nonnull String title, @Nonnull Supplier<BlockEntityType<BAGearshiftBlockEntity>> blockEntity, @Nonnull Supplier<Block> sharedProperties, @Nonnull MapColor mapColor) {
		return REGISTRATE.block(id + "_gearshift", (prop) -> new BAGearshiftBlock(prop, blockEntity))
		       .lang(title + " Gearshift")
		       .initialProperties(sharedProperties::get)
		       .properties(BlockBehaviour.Properties::noOcclusion)
		       .properties(p -> p.mapColor(mapColor))
		       .addLayer(() -> RenderType::cutoutMipped)
		       .transform(BlockStressDefaults.setNoImpact())
		       .transform(TagGen.axeOrPickaxe())
		       .blockstate((c, p) -> BlockStateGen.axisBlock(c, p, $ -> AssetLookup.partialBaseModel(c, p)))
		       .item()
		       .transform(ModelGen.customItemModel())
		       .register();
	}
	
	public static void load() {}
}