package com.alpha67.AMCBase.tileentity;

import com.alpha67.AMCBase.init.ModItems;
import com.alpha67.AMCBase.init.ModTileEntities;
import com.alpha67.AMCBase.tileentity.util.CustomEnergyStorage;
import com.alpha67.AMCBase.tileentity.util.ISharingEnergyProvider;
import com.alpha67.AMCBase.tileentity.util.TileEntityBase;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CompressorBlockTile extends TileEntityBase implements ISharingEnergyProvider {
    int state;
    int slotNumber = 6;
    int maxStack = 64;

    public int finishTime = 1;

  //  public final CustomEnergyStorage storage = new CustomEnergyStorage(60000, 0, 80);
   // public final LazyOptional<IEnergyStorage> lazyEnergy = LazyOptional.of(() -> this.storage);

    public CompressorBlockTile() {
        super(ModTileEntities.COMPRESSOR_BLOCK_TILE.get());
    }

   public final ItemStackHandler itemHandler = createHandler();
    public final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
       // this.storage.readFromNBT(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        if (nbt.get("energyStorage") != null)
            CapabilityEnergy.ENERGY.readNBT(storage, null, nbt.get("energyStorage"));
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
       // this.storage.writeToNBT(compound);
        compound.put("inv", itemHandler.serializeNBT());
        compound.put("energyStorage", CapabilityEnergy.ENERGY.writeNBT(storage, null));
        return super.write(compound);
    }

    public int getState() {
        return  0;
        //return this.getTileData().getInt("state");
    }

    public int getTime() {
        return 0;
        //return this.getTileData().getInt("time");
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(slotNumber) {
            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return true;
            }

            @Override
            public int getSlotLimit(int slot) {
                return maxStack;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if(!isItemValid(slot, stack)) {
                    return stack;
                }

                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    int time;
    int i;
    int a =0;

   @Override
    public void updateEntity() {

        super.updateEntity();

     if(world.isRemote)
            return;

        this.getTileData().putInt("state", state);
        this.getTileData().putInt("time", time);
       // System.out.println("state2"+state);
/*
        if (itemHandler.getStackInSlot(1).getItem() == Blocks.COBBLESTONE.asItem() || itemHandler.getStackInSlot(2).getItem() == Blocks.COBBLESTONE.asItem() ||
                    itemHandler.getStackInSlot(3).getItem() == Blocks.COBBLESTONE.asItem() || itemHandler.getStackInSlot(4).getItem() == Blocks.COBBLESTONE.asItem()) {
                this.state = 1;
               // System.out.println("cobbleeee");
            } else {
                this.state = 0;
            }

         if (itemHandler.getStackInSlot(0).getItem() == Items.SLIME_BALL.getItem() && itemHandler.getStackInSlot(0).getCount() >= 1) {
                System.out.println("1");
                if (itemHandler.getStackInSlot(1).getItem() == Blocks.COBBLESTONE.asItem() && itemHandler.getStackInSlot(1).getCount() >= 64) {
                    System.out.println("2");
                    if (itemHandler.getStackInSlot(2).getItem() == ModItems.EMPTY_PALLET.get() && itemHandler.getStackInSlot(2).getCount() >= 64) {
                        if (itemHandler.getStackInSlot(3).getItem() == Blocks.COBBLESTONE.asItem() && itemHandler.getStackInSlot(3).getCount() >= 64) {
                            if (itemHandler.getStackInSlot(4).getItem() == Blocks.COBBLESTONE.asItem() && itemHandler.getStackInSlot(4).getCount() >= 64) {
                                time = time + 1;

                                System.out.println("3");

                                state = 2;

                                if (time >= finishTime*20) {

                                    state = 0;
                                    time = 0;

                                    System.out.println("4");

                                    itemHandler.extractItem(0, 1, false);
                                    itemHandler.extractItem(1, 64, false);
                                    itemHandler.extractItem(2, 64, false);
                                    itemHandler.extractItem(3, 64, false);
                                    itemHandler.extractItem(4, 64, false);

                                    itemHandler.insertItem(5, ModItems.STONE_PALLET.get().getDefaultInstance(), false);
                                }
                            }
                        }
                    }
                }
            }*/





    }


    public final CustomEnergyStorage storage = new CustomEnergyStorage(500000, 0, 700);
    public final LazyOptional<IEnergyStorage> lazyEnergy = LazyOptional.of(() -> this.storage);



    @Override
    public int getEnergyToSplitShare() {
        return this.storage.getEnergyStored();
    }

    @Override
    public boolean doesShareEnergy() {
        return true;
    }

    @Override
    public Direction[] getEnergyShareSides() {
        return Direction.values();
    }

    @Override
    public boolean canShareTo(TileEntity tile) {
        return true;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        if (!this.removed && cap == CapabilityEnergy.ENERGY)
            return LazyOptional.of(() -> state).cast();;

        return super.getCapability(cap, side);
    }



}
