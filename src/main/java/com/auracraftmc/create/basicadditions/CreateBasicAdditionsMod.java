package com.auracraftmc.create.basicadditions;

import javax.annotation.Nonnull;

import com.auracraftmc.create.basicadditions.recipes.StandardRecipeProvider;
import com.auracraftmc.create.basicadditions.registries.*;
import com.auracraftmc.create.basicadditions.tabs.CreateBasicAdditionsTab;
import com.simibubi.create.foundation.data.CreateRegistrate;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.world.item.CreativeModeTab;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateBasicAdditionsMod implements ModInitializer {
	
	public static final String MODID = "create_basic_additions";
	public static final String NAME = "Create: Basic Additions";
	public static final String VERSION = "1.1.0";
	
	public static CreateBasicAdditionsMod instance;
	public static final Logger logger = LoggerFactory.getLogger(NAME);
	
	public static final CreativeModeTab MAIN_TAB = new CreateBasicAdditionsTab();
	
	public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MODID).creativeModeTab(() -> MAIN_TAB, NAME);
	
	@Override
	public void onInitialize() {
		instance = this;
		
		Blocks.load();
		Items.load();
		BlockEntities.load();
		
		REGISTRATE.register();
	}

	public static void gatherData(@Nonnull FabricDataGenerator dataGenerator) {
		dataGenerator.addProvider(new StandardRecipeProvider(dataGenerator));
	}
}