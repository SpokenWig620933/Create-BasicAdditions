package com.auracraftmc.create.basicadditions.tabs;

import javax.annotation.Nonnull;

import com.auracraftmc.create.basicadditions.CreateBasicAdditionsMod;
import com.auracraftmc.create.basicadditions.registries.Blocks;
import io.github.fabricators_of_create.porting_lib.util.ItemGroupUtil;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class CreateBasicAdditionsTab extends CreativeModeTab {

	public CreateBasicAdditionsTab() {
		super(ItemGroupUtil.expandArrayAndGetId(), CreateBasicAdditionsMod.MODID + ".main");
	}
	
	@Nonnull
	@Override
	public ItemStack makeIcon() {
		return new ItemStack(Blocks.BRASS_GEARBOX.get());
	}
}