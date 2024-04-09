package com.alpha67.AMCBase.network;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ButtonPacket {

    public String value;
    int x;
    int y;
    int z;

    public ButtonPacket(String value)
    {
        this.value = value;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static void encode(ButtonPacket bp, PacketBuffer packetBuffer) {
        packetBuffer.writeString(bp.value);
        packetBuffer.writeInt(bp.x);
        packetBuffer.writeInt(bp.y);
        packetBuffer.writeInt(bp.z);
    }

    public static ButtonPacket decode(PacketBuffer packetBuffer) {
        String Value = packetBuffer.readString();
        int x = packetBuffer.readInt();
        return new ButtonPacket(Value);
    }

    public static void handle(ButtonPacket bp, Supplier<NetworkEvent.Context> contextSupplier) {
        System.out.println(bp.value);

        ServerPlayerEntity player = contextSupplier.get().getSender();
        //BlockPos pos = new BlockPos(x, y, z);
        //StoneMarketTile te = (StoneMarketTile) player.getServerWorld().getTileEntity(pos);






        //System.out.println(entity.container.getSlot(0).getStack());
        //entity.inventory.setInventorySlotContents(0,ModItems.AMC_LOGO.get().getDefaultInstance());



    }
}
