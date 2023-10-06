package com.auracraftmc.create.basicadditions.registries;

import com.auracraftmc.create.basicadditions.CreateBasicAdditionsMod;
import com.auracraftmc.create.basicadditions.blocks.*;
import com.simibubi.create.AllSpriteShifts;
import com.simibubi.create.content.decoration.encasing.EncasedCTBehaviour;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.content.kinetics.gearbox.GearboxBlock;
import com.simibubi.create.foundation.data.*;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.material.MaterialColor;

public class Blocks extends net.minecraft.world.level.block.Blocks {
	
	private static final CreateRegistrate REGISTRATE = CreateBasicAdditionsMod.REGISTRATE;
	
	public static final BlockEntry<BAGearboxBlock> BRASS_GEARBOX = REGISTRATE.block("brass_gearbox", (prop) -> new BAGearboxBlock(prop, Items.VERTICAL_BRASS_GEARBOX, BlockEntities.BRASS_GEARBOX))
			.lang("Brass Gearbox")
			.initialProperties(SharedProperties::softMetal)
			.properties(p -> p.noOcclusion().color(MaterialColor.TERRACOTTA_BROWN))
			.transform(BlockStressDefaults.setNoImpact())
			.transform(TagGen.axeOrPickaxe())
			.onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCTBehaviour(AllSpriteShifts.BRASS_CASING)))
			.onRegister(CreateRegistrate.casingConnectivity((block, cc) -> cc.make(block, AllSpriteShifts.BRASS_CASING, (s, f) -> f.getAxis() == s.getValue(GearboxBlock.AXIS))))
			.blockstate((c, p) -> BlockStateGen.axisBlock(c, p, $ -> AssetLookup.partialBaseModel(c, p), true))
			.item()
			.transform(ModelGen.customItemModel())
			.register();
	
	public static final BlockEntry<BAGearshiftBlock> BRASS_GEARSHIFT = REGISTRATE.block("brass_gearshift", (prop) -> new BAGearshiftBlock(prop, BlockEntities.BRASS_GEARSHIFT))
	        .lang("Brass Gearshift")
	        .initialProperties(SharedProperties::softMetal)
	        .properties(p -> p.noOcclusion().color(MaterialColor.TERRACOTTA_BROWN))
	        .addLayer(() -> RenderType::cutoutMipped)
	        .transform(BlockStressDefaults.setNoImpact())
	        .transform(TagGen.axeOrPickaxe())
	        .blockstate((c, p) -> BlockStateGen.axisBlock(c, p, AssetLookup.forPowered(c, p)))
	        .item()
	        .transform(ModelGen.customItemModel())
	        .register();
	
	public static final BlockEntry<BasicGearshiftBlock> BASIC_GEARSHIFT = REGISTRATE.block("basic_gearshift", (prop) -> new BasicGearshiftBlock(prop, BlockEntities.BASIC_GEARSHIFT::get))
			.lang("Basic Gearshift")
			.initialProperties(SharedProperties::stone)
	        .properties(p -> p.noOcclusion().color(MaterialColor.PODZOL))
	        .addLayer(() -> RenderType::cutoutMipped)
			.transform(BlockStressDefaults.setNoImpact())
			.transform(TagGen.axeOrPickaxe())
			.blockstate((c, p) -> BlockStateGen.axisBlock(c, p, $ -> AssetLookup.partialBaseModel(c, p)))
			.item()
			.transform(ModelGen.customItemModel())
			.register();
	
	public static final BlockEntry<BasicGearshiftBlock> BASIC_BRASS_GEARSHIFT = REGISTRATE.block("basic_brass_gearshift", (prop) -> new BasicGearshiftBlock(prop, BlockEntities.BASIC_BRASS_GEARSHIFT::get))
	        .lang("Basic Brass Gearshift")
	        .initialProperties(SharedProperties::softMetal)
	        .properties(p -> p.noOcclusion().color(MaterialColor.TERRACOTTA_BROWN))
	        .addLayer(() -> RenderType::cutoutMipped)
	        .transform(BlockStressDefaults.setNoImpact())
	        .transform(TagGen.axeOrPickaxe())
	        .blockstate((c, p) -> BlockStateGen.axisBlock(c, p, $ -> AssetLookup.partialBaseModel(c, p)))
	        .item()
	        .transform(ModelGen.customItemModel())
	        .register();
	
	public static void load() {}
}