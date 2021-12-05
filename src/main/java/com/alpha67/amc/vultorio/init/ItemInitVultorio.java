package com.alpha67.amc.vultorio.init;

import com.alpha67.amc.amc;
import com.alpha67.amc.mcreator.AlphatabItemGroup;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ItemInitVultorio {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, amc.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static final RegistryObject<Item> alpharium_ingot = ITEMS.register("alpharium_ingot",
            () -> new Item(new Item.Properties().tab(AlphatabItemGroup.tab)));

    public static final RegistryObject<Item> tungstene_ingot = ITEMS.register("tungstene_ingot",
            () -> new Item(new Item.Properties().tab(AlphatabItemGroup.tab)));

    public static final RegistryObject<Item> ruby_nugets = ITEMS.register("ruby_nugets",
            () -> new Item(new Item.Properties().tab(AlphatabItemGroup.tab)));


}
