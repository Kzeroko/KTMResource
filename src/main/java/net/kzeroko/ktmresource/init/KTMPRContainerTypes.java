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
import net.kzeroko.ktmresource.containers.AlloyFurnaceContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class KTMPRContainerTypes {
    public static final DeferredRegister<MenuType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, KTMResource.MOD_ID);

    public static final RegistryObject<MenuType<AlloyFurnaceContainer>> ALLOY_FURNACE_CONTAINER = CONTAINER_TYPES.register("alloy_furnace_container", () -> IForgeMenuType.create(((windowId, inv, data) -> new AlloyFurnaceContainer(windowId, inv))));

    public static void register(IEventBus eventBus) {
        CONTAINER_TYPES.register(eventBus);
        KTMResource.LOGGER.info("KTMResource: Container Types Registered!");
    }
}
