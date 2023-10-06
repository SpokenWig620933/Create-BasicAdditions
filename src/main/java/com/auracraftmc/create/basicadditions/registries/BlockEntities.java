package com.auracraftmc.create.basicadditions.registries;

import com.auracraftmc.create.basicadditions.CreateBasicAdditionsMod;
import com.auracraftmc.create.basicadditions.blocks.tiles.BasicGearshiftBlockEntity;
import com.simibubi.create.content.kinetics.gearbox.*;
import com.simibubi.create.content.kinetics.transmission.*;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

public class BlockEntities {

	private static final CreateRegistrate REGISTRATE = CreateBasicAdditionsMod.REGISTRATE.creativeModeTab(() -> CreateBasicAdditionsMod.MAIN_TAB);
	
	public static final BlockEntityEntry<GearboxBlockEntity> BRASS_GEARBOX = REGISTRATE.blockEntity("brass_gearbox", GearboxBlockEntity::new)
			.instance(() -> GearboxInstance::new, false)
			.validBlocks(Blocks.BRASS_GEARBOX)
			.renderer(() -> GearboxRenderer::new)
			.register();
	
	public static final BlockEntityEntry<GearshiftBlockEntity> BRASS_GEARSHIFT = REGISTRATE.blockEntity("brass_gearshift", GearshiftBlockEntity::new)
	        .instance(() -> SplitShaftInstance::new, false)
	        .validBlocks(Blocks.BRASS_GEARSHIFT)
	        .renderer(() -> SplitShaftRenderer::new)
	        .register();
	
	public static final BlockEntityEntry<BasicGearshiftBlockEntity> BASIC_GEARSHIFT = REGISTRATE.blockEntity("basic_gearshift", BasicGearshiftBlockEntity::new)
			.instance(() -> SplitShaftInstance::new, false)
			.validBlocks(Blocks.BASIC_GEARSHIFT)
			.renderer(() -> SplitShaftRenderer::new)
			.register();
	
	
	public static final BlockEntityEntry<BasicGearshiftBlockEntity> BASIC_BRASS_GEARSHIFT = REGISTRATE.blockEntity("basic_brass_gearshift", BasicGearshiftBlockEntity::new)
	        .instance(() -> SplitShaftInstance::new, false)
	        .validBlocks(Blocks.BASIC_BRASS_GEARSHIFT)
	        .renderer(() -> SplitShaftRenderer::new)
	        .register();

	public static void load() {}
}