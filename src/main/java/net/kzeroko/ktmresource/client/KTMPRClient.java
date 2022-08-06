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

package net.kzeroko.ktmresource.client;

import net.kzeroko.ktmresource.client.gui.screen.inventory.AlloyFurnaceScreen;
import net.kzeroko.ktmresource.init.KTMPRBlocks;
import net.kzeroko.ktmresource.init.KTMPRContainerTypes;
import net.kzeroko.ktmresource.init.KTMPRRecipes;
import net.kzeroko.ktmresource.items.KtmItems;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.client.RecipeBookRegistry;

import java.util.List;

public class KTMPRClient {

    private static RecipeBookCategories ALLOY_FURNACE;

    public static void init() {

        MenuScreens.register(KTMPRContainerTypes.ALLOY_FURNACE_CONTAINER.get(), AlloyFurnaceScreen::new);

        // TBD
        // wm this create?
        ALLOY_FURNACE = RecipeBookCategories.create("ALLOY_FURNACE", new ItemStack(KTMPRBlocks.ALLOY_FURNACE.get()));
        RecipeBookRegistry.addCategoriesToType(KTMPRRecipes.ALLOY_FURNACE, List.of(ALLOY_FURNACE));
        RecipeBookRegistry.addCategoriesFinder(KTMPRRecipes.FORGING_RECIPE_TYPE, KTMPRClient::getForgingCategory);

    }

    private static RecipeBookCategories getForgingCategory(Recipe<?> recipe) {
        RecipeType<?> recipeType = recipe.getType();
        if (recipeType.equals(KTMPRRecipes.FORGING_RECIPE_TYPE)) {
            return ALLOY_FURNACE;
        } else {
            return RecipeBookCategories.UNKNOWN;
        }
    }
}
