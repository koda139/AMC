package com.alpha67.amc.koda;

import com.alpha67.amc.amc;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInitKoda {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, amc.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
    //tu déclare ici t'es item SIMON.
    //public static final RegistryObject<Item> AMETHYST = ITEMS.register("item_test", () -> new Item(new Item.Properties().stacksTo(3)));
}
