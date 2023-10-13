package com.auracraftmc.create.basicadditions.registries;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import javax.annotation.Nonnull;

import com.auracraftmc.create.basicadditions.configs.BAConfigBase;
import com.auracraftmc.create.basicadditions.configs.CommonConfig;
import com.simibubi.create.foundation.block.BlockStressValues;
import net.minecraftforge.api.ModLoadingContext;
import net.minecraftforge.api.fml.event.config.ModConfigEvent;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class Configs {
	
	private static final Map<ModConfig.Type, BAConfigBase> SPECs = new ConcurrentHashMap<>();
	
	public static final CommonConfig COMMON = create(CommonConfig::new, ModConfig.Type.COMMON);
	
	@Nonnull
	public static <T extends BAConfigBase> T create(@Nonnull Supplier<T> constructor, @Nonnull ModConfig.Type type) {
		Pair<T, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(builder -> {
			T config = constructor.get();
			config.registerAll(builder);
			
			return config;
		});
		
		T config = specPair.getLeft();
		config.specification = specPair.getRight();
		SPECs.put(type, config);
		
		return config;
	}
	
	public static void register(@Nonnull String modid) {
		for(Entry<ModConfig.Type, BAConfigBase> entry : SPECs.entrySet()) {
			ModLoadingContext.registerConfig(modid, entry.getKey(), entry.getValue().specification);
		}
		
		BlockStressValues.registerProvider(modid, COMMON.stressValues);
		
		ModConfigEvent.LOADING.register(Configs::onLoad);
		ModConfigEvent.RELOADING.register(Configs::onReload);
	}
	
	public static void onLoad(@Nonnull ModConfig modConfig) {
		for(BAConfigBase config : SPECs.values()) {
			if(config.specification == modConfig.getSpec()) config.onLoad();
		}
	}
	
	public static void onReload(@Nonnull ModConfig modConfig) {
		for(BAConfigBase config : SPECs.values()) {
			if(config.specification == modConfig.getSpec()) config.onReload();
		}
	}
}