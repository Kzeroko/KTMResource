package net.kzeroko.ktmresource.init;

import net.kzeroko.ktmresource.KTMResource;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class KtmParticles {
	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, KTMResource.MOD_ID);

	public static final RegistryObject<SimpleParticleType> SLASHFIRE = PARTICLES.register("slashfire", () -> new SimpleParticleType(true));
	public static final RegistryObject<SimpleParticleType> BLUESTRIKE = PARTICLES.register("bluestrike", () -> new SimpleParticleType(true));
}