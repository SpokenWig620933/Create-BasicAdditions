package com.auracraftmc.create.basicadditions;

import javax.annotation.Nonnull;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class CreateBasicAdditionsData implements DataGeneratorEntrypoint {
	
	@Override
	public void onInitializeDataGenerator(@Nonnull FabricDataGenerator fabricDataGenerator) {
		CreateBasicAdditionsMod.REGISTRATE.setupDatagen(fabricDataGenerator, ExistingFileHelper.withResourcesFromArg());
		CreateBasicAdditionsMod.gatherData(fabricDataGenerator);
	}
}