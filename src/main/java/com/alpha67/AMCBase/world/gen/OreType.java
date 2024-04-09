package com.alpha67.AMCBase.world.gen;

import net.minecraft.block.Block;
import net.minecraftforge.common.util.Lazy;
import com.alpha67.AMCBase.init.ModBlocks;

public enum OreType {

   // AMETHYST(Lazy.of(ModBlocks.AMETHYST_ORE), 200, 25, 100, 200),
    COPPER(Lazy.of(ModBlocks.COPPER_ORE), 7, 0, 100, 18),
    LITHIUM(Lazy.of(ModBlocks.LITHIUM_ORE), 3, 0, 25, 20),
    RUBY(Lazy.of(ModBlocks.RUBY_ORE), 3, 0, 40, 40),
    RUBY_NETHER(Lazy.of(ModBlocks.RUBY_ORE_NETHER), 3, 0, 200, 15),
    FIRESTONE(Lazy.of(ModBlocks.FIRESTONE_BLOCK), 3, 10, 80, 5);

    private final Lazy<Block> block;
    private final int maxVeinSize;
    private final int minHeight;
    private final int maxHeight;
    private final int veinsPerChunk;

    OreType(Lazy<Block> block, int maxVeinSize, int minHeight, int maxHeight, int veinsPerChunk) {
        this.block = block;
        this.maxVeinSize = maxVeinSize;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.veinsPerChunk = veinsPerChunk;
    }


    public int getVeinsPerChunk() {
        return veinsPerChunk;
    }

    public Lazy<Block> getBlock() {
        return block;
    }

    public int getMaxVeinSize() {
        return maxVeinSize;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public static OreType get(Block block) {
        for (OreType ore : values()) {
            if(block == ore.block) {
                return ore;
            }
        }
        return null;
    }
}
