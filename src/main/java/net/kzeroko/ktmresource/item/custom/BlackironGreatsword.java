package net.kzeroko.ktmresource.item.custom;

import net.kzeroko.ktmresource.item.KtmItemDesc;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlackironGreatsword extends SwordItem {
    public BlackironGreatsword(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        KtmItemDesc.WEAPON_ELITE(pTooltipComponents);

        pTooltipComponents.add(new TranslatableComponent("desc.ktmresource.elite_weapons").withStyle(KtmItemDesc.WEAPON_ELITE));

        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(new TranslatableComponent("desc.ktmresource.blackiron_greatsword").withStyle(KtmItemDesc.DESCRIPTION));
        }
    }
}
