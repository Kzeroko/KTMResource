/*
 * Copyright 2021 Infernal Studios
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
