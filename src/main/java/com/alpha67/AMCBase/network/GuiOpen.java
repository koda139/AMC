package com.alpha67.AMCBase.network;

import com.alpha67.AMCBase.tileentity.ATMBlockTile;
import com.alpha67.AMCBase.tileentity.CoalGeneratorTile;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class GuiOpen {

    private final int x, y, z;
    private final Boolean guiOpen;


    public GuiOpen(ByteBuf buf) {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        guiOpen = buf.readBoolean();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeBoolean(guiOpen);
    }

    public GuiOpen(BlockPos pos, Boolean guiOpen) {
        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();
        this.guiOpen = guiOpen;
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity player = ctx.get().getSender();
            BlockPos pos = new BlockPos(x, y, z);

            if(player.getServerWorld().getTileEntity(pos) instanceof ATMBlockTile)
            {
                ATMBlockTile te = (ATMBlockTile) player.getServerWorld().getTileEntity(pos);
                te.isGuiOpen(guiOpen);
            }
            else if (player.getServerWorld().getTileEntity(pos) instanceof CoalGeneratorTile)
            {
                CoalGeneratorTile te = (CoalGeneratorTile) player.getServerWorld().getTileEntity(pos);
                te.isGuiOpen(guiOpen);
            }

        ctx.get().setPacketHandled(true);
        });
    }
}
