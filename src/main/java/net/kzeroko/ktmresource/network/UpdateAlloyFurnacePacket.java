package net.kzeroko.ktmresource.network;


import net.kzeroko.ktmresource.containers.AlloyFurnaceContainer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class UpdateAlloyFurnacePacket {
    private final boolean isActive;

    public UpdateAlloyFurnacePacket(boolean isActive) {
        this.isActive = isActive;
    }

    public static void encode(UpdateAlloyFurnacePacket message, FriendlyByteBuf buffer) {
        buffer.writeBoolean(message.isActive);
    }

    public static UpdateAlloyFurnacePacket decode(FriendlyByteBuf buffer) {
        return new UpdateAlloyFurnacePacket(buffer.readBoolean());
    }

    public static void handle(UpdateAlloyFurnacePacket message, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            Player playerEntity = context.get().getSender();

            if (playerEntity != null && playerEntity.getServer() != null && playerEntity.containerMenu instanceof AlloyFurnaceContainer) {
                ((AlloyFurnaceContainer)playerEntity.containerMenu).setForgeActive(message.isActive);
            }
        });

        context.get().setPacketHandled(true);
    }
}
