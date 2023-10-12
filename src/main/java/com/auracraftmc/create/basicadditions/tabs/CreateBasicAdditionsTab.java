package com.auracraftmc.create.basicadditions.tabs;

import javax.annotation.Nonnull;

import com.auracraftmc.create.basicadditions.registries.Blocks;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class CreateBasicAdditionsTab extends CreativeModeTab {

	public CreateBasicAdditionsTab() {
		super("create.basic_additions.main");
	}
	
	@Nonnull
	@Override
	public ItemStack makeIcon() {
		return new ItemStack(Blocks.BRASS_GEARBOX.get());
	}
}