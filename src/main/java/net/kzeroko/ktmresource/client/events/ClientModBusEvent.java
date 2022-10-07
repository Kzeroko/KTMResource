package net.kzeroko.ktmresource.client.events;

import net.kzeroko.ktmresource.KTMResource;
import net.kzeroko.ktmresource.client.particles.BlueStrikeParticle;
import net.kzeroko.ktmresource.init.KtmParticles;
import net.kzeroko.ktmresource.client.particles.SlashFireParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid= KTMResource.MOD_ID, value=Dist.CLIENT, bus=EventBusSubscriber.Bus.MOD)
public class ClientModBusEvent {
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onParticleRegistry(final ParticleFactoryRegisterEvent event) {
		Minecraft mc = Minecraft.getInstance();
		ParticleEngine particleEngine = mc.particleEngine;
    	// From Epic Fight mod
		// particleEngine.register(KtmParticles.HIT_BLADE.get(), new HitCutParticle.Provider());
		particleEngine.register(KtmParticles.SLASHFIRE.get(), SlashFireParticle.Provider::new);
		particleEngine.register(KtmParticles.BLUESTRIKE.get(), BlueStrikeParticle.Provider::new);
    }
}