package com.auracraftmc.create.basicadditions.recipes;

import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.auracraftmc.create.basicadditions.CreateBasicAdditionsMod;
import com.auracraftmc.create.basicadditions.registries.Blocks;
import com.auracraftmc.create.basicadditions.registries.Items;
import com.simibubi.create.AllBlocks;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

public class StandardRecipeProvider extends RecipeProvider {

	public StandardRecipeProvider(@Nonnull DataGenerator gen) {
		super(gen);
	}

    @Override
    protected void buildCraftingRecipes(@Nonnull Consumer<FinishedRecipe> consumer) {
    	generateShaped(consumer);
    	generateShapeless(consumer);
    }

    private void generateShaped(@Nonnull Consumer<FinishedRecipe> consumer) {
    	ShapedRecipeBuilder.shaped(Blocks.BRASS_GEARBOX.get())
    			.unlockedBy("has_item", inventoryTrigger(ItemPredicate.Builder.item().of(AllBlocks.COGWHEEL.get()).build()))
    			.pattern(" c ")
    			.pattern("cbc")
    			.pattern(" c ")
    			.define('c', AllBlocks.COGWHEEL.get())
    			.define('b', AllBlocks.BRASS_CASING.get())
    			.save(consumer, recipeId("workbench", Blocks.BRASS_GEARBOX.get()));
    }

    private void generateShapeless(@Nonnull Consumer<FinishedRecipe> consumer) {
		ShapelessRecipeBuilder.shapeless(Blocks.BRASS_GEARSHIFT.get())
		        .unlockedBy("has_item", inventoryTrigger(ItemPredicate.Builder.item().of(AllBlocks.BRASS_CASING.get()).build()))
		        .requires(AllBlocks.BRASS_CASING.get())
		        .requires(AllBlocks.COGWHEEL.get())
				.requires(Blocks.REDSTONE_WIRE)
		        .save(consumer,recipeId("workbench", Blocks.BRASS_GEARSHIFT.get()));
		
    	ShapelessRecipeBuilder.shapeless(Blocks.BASIC_GEARSHIFT.get())
				.unlockedBy("has_item", inventoryTrigger(ItemPredicate.Builder.item().of(AllBlocks.ANDESITE_CASING.get()).build()))
    			.requires(AllBlocks.ANDESITE_CASING.get())
    			.requires(AllBlocks.COGWHEEL.get())
    			.save(consumer, recipeId("workbench", Blocks.BASIC_GEARSHIFT.get()));
	    
	    ShapelessRecipeBuilder.shapeless(Blocks.BASIC_BRASS_GEARSHIFT.get())
	            .unlockedBy("has_item", inventoryTrigger(ItemPredicate.Builder.item().of(AllBlocks.BRASS_CASING.get()).build()))
	            .requires(AllBlocks.BRASS_CASING.get())
	            .requires(AllBlocks.COGWHEEL.get())
	            .save(consumer, recipeId("workbench", Blocks.BASIC_BRASS_GEARSHIFT.get()));
		
    	ShapelessRecipeBuilder.shapeless(AllBlocks.GEARSHIFT.get())
    			.unlockedBy("has_item", inventoryTrigger(ItemPredicate.Builder.item().of(Blocks.BASIC_GEARSHIFT.get()).build(), ItemPredicate.Builder.item().of(AllBlocks.GEARSHIFT.get()).build()))
    			.requires(Blocks.BASIC_GEARSHIFT.get())
    			.requires(Items.REDSTONE)
				.save(consumer, recipeId("workbench", AllBlocks.GEARSHIFT.get(), "powered"));
		
	    ShapelessRecipeBuilder.shapeless(Blocks.BRASS_GEARSHIFT.get())
	            .unlockedBy("has_item", inventoryTrigger(ItemPredicate.Builder.item().of(Blocks.BASIC_BRASS_GEARSHIFT.get()).build(), ItemPredicate.Builder.item().of(Blocks.BRASS_GEARSHIFT.get()).build()))
	            .requires(Blocks.BASIC_BRASS_GEARSHIFT.get())
	            .requires(Items.REDSTONE)
	            .save(consumer, recipeId("workbench", Blocks.BRASS_GEARSHIFT.get(), "powered"));
		
    	ShapelessRecipeBuilder.shapeless(Blocks.BRASS_GEARBOX.get())
				.unlockedBy("has_item", inventoryTrigger(ItemPredicate.Builder.item().of(Items.VERTICAL_BRASS_GEARBOX.get()).build()))
    			.requires(Items.VERTICAL_BRASS_GEARBOX.get())
    			.save(consumer, recipeId("workbench", Blocks.BRASS_GEARBOX.get(), "conversion"));

    	ShapelessRecipeBuilder.shapeless(Items.VERTICAL_BRASS_GEARBOX.get())
				.unlockedBy("has_item", inventoryTrigger(ItemPredicate.Builder.item().of(Blocks.BRASS_GEARBOX.get()).build()))
    			.requires(Blocks.BRASS_GEARBOX.get())
    			.save(consumer, recipeId("workbench", Items.VERTICAL_BRASS_GEARBOX.get(), "conversion"));
    }
	
	@Nonnull
	private ResourceLocation recipeId(@Nonnull String recipeType, @Nonnull ItemLike result) {
    	return recipeId(recipeType, result, null);
    }
	
	@Nonnull
	private ResourceLocation recipeId(@Nonnull String recipeType, @Nonnull ItemLike result, @Nullable String suffix) {
    	ResourceLocation id = result.asItem().getRegistryName();

    	if(id == null) throw new IllegalArgumentException("Could not get key for item " + result.asItem() + "!");

    	return new ResourceLocation(CreateBasicAdditionsMod.MODID, recipeType + "/" + id.getPath() + (suffix != null ? "_" + suffix : ""));
    }
}