package com.alpha67.AMCBase.tileentity;

import com.alpha67.AMCBase.init.ModTileEntities;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class ModSignTileEntity extends SignTileEntity {
    public ModSignTileEntity() {
        super();
    }

    @Override
    public TileEntityType<?> getType() {
        return ModTileEntities.SIGN_TILE_ENTITIES.get();
    }
}
