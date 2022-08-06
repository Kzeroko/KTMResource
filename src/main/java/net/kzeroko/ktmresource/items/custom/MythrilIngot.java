package net.kzeroko.ktmresource.items.custom;

import net.kzeroko.ktmresource.items.KtmItemDesc;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MythrilIngot extends Item {
    public MythrilIngot(Properties properties) {super(properties);}

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        KtmItemDesc.INGOT_MYTHIC(pTooltipComponents);
    }
}
