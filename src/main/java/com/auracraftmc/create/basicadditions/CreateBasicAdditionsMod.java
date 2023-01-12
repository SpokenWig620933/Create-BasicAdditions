package com.auracraftmc.create.basicadditions;

import com.auracraftmc.create.basicadditions.recipes.*;
import com.auracraftmc.create.basicadditions.registries.*;
import com.auracraftmc.create.basicadditions.tabs.*;
import com.mojang.logging.LogUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

import org.slf4j.Logger;

@Mod(CreateBasicAdditionsMod.MODID)
public class CreateBasicAdditionsMod {

	public static final String MODID = "create_basic_additions";
	public static final String NAME = "Create: Basic Additions";
	public static final String VERSION = "1.0.0";

	public static CreateBasicAdditionsMod instance;
	public static final Logger logger = LogUtils.getLogger();
	
	public static final CreativeModeTab MAIN_TAB = new CreateBasicAdditionsTab();

	public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MODID).creativeModeTab(() -> MAIN_TAB, NAME);
	
	
	public CreateBasicAdditionsMod() {
		instance = this;
		
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		REGISTRATE.registerEventListeners(modEventBus);
		
		Blocks.load();
		Items.load();
		TileEntities.load();
		
		modEventBus.addListener(EventPriority.LOWEST, this::gatherData);
	}

	public void gatherData(GatherDataEvent event) {
		DataGenerator gen = event.getGenerator();

		if(event.includeServer()) {
			gen.addProvider(new StandardRecipeProvider(gen));
		}
	}
}
