package com.auracraftmc.create.basicadditions.tabs;

import com.auracraftmc.create.basicadditions.CreateBasicAdditionsMod;
import com.auracraftmc.create.basicadditions.registries.Blocks;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class CreateBasicAdditionsTab extends CreativeModeTab {

	public CreateBasicAdditionsTab() {
		super("create.basicadditions.main");
	}

	@Override
	public Component getDisplayName() {
		return new TextComponent(CreateBasicAdditionsMod.NAME);
	}
	
	@Override
	public ItemStack makeIcon() {
		return new ItemStack(Blocks.BRASS_GEARBOX.get());
	}
}
