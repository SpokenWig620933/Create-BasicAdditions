package com.auracraftmc.create.basicadditions.tabs;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import javax.annotation.Nonnull;

import com.auracraftmc.create.basicadditions.CreateBasicAdditionsMod;
import com.mojang.datafixers.util.Pair;
import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class TabDisplayGenerator implements DisplayItemsGenerator {
	
	private final AbstractRegistrate<?> REGISTRATE;
	
	private final Map<ItemOrder, List<Pair<Supplier<Item>, Supplier<Item>>>> orderPairMap = new ConcurrentHashMap<>(Map.of(ItemOrder.BEFORE, new ArrayList<>(), ItemOrder.AFTER, new ArrayList<>()));
	private final List<Supplier<Item>> exclude = new ArrayList<>();
	private final List<Supplier<Item>> forceInclude = new ArrayList<>();
	
	public TabDisplayGenerator(@Nonnull AbstractRegistrate<?> registrate) {
		this.REGISTRATE = registrate;
	}
	
	@Override
	public void accept(@Nonnull ItemDisplayParameters parameters, @Nonnull Output output) {
		List<Item> exclude = this.exclude.stream().map(Supplier::get).toList();
		List<Item> forceInclude = this.forceInclude.stream().map(Supplier::get).toList();
		
		for(Item item : exclude) {
			CreateBasicAdditionsMod.logger.warn("Excluding Item from Tab: " + item.getDescriptionId());
		}
		for(Item item : forceInclude) {
			CreateBasicAdditionsMod.logger.warn("ForceIncluding Item from Tab: " + item.getDescriptionId());
		}
		
		List<Item> items = new ArrayList<>();
		
		items.addAll(getItems(Registries.ITEM, exclude, forceInclude));
		//items.addAll(getItems(Registries.BLOCK, exclude, forceInclude));
		
		for(ItemOrder order : ItemOrder.values()) {
			for(Pair<Supplier<Item>, Supplier<Item>> pair : this.orderPairMap.get(order)) {
				Item anchor = pair.getFirst().get();
				Item toSort = pair.getSecond().get();
				
				CreateBasicAdditionsMod.logger.warn("Attempting to order Items: " + toSort + " " + order.name() + " " + anchor);
				
				if(items.contains(anchor)) {
					items.remove(toSort);
					
					items.add(items.indexOf(anchor) + (order == ItemOrder.BEFORE ? 0 : 1), toSort);
					
					CreateBasicAdditionsMod.logger.warn("Ordered Items: " + toSort + " " + order.name() + " " + anchor);
				}
			}
		}
		
		List<ItemStack> stacks = items.stream().map(ItemStack::new).filter(stack -> stack.getCount() == 1 && (forceInclude.contains(stack.getItem()) || !exclude.contains(stack.getItem()))).toList();
		stacks.forEach(s -> CreateBasicAdditionsMod.logger.warn("Added Stack to Tab: " + s.getDescriptionId() + "|" + s.getCount()));
		
		output.acceptAll(stacks);
	}
	
	protected <R extends ItemLike> List<Item> getItems(@Nonnull ResourceKey<? extends Registry<R>> registry, @Nonnull List<Item> exclude, @Nonnull List<Item> forceInclude) {
		List<Item> items = new ArrayList<>();
		
		for(RegistryEntry<R> entry : REGISTRATE.getAll(registry)) {
			Item item = entry.get().asItem();
			
			if(!forceInclude.contains(item) && exclude.contains(item)) continue;
			
			CreateBasicAdditionsMod.logger.warn("Added Item to Main Tab: " + item.getDescriptionId());
			items.add(item);
		}
		
		return items;
	}
	
	public TabDisplayGenerator order(@Nonnull ItemOrder order, @Nonnull ItemProviderEntry<?> anchor, @Nonnull ItemProviderEntry<?> toSort) {
		this.orderPairMap.get(order).add(new Pair<>(anchor::asItem, toSort::asItem));
		
		return this;
	}
	
	public TabDisplayGenerator exclude(@Nonnull ItemProviderEntry<?>... entries) {
		for(ItemProviderEntry<?> entry : entries) {
			this.exclude.add(entry::asItem);
		}
		
		return this;
	}
	
	public TabDisplayGenerator forceInclude(@Nonnull ItemProviderEntry<?>... entries) {
		for(ItemProviderEntry<?> entry : entries) {
			this.forceInclude.add(entry::asItem);
		}
		
		return this;
	}
	
	public enum ItemOrder {
		BEFORE,
		AFTER
	}
}