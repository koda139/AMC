/*
 * This file ("WorldUtil.java") is part of the Actually Additions mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Actually Additions License to be found at
 * http://ellpeck.de/actaddlicense
 * View the source code at https://github.com/Ellpeck/ActuallyAdditions
 *
 * © 2015-2017 Ellpeck
 */

package com.alpha67.AMCBase.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.ArrayList;
import java.util.List;

public final class WorldUtil {





    public static void doEnergyInteraction(TileEntity tileFrom, TileEntity tileTo, Direction sideTo, int maxTransfer) {
        if (maxTransfer > 0) {
            Direction opp = sideTo == null
                    ? null
                    : sideTo.getOpposite();
            LazyOptional<IEnergyStorage> handlerFrom = tileFrom.getCapability(CapabilityEnergy.ENERGY, sideTo);
            LazyOptional<IEnergyStorage> handlerTo = tileTo.getCapability(CapabilityEnergy.ENERGY, opp);
            handlerFrom.ifPresent((from) -> {
                handlerTo.ifPresent((to) -> {
                    int drain = from.extractEnergy(maxTransfer, true);
                    if (drain > 0) {
                        int filled = to.receiveEnergy(drain, false);
                        from.extractEnergy(filled, false);
                    }
                });
            });
        }
    }

    public static void doFluidInteraction(TileEntity tileFrom, TileEntity tileTo, Direction sideTo, int maxTransfer) {
        if (maxTransfer > 0) {
            LazyOptional<IFluidHandler> optionalFrom = tileFrom.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, sideTo);
            LazyOptional<IFluidHandler> optionalTo = tileTo.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, sideTo.getOpposite());
            optionalFrom.ifPresent((from) -> {
                optionalTo.ifPresent((to) -> {
                    FluidStack drain = from.drain(maxTransfer, IFluidHandler.FluidAction.SIMULATE);
                    if (!drain.isEmpty()) {
                        int filled = to.fill(drain.copy(), IFluidHandler.FluidAction.EXECUTE);
                        from.drain(filled, IFluidHandler.FluidAction.EXECUTE);
                    }
                });
            });
        }
    }

    /**
     * Checks if a given Block with a given Meta is present in given Positions
     *
     * @param positions The Positions, an array of {x, y, z} arrays containing Positions
     * @param block     The Block
     * @param world     The World
     * @return Is every block present?
     */
    public static boolean hasBlocksInPlacesGiven(BlockPos[] positions, Block block, World world) {
        for (BlockPos pos : positions) {
            BlockState state = world.getBlockState(pos);
            if (!(state.getBlock() == block)) {
                return false;
            }
        }
        return true;
    }



    public static Direction getDirectionBySidesInOrder(int side) {
        switch (side) {
            case 0:
                return Direction.UP;
            case 1:
                return Direction.DOWN;
            case 2:
                return Direction.NORTH;
            case 3:
                return Direction.EAST;
            case 4:
                return Direction.SOUTH;
            default:
                return Direction.WEST;
        }
    }
}






    //I think something is up with this, but I'm not entirely certain what.


