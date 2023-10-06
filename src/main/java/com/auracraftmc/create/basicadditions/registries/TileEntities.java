package com.auracraftmc.create.basicadditions.registries;

import com.auracraftmc.create.basicadditions.CreateBasicAdditionsMod;
import com.auracraftmc.create.basicadditions.blocks.tiles.BasicGearshiftTileEntity;
import com.simibubi.create.content.contraptions.relays.encased.SplitShaftInstance;
import com.simibubi.create.content.contraptions.relays.encased.SplitShaftRenderer;
import com.simibubi.create.content.contraptions.relays.gearbox.*;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

public class TileEntities {

	private static final CreateRegistrate REGISTRATE = CreateBasicAdditionsMod.REGISTRATE.creativeModeTab(() -> CreateBasicAdditionsMod.MAIN_TAB);
	
	public static final BlockEntityEntry<GearboxTileEntity> BRASS_GEARBOX = REGISTRATE.tileEntity("brass_gearbox", GearboxTileEntity::new)
			.instance(() -> GearboxInstance::new, false)
			.validBlocks(Blocks.BRASS_GEARBOX)
			.renderer(() -> GearboxRenderer::new)
			.register();
	
	public static final BlockEntityEntry<GearshiftTileEntity> BRASS_GEARSHIFT = REGISTRATE.tileEntity("brass_gearshift", GearshiftTileEntity::new)
	        .instance(() -> SplitShaftInstance::new, false)
	        .validBlocks(Blocks.BRASS_GEARSHIFT)
	        .renderer(() -> SplitShaftRenderer::new)
	        .register();
	
	public static final BlockEntityEntry<BasicGearshiftTileEntity> BASIC_GEARSHIFT = REGISTRATE.tileEntity("basic_gearshift", BasicGearshiftTileEntity::new)
			.instance(() -> SplitShaftInstance::new, false)
			.validBlocks(Blocks.BASIC_GEARSHIFT)
			.renderer(() -> SplitShaftRenderer::new)
			.register();
	
	
	public static final BlockEntityEntry<BasicGearshiftTileEntity> BASIC_BRASS_GEARSHIFT = REGISTRATE.tileEntity("basic_brass_gearshift", BasicGearshiftTileEntity::new)
	        .instance(() -> SplitShaftInstance::new, false)
	        .validBlocks(Blocks.BASIC_BRASS_GEARSHIFT)
	        .renderer(() -> SplitShaftRenderer::new)
	        .register();

	public static void load() {}
}