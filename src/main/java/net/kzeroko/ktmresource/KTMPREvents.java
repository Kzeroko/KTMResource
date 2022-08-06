package net.kzeroko.ktmresource;


import net.kzeroko.ktmresource.init.KTMPRRecipes;
import net.kzeroko.ktmresource.recipes.ForgingRecipe;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = KTMResource.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KTMPREvents {

    // Register the custom recipe type
    @SubscribeEvent
    public static void registerRecipeTypes(RegistryEvent.Register<RecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, ForgingRecipe.TYPE_ID, KTMPRRecipes.FORGING_RECIPE_TYPE);
    }

}
