package com.auracraftmc.create.basicadditions.registries;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import javax.annotation.Nonnull;

import com.auracraftmc.create.basicadditions.configs.BAConfigBase;
import com.auracraftmc.create.basicadditions.configs.CommonConfig;
import com.simibubi.create.foundation.block.BlockStressValues;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import org.apache.commons.lang3.tuple.Pair;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
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
	
	public static void register(@Nonnull ModLoadingContext ctx) {
		for(Entry<ModConfig.Type, BAConfigBase> entry : SPECs.entrySet()) {
			ctx.registerConfig(entry.getKey(), entry.getValue().specification);
		}
		
		BlockStressValues.registerProvider(ctx.getActiveNamespace(), COMMON.stressValues);
	}
	
	@SubscribeEvent
	public static void onLoad(@Nonnull ModConfigEvent.Loading event) {
		for(BAConfigBase config : SPECs.values()) {
			if(config.specification == event.getConfig().getSpec()) config.onLoad();
		}
	}
	
	@SubscribeEvent
	public static void onReload(@Nonnull ModConfigEvent.Reloading event){
		for(BAConfigBase config : SPECs.values()) {
			if(config.specification == event.getConfig().getSpec()) config.onReload();
		}
	}
}