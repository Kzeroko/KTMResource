package net.kzeroko.ktmresource.init;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import vectorwing.farmersdelight.common.registry.ModEffects;
import net.minecraft.world.food.FoodProperties;

import static vectorwing.farmersdelight.common.FoodValues.*;

public class KtmFoods {

    // Drinks
    public static final FoodProperties SLIMEDRINK = (new FoodProperties.Builder())
            .nutrition(1).saturationMod(1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 300, 0), 1.0F).build();

    // Meals
    public static final FoodProperties DRAGONFLESH_SANDWICH = (new FoodProperties.Builder())
            .nutrition(20).saturationMod(1.0f).build();

    // Bowls
    public static final FoodProperties PIXIESOUL_STEW = (new FoodProperties.Builder())
            .nutrition(14).saturationMod(0.7f)
            .effect(() -> new MobEffectInstance(ModEffects.COMFORT.get(), LONG_DURATION, 0), 1.0F).build();

    // Big Meals (WIP)

    /* public static final FoodProperties EXAMPLE_MEAL = (new FoodProperties.Builder())
            .nutrition(10).saturationMod(0.5f)
            .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), SHORT_DURATION, 0), 1.0F).build();
    */
}
