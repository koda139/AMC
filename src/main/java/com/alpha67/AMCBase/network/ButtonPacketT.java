package com.alpha67.AMCBase.network;

import com.alpha67.AMCBase.tileentity.StoneMarketTile;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ButtonPacketT {

    private final int x, y, z;


    public ButtonPacketT(ByteBuf buf) {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
    }

    public ButtonPacketT(BlockPos pos) {
        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity player = ctx.get().getSender();
            BlockPos pos = new BlockPos(x, y, z);
            StoneMarketTile te = (StoneMarketTile) player.getServerWorld().getTileEntity(pos);


            //te.craftTheItem();


        ctx.get().setPacketHandled(true);
        });
    }
}
