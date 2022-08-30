package com.alpha67.AMCBase.tileentity;

import com.alpha67.AMCBase.init.ModBlocks;
import com.alpha67.AMCBase.init.ModItems;
import com.alpha67.AMCBase.init.ModTileEntities;
import com.alpha67.AMCBase.pluginManage.money;
import com.alpha67.AMCBase.tileentity.util.CustomEnergyStorage;
import com.alpha67.AMCBase.tileentity.util.IEnergyDisplay;
import com.alpha67.AMCBase.tileentity.util.ISharingEnergyProvider;
import com.alpha67.AMCBase.tileentity.util.TileEntityBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
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

public class ATMBlockTile extends TileEntityBase implements ITickableTileEntity, ISharingEnergyProvider, IEnergyDisplay {

    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    public final CustomEnergyStorage storage = new CustomEnergyStorage(40000, 1000, 0);
    public final LazyOptional<IEnergyStorage> lazyEnergy = LazyOptional.of(() -> this.storage);

    int state;
    int time;
    int finishTime = 3;
    int maxStack = 64;
    int i = 0;

    public int avanc;

    public ATMBlockTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public ATMBlockTile() {
        this(ModTileEntities.ATM_BLOCK_TILE.get());
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        System.out.println("packet 2");
        return new SUpdateTileEntityPacket(this.pos, 0, this.getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        System.out.println("packet 1");
        this.read(this.getBlockState(), pkt.getNbtCompound());
    }




    private ItemStackHandler createHandler() {
        return new ItemStackHandler(6) {
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

   @Override
    public void writeSyncableNBT(CompoundNBT compound, NBTType type) {
        if (type != NBTType.SAVE_BLOCK) {}
        this.storage.writeToNBT(compound);
        super.writeSyncableNBT(compound, type);
    }

    @Override
    public void readSyncableNBT(CompoundNBT compound, NBTType type) {
        if (type != NBTType.SAVE_BLOCK) {}
        this.storage.readFromNBT(compound);
        super.readSyncableNBT(compound, type);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }

        if (!this.removed && cap == CapabilityEnergy.ENERGY)
            return LazyOptional.of(() -> storage).cast();;

        return super.getCapability(cap, side);
    }

    public int getAvanc()
    {
        return this.getTileData().getInt("avanc");
    }


    public void getF()
    {
        String UUID = this.getTileData().getString("uuid");
        if(itemHandler.getStackInSlot(0) == ModItems.FIVE_CF.get().getDefaultInstance() || itemHandler.getStackInSlot(0).getCount() == 0) {
            if(money.removeMoney(UUID, 50))
            {
                itemHandler.insertItem(0, ModItems.FIFTY_CF.get().getDefaultInstance(), false);
            }
        }
    }

    public void getH()
    {
        String UUID = this.getTileData().getString("uuid");
        if(itemHandler.getStackInSlot(0) == ModItems.HUNDRED_CF.get().getDefaultInstance() || itemHandler.getStackInSlot(0).getCount() == 0) {
            if(money.removeMoney(UUID, 100))
            {
                itemHandler.insertItem(0, ModItems.HUNDRED_CF.get().getDefaultInstance(), false);
            }
        }
    }

    public void getFH()
    {
        String UUID = this.getTileData().getString("uuid");
        if(itemHandler.getStackInSlot(0) == ModItems.FIVE_HUNDRED_CF.get().getDefaultInstance() || itemHandler.getStackInSlot(0).getCount() == 0) {
            if(money.removeMoney(UUID, 500))
            {
                itemHandler.insertItem(0, ModItems.FIVE_HUNDRED_CF.get().getDefaultInstance(), false);
            }
        }
    }

    public void send()
    {
        String UUID = this.getTileData().getString("uuid");
        System.out.println(itemHandler.getStackInSlot(0));
        System.out.println(ModItems.FIVE_HUNDRED_CF.get().getDefaultInstance());
        if(itemHandler.getStackInSlot(0).getItem() == ModItems.ONE_CF.get().asItem())
        {
            money.giveMoney(UUID, 1*itemHandler.getStackInSlot(0).getCount());
            itemHandler.getStackInSlot(0).setCount(0);
        }
        else if(itemHandler.getStackInSlot(0).getItem() == ModItems.FIVE_CF.get().asItem())
        {
            money.giveMoney(UUID, 5*itemHandler.getStackInSlot(0).getCount());
            itemHandler.getStackInSlot(0).setCount(0);
        }
        else if(itemHandler.getStackInSlot(0).getItem() == ModItems.TEN_CF.get().asItem())
        {
            money.giveMoney(UUID, 10*itemHandler.getStackInSlot(0).getCount());
            itemHandler.getStackInSlot(0).setCount(0);
        }
        else if(itemHandler.getStackInSlot(0).getItem() == ModItems.TWENTY_CF.get().asItem())
        {
            money.giveMoney(UUID, 20*itemHandler.getStackInSlot(0).getCount());
            itemHandler.getStackInSlot(0).setCount(0);
        }
        else if(itemHandler.getStackInSlot(0).getItem() == ModItems.FIVE_CF.get().asItem())
        {
            money.giveMoney(UUID, 50*itemHandler.getStackInSlot(0).getCount());
            itemHandler.getStackInSlot(0).setCount(0);
        }
        else if(itemHandler.getStackInSlot(0).getItem() == ModItems.HUNDRED_CF.get().asItem())
        {
            money.giveMoney(UUID, 100*itemHandler.getStackInSlot(0).getCount());
            itemHandler.getStackInSlot(0).setCount(0);
        }
        else if(itemHandler.getStackInSlot(0).getItem() == ModItems.FIVE_HUNDRED_CF.get().asItem())
        {
            money.giveMoney(UUID, 500*itemHandler.getStackInSlot(0).getCount());
            itemHandler.getStackInSlot(0).setCount(0);
        }

    }


    @Override
    public void updateEntity() {
        super.updateEntity();
        if (!world.isRemote) {

        }
    }



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

    @Override
    public CustomEnergyStorage getEnergyStorage() {
        return storage;
    }

    @Override
    public boolean needsHoldShift() {
        return false;
    }
}
