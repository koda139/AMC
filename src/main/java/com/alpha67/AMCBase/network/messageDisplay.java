package com.alpha67.AMCBase.network;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class messageDisplay {

 //   private final String message;


   public messageDisplay(PacketBuffer buf) {
        //message = buf.writ();
    }

    public static void toBytes(messageDisplay md,PacketBuffer buf) {
       // buf.writeU;
    }


    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity player = ctx.get().getSender();

        ctx.get().setPacketHandled(true);
        });
    }
}
