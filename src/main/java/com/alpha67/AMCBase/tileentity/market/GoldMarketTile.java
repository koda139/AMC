package com.alpha67.AMCBase.tileentity.market;

import com.alpha67.AMCBase.init.ModBlocks;
import com.alpha67.AMCBase.init.ModTileEntities;
import com.alpha67.AMCBase.pluginManage.money;
import com.alpha67.AMCBase.pluginManage.sell;
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
import net.minecraft.util.math.BlockPos;
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

public class GoldMarketTile extends TileEntityBase implements ITickableTileEntity, ISharingEnergyProvider, IEnergyDisplay {

    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    public final CustomEnergyStorage storage = new CustomEnergyStorage(40000, 1000, 0);
    public final LazyOptional<IEnergyStorage> lazyEnergy = LazyOptional.of(() -> this.storage);

    int state;
    int time;
    int finishTime = 10;
    int maxStack = 64;
    int i = 0;

    int x;
    int y;
    int z;

    boolean buttonClick;

    public int avanc;

    public GoldMarketTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public GoldMarketTile() {
        this(ModTileEntities.GOLD_MARKET_TILE.get());
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
        compound.put("inv", itemHandler.serializeNBT());
        super.writeSyncableNBT(compound, type);
    }

    @Override
    public void readSyncableNBT(CompoundNBT compound, NBTType type) {
        if (type != NBTType.SAVE_BLOCK) {}
        this.storage.readFromNBT(compound);
        this.buttonClick = this.getTileData().getBoolean("buttonClick");
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

            if (owner.contains("1") || owner.contains("2") ||owner.contains("3") ||owner.contains("4") ||owner.contains("5") ||owner.contains("6") || owner.contains("7") || owner.contains("8") || owner.contains("9")) {


                if (i >= 8) {
                    x = pos.getX();
                    y = pos.getY();
                    z = pos.getZ();
                    world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 1);

                    try {
                        Object ob2 = new JSONParser().parse(new FileReader("communication-alpha/playerData/" + owner + ".json"));
                        JSONObject js2 = (JSONObject) ob2;

                        double money = (double) js2.get("money");

                        this.getTileData().putDouble("money", money);

                        Object ob3 = new JSONParser().parse(new FileReader("communication-alpha/bridge-server-.json"));
                        JSONObject js3 = (JSONObject) ob3;

                        double GoldPrice = (double) js3.get("GoldPrice");
                        double GoldMax = (double) js3.get("GoldMax");

                        this.getTileData().putDouble("GoldPrice", GoldPrice);
                        this.getTileData().putDouble("GoldMax", GoldMax);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else
                    i = i + 1;

                // System.out.println("owner" + owner);

                if (itemHandler.getStackInSlot(0).getItem() == ModBlocks.GOLD_PALLET.get().asItem() && this.buttonClick && storage.getEnergyStored() >= 100) {
                    time = time + 1;
                    this.getTileData().putInt("GoldTime", time / 2);

                    if (this.buttonClick && world.getBlockState(new BlockPos((int) x, (int) y + 1, (int) z)).getBlock() == ModBlocks.ANTENNA.get()) {

                        if (time >= finishTime * 20) {

                            storage.extractEnergy(100, false);
                            time = 0;

                            this.getTileData().putBoolean("buttonClick", false);
                            this.buttonClick = false;

                            Object ob = null;
                            try {
                                ob = new JSONParser().parse(new FileReader("communication-alpha/bridge-server-.json"));
                                JSONObject js = (JSONObject) ob;

                                double GoldPrice = (double) js.get("GoldPrice");

                                System.out.println("give the money !!!!!");

                                money.giveMoney(owner, GoldPrice);

                                itemHandler.extractItem(0, 1, false);

                                sell.sell("gold");


                            } catch (IOException | ParseException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    } else {
                        time = 0;
                    }

                }
                else {time=0;}
            }
        }
    }

    public double getGoldPrice() {
        try{
            return this.getTileData().getDouble("GoldPrice");
        }

        catch (Exception e)
        {
            return -1;
        }
    }

    public int getGoldTime() {
        try{
            return this.getTileData().getInt("GoldTime");
        }

        catch (Exception e)
        {
            return -1;
        }
    }

    public double getMaxPrice() {
        try{
            return this.getTileData().getDouble("GoldMax");
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
