package net.kzeroko.ktmresource.item.custom;

import net.kzeroko.ktmresource.item.KtmItemDesc;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EternalStone extends Item {
    public EternalStone(Properties properties) {super(properties);}

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {

        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(new TranslatableComponent("desc.ktmresource.mythic_item").withStyle(KtmItemDesc.WEAPON_MYTHIC));
            pTooltipComponents.add(new TranslatableComponent("tooltip.ktmresource.eternal_stone"));
        } else {
            pTooltipComponents.add(new TranslatableComponent("desc.ktmresource.mythic_item").withStyle(KtmItemDesc.WEAPON_MYTHIC));
            pTooltipComponents.add(new TranslatableComponent("tooltip.ktmresource.press_shift"));
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
