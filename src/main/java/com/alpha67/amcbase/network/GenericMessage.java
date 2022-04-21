package com.alpha67.amcbase.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class GenericMessage{


    String s;

    // Constructor for outgoing messages
    public GenericMessage(String s) {
        this.s = s;
    }

    public static GenericMessage fromBytes(PacketBuffer packetBuffer) {
        String s = packetBuffer.readUtf();
        return new GenericMessage(s);
    }

    // Just returns the message stored in the GenericMessage object
    public String getMessage() {
        return s;
    }


    public void toBytes(ByteBuf buf) {// Converts the message from the outgoing constructor to bytes for sending.
        buf.writeBytes(s.getBytes());
    }

    public static void handle(GenericMessage genericmessage, Supplier<NetworkEvent.Context> contextSupplier) {

        System.out.println("message incoming"+ contextSupplier.get().toString());

    }


}
