package net.kzeroko.ktmresource.items.custom;

import net.kzeroko.ktmresource.client.particles.KtmParticles;
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

public class BlackSword extends SwordItem {
    public BlackSword(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        KtmItemDesc.WEAPON_ELITE(pTooltipComponents);

        pTooltipComponents.add(new TranslatableComponent("desc.ktmresource.elite_weapons").withStyle(KtmItemDesc.WEAPON_ELITE));

        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(new TranslatableComponent("desc.ktmresource.black_sword").withStyle(KtmItemDesc.DESCRIPTION));
        }
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker){
        if (!pTarget.level.isClientSide){
            Objects.requireNonNull(pTarget.getServer()).getLevel(pTarget.level.dimension())
                    .sendParticles(KtmParticles.BLADENORM.get(), pTarget.getX(), pTarget.getY()+pTarget.getEyeHeight()*0.618, pTarget.getZ(), 1,.0f, .0f, .0f, .01f);
        }
        return false;
    }
}
