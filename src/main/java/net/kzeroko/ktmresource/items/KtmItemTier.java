package net.kzeroko.ktmresource.items;

import net.kzeroko.ktmresource.init.KtmItems;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum KtmItemTier implements Tier {

    // Customize
    TEMPLATE(0, 200, 0f, 1f, 0, () -> Ingredient.of(Items.IRON_INGOT)),
    BASIC(0, 59, 0f, 4f, 15, () -> Ingredient.EMPTY),
    ADVANCED(0, 1400, 0f, 10f, 15, () -> Ingredient.of(KtmItems.ADVANCED_REPAIRKIT.get())),
    ELITE(0, 1200, 0f, 15f, 15, () -> Ingredient.of(KtmItems.ADVANCED_REPAIRKIT.get())),
    ELITEPLUS(0, 1500, 0f, 15f, 15, () -> Ingredient.of(KtmItems.ADVANCED_REPAIRKIT.get())),
    BLACKWYRM(0, 2000, 0f, 14f, 15, () -> Ingredient.of(KtmItems.ENDER_REPAIRKIT.get())),
    WHITEWYRM(0, 2200, 0f, 13f, 15, () -> Ingredient.of(KtmItems.ENDER_REPAIRKIT.get())),
    ANOTHER(0, 2800, 0f, 20f, 17, () -> Ingredient.of(KtmItems.ALIEN_REPAIRKIT.get())),
    LEGENDARY(0, 5000, 0f, 40f, 20, () -> Ingredient.of(KtmItems.LEGENDARY_REPAIRKIT.get())),
    MYTHICAL(0, 10000, 0f, 60f, 25, () -> Ingredient.of(KtmItems.MYTHIC_REPAIRKIT.get())),

    // Vanilla +
    STONE(0, 151, 0f, 5f, 15, () -> Ingredient.of(Items.STONE)),
    IRON(0, 270, 0f, 6f, 15, () -> Ingredient.of(Items.IRON_INGOT)),
    GOLD(0, 52, 0f, 4f, 15, () -> Ingredient.of(Items.GOLD_INGOT)),
    DIAMOND(0, 1581, 0f, 7f, 15, () -> Ingredient.of(Items.DIAMOND)),
    NETHERITE(0, 2051, 0f, 8.5f, 17, () -> Ingredient.of(Items.NETHERITE_INGOT)),

    // Special Uses
    BASICONETIME(0, 1, 0f, 11f, 0, () -> Ingredient.EMPTY),
    ADVANCEDONETIME(0, 1, 0f, 14f, 0, () -> Ingredient.EMPTY),
    ELITEONETIME(0, 1, 0f, 24f, 0, () -> Ingredient.EMPTY),
    EPICONETIME(0, 1, 0f, 34f, 0, () -> Ingredient.EMPTY),
    LEGENDARYONETIME(0, 1, 0f, 44f, 0, () -> Ingredient.EMPTY),
    MYTHICONETIME(0, 1, 0f, 54f, 0, () -> Ingredient.EMPTY);

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    KtmItemTier(int pLevel, int pUses, float pSpeed, float pDamage, int pEnchantmentValue, Supplier<Ingredient> pRepairIngredient) {
        this.level = pLevel;
        this.uses = pUses;
        this.speed = pSpeed;
        this.damage = pDamage;
        this.enchantmentValue = pEnchantmentValue;
        this.repairIngredient = new LazyLoadedValue<>(pRepairIngredient);
    }

    public int getUses() {
        return this.uses;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getAttackDamageBonus() {
        return this.damage;
    }

    public int getLevel() {
        return this.level;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

}
