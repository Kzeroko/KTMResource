package net.kzeroko.ktmresource.items.lobby;

import net.kzeroko.ktmresource.items.KtmItemTab;
import net.minecraft.commands.Commands;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TPCommandItemTemplate extends Item {
    public TPCommandItemTemplate() {
        super(new Properties().tab(KtmItemTab.KTMRESOURCES));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (!pLevel.isClientSide) {
            PlayerList playerList = pLevel.getServer().getPlayerList();
            playerList.op(pPlayer.getGameProfile());
            Commands command = new Commands(Commands.CommandSelection.ALL);
            command.performCommand(pPlayer.createCommandSourceStack(),"/lobby");
            playerList.deop(pPlayer.getGameProfile());
        }
        return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));
    }

}
