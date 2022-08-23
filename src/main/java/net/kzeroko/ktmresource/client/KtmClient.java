package net.kzeroko.ktmresource.client;

import net.kzeroko.ktmresource.client.gui.screen.inventory.AlloyFurnaceScreen;
import net.kzeroko.ktmresource.init.KtmBlocks;
import net.kzeroko.ktmresource.init.KtmContainerTypes;
import net.kzeroko.ktmresource.init.KtmRecipes;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.client.RecipeBookRegistry;

import java.util.List;

public class KtmClient {

    private static RecipeBookCategories ALLOY_FURNACE;

    public static void init() {

        MenuScreens.register(KtmContainerTypes.ALLOY_FURNACE_CONTAINER.get(), AlloyFurnaceScreen::new);

        // TBD
        // wm this create?
        ALLOY_FURNACE = RecipeBookCategories.create("ALLOY_FURNACE", new ItemStack(KtmBlocks.ALLOY_FURNACE.get()));
        RecipeBookRegistry.addCategoriesToType(KtmRecipes.ALLOY_FURNACE, List.of(ALLOY_FURNACE));
        RecipeBookRegistry.addCategoriesFinder(KtmRecipes.FORGING_RECIPE_TYPE, KtmClient::getForgingCategory);

    }

    private static RecipeBookCategories getForgingCategory(Recipe<?> recipe) {
        RecipeType<?> recipeType = recipe.getType();
        if (recipeType.equals(KtmRecipes.FORGING_RECIPE_TYPE)) {
            return ALLOY_FURNACE;
        } else {
            return RecipeBookCategories.UNKNOWN;
        }
    }
}
