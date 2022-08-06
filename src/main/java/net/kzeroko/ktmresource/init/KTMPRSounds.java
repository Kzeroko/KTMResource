package net.kzeroko.ktmresource.init;


import net.kzeroko.ktmresource.KTMResource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class KTMPRSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, KTMResource.MOD_ID);

    // TBD
    // ALLOY FURNACE
    public static final RegistryObject<SoundEvent> ALLOY_FURNACE_COOK = registerSound("alloyfurnace.cook");

    public static void register(IEventBus eventBus) {
        SOUNDS.register(eventBus);
        KTMResource.LOGGER.info("KTMResource: Sounds Registered!");
    }

    public static RegistryObject<SoundEvent> registerSound(String name) {
        return SOUNDS.register(name, () -> new SoundEvent(new ResourceLocation(KTMResource.MOD_ID, name)));
    }
}