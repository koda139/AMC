package com.alpha67.AMCBase.util;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.server.ServerWorld;

public final class VanillaPacketDispatcher {

    //Don't call from the client.
    public static void dispatchTEToNearbyPlayers(TileEntity tile) {
        ServerWorld world = (ServerWorld) tile.getWorld();
        Chunk chunk = world.getChunk(tile.getPos().getX() >> 4, tile.getPos().getZ() >> 4);

        world.getChunkProvider().chunkManager.getTrackingPlayers(chunk.getPos(), false).forEach(e -> {
            e.connection.sendPacket(tile.getUpdatePacket());
        });
    }

    public static void dispatchTEToNearbyPlayers(World world, BlockPos pos) {
        TileEntity tile = world.getTileEntity(pos);
        if (tile != null) {
            dispatchTEToNearbyPlayers(tile);
        }
    }
}
