package com.alpha67.AMCBase.network;

import com.alpha67.AMCBase.tileentity.market.DiamondMarketTile;
import com.alpha67.AMCBase.tileentity.market.GoldMarketTile;
import com.alpha67.AMCBase.tileentity.market.StoneMarketTile;
import com.alpha67.AMCBase.tileentity.market.WoodMarketTile;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ButtonMarket {

    private final int x, y, z, id;


    public ButtonMarket(ByteBuf buf) {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        id = buf.readInt();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(id);
    }

    public ButtonMarket(BlockPos pos, int id) {
        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();
        this.id = id;
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity player = ctx.get().getSender();
            BlockPos pos = new BlockPos(x, y, z);

            if (id == 0)
            {
                StoneMarketTile te = (StoneMarketTile) player.getServerWorld().getTileEntity(pos);
                te.startSell();
            }
            else if (id == 1) {
                WoodMarketTile te = (WoodMarketTile) player.getServerWorld().getTileEntity(pos);
                te.startSell();
            }
            else if (id == 2) {
                GoldMarketTile te = (GoldMarketTile) player.getServerWorld().getTileEntity(pos);
                te.startSell();
            }
            else if (id == 3) {
                DiamondMarketTile te = (DiamondMarketTile) player.getServerWorld().getTileEntity(pos);
                te.startSell();
            }
            else {
                System.out.println("no id found");
            }


            //te.craftTheItem();


        ctx.get().setPacketHandled(true);
        });
    }
}
