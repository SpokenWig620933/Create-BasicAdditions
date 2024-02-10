package com.auracraftmc.create.basicadditions.recipes;

import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.auracraftmc.create.basicadditions.CreateBasicAdditionsMod;
import com.auracraftmc.create.basicadditions.registries.Blocks;
import com.auracraftmc.create.basicadditions.registries.Items;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.foundation.utility.RegisteredObjects;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

public class StandardRecipeProvider extends RecipeProvider {

	public StandardRecipeProvider(@Nonnull PackOutput output) {
		super(output);
	}

    @Override
    protected void buildRecipes(@Nonnull Consumer<FinishedRecipe> consumer) {
    	generateShaped(consumer);
    	generateShapeless(consumer);
    }

    private void generateShaped(@Nonnull Consumer<FinishedRecipe> consumer) {
    	ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Blocks.BRASS_GEARBOX.get())
    			.unlockedBy("has_item", inventoryTrigger(ItemPredicate.Builder.item().of(AllBlocks.COGWHEEL.get()).build()))
    			.pattern(" c ")
    			.pattern("cbc")
    			.pattern(" c ")
    			.define('c', AllBlocks.COGWHEEL.get())
    			.define('b', AllBlocks.BRASS_CASING.get())
    			.save(consumer, recipeId("workbench", Blocks.BRASS_GEARBOX.get()));
	    
	    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Blocks.COPPER_GEARBOX.get())
	            .unlockedBy("has_item", inventoryTrigger(ItemPredicate.Builder.item().of(AllBlocks.COGWHEEL.get()).build()))
			    .pattern(" c ")
			    .pattern("cbc")
			    .pattern(" c ")
			    .define('c', AllBlocks.COGWHEEL.get())
			    .define('b', AllBlocks.COPPER_CASING.get())
			    .save(consumer, recipeId("workbench", Blocks.COPPER_GEARBOX.get()));
	    
	    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Blocks.RAILWAY_GEARBOX.get())
			    .unlockedBy("has_item", inventoryTrigger(ItemPredicate.Builder.item().of(AllBlocks.COGWHEEL.get()).build()))
			    .pattern(" c ")
			    .pattern("cbc")
			    .pattern(" c ")
			    .define('c', AllBlocks.COGWHEEL.get())
			    .define('b', AllBlocks.RAILWAY_CASING.get())
			    .save(consumer, recipeId("workbench", Blocks.RAILWAY_GEARBOX.get()));
    }

    private void generateShapeless(@Nonnull Consumer<FinishedRecipe> consumer) {
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Blocks.BRASS_GEARSHIFT.get())
		        .unlockedBy("has_item", inventoryTrigger(ItemPredicate.Builder.item().of(AllBlocks.BRASS_CASING.get()).build()))
		        .requires(AllBlocks.BRASS_CASING.get())
		        .requires(AllBlocks.COGWHEEL.get())
				.requires(Blocks.REDSTONE_WIRE)
		        .save(consumer,recipeId("workbench", Blocks.BRASS_GEARSHIFT.get()));
		
	    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Blocks.COPPER_GEARSHIFT.get())
			    .unlockedBy("has_item", inventoryTrigger(ItemPredicate.Builder.item().of(AllBlocks.COPPER_CASING.get()).build()))
			    .requires(AllBlocks.COPPER_CASING.get())
			    .requires(AllBlocks.COGWHEEL.get())
			    .requires(Blocks.REDSTONE_WIRE)
			    .save(consumer, recipeId("workbench", Blocks.COPPER_GEARSHIFT.get()));
		
	    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Blocks.RAILWAY_GEARSHIFT.get())
			    .unlockedBy("has_item", inventoryTrigger(ItemPredicate.Builder.item().of(AllBlocks.RAILWAY_CASING.get()).build()))
			    .requires(AllBlocks.RAILWAY_CASING.get())
			    .requires(AllBlocks.COGWHEEL.get())
			    .requires(Blocks.REDSTONE_WIRE)
			    .save(consumer, recipeId("workbench", Blocks.RAILWAY_GEARSHIFT.get()));
		
    	ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Blocks.BASIC_GEARSHIFT.get())
				.unlockedBy("has_item", inventoryTrigger(ItemPredicate.Builder.item().of(AllBlocks.ANDESITE_CASING.get()).build()))
    			.requires(AllBlocks.ANDESITE_CASING.get())
    			.requires(AllBlocks.COGWHEEL.get())
    			.save(consumer, recipeId("workbench", Blocks.BASIC_GEARSHIFT.get()));
	    
	    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Blocks.BASIC_BRASS_GEARSHIFT.get())
	            .unlockedBy("has_item", inventoryTrigger(ItemPredicate.Builder.item().of(AllBlocks.BRASS_CASING.get()).build()))
	            .requires(AllBlocks.BRASS_CASING.get())
	            .requires(AllBlocks.COGWHEEL.get())
	            .save(consumer, recipeId("workbench", Blocks.BASIC_BRASS_GEARSHIFT.get()));
	    
	    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Blocks.BASIC_COPPER_GEARSHIFT.get())
		    .unlockedBy("has_item", inventoryTrigger(ItemPredicate.Builder.item().of(AllBlocks.COPPER_CASING.get()).build()))
		    .requires(AllBlocks.COPPER_CASING.get())
		    .requires(AllBlocks.COGWHEEL.get())
		    .save(consumer, recipeId("workbench", Blocks.BASIC_COPPER_GEARSHIFT.get()));
	    
	    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Blocks.BASIC_RAILWAY_GEARSHIFT.get())
			    .unlockedBy("has_item", inventoryTrigger(ItemPredicate.Builder.item().of(AllBlocks.RAILWAY_CASING.get()).build()))
			    .requires(AllBlocks.RAILWAY_CASING.get())
			    .requires(AllBlocks.COGWHEEL.get())
			    .save(consumer, recipeId("workbench", Blocks.BASIC_RAILWAY_GEARSHIFT.get()));
		
    	ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllBlocks.GEARSHIFT.get())
    			.unlockedBy("has_item", inventoryTrigger(ItemPredicate.Builder.item().of(Blocks.BASIC_GEARSHIFT.get()).build(), ItemPredicate.Builder.item().of(AllBlocks.GEARSHIFT.get()).build()))
    			.requires(Blocks.BASIC_GEARSHIFT.get())
    			.requires(Items.REDSTONE)
				.save(consumer, recipeId("workbench", AllBlocks.GEARSHIFT.get(), "powered"));
		
	    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Blocks.BRASS_GEARSHIFT.get())
	            .unlockedBy("has_item", inventoryTrigger(ItemPredicate.Builder.item().of(Blocks.BASIC_BRASS_GEARSHIFT.get()).build(), ItemPredicate.Builder.item().of(Blocks.BRASS_GEARSHIFT.get()).build()))
	            .requires(Blocks.BASIC_BRASS_GEARSHIFT.get())
	            .requires(Items.REDSTONE)
	            .save(consumer, recipeId("workbench", Blocks.BRASS_GEARSHIFT.get(), "powered"));
	    
	    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Blocks.COPPER_GEARSHIFT.get())
		    .unlockedBy("has_item", inventoryTrigger(ItemPredicate.Builder.item().of(Blocks.BASIC_COPPER_GEARSHIFT.get()).build(), ItemPredicate.Builder.item().of(Blocks.COPPER_GEARSHIFT.get()).build()))
		    .requires(Blocks.BASIC_COPPER_GEARSHIFT.get())
		    .requires(Items.REDSTONE)
		    .save(consumer, recipeId("workbench", Blocks.COPPER_GEARSHIFT.get(), "powered"));
	    
	    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Blocks.RAILWAY_GEARSHIFT.get())
		    .unlockedBy("has_item", inventoryTrigger(ItemPredicate.Builder.item().of(Blocks.BASIC_RAILWAY_GEARSHIFT.get()).build(), ItemPredicate.Builder.item().of(Blocks.RAILWAY_GEARSHIFT.get()).build()))
		    .requires(Blocks.BASIC_RAILWAY_GEARSHIFT.get())
		    .requires(Items.REDSTONE)
		    .save(consumer, recipeId("workbench", Blocks.RAILWAY_GEARSHIFT.get(), "powered"));
		
    	ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Blocks.BRASS_GEARBOX.get())
				.unlockedBy("has_item", inventoryTrigger(ItemPredicate.Builder.item().of(Items.VERTICAL_BRASS_GEARBOX.get()).build()))
    			.requires(Items.VERTICAL_BRASS_GEARBOX.get())
    			.save(consumer, recipeId("workbench", Blocks.BRASS_GEARBOX.get(), "conversion"));
	    
	    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Blocks.COPPER_GEARBOX.get())
		    .unlockedBy("has_item", inventoryTrigger(ItemPredicate.Builder.item().of(Items.VERTICAL_COPPER_GEARBOX.get()).build()))
		    .requires(Items.VERTICAL_COPPER_GEARBOX.get())
		    .save(consumer, recipeId("workbench", Blocks.COPPER_GEARBOX.get(), "conversion"));
	    
	    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Blocks.RAILWAY_GEARBOX.get())
		    .unlockedBy("has_item", inventoryTrigger(ItemPredicate.Builder.item().of(Items.VERTICAL_RAILWAY_GEARBOX.get()).build()))
		    .requires(Items.VERTICAL_RAILWAY_GEARBOX.get())
		    .save(consumer, recipeId("workbench", Blocks.RAILWAY_GEARBOX.get(), "conversion"));

    	ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.VERTICAL_BRASS_GEARBOX.get())
				.unlockedBy("has_item", inventoryTrigger(ItemPredicate.Builder.item().of(Blocks.BRASS_GEARBOX.get()).build()))
    			.requires(Blocks.BRASS_GEARBOX.get())
    			.save(consumer, recipeId("workbench", Items.VERTICAL_BRASS_GEARBOX.get(), "conversion"));
	    
	    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.VERTICAL_COPPER_GEARBOX.get())
		    .unlockedBy("has_item", inventoryTrigger(ItemPredicate.Builder.item().of(Blocks.COPPER_GEARBOX.get()).build()))
		    .requires(Blocks.COPPER_GEARBOX.get())
		    .save(consumer, recipeId("workbench", Items.VERTICAL_COPPER_GEARBOX.get(), "conversion"));
	    
	    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.VERTICAL_RAILWAY_GEARBOX.get())
		    .unlockedBy("has_item", inventoryTrigger(ItemPredicate.Builder.item().of(Blocks.RAILWAY_GEARBOX.get()).build()))
		    .requires(Blocks.RAILWAY_GEARBOX.get())
		    .save(consumer, recipeId("workbench", Items.VERTICAL_RAILWAY_GEARBOX.get(), "conversion"));
    }
	
	@Nonnull
	private ResourceLocation recipeId(@Nonnull String recipeType, @Nonnull ItemLike result) {
    	return recipeId(recipeType, result, null);
    }
	
	@Nonnull
	private ResourceLocation recipeId(@Nonnull String recipeType, @Nonnull ItemLike result, @Nullable String suffix) {
    	return new ResourceLocation(CreateBasicAdditionsMod.MODID, recipeType + "/" + RegisteredObjects.getKeyOrThrow(result.asItem()).getPath() + (suffix != null ? "_" + suffix : ""));
    }
}