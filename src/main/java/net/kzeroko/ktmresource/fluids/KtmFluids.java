package net.kzeroko.ktmresource.fluids;

import net.kzeroko.ktmresource.KTMResource;
import net.kzeroko.ktmresource.init.KtmItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.kzeroko.ktmresource.init.KtmBlocks.BLAZING_SOUL;

public class KtmFluids {
    public static final ResourceLocation BLAZINGSOUL_STILL = new ResourceLocation(KTMResource.MOD_ID,
            "block/fluid/blazing_soul_still");
    public static final ResourceLocation BLAZINGSOUL_FLOW = new ResourceLocation(KTMResource.MOD_ID,
            "block/fluid/blazing_soul_flowing");

    public static final DeferredRegister<Fluid> FLUID = DeferredRegister.create(ForgeRegistries.FLUIDS, KTMResource.MOD_ID);

    public static final RegistryObject<ForgeFlowingFluid.Source> BLAZINGSOUL = FLUID.register("blazing_soul",
            ()-> new ForgeFlowingFluid.Source(makeBlazingSoulProperties()));
    public static final RegistryObject<ForgeFlowingFluid.Flowing> flowing_BLAZINGSOUL = FLUID.register("flowing_blazingsoul",
            ()-> new ForgeFlowingFluid.Flowing(makeBlazingSoulProperties()));

    private static ForgeFlowingFluid.Properties makeBlazingSoulProperties() {
        return new ForgeFlowingFluid.Properties(BLAZINGSOUL, flowing_BLAZINGSOUL,
                FluidAttributes.builder(BLAZINGSOUL_STILL, BLAZINGSOUL_FLOW)
                        .luminosity(15).density(3000).viscosity(6000).temperature(1500).sound(SoundEvents.BUCKET_FILL_LAVA, SoundEvents.BUCKET_EMPTY_LAVA))
                .bucket(KtmItems.BLAZINGSOUL_BUCKET).block(BLAZING_SOUL).explosionResistance(1000F).tickRate(9);
    }

    public static void register(IEventBus eventBus) {
        FLUID.register(eventBus);
    }
}
