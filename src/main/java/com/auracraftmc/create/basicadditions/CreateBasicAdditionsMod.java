package com.auracraftmc.create.basicadditions;

import java.util.List;
import javax.annotation.Nonnull;

import com.auracraftmc.create.basicadditions.recipes.StandardRecipeProvider;
import com.auracraftmc.create.basicadditions.registries.*;
import com.mojang.datafixers.util.Pair;
import com.mojang.logging.LogUtils;
import com.simibubi.create.foundation.ModFilePackResources;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.utility.Components;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forgespi.language.IModFileInfo;
import net.minecraftforge.forgespi.locating.IModFile;
import org.slf4j.Logger;

@Mod(CreateBasicAdditionsMod.MODID)
public class CreateBasicAdditionsMod {

	public static final String MODID = "create_basic_additions";
	public static final String NAME = "Create: Basic Additions";
	public static final String VERSION = "1.0.1";

	public static CreateBasicAdditionsMod instance;
	public static final Logger logger = LogUtils.getLogger();
	
	public static CreateRegistrate REGISTRATE = CreateRegistrate.create(MODID);
	
	public CreateBasicAdditionsMod() {
		instance = this;
		
		ModLoadingContext modLoadingContext = ModLoadingContext.get();
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
		
		REGISTRATE.registerEventListeners(modEventBus);
		
		Blocks.load();
		Items.load();
		BlockEntities.load();
		
		CreativeModeTabs.register(modEventBus);
		Configs.register(modLoadingContext);
		
		modEventBus.addListener(EventPriority.LOWEST, this::gatherData);
		modEventBus.addListener(this::resourcepacks);
		
		//DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> CreateBasicAdditionsClient.init(modEventBus, forgeEventBus));
	}

	public void gatherData(@Nonnull GatherDataEvent event) {
		DataGenerator gen = event.getGenerator();
		PackOutput output = gen.getPackOutput();

		if(event.includeServer()) gen.addProvider(true, new StandardRecipeProvider(output));
	}
	
	public void resourcepacks(@Nonnull AddPackFindersEvent event) {
		if(event.getPackType() == PackType.CLIENT_RESOURCES) {
			IModFileInfo modFileInfo = ModList.get().getModFileById(MODID);
			
			if(modFileInfo == null) {
				logger.error("Could not find mod file info; built-in resource packs will be missing!");
				
				return;
			}
			
			IModFile modFile = modFileInfo.getFile();
			List<Pair<ResourceLocation, String>> resourcepacks = List.of(
				new Pair<>(new ResourceLocation(MODID, "alternate_brass"), "Alternate Brass Textures"),
				new Pair<>(new ResourceLocation(MODID, "alternate_copper"), "Alternate Copper Textures"),
				new Pair<>(new ResourceLocation(MODID, "alternate_railway"), "Alternate Train Textures")
			);
			
			for(Pair<ResourceLocation, String> resourcepack : resourcepacks) {
				event.addRepositorySource(consumer -> {
					Pack pack = Pack.readMetaAndCreate(resourcepack.getFirst().toString(), Components.literal(resourcepack.getSecond()), false, id -> new ModFilePackResources(id, modFile, "resourcepacks/" + resourcepack.getFirst().getPath()), PackType.CLIENT_RESOURCES, Pack.Position.TOP, PackSource.BUILT_IN);
					
					if(pack != null) consumer.accept(pack);
				});
			}
		}
	}
}