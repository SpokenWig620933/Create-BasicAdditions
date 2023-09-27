package com.auracraftmc.create.basicadditions.tabs;

import javax.annotation.Nonnull;

import com.auracraftmc.create.basicadditions.CreateBasicAdditionsMod;
import com.auracraftmc.create.basicadditions.registries.Blocks;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class CreateBasicAdditionsTab extends CreativeModeTab {

	public CreateBasicAdditionsTab() {
		super(CreateBasicAdditionsMod.MODID + ".main");
	}
	
	@Nonnull
	@Override
	public ItemStack makeIcon() {
		return new ItemStack(Blocks.BRASS_GEARBOX.get());
	}
}