package net.kzeroko.ktmresource.init;

import net.kzeroko.ktmresource.KTMResource;
import net.kzeroko.ktmresource.tileentities.AlloyFurnaceTileEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class KTMPRTileEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, KTMResource.MOD_ID);

    public static final RegistryObject<BlockEntityType<AlloyFurnaceTileEntity>> ALLOY_FURNACE_TILE_ENTITY = TILE_ENTITY_TYPES.register("alloy_furnace_tile_entity", () -> BlockEntityType.Builder.of(AlloyFurnaceTileEntity::new, KTMPRBlocks.ALLOY_FURNACE.get()).build(null));

    public static void register(IEventBus eventBus) {
        TILE_ENTITY_TYPES.register(eventBus);
        KTMResource.LOGGER.info("KTMResource: Tile Entity Types Registered!");
    }
}
