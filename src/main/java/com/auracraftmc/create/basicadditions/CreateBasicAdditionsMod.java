package com.auracraftmc.create.basicadditions;

import com.auracraftmc.create.basicadditions.registries.Blocks;
import com.auracraftmc.create.basicadditions.registries.Items;
import com.auracraftmc.create.basicadditions.registries.TileEntities;
import com.auracraftmc.create.basicadditions.tabs.CreateBasicAdditionsTab;
import com.mojang.logging.LogUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;

import io.netty.handler.codec.http2.Http2FrameReader.Configuration;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.slf4j.Logger;

@Mod(CreateBasicAdditionsMod.MODID)
public class CreateBasicAdditionsMod {

	public static final String MODID = "create_basic_additions";
	public static final String NAME = "Create: Basic Additions";
	public static final String VERSION = "1.0.0";

	//public static final ModInfo MODINFO = new ModInfo(MODID, NAME, VERSION);

	public static CreateBasicAdditionsMod instance;
	public static final Logger logger = LogUtils.getLogger();
	public static Configuration config;

	public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MODID);
	
	public static final CreativeModeTab MAIN_TAB = new CreateBasicAdditionsTab();
	
	public CreateBasicAdditionsMod() {
		instance = this;

		REGISTRATE.registerEventListeners(FMLJavaModLoadingContext.get().getModEventBus());
		
		Blocks.load();
		Items.load();
		TileEntities.load();
	}
}
