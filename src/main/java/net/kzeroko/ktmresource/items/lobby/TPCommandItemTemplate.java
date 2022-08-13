package net.kzeroko.ktmresource.items.lobby;

import net.kzeroko.ktmresource.items.KtmItemTab;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.ClientCommandHandler;

public class TPCommandItemTemplate extends Item {
    public TPCommandItemTemplate() {
        super(new Properties().tab(KtmItemTab.KTMRESOURCES));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (pLevel.isClientSide) {
            ClientCommandHandler.sendMessage("/lobby");
        }
        return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));
    }

}
