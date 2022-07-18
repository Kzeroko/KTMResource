package net.kzeroko.ktmresource.item;

import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum KtmItemTier implements Tier {

    BASIC(0, 150, 0f, 4f, 15, () -> Ingredient.EMPTY),
    ADVANCED(0, 1400, 0f, 10f, 15, () -> Ingredient.of(KtmItems.ADVANCED_REPAIRKIT.get())),
    ELITE(0, 1200, 0f, 15f, 15, () -> Ingredient.of(KtmItems.ADVANCED_REPAIRKIT.get())),
    BLACKWYRM(0, 2000, 0f, 14f, 15, () -> Ingredient.of(KtmItems.ENDER_REPAIRKIT.get())),
    WHITEWYRM(0, 2200, 0f, 13f, 15, () -> Ingredient.of(KtmItems.ENDER_REPAIRKIT.get())),
    ANOTHER(0, 2800, 0f, 20f, 17, () -> Ingredient.of(KtmItems.ALIEN_REPAIRKIT.get())),
    LEGENDARY(0, 5000, 0f, 40f, 20, () -> Ingredient.of(KtmItems.LEGENDARY_REPAIRKIT.get())),
    MYTHICAL(0, 10000, 0f, 60f, 25, () -> Ingredient.of(KtmItems.MYTHIC_REPAIRKIT.get()));

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
