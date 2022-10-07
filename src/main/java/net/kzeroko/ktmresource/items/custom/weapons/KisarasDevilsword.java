package net.kzeroko.ktmresource.items.custom.weapons;

import net.kzeroko.ktmresource.items.KtmItemDesc;
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

public class KisarasDevilsword extends SwordItem {
    public KisarasDevilsword(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        KtmItemDesc.WEAPON_ANOTHER(pTooltipComponents);

        pTooltipComponents.add(new TranslatableComponent("desc.ktmresource.another_weapons").withStyle(KtmItemDesc.WEAPON_ANOTHER));

        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(new TranslatableComponent("desc.ktmresource.kisaras_devilsword").withStyle(KtmItemDesc.DESCRIPTION));
        }
    }
}
