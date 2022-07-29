package net.kzeroko.ktmresource.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

import java.util.List;

public class KtmItemDesc {
    public static String INGOTMYTHIC_DESC = "desc.ktmresource.ingot_mythic";
    public static String SHOW_DESC = "desc.ktmresource.shift_info";
    public static String ADVANCED_TIER = "desc.ktmresource.advanced_item";
    public static String ELITE_TIER = "desc.ktmresource.elite_item";
    public static String ANOTHER_TIER = "desc.ktmresource.another_item";
    public static String LEGENDARY_TIER = "desc.ktmresource.legendary_item";
    public static String MYTHIC_TIER = "desc.ktmresource.mythic_item";
    public static String CUSTOM_TIER = "desc.ktmresource.custom_item";
    public static String UNIQUE_TIER = "desc.ktmresource.unique_item";
    public static String EXOTIC_TIER = "desc.ktmresource.exotic_item";


    public static ChatFormatting DESCRIPTION = ChatFormatting.ITALIC;
    public static ChatFormatting WEAPON_OUTER = ChatFormatting.AQUA;
    public static ChatFormatting WEAPON_ADVANCED = ChatFormatting.DARK_AQUA;
    public static ChatFormatting WEAPON_ELITE = ChatFormatting.BLUE;
    public static ChatFormatting WEAPON_ANOTHER = ChatFormatting.DARK_PURPLE;
    public static ChatFormatting WEAPON_LEGENDARY = ChatFormatting.GOLD;
    public static ChatFormatting WEAPON_MYTHIC = ChatFormatting.RED;
    public static ChatFormatting WEAPON_CUSTOM = ChatFormatting.YELLOW;
    public static ChatFormatting WEAPON_UNIQUE = ChatFormatting.DARK_RED;
    public static ChatFormatting WEAPON_EXOTIC = ChatFormatting.LIGHT_PURPLE;

    public static void INGOT_MYTHIC(List<Component> tooltip) {
        tooltip.add(new TranslatableComponent(KtmItemDesc.MYTHIC_TIER).withStyle(KtmItemDesc.WEAPON_MYTHIC));
        tooltip.add(new TranslatableComponent(KtmItemDesc.INGOTMYTHIC_DESC).withStyle(KtmItemDesc.WEAPON_LEGENDARY));
    }

    public static void WEAPON_ADVANCED(List<Component> tooltip) {
        tooltip.add(new TranslatableComponent(KtmItemDesc.ADVANCED_TIER).withStyle(KtmItemDesc.WEAPON_ADVANCED));
        tooltip.add(new TranslatableComponent(KtmItemDesc.SHOW_DESC).withStyle(ChatFormatting.GRAY));
    }

    public static void WEAPON_ELITE(List<Component> tooltip) {
        tooltip.add(new TranslatableComponent(KtmItemDesc.ELITE_TIER).withStyle(KtmItemDesc.WEAPON_ELITE));
        tooltip.add(new TranslatableComponent(KtmItemDesc.SHOW_DESC).withStyle(ChatFormatting.GRAY));
    }

    public static void WEAPON_CUSTOM(List<Component> tooltip) {
        tooltip.add(new TranslatableComponent(KtmItemDesc.CUSTOM_TIER).withStyle(KtmItemDesc.WEAPON_CUSTOM).withStyle(ChatFormatting.BOLD));
        tooltip.add(new TranslatableComponent(KtmItemDesc.SHOW_DESC).withStyle(ChatFormatting.GRAY));
    }

    public static void WEAPON_UNIQUE(List<Component> tooltip) {
        tooltip.add(new TranslatableComponent(KtmItemDesc.UNIQUE_TIER).withStyle(KtmItemDesc.WEAPON_UNIQUE).withStyle(ChatFormatting.BOLD));
        tooltip.add(new TranslatableComponent(KtmItemDesc.SHOW_DESC).withStyle(ChatFormatting.GRAY));
    }

    public static void WEAPON_EXOTIC(List<Component> tooltip) {
        tooltip.add(new TranslatableComponent(KtmItemDesc.EXOTIC_TIER).withStyle(KtmItemDesc.WEAPON_EXOTIC).withStyle(ChatFormatting.BOLD));
        tooltip.add(new TranslatableComponent(KtmItemDesc.SHOW_DESC).withStyle(ChatFormatting.GRAY));
    }

    public static void WEAPON_ANOTHER(List<Component> tooltip) {
        tooltip.add(new TranslatableComponent(KtmItemDesc.ANOTHER_TIER).withStyle(KtmItemDesc.WEAPON_ANOTHER));
        tooltip.add(new TranslatableComponent(KtmItemDesc.SHOW_DESC).withStyle(ChatFormatting.GRAY));
    }

    public static void WEAPON_LEGENDARY(List<Component> tooltip) {
        tooltip.add(new TranslatableComponent(KtmItemDesc.LEGENDARY_TIER).withStyle(KtmItemDesc.WEAPON_LEGENDARY));
        tooltip.add(new TranslatableComponent(KtmItemDesc.SHOW_DESC).withStyle(ChatFormatting.GRAY));
    }

    public static void WEAPON_MYTHIC(List<Component> tooltip) {
        tooltip.add(new TranslatableComponent(KtmItemDesc.MYTHIC_TIER).withStyle(KtmItemDesc.WEAPON_MYTHIC));
        tooltip.add(new TranslatableComponent(KtmItemDesc.SHOW_DESC).withStyle(ChatFormatting.GRAY));
    }
}
