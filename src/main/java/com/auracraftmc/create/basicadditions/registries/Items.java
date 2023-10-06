package com.auracraftmc.create.basicadditions.registries;

import com.auracraftmc.create.basicadditions.CreateBasicAdditionsMod;
import com.auracraftmc.create.basicadditions.items.BAVerticalGearboxItem;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.ItemEntry;

public class Items extends net.minecraft.world.item.Items {

	private static final CreateRegistrate REGISTRATE = CreateBasicAdditionsMod.REGISTRATE;
	
	public static final ItemEntry<BAVerticalGearboxItem> VERTICAL_BRASS_GEARBOX = REGISTRATE.item("vertical_brass_gearbox", (prop) -> new BAVerticalGearboxItem(prop, Blocks.BRASS_GEARBOX))
			.lang("Vertical Brass Gearbox")
			.model(AssetLookup.customBlockItemModel("brass_gearbox", "item_vertical"))
			.register();

	public static void load() {}
}