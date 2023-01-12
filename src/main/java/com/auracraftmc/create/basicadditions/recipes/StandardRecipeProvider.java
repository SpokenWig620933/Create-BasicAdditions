package com.auracraftmc.create.basicadditions.recipes;

import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.auracraftmc.create.basicadditions.CreateBasicAdditionsMod;
import com.auracraftmc.create.basicadditions.registries.Blocks;
import com.auracraftmc.create.basicadditions.registries.Items;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.foundation.utility.RegisteredObjects;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

public class StandardRecipeProvider extends RecipeProvider {

	public StandardRecipeProvider(DataGenerator gen) {
		super(gen);
	}
	
    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
    	generateShaped(consumer);
    	generateShapeless(consumer);
    	generateConversions(consumer);
    }
    
    private void generateShaped(Consumer<FinishedRecipe> consumer) {
    	ShapedRecipeBuilder.shaped(Blocks.BRASS_GEARBOX.get())
    			.unlockedBy("has_item", inventoryTrigger(ItemPredicate.Builder.item().of(AllBlocks.COGWHEEL.get()).build()))
    			.pattern(" c ")
    			.pattern("cbc")
    			.pattern(" c ")
    			.define('c', AllBlocks.COGWHEEL.get())
    			.define('b', AllBlocks.BRASS_CASING.get())
    			.save(consumer);
    }
    
    private void generateShapeless(Consumer<FinishedRecipe> consumer) {
    	ShapelessRecipeBuilder.shapeless(Blocks.BASIC_GEARSHIFT.get())
				.unlockedBy("has_item", inventoryTrigger(ItemPredicate.Builder.item().of(AllBlocks.ANDESITE_CASING.get()).build()))
    			.requires(AllBlocks.ANDESITE_CASING.get())
    			.requires(AllBlocks.COGWHEEL.get())
    			.save(consumer);
    	ShapelessRecipeBuilder.shapeless(AllBlocks.GEARSHIFT.get())
    			.unlockedBy("has_item", inventoryTrigger(ItemPredicate.Builder.item().of(Blocks.BASIC_GEARSHIFT.get()).build()))
    			.requires(Blocks.BASIC_GEARSHIFT.get())
    			.requires(Items.REDSTONE)
    			.save(consumer);
    }
    
    private void generateConversions(Consumer<FinishedRecipe> consumer) {
    	ShapelessRecipeBuilder.shapeless(Blocks.BRASS_GEARBOX.get())
				.unlockedBy("has_item", inventoryTrigger(ItemPredicate.Builder.item().of(Items.VERTICAL_BRASS_GEARBOX.get()).build()))
    			.requires(Items.VERTICAL_BRASS_GEARBOX.get())
    			.save(consumer, recipeId("workbench", Blocks.BRASS_GEARBOX.get(), "conversion"));
    	
    	ShapelessRecipeBuilder.shapeless(Items.VERTICAL_BRASS_GEARBOX.get())
				.unlockedBy("has_item", inventoryTrigger(ItemPredicate.Builder.item().of(Blocks.BRASS_GEARBOX.get()).build()))
    			.requires(Blocks.BRASS_GEARBOX.get())
    			.save(consumer, recipeId("workbench", Items.VERTICAL_BRASS_GEARBOX.get(), "conversion"));
    }
    
    private ResourceLocation recipeId(@Nonnull String recipeType, @Nonnull ItemLike result) {
    	return recipeId(recipeType, result, null);
    }
    
    private ResourceLocation recipeId(@Nonnull String recipeType, @Nonnull ItemLike result, @Nullable String suffix) {
    	ResourceLocation id = result.asItem().getRegistryName();
    	
    	if(id == null) throw new IllegalArgumentException("Could not get key for item " + result.asItem() + "!");
    	
    	return new ResourceLocation(CreateBasicAdditionsMod.MODID, recipeType + "/" + id.getPath() + (suffix != null ? "_" + suffix : ""));
    }
}