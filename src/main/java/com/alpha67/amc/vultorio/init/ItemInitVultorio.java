package com.alpha67.amc.vultorio.init;

import com.alpha67.amc.amc;
import com.alpha67.amc.CommonUser.AmcItemTier;
import com.alpha67.amc.mcreator.AlphatabItemGroup;
import net.minecraft.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
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

    public static final RegistryObject<PickaxeItem> alpharium_pickaxe = ITEMS.register("alpharium_pickaxe",
            () -> new PickaxeItem(AmcItemTier.ALPHARIUM,5, 5f, new Item.Properties().tab(AlphatabItemGroup.tab)));
    public static final RegistryObject<AxeItem> alpharium_axe = ITEMS.register("alpharium_axe",
            () -> new AxeItem(AmcItemTier.ALPHARIUM,5, 5f, new Item.Properties().tab(AlphatabItemGroup.tab)));
    public static final RegistryObject<ShovelItem> alpharium_shovel = ITEMS.register("alpharium_shovel",
            () -> new ShovelItem(AmcItemTier.ALPHARIUM,5, 5f, new Item.Properties().tab(AlphatabItemGroup.tab)));
    public static final RegistryObject<HoeItem> alpharium_hoe = ITEMS.register("alpharium_hoe",
            () -> new HoeItem(AmcItemTier.ALPHARIUM,5, 5f, new Item.Properties().tab(AlphatabItemGroup.tab)));
    public static final RegistryObject<SwordItem> alpharium_sword = ITEMS.register("alpharium_sword",
            () -> new SwordItem(AmcItemTier.ALPHARIUM,5, 5f, new Item.Properties().tab(AlphatabItemGroup.tab)));
}
