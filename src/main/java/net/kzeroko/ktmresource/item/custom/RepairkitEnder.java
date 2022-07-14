package net.kzeroko.ktmresource.item.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RepairkitEnder extends Item {
    public RepairkitEnder(Properties properties) {super(properties);}

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {

        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(new TranslatableComponent("tooltip.ktmresource.ender_repairkit"));
        } else {
            pTooltipComponents.add(new TranslatableComponent("tooltip.ktmresource.press_shift"));
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
