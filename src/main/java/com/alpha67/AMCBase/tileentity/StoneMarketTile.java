package com.alpha67.AMCBase.tileentity;

import com.alpha67.AMCBase.init.ModBlocks;
import com.alpha67.AMCBase.init.ModTileEntities;
import com.alpha67.AMCBase.tileentity.util.TileEntityBase;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.annotation.Nonnull;
import java.io.FileReader;
import java.util.UUID;

public class StoneMarketTile extends TileEntityBase {
    String test;

    int i = 0;
    int y = 0;
    double money;
    double stonePrice;
    double maxPrice;

    public StoneMarketTile() {
        super(ModTileEntities.STONE_MARKET_TILE.get());
    }
    public final ItemStackHandler itemHandler = createHandler();
    public final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    public StoneMarketTile(TileEntityType<?> tileentitytypeIn) {
        super(tileentitytypeIn);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("inv", itemHandler.serializeNBT());
        return super.write(compound);
    }

    private ItemStackHandler createHandler() {
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
                return 1;
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

            } catch (Exception er) {
              //er.printStackTrace();
            }
        }
       i = i+1;
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


    public double getData()
    {try{double teee = Double.parseDouble(this.getTileData().getString("money"));return teee;}
        catch (Exception e)
        {return -1;}

    }
}
