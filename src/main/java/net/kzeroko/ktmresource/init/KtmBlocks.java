package net.kzeroko.ktmresource.init;

import net.kzeroko.ktmresource.KTMResource;
import net.kzeroko.ktmresource.blocks.AlloyFurnaceBlock;
import net.kzeroko.ktmresource.blocks.UnbreakableBlockTemplate;
import net.kzeroko.ktmresource.fluids.KtmFluids;
import net.kzeroko.ktmresource.items.KtmItemTab;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class KtmBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, KTMResource.MOD_ID);

    // Fluid Blocks
    public static final RegistryObject<LiquidBlock> BLAZING_SOUL = BLOCKS
            .register("blazingsoul_block", () -> new LiquidBlock(KtmFluids.BLAZINGSOUL,
                    Block.Properties.of(Material.LAVA).lightLevel((state) -> {	return 15;	}).randomTicks().strength(100.0F).noDrops()));

    // Blocks
    public static final RegistryObject<Block> ALLOY_FURNACE = registerBlockWithDefaultItem("alloy_furnace", () -> new AlloyFurnaceBlock(getProperties(Blocks.FURNACE)));
    public static final RegistryObject<Block> UNBREAK_TEMPLATE = registerBlockWithDefaultItem("unbreak_template", UnbreakableBlockTemplate::new);

    //*******************************************************************************************************************

    public static BlockBehaviour.Properties getProperties(Material materialIn, float hardnessAndResistanceIn) {
        return getProperties(materialIn, hardnessAndResistanceIn, hardnessAndResistanceIn);
    }

    public static BlockBehaviour.Properties getProperties(Material materialIn, float hardnessIn, float resistanceIn) {
        return BlockBehaviour.Properties.of(materialIn).strength(hardnessIn, resistanceIn);
    }

    public static BlockBehaviour.Properties getProperties(Material materialIn) {
        return BlockBehaviour.Properties.of(materialIn).instabreak();
    }

    public static BlockBehaviour.Properties getProperties(Block block) {
        return BlockBehaviour.Properties.copy(block);
    }

    public static <T extends Block> RegistryObject<T> registerBlockWithDefaultItem(String name, Supplier<? extends T> blockSupplier) {
        RegistryObject<T> block = BLOCKS.register(name, blockSupplier);
        KtmItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(KtmItemTab.KTMBLOCKS)));
        return block;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        KTMResource.LOGGER.info("KTMResource: Blocks Registered!");
    }
}
