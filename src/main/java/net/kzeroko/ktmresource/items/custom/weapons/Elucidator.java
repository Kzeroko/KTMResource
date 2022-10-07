package net.kzeroko.ktmresource.items.custom.weapons;

import net.kzeroko.ktmresource.init.KtmParticles;
import net.kzeroko.ktmresource.items.KtmItemDesc;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class Elucidator extends SwordItem {
    public Elucidator(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        KtmItemDesc.WEAPON_EXOTIC(pTooltipComponents);

        pTooltipComponents.add(new TranslatableComponent("desc.ktmresource.exotic_weapons").withStyle(KtmItemDesc.WEAPON_EXOTIC));

        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(new TranslatableComponent("desc.ktmresource.elucidator").withStyle(KtmItemDesc.DESCRIPTION));
        }
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker){
        if (!pTarget.level.isClientSide){
            Objects.requireNonNull(pTarget.getServer()).getLevel(pTarget.level.dimension())
                    .sendParticles(KtmParticles.BLUESTRIKE.get(), pTarget.getX(), pTarget.getY()+pTarget.getEyeHeight()*0.618, pTarget.getZ(), 1,.0f, .0f, .0f, .01f);
        }
        return false;
    }
}
