package com.alpha67.amcbase.test;

import com.alpha67.amcbase.AMCBase;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.storage.WorldSavedData;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

public class saveData extends WorldSavedData {

    public final static String Name = AMCBase.MODID;

    private static final List<StorageObject> DATA = new ArrayList<>();

    public saveData(String p_i2141_1_) {
        super(p_i2141_1_);
    }

    public saveData() {
        this(Name);
    }

    @Override
    public void load(CompoundNBT nbt) {

        CompoundNBT saveData = nbt.getCompound("savedata");
        for (int i = 0; saveData.contains("data" + 1); i++)
        {
            DATA.add(StorageObject.serialize(saveData.getCompound("data" + i)));
        }

    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        CompoundNBT saveData = new CompoundNBT();
        for(ListIterator<StorageObject> iterator = DATA.listIterator();iterator.hasNext(); )
        {
            saveData.put("data"+iterator.nextIndex(),iterator.next().deserializer());
        }
        nbt.put("savedata", saveData);
        return null;
    }

    static class StorageObject {
        private final int randomInt;
        private final BlockPos blockPos;
        private final UUID id;

        StorageObject(int randomInt, BlockPos blockPos, UUID id) {
            this.randomInt = randomInt;
            this.blockPos = blockPos;
            this.id = id;
        }
        public CompoundNBT deserializer()
        {
            CompoundNBT nbt = new CompoundNBT();
            nbt.putInt("randoint",randomInt);
            nbt.putLong("pos", blockPos.asLong());
            nbt.putUUID("id",id);

            return nbt;
        }

        public static StorageObject serialize(CompoundNBT nbt) {
            return new StorageObject(nbt.getInt("randoint"), BlockPos.of(nbt.getLong("pos")), nbt.getUUID("id"));
        }
    }

}
