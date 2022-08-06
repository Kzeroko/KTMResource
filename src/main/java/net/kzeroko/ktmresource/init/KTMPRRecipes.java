/*
 * Copyright 2022 Infernal Studios
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
import net.kzeroko.ktmresource.recipes.ForgingRecipe;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class KTMPRRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, KTMResource.MOD_ID);

    public static final RegistryObject<RecipeSerializer<ForgingRecipe>> FORGING_RECIPE = RECIPE_SERIALIZERS.register("forging_recipe", ForgingRecipe.ForgingRecipeSerializer::new);

    public static RecipeType<ForgingRecipe> FORGING_RECIPE_TYPE = new ForgingRecipe.ForgingRecipeType();
    public static RecipeBookType ALLOY_FURNACE = RecipeBookType.create("ALLOY_FURNACE");

    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);

        KTMResource.LOGGER.info("KTMResource: Recipes Registered!");
    }
}
