package com.alpha67.AMCBase.tileentity;

import com.alpha67.AMCBase.init.ModBlocks;
import com.alpha67.AMCBase.init.ModTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
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
import java.util.UUID;

public class StoneMarketTile extends TileEntity implements ITickableTileEntity {
    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    String test;

    int i = 0;
    int y = 0;
    double money;
    double stonePrice;
    double maxPrice;

    public StoneMarketTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public StoneMarketTile() {
        this(ModTileEntities.STONE_MARKET_TILE.get());
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("pos"));
        this.test = nbt.getString("pos");
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        //compound.put("inv", itemHandler.serializeNBT());
        compound.putString("pos", String.valueOf(pos));
        System.out.println("nbt save");
        return super.write(compound);
    }

    public ItemStackHandler createHandler() {
        return new ItemStackHandler(1) {
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
                return 64;
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

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        //System.out.println("packet2");
        return new SUpdateTileEntityPacket(this.pos, 0, this.getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        this.read(this.getBlockState(), pkt.getNbtCompound());
        //System.out.println("packet1");
    }

    private void strikeLightning() {
        if(!this.world.isRemote()) {
            EntityType.LIGHTNING_BOLT.spawn((ServerWorld)world, null, null,
                    pos, SpawnReason.TRIGGERED, true, true);
        }

    }

    public void craft() {
        Inventory inv = new Inventory(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inv.setInventorySlotContents(i, itemHandler.getStackInSlot(i));
        }





    //craftTheItem();

        //if (itemHandler.getSlots(0) )

       /* Optional<LightningChannelerRecipe> recipe = world.getRecipeManager()
                .getRecipe(ModRecipeTypes.LIGHTNING_RECIPE, inv, world);

        recipe.ifPresent(iRecipe -> {
            ItemStack output = iRecipe.getRecipeOutput();

            if(iRecipe.getWeather().equals(LightningChannelerRecipe.Weather.CLEAR) &&
                    !world.isRaining()) {
                craftTheItem(output);
            }

            if(iRecipe.getWeather().equals(LightningChannelerRecipe.Weather.RAIN) &&
                    world.isRaining()) {
                craftTheItem(output);
            }

            if(iRecipe.getWeather().equals(LightningChannelerRecipe.Weather.THUNDERING) &&
                    world.isThundering()) {
                strikeLightning();
                craftTheItem(output);
            }

            markDirty();
        });*/
    }

    public void craftTheItem() {
        //itemHandler.extractItem(0, 1, false);
        //itemHandler.extractItem(1, 1, false);
        //System.out.println("test");
        ItemStack stack = itemHandler.getStackInSlot(0).getStack();
        int count = itemHandler.getStackInSlot(0).getCount();
        //itemHandler.insertItem(0, ModItems.AMC_LOGO.get().getDefaultInstance(), false);
        itemHandler.getStackInSlot(0).setCount(count-1);

    }


    public double getData()
    {
        try{
            double teee = Double.parseDouble(this.getTileData().getString("money"));
            return teee;
        }

        catch (Exception e)
        {
            return -1;
        }

    }



    @Override
    public void tick() {
        if(world.isRemote)
            return;

        y = y+1;

       if(y >=40 )
        {

            y = 0;
            Object ob = null;
            Object stone = null;
            System.out.println("ok");
            try {



                UUID uuid = UUID.fromString(this.getTileEntity().getTileData().getString("player"));

                PlayerEntity player = world.getPlayerByUuid(uuid);

                ob = new JSONParser().parse(new FileReader("communication-alpha/playerData/"+uuid+".json"));
                JSONObject js = (JSONObject) ob;

                stone = new JSONParser().parse(new FileReader("communication-alpha/bridge-Server.json"));
                JSONObject jstone = (JSONObject) stone;

                //Boolean modif = (Boolean) js.get("modification");

                this.money = (double) js.get("money");
                this.stonePrice = (double) jstone.get("stone");
                this.maxPrice = (double) jstone.get("stoneMaxPrice");

                System.out.println(money);

                this.getTileData().putString("money", String.valueOf(this.money));
                this.getTileData().putString("stone", String.valueOf(this.stonePrice));
                this.getTileData().putString("stoneMaxPrice", String.valueOf(this.maxPrice));
                System.out.println( this.getTileData().get("owner"));

                //System.out.println(this.getTileData().getString("money"));

                BlockState bs = this.getBlockState();

                world.updateBlock(pos, ModBlocks.STONE_MARKET.get());

                if (world instanceof World) {
                    ((World) world).notifyBlockUpdate(pos, bs, bs, 3);
                }


               // System.out.println(this.money);



            } catch (Exception er) {
              er.printStackTrace();

            }

        }

       i = i+1;

        craft();
    }

    public double getStonePrice() {
        try{
            double teee = Double.parseDouble(this.getTileData().getString("stone"));
            return teee;
        }

        catch (Exception e)
        {
            return -1;
        }
    }

    public double getMaxPrice() {
        try{
            double teee = Double.parseDouble(this.getTileData().getString("stoneMaxPrice"));
            return teee;
        }

        catch (Exception e)
        {
            return -1;
        }
    }
}
