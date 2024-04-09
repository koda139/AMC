package com.alpha67.AMCBase.tileentity;

import com.alpha67.AMCBase.init.ModTileEntities;
import com.alpha67.AMCBase.tileentity.util.CustomEnergyStorage;
import com.alpha67.AMCBase.tileentity.util.ISharingEnergyProvider;
import com.alpha67.AMCBase.tileentity.util.TileEntityBase;
import com.alpha67.AMCBase.util.WorldUtil;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class electricBankTile extends TileEntityBase implements ITickableTileEntity, ISharingEnergyProvider {

    public electricBankTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public electricBankTile() {
        this(ModTileEntities.ELECTRIC_BANK_TILE.get());
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
        if (!this.removed && cap == CapabilityEnergy.ENERGY)
            return LazyOptional.of(() -> storage).cast();;

        return super.getCapability(cap, side);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (!world.isRemote) {
            System.out.println(storage.getEnergyStored());

        }
    }





    public final CustomEnergyStorage storage = new CustomEnergyStorage(500000, 1000, 1000);
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
}
