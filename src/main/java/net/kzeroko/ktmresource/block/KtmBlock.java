package net.kzeroko.ktmresource.block;

import net.kzeroko.ktmresource.KTMResource;
import net.kzeroko.ktmresource.fluid.KtmFluid;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class KtmBlock {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, KTMResource.MOD_ID);

    // Fluid Blocks

    public static final RegistryObject<LiquidBlock> BLAZING_SOUL = BLOCKS
            .register("blazingsoul_block", () -> new LiquidBlock(KtmFluid.BLAZINGSOUL,
                    Block.Properties.of(Material.LAVA).lightLevel((state) -> {	return 15;	}).randomTicks().strength(100.0F).noDrops()));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
