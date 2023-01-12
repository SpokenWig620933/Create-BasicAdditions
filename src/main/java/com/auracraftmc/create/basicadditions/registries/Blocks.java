package com.auracraftmc.create.basicadditions.registries;

import com.auracraftmc.create.basicadditions.CreateBasicAdditionsMod;
import com.auracraftmc.create.basicadditions.blocks.*;

import com.simibubi.create.AllSpriteShifts;
import com.simibubi.create.content.contraptions.relays.encased.EncasedCTBehaviour;
import com.simibubi.create.content.contraptions.relays.gearbox.GearboxBlock;
import com.simibubi.create.foundation.block.BlockStressDefaults;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.ModelGen;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.foundation.data.TagGen;
import com.tterrag.registrate.util.entry.BlockEntry;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MaterialColor;

public class Blocks extends net.minecraft.world.level.block.Blocks {
	
	private static final CreateRegistrate REGISTRATE = CreateBasicAdditionsMod.REGISTRATE;
	
	public static final BlockEntry<BrassGearboxBlock> BRASS_GEARBOX = REGISTRATE.block("brass_gearbox", BrassGearboxBlock::new)
			.lang("Brass Gearbox")
			.initialProperties(SharedProperties::softMetal)
			.properties(BlockBehaviour.Properties::noOcclusion)
			.properties(p -> p.color(MaterialColor.TERRACOTTA_BROWN))
			.transform(BlockStressDefaults.setNoImpact())
			.transform(TagGen.axeOrPickaxe())
			.onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCTBehaviour(AllSpriteShifts.BRASS_CASING)))
			.onRegister(CreateRegistrate.casingConnectivity((block, cc) -> cc.make(block, AllSpriteShifts.BRASS_CASING,
				(s, f) -> f.getAxis() == s.getValue(GearboxBlock.AXIS))))
			.blockstate((c, p) -> BlockStateGen.axisBlock(c, p, $ -> AssetLookup.partialBaseModel(c, p), true))
			.item()
			.transform(ModelGen.customItemModel())
			.register();
	
	public static final BlockEntry<BasicGearshiftBlock> BASIC_GEARSHIFT = REGISTRATE.block("basic_gearshift", BasicGearshiftBlock::new)
			.lang("Basic Gearshift")
			.initialProperties(SharedProperties::stone)
			.properties(BlockBehaviour.Properties::noOcclusion)
			.properties(p -> p.color(MaterialColor.PODZOL))
			.transform(BlockStressDefaults.setNoImpact())
			.transform(TagGen.axeOrPickaxe())
			.blockstate((c, p) -> BlockStateGen.axisBlock(c, p, $ -> AssetLookup.partialBaseModel(c, p)))
			.item()
			.transform(ModelGen.customItemModel())
			.register();
	
	public static void load() {}
}