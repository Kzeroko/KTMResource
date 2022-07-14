package net.kzeroko.ktmresource.item;

import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Rarity;

public class KtmItemRarity {
    public static final Rarity LEGENDARY = Rarity.create("legendary", ChatFormatting.GOLD);
    public static final Rarity FORBIDDEN = Rarity.create("forbidden", ChatFormatting.RED);
    public static final Rarity TREASURY = Rarity.create("treasury", ChatFormatting.BLUE);
    public static final Rarity OUTER = Rarity.create("outer", ChatFormatting.AQUA);
    public static final Rarity MONSTROUS = Rarity.create("monstrous", ChatFormatting.LIGHT_PURPLE);
    public static final Rarity VENOMOUS = Rarity.create("venomous", ChatFormatting.GREEN);
    public static final Rarity ADVANCED = Rarity.create("advanced", ChatFormatting.YELLOW);
}
