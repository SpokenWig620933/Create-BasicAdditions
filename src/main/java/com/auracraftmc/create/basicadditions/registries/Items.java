package com.auracraftmc.create.basicadditions.registries;

import java.util.function.Supplier;
import javax.annotation.Nonnull;

import com.auracraftmc.create.basicadditions.CreateBasicAdditionsMod;
import com.auracraftmc.create.basicadditions.items.BAVerticalGearboxItem;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.level.block.Block;

public class Items extends net.minecraft.world.item.Items {

	private static final CreateRegistrate REGISTRATE = CreateBasicAdditionsMod.REGISTRATE;
	
	public static final ItemEntry<BAVerticalGearboxItem> VERTICAL_BRASS_GEARBOX = createVerticalGearbox("brass", "Brass", () -> Blocks.BRASS_GEARBOX);
	public static final ItemEntry<BAVerticalGearboxItem> VERTICAL_COPPER_GEARBOX = createVerticalGearbox("copper", "Copper", () -> Blocks.COPPER_GEARBOX);
	public static final ItemEntry<BAVerticalGearboxItem> VERTICAL_RAILWAY_GEARBOX = createVerticalGearbox("railway", "Train", () -> Blocks.RAILWAY_GEARBOX);
	
	public static ItemEntry<BAVerticalGearboxItem> createVerticalGearbox(@Nonnull String id, @Nonnull String title, @Nonnull Supplier<? extends Supplier<? extends Block>> gearboxBlock) {
		return REGISTRATE.item("vertical_" + id + "_gearbox", (prop) -> new BAVerticalGearboxItem(prop, gearboxBlock.get()))
		       .lang("Vertical " + title + " Gearbox")
		       .model(AssetLookup.customBlockItemModel(id + "_gearbox", "item_vertical"))
		       .register();
	}
	
	public static void load() {}
}