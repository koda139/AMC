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
import net.minecraft.item.Items;
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
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.FileReader;

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
    int a = 0;

    Boolean guiOpen = false;

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
       compound.put("inv", itemHandler.serializeNBT());
        super.writeSyncableNBT(compound, type);
    }

    @Override
    public void readSyncableNBT(CompoundNBT compound, NBTType type) {
        if (type != NBTType.SAVE_BLOCK) {}
        this.storage.readFromNBT(compound);
        itemHandler.deserializeNBT(compound.getCompound("inv"));
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
        if(itemHandler.getStackInSlot(0).getItem() == ModItems.FIVE_CF.get().asItem() || itemHandler.getStackInSlot(0).getCount() == 0
                && itemHandler.getStackInSlot(1).getItem() == Items.PAPER.asItem() && this.getEnergyStorage().getEnergyStored() >= 1500) {
            if(money.removeMoney(UUID, 50))
            {
                storage.setEnergyStored(storage.getEnergyStored()-1500);
                itemHandler.extractItem(1, 1, false);
                itemHandler.insertItem(0, ModItems.FIFTY_CF.get().getDefaultInstance(), false);
            }
        }
    }

    public void getH()
    {
        String UUID = this.getTileData().getString("uuid");
        if(itemHandler.getStackInSlot(0).getItem() == ModItems.HUNDRED_CF.get().asItem() || itemHandler.getStackInSlot(0).getCount() == 0
                && itemHandler.getStackInSlot(1).getItem() == Items.PAPER.asItem() && this.getEnergyStorage().getEnergyStored() >= 1500) {
            if(money.removeMoney(UUID, 100))
            {
                storage.setEnergyStored(storage.getEnergyStored()-1500);
                itemHandler.extractItem(1, 1, false);
                itemHandler.insertItem(0, ModItems.HUNDRED_CF.get().getDefaultInstance(), false);
            }
        }
    }

    public void getFH()
    {
        String UUID = this.getTileData().getString("uuid");
        if(itemHandler.getStackInSlot(0).getItem() == ModItems.FIVE_HUNDRED_CF.get().asItem() || itemHandler.getStackInSlot(0).getCount() == 0
                && itemHandler.getStackInSlot(1).getItem() == Items.PAPER.asItem() && this.getEnergyStorage().getEnergyStored() >= 1500){
            if(money.removeMoney(UUID, 500))
            {
                storage.setEnergyStored(storage.getEnergyStored()-1500);
                itemHandler.extractItem(1, 1, false);
                itemHandler.insertItem(0, ModItems.FIVE_HUNDRED_CF.get().getDefaultInstance(), false);
            }
        }
    }

    public void send()
    {
        String UUID = this.getTileData().getString("uuid");
        int itemNumber = itemHandler.getStackInSlot(0).getCount();

        if(this.getEnergyStorage().getEnergyStored() >= 750*itemNumber)
        {
            if(itemHandler.getStackInSlot(0).getItem() == ModItems.ONE_CF.get().asItem())
            {
                storage.setEnergyStored(storage.getEnergyStored()-1000*itemNumber);
                money.giveMoney(UUID, itemNumber*1*itemHandler.getStackInSlot(0).getCount());
                itemHandler.getStackInSlot(0).setCount(0);
            }
            else if(itemHandler.getStackInSlot(0).getItem() == ModItems.FIVE_CF.get().asItem())
            {
                storage.setEnergyStored(storage.getEnergyStored()-1000*itemNumber);
                money.giveMoney(UUID, itemNumber*5*itemHandler.getStackInSlot(0).getCount());
                itemHandler.getStackInSlot(0).setCount(0);
            }
            else if(itemHandler.getStackInSlot(0).getItem() == ModItems.TEN_CF.get().asItem())
            {
                storage.setEnergyStored(storage.getEnergyStored()-1000*itemNumber);
                money.giveMoney(UUID, itemNumber*10*itemHandler.getStackInSlot(0).getCount());
                itemHandler.getStackInSlot(0).setCount(0);
            }
            else if(itemHandler.getStackInSlot(0).getItem() == ModItems.TWENTY_CF.get().asItem())
            {
                storage.setEnergyStored(storage.getEnergyStored()-1000*itemNumber);
                money.giveMoney(UUID, itemNumber*20*itemHandler.getStackInSlot(0).getCount());
                itemHandler.getStackInSlot(0).setCount(0);
            }
            else if(itemHandler.getStackInSlot(0).getItem() == ModItems.FIFTY_CF.get().asItem())
            {
                storage.setEnergyStored(storage.getEnergyStored()-1000*itemNumber);
                money.giveMoney(UUID, itemNumber*50*itemHandler.getStackInSlot(0).getCount());
                itemHandler.getStackInSlot(0).setCount(0);
            }
            else if(itemHandler.getStackInSlot(0).getItem() == ModItems.HUNDRED_CF.get().asItem())
            {
                storage.setEnergyStored(storage.getEnergyStored()-1000*itemNumber);
                money.giveMoney(UUID, itemNumber*100*itemHandler.getStackInSlot(0).getCount());
                itemHandler.getStackInSlot(0).setCount(0);
            }
            else if(itemHandler.getStackInSlot(0).getItem() == ModItems.FIVE_HUNDRED_CF.get().asItem())
            {
                storage.setEnergyStored(storage.getEnergyStored()-1000*itemNumber);
                money.giveMoney(UUID, itemNumber*500*itemHandler.getStackInSlot(0).getCount());
                itemHandler.getStackInSlot(0).setCount(0);
            }

        }

    }

    public void isGuiOpen(Boolean guiopen){
        guiOpen = guiopen;
        System.out.println(guiopen + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }


    @Override
    public void updateEntity() {
        super.updateEntity();
        if (!world.isRemote) {

            if(guiOpen) {

                this.getEnergyStorage().extractEnergy(7, false);

                if (a >= 10) {
                    System.out.println(this.getEnergyStorage().getEnergyStored());
                    a = 0;
                    String UUID = this.getTileData().getString("uuid");

                    try {
                        Object ob2 = new JSONParser().parse(new FileReader("communication-alpha/playerData/" + UUID + ".json"));
                        JSONObject js2 = (JSONObject) ob2;

                        double money = (double) js2.get("money");

                        this.getTileData().putDouble("money", money);
                        System.out.println(money);

                        Object ob3 = new JSONParser().parse(new FileReader("communication-alpha/bridge-server-.json"));
                        JSONObject js3 = (JSONObject) ob3;


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                a = a + 1;

            }

        }
    }

    public double getMoney()
    {
        try{
            double teee = this.getTileData().getDouble("money");
            System.out.println(teee);
            return teee;}
        catch (Exception e)
        {
            return -2;
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
        return false;
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
