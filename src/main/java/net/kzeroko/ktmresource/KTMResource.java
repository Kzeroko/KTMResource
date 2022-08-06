package net.kzeroko.ktmresource;

import com.mojang.logging.LogUtils;
import net.kzeroko.ktmresource.client.KTMPRClient;
import net.kzeroko.ktmresource.fluids.KtmFluid;
import net.kzeroko.ktmresource.init.*;
import net.kzeroko.ktmresource.items.KtmItems;
import net.kzeroko.ktmresource.network.KTMPRNetworkHandler;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(KTMResource.MOD_ID)
public class KTMResource
{
    public static final String MOD_ID = "ktmresource";
    public static final Logger LOGGER = LogUtils.getLogger();

    public KTMResource()
    {
        final ModLoadingContext modLoadingContext = ModLoadingContext.get();
        final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::clientSetup);

        KtmItems.register(eventBus);
        KtmFluid.register(eventBus);
        KTMPRBlocks.register(eventBus);
        KTMPRRecipes.register(eventBus);
        KTMPRContainerTypes.register(eventBus);
        KTMPRTileEntityTypes.register(eventBus);
        KTMPRSounds.register(eventBus);

        eventBus.addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(KTMPRNetworkHandler::register);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> KTMPRClient::init);
    }

}
