package net.kzeroko.ktmresource.client.integration.jei;

import com.google.common.collect.ImmutableList;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.*;
import net.kzeroko.ktmresource.KTMResource;
import net.kzeroko.ktmresource.init.KTMPRBlocks;
import net.kzeroko.ktmresource.init.KTMPRRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;

import java.util.Collection;
import java.util.List;

// unused?
@JeiPlugin
public class KTMPRJEIPlugin implements IModPlugin {
    private static final ResourceLocation UID = new ResourceLocation(KTMResource.MOD_ID, "jei_main");

    @Override
    public ResourceLocation getPluginUid() {
        return UID;
    }
    
    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new AlloyFurnaceRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @SuppressWarnings("resource")
    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        Collection<Recipe<?>> recipes = Minecraft.getInstance().level.getRecipeManager().getRecipes();
        List<Recipe<?>> gemForgingRecipes = recipes.stream()
            .filter(recipe -> recipe.getType() == KTMPRRecipes.FORGING_RECIPE_TYPE)
            .collect(ImmutableList.toImmutableList());
        registration.addRecipes(gemForgingRecipes, AlloyFurnaceRecipeCategory.UID);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {

    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(KTMPRBlocks.ALLOY_FURNACE.get().asItem()), AlloyFurnaceRecipeCategory.UID);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {

    }

    @Override
    public void registerAdvanced(IAdvancedRegistration registration) {

    }
}
