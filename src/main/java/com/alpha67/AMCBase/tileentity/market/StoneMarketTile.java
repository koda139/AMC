package com.alpha67.AMCBase.tileentity.market;

import com.alpha67.AMCBase.init.ModBlocks;
import com.alpha67.AMCBase.init.ModItems;
import com.alpha67.AMCBase.init.ModTileEntities;
import com.alpha67.AMCBase.pluginManage.money;
import com.alpha67.AMCBase.tileentity.util.CustomEnergyStorage;
import com.alpha67.AMCBase.tileentity.util.IEnergyDisplay;
import com.alpha67.AMCBase.tileentity.util.ISharingEnergyProvider;
import com.alpha67.AMCBase.tileentity.util.TileEntityBase;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
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
import org.json.simple.parser.ParseException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.FileReader;
import java.io.IOException;

public class StoneMarketTile extends TileEntityBase implements ITickableTileEntity, ISharingEnergyProvider, IEnergyDisplay {

    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    public final CustomEnergyStorage storage = new CustomEnergyStorage(40000, 1000, 0);
    public final LazyOptional<IEnergyStorage> lazyEnergy = LazyOptional.of(() -> this.storage);

    int state;
    int time;
    int finishTime = 10;
    int maxStack = 64;
    int i = 0;

    boolean buttonClick;

    public int avanc;

    public StoneMarketTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public StoneMarketTile() {
        this(ModTileEntities.STONE_MARKET_TILE.get());
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        //System.out.println("packet 2");
        return new SUpdateTileEntityPacket(this.pos, 0, this.getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
       // System.out.println("packet 1");
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
        this.buttonClick = this.getTileData().getBoolean("buttonClick");
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

    public void startSell()
    {
        this.buttonClick = true;
        this.getTileData().putBoolean("buttonClick", true);
    }


    @Override
    public void updateEntity() {
        super.updateEntity();
        if (!world.isRemote) {

            avanc = (time/(finishTime*20))*100;
            this.getTileData().putInt("avanc", avanc);
            String owner = this.getTileData().getString("owner");

            if(i >=5)
                world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 1);
            else
                i = i+1;

            System.out.println("energy store "+storage.getEnergyStored());
           // System.out.println("owner" + owner);

            try {
                Object ob2 = new JSONParser().parse(new FileReader("communication-alpha/playerData/"+owner+".json"));
                JSONObject js2 = (JSONObject) ob2;

                double money = (double) js2.get("money");

                this.getTileData().putDouble("money", money);

                Object ob3 = new JSONParser().parse(new FileReader("communication-alpha/bridge-Server.json"));
                JSONObject js3 = (JSONObject) ob3;

                double stonePrice = (double) js3.get("StonePrice");
                double stoneMax = (double) js3.get("StoneMax");

                this.getTileData().putDouble("StonePrice", stonePrice);
                this.getTileData().putDouble("StoneMax", stoneMax);


            } catch (Exception e) {
                e.printStackTrace();
            }



            if (itemHandler.getStackInSlot(0).getItem() == ModBlocks.STONE_PALLET.get().asItem() && this.buttonClick)
            {
                time = time+1;
                this.getTileData().putInt("stoneTime", time);

                if (time >= finishTime*20 && this.buttonClick)
                {
                    System.out.println(time);
                    time = 0;

                    this.getTileData().putBoolean("buttonClick", false);
                    this.buttonClick = false;

                    Object ob = null;
                    try {
                        ob = new JSONParser().parse(new FileReader("communication-alpha/bridge-Server.json"));
                        JSONObject js = (JSONObject) ob;

                        double stonePrice = (double) js.get("stonePrice");

                        System.out.println("give the money !!!!!");

                        money.giveMoney(owner, stonePrice);

                        itemHandler.extractItem(0, 1, false);


                    } catch (IOException | ParseException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        }
    }

    public double getStonePrice() {
        try{
            return this.getTileData().getDouble("stonePrice");
        }

        catch (Exception e)
        {
            return -1;
        }
    }

    public int getStoneTime() {
        try{
            return this.getTileData().getInt("stoneTime");
        }

        catch (Exception e)
        {
            return -1;
        }
    }

    public double getMaxPrice() {
        try{
            return this.getTileData().getDouble("stoneMax");
        }

        catch (Exception e)
        {
            return -1;
        }
    }

    public double getMoney()
    {
        try{
            double teee = this.getTileData().getDouble("money");
            //System.out.println("teeee"+teee);
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
