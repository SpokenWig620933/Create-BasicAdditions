package com.auracraftmc.create.basicadditions.registries;

import java.util.function.Consumer;
import javax.annotation.Nonnull;

import com.auracraftmc.create.basicadditions.CreateBasicAdditionsMod;
import com.auracraftmc.create.basicadditions.tabs.TabDisplayGenerator;
import com.auracraftmc.create.basicadditions.tabs.TabDisplayGenerator.ItemOrder;
import com.simibubi.create.AllCreativeModeTabs;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeModeTabs extends net.minecraft.world.item.CreativeModeTabs {
	
	private static final CreateRegistrate REGISTRATE = CreateBasicAdditionsMod.REGISTRATE;
	private static final DeferredRegister<CreativeModeTab> TAB_REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreateBasicAdditionsMod.MODID);
	
	public static final RegistryObject<CreativeModeTab> MAIN = create(REGISTRATE, TAB_REGISTRY, "main", CreateBasicAdditionsMod.NAME, Blocks.BRASS_GEARBOX, builder -> builder.withTabsBefore(AllCreativeModeTabs.PALETTES_CREATIVE_TAB.getKey()).displayItems(new TabDisplayGenerator(REGISTRATE).order(ItemOrder.AFTER, Blocks.BRASS_GEARBOX, Items.VERTICAL_BRASS_GEARBOX).order(ItemOrder.AFTER, Blocks.COPPER_GEARBOX, Items.VERTICAL_COPPER_GEARBOX).order(ItemOrder.AFTER, Blocks.RAILWAY_GEARBOX, Items.VERTICAL_RAILWAY_GEARBOX).exclude(Blocks.COPPER_ENCASED_SHAFT, Blocks.COPPER_ENCASED_COGWHEEL, Blocks.COPPER_ENCASED_LARGE_COGWHEEL, Blocks.RAILWAY_ENCASED_SHAFT, Blocks.RAILWAY_ENCASED_COGWHEEL, Blocks.RAILWAY_ENCASED_LARGE_COGWHEEL)));
	
	protected static RegistryObject<CreativeModeTab> create(@Nonnull AbstractRegistrate<?> registrate, @Nonnull DeferredRegister<CreativeModeTab> registry, @Nonnull String id, @Nonnull String title, @Nonnull ItemProviderEntry<?> icon, @Nonnull Consumer<CreativeModeTab.Builder> configure) {
		return registry.register(id, () -> {
			CreativeModeTab.Builder builder = CreativeModeTab.builder()
				.title(registrate.addLang("itemGroup", new ResourceLocation(registrate.getModid(), id), title))
				.icon(icon::asStack);
			
			configure.accept(builder);
			
			return builder.build();
		});
	}
	
	public static void register(@Nonnull IEventBus modEventBus) {
		TAB_REGISTRY.register(modEventBus);
	}
}