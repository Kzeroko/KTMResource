package net.kzeroko.ktmresource;

import com.mojang.logging.LogUtils;
import net.kzeroko.ktmresource.block.KtmBlock;
import net.kzeroko.ktmresource.fluid.KtmFluid;
import net.kzeroko.ktmresource.item.KtmItems;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.stream.Collectors;

@Mod(KTMResource.MOD_ID)
public class KTMResource
{
    public static final String MOD_ID = "ktmresource";

    private static final Logger LOGGER = LogUtils.getLogger();

    public KTMResource()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        KtmItems.register(eventBus);
        KtmBlock.register(eventBus);
        KtmFluid.register(eventBus);

        eventBus.addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
}
