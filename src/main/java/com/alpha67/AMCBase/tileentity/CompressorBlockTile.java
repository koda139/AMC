package com.alpha67.AMCBase.tileentity;

import com.alpha67.AMCBase.init.ModBlocks;
import com.alpha67.AMCBase.init.ModItems;
import com.alpha67.AMCBase.init.ModTileEntities;
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CompressorBlockTile extends TileEntityBase implements ITickableTileEntity, ISharingEnergyProvider, IEnergyDisplay {

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

    public CompressorBlockTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public CompressorBlockTile() {
        this(ModTileEntities.COMPRESSOR_BLOCK_TILE.get());
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


    @Override
    public void updateEntity() {
        super.updateEntity();
        if (!world.isRemote) {

            avanc = (time/finishTime*100)/20;
            this.getTileData().putInt("avanc", avanc);

            if(i >=5)
                world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 1);
            else
                i = i+1;
            // System.out.println("state2"+state);
            if (storage.getEnergyStored() >= 100) {
                if (itemHandler.getStackInSlot(0).getItem() == Items.SLIME_BALL.getItem() && itemHandler.getStackInSlot(0).getCount() >= 1) {
                    if (itemHandler.getStackInSlot(1).getItem() == Blocks.COBBLESTONE.asItem() && itemHandler.getStackInSlot(1).getCount() >= 64) {
                        if (itemHandler.getStackInSlot(2).getItem() == ModItems.EMPTY_PALLET.get() && itemHandler.getStackInSlot(2).getCount() >= 1) {
                            if (itemHandler.getStackInSlot(3).getItem() == Blocks.COBBLESTONE.asItem() && itemHandler.getStackInSlot(3).getCount() >= 64) {
                                if (itemHandler.getStackInSlot(4).getItem() == Blocks.COBBLESTONE.asItem() && itemHandler.getStackInSlot(4).getCount() >= 64) {
                                    if (itemHandler.getStackInSlot(5).getCount() == 0)
                                    {
                                    time = time + 1;
                                    storage.setEnergyStored(storage.getEnergyStored()-100);

                                    state = 2;

                                    if (time >= finishTime * 20) {

                                        state = 0;
                                        time = 0;

                                        itemHandler.extractItem(0, 1, false);
                                        itemHandler.extractItem(1, 64, false);
                                        itemHandler.extractItem(2, 1, false);
                                        itemHandler.extractItem(3, 64, false);
                                        itemHandler.extractItem(4, 64, false);

                                        itemHandler.insertItem(5, ModBlocks.STONE_PALLET.get().asItem().getDefaultInstance(), false);

                                    }
                                    }
                                }
                            }
                        }
                    }
                }

                if (itemHandler.getStackInSlot(0).getItem() == Items.SLIME_BALL.getItem() && itemHandler.getStackInSlot(0).getCount() >= 1) {
                    if (itemHandler.getStackInSlot(1).getItem() == Blocks.OAK_PLANKS.asItem() && itemHandler.getStackInSlot(1).getCount() >= 64) {
                        if (itemHandler.getStackInSlot(2).getItem() == ModItems.EMPTY_PALLET.get() && itemHandler.getStackInSlot(2).getCount() >= 1) {
                            if (itemHandler.getStackInSlot(3).getItem() == Blocks.OAK_PLANKS.asItem() && itemHandler.getStackInSlot(3).getCount() >= 64) {
                                if (itemHandler.getStackInSlot(4).getItem() == Blocks.OAK_PLANKS.asItem() && itemHandler.getStackInSlot(4).getCount() >= 64) {
                                    if (itemHandler.getStackInSlot(5).getCount() == 0)
                                    {
                                        time = time + 1;
                                        storage.setEnergyStored(storage.getEnergyStored()-100);

                                        state = 2;

                                        if (time >= finishTime * 20) {

                                            state = 0;
                                            time = 0;

                                            itemHandler.extractItem(0, 1, false);
                                            itemHandler.extractItem(1, 64, false);
                                            itemHandler.extractItem(2, 1, false);
                                            itemHandler.extractItem(3, 64, false);
                                            itemHandler.extractItem(4, 64, false);

                                            itemHandler.insertItem(5, ModBlocks.WOOD_PALLET.get().asItem().getDefaultInstance(), false);

                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                if (itemHandler.getStackInSlot(0).getItem() == Items.SLIME_BALL.getItem() && itemHandler.getStackInSlot(0).getCount() >= 1) {
                    if (itemHandler.getStackInSlot(1).getItem() == Blocks.GOLD_BLOCK.asItem() && itemHandler.getStackInSlot(1).getCount() >= 1) {
                        if (itemHandler.getStackInSlot(2).getItem() == ModItems.EMPTY_PALLET.get() && itemHandler.getStackInSlot(2).getCount() >= 1) {
                            if (itemHandler.getStackInSlot(3).getItem() == Blocks.GOLD_BLOCK.asItem() && itemHandler.getStackInSlot(3).getCount() >= 1) {
                                if (itemHandler.getStackInSlot(4).getItem() == Blocks.GOLD_BLOCK.asItem() && itemHandler.getStackInSlot(4).getCount() >= 1) {
                                    if (itemHandler.getStackInSlot(5).getCount() == 0)
                                    {
                                        time = time + 1;
                                        storage.setEnergyStored(storage.getEnergyStored()-100);

                                        state = 2;

                                        if (time >= finishTime * 20) {

                                            state = 0;
                                            time = 0;

                                            itemHandler.extractItem(0, 1, false);
                                            itemHandler.extractItem(1, 1, false);
                                            itemHandler.extractItem(2, 1, false);
                                            itemHandler.extractItem(3, 1, false);
                                            itemHandler.extractItem(4, 1, false);

                                            itemHandler.insertItem(5, ModBlocks.GOLD_PALLET.get().asItem().getDefaultInstance(), false);

                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                if (itemHandler.getStackInSlot(0).getItem() == Items.SLIME_BALL.getItem() && itemHandler.getStackInSlot(0).getCount() >= 1) {
                    if (itemHandler.getStackInSlot(1).getItem() == Items.DIAMOND.getItem() && itemHandler.getStackInSlot(1).getCount() >= 2) {
                        if (itemHandler.getStackInSlot(2).getItem() == ModItems.EMPTY_PALLET.get() && itemHandler.getStackInSlot(2).getCount() >= 1) {
                            if (itemHandler.getStackInSlot(3).getItem() == Items.DIAMOND.getItem() && itemHandler.getStackInSlot(3).getCount() >= 2) {
                                if (itemHandler.getStackInSlot(4).getItem() == Items.DIAMOND.getItem() && itemHandler.getStackInSlot(4).getCount() >= 2) {
                                    if (itemHandler.getStackInSlot(5).getCount() == 0)
                                    {
                                        time = time + 1;
                                        storage.setEnergyStored(storage.getEnergyStored()-100);

                                        state = 2;

                                        if (time >= finishTime * 20) {

                                            state = 0;
                                            time = 0;

                                            itemHandler.extractItem(0, 1, false);
                                            itemHandler.extractItem(1, 2, false);
                                            itemHandler.extractItem(2, 1, false);
                                            itemHandler.extractItem(3, 2, false);
                                            itemHandler.extractItem(4, 2, false);

                                            itemHandler.insertItem(5, ModBlocks.DIAMOND_PALLET.get().asItem().getDefaultInstance(), false);

                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
            else {
                time = 0;
            }
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
