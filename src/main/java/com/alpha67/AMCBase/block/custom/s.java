package com.alpha67.AMCBase.block.custom;

import com.alpha67.AMCBase.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;

import java.util.Collections;
import java.util.List;


public class s extends Block {

    public s(Properties properties) {
        super(properties);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {

        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.singletonList(ModItems.LITHIUM_FRAGMENT.get().getDefaultInstance());
    }


}

