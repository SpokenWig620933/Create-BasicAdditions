package com.auracraftmc.create.basicadditions.configs;

import javax.annotation.Nonnull;

import com.auracraftmc.create.basicadditions.CreateBasicAdditionsMod;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.foundation.config.ConfigBase;
import com.simibubi.create.infrastructure.config.CStress;
import net.minecraftforge.common.ForgeConfigSpec.Builder;

public class CommonConfig extends ConfigBase {
	
	public final StressProvider stressValues = nested(1, () -> new StressProvider(CreateBasicAdditionsMod.MODID), "Fine tune the kinetic stats of individual components");
	
	@Override
	public String getName() {
		return "common";
	}
	
	public static class StressProvider extends CStress {
		
		private final String modid;
		
		public StressProvider(@Nonnull String modid) {
			this.modid = modid;
		}
		
		@Override
		public void registerAll(@Nonnull Builder builder) {
			builder.comment(".", "[in Stress Units]", "Configure the individual stress impact of mechanical blocks. Note that this cost is doubled for every speed increase it receives.").push("impact");
			BlockStressDefaults.DEFAULT_IMPACTS.forEach((r, i) -> {
				if(r.getNamespace().equals(this.modid)) getImpacts().put(r, builder.define(r.getPath(), i));
			});
			builder.pop();
			
			builder.comment(".", "[in Stress Units]", "Configure how much stress a source can accommodate for.").push("capacity");
			BlockStressDefaults.DEFAULT_CAPACITIES.forEach((r, i) -> {
				if(r.getNamespace().equals(this.modid)) getCapacities().put(r, builder.define(r.getPath(), i));
			});
			builder.pop();
		}
	}
}