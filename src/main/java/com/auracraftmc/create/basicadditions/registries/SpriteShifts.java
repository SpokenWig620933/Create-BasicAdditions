package com.auracraftmc.create.basicadditions.registries;

import javax.annotation.Nonnull;

import com.auracraftmc.create.basicadditions.CreateBasicAdditionsMod;
import com.simibubi.create.foundation.block.connected.*;
import com.simibubi.create.foundation.block.render.SpriteShiftEntry;
import com.simibubi.create.foundation.block.render.SpriteShifter;
import net.minecraft.resources.ResourceLocation;

public class SpriteShifts {
	
	public static final CTSpriteShiftEntry COPPER_ENCASED_COGWHEEL_SIDE = vertical("copper_encased_cogwheel_side");
	public static final CTSpriteShiftEntry COPPER_ENCASED_COGWHEEL_OTHERSIDE = horizontal("copper_encased_cogwheel_side");
	
	public static final CTSpriteShiftEntry RAILWAY_ENCASED_COGWHEEL_SIDE = vertical("railway_encased_cogwheel_side");
	public static final CTSpriteShiftEntry RAILWAY_ENCASED_COGWHEEL_OTHERSIDE = horizontal("railway_encased_cogwheel_side");
	
	@Nonnull
	private static CTSpriteShiftEntry omni(@Nonnull String name) {
		return getCT(AllCTTypes.OMNIDIRECTIONAL, name);
	}
	
	@Nonnull
	private static CTSpriteShiftEntry horizontal(@Nonnull String name) {
		return getCT(AllCTTypes.HORIZONTAL, name);
	}
	
	@Nonnull
	private static CTSpriteShiftEntry vertical(@Nonnull String name) {
		return getCT(AllCTTypes.VERTICAL, name);
	}
	
	@Nonnull
	private static SpriteShiftEntry get(@Nonnull String originalLocation, @Nonnull String targetLocation) {
		return SpriteShifter.get(new ResourceLocation(CreateBasicAdditionsMod.MODID, originalLocation), new ResourceLocation(CreateBasicAdditionsMod.MODID, targetLocation));
	}
	
	@Nonnull
	private static CTSpriteShiftEntry getCT(@Nonnull CTType type, @Nonnull String blockTextureName, @Nonnull String connectedTextureName) {
		return CTSpriteShifter.getCT(type, new ResourceLocation(CreateBasicAdditionsMod.MODID, "block/" + blockTextureName), new ResourceLocation(CreateBasicAdditionsMod.MODID, "block/" + connectedTextureName + "_connected"));
	}
	
	@Nonnull
	private static CTSpriteShiftEntry getCT(@Nonnull CTType type, @Nonnull String blockTextureName) {
		return getCT(type, blockTextureName, blockTextureName);
	}
}