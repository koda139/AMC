package com.alpha67.amc.koda;

import com.alpha67.amc.amc;
import com.alpha67.amc.mcreator.AlphatabItemGroup;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class BlockInitKoda {

    public static final DeferredRegister<Block> BLOCKS
            = DeferredRegister.create(ForgeRegistries.BLOCKS, amc.MODID);


    //tu déclare t'es block ici
    //public static final RegistryObject<Block> AMETHYST_ORE = registerBlock("amethyst_ore", () -> new Block(AbstractBlock.Properties.of(Material.STONE)));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ItemInitKoda.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(AlphatabItemGroup.tab)));
    }
}
