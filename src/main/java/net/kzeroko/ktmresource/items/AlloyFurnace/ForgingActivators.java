package net.kzeroko.ktmresource.items.AlloyFurnace;


import net.kzeroko.ktmresource.items.KtmItemTab;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class ForgingActivators extends Item {
    public ForgingActivators() {
        super(new Properties().tab(KtmItemTab.KTMRESOURCES));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(new TranslatableComponent("item.ktmresource."+this.getRegistryName().getPath()+".tooltip"));
    }

}
