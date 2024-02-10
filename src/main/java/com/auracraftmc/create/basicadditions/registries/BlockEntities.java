package com.auracraftmc.create.basicadditions.registries;

import com.auracraftmc.create.basicadditions.CreateBasicAdditionsMod;
import com.auracraftmc.create.basicadditions.blocks.*;
import com.auracraftmc.create.basicadditions.blocks.tiles.BAGearshiftBlockEntity;
import com.simibubi.create.content.kinetics.base.*;
import com.simibubi.create.content.kinetics.gearbox.*;
import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogInstance;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogRenderer;
import com.simibubi.create.content.kinetics.transmission.SplitShaftInstance;
import com.simibubi.create.content.kinetics.transmission.SplitShaftRenderer;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

public class BlockEntities {

	private static final CreateRegistrate REGISTRATE = CreateBasicAdditionsMod.REGISTRATE;
	
	public static final BlockEntityEntry<KineticBlockEntity> ENCASED_SHAFT = REGISTRATE.blockEntity("encased_shaft", KineticBlockEntity::new)
	        .instance(() -> ShaftInstance::new, false)
	        .validBlocksDeferred(BAEncasedShaftBlock.getEncasedBlocks())
	        .renderer(() -> ShaftRenderer::new)
	        .register();
	
	public static final BlockEntityEntry<SimpleKineticBlockEntity> ENCASED_COGWHEEL = REGISTRATE.blockEntity("encased_cogwheel", SimpleKineticBlockEntity::new)
	        .instance(() -> EncasedCogInstance::small, false)
	        .validBlocksDeferred(BAEncasedCogwheelBlock.getEncasedBlocks(false))
	        .renderer(() -> EncasedCogRenderer::small)
	        .register();
	
	public static final BlockEntityEntry<SimpleKineticBlockEntity> ENCASED_LARGE_COGWHEEL = REGISTRATE.blockEntity("encased_large_cogwheel", SimpleKineticBlockEntity::new)
	        .instance(() -> EncasedCogInstance::large, false)
	        .validBlocksDeferred(BAEncasedCogwheelBlock.getEncasedBlocks(true))
	        .renderer(() -> EncasedCogRenderer::large)
	        .register();
	
	public static final BlockEntityEntry<GearboxBlockEntity> GEARBOX = REGISTRATE.blockEntity("gearbox", GearboxBlockEntity::new)
			.instance(() -> GearboxInstance::new, false)
			.validBlocksDeferred(BAGearboxBlock.getEncasedBlocks())
			.renderer(() -> GearboxRenderer::new)
			.register();
	
	public static final BlockEntityEntry<BAGearshiftBlockEntity> GEARSHIFT = REGISTRATE.blockEntity("gearshift", BAGearshiftBlockEntity::new)
	        .instance(() -> SplitShaftInstance::new, false)
	        .validBlocksDeferred(BAGearshiftBlock.getEncasedBlocks())
	        .renderer(() -> SplitShaftRenderer::new)
	        .register();

	public static void load() {}
}