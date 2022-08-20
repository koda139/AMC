package com.alpha67.AMCBase.init;

import com.alpha67.AMCBase.item.ModArmorMaterial;
import com.alpha67.AMCBase.item.ModItemGroup;
import com.alpha67.AMCBase.item.ModItemTier;
import com.alpha67.AMCBase.item.custom.*;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import com.alpha67.AMCBase.AMCBase;
//import net.tutorialsbykaupenjoe.tutorialmod.item.custom.*;
import com.alpha67.AMCBase.util.ModSoundEvents;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, AMCBase.MOD_ID);

    // les items de AMC
    public static final RegistryObject<Item> AMC_LOGO = ITEMS.register("amc_logo",
            () -> new Item(new Item.Properties().group(AMCBase.AMCBase)));

    public static final RegistryObject<Item> CREDIT_CARD = ITEMS.register("credit_card",
            () -> new Item(new Item.Properties().group(AMCBase.AMCBase).maxStackSize(1)));

    public static final RegistryObject<Item> EMPTY_PALLET = ITEMS.register("empty_pallet",
            () -> new Item(new Item.Properties().group(AMCBase.AMCBase)));

    public static final RegistryObject<Item> BATTERIE = ITEMS.register("batterie",
            () -> new Item(new Item.Properties().group(AMCBase.AMCBase)));

    public static final RegistryObject<Item> MARKET_COMPONEMENT = ITEMS.register("market_componement",
            () -> new Item(new Item.Properties().group(AMCBase.AMCBase)));


    public static final RegistryObject<Item> COPPER_CABLE = ITEMS.register("copper_cable",
            () -> new Item(new Item.Properties().group(AMCBase.AMCBase)));



    //spécial minerai
    public static final RegistryObject<Item> COPPER_INGOT = ITEMS.register("copper_ingot",
            () -> new Item(new Item.Properties().group(AMCBase.AMCBase)));

    public static final RegistryObject<Item> LITHIUM_FRAGMENT = ITEMS.register("lithium_fragment",
            () -> new Item(new Item.Properties().group(AMCBase.AMCBase)));

    public static final RegistryObject<Item> LITHIUM_INGOT = ITEMS.register("lithium_ingot",
            () -> new Item(new Item.Properties().group(AMCBase.AMCBase)));







    // la money
    public static final RegistryObject<Item> ONE_CF = ITEMS.register("one_cf",
            () -> new Item(new Item.Properties().group(AMCBase.AMCBase).maxStackSize(50)));

    public static final RegistryObject<Item> FIVE_CF = ITEMS.register("five_cf",
            () -> new Item(new Item.Properties().group(AMCBase.AMCBase).maxStackSize(50)));

    public static final RegistryObject<Item> TEN_CF = ITEMS.register("ten_cf",
            () -> new Item(new Item.Properties().group(AMCBase.AMCBase).maxStackSize(50)));

    public static final RegistryObject<Item> TWENTY_CF = ITEMS.register("twenty_cf",
            () -> new Item(new Item.Properties().group(AMCBase.AMCBase).maxStackSize(50)));

    public static final RegistryObject<Item> FIFTY_CF = ITEMS.register("fifty_cf",
            () -> new Item(new Item.Properties().group(AMCBase.AMCBase).maxStackSize(50)));

    public static final RegistryObject<Item> HUNDRED_CF = ITEMS.register("hundred_cf",
            () -> new Item(new Item.Properties().group(AMCBase.AMCBase).maxStackSize(50)));

    public static final RegistryObject<Item> FIVE_HUNDRED_CF = ITEMS.register("five_hundred_cf",
            () -> new Item(new Item.Properties().group(AMCBase.AMCBase).maxStackSize(50)));



    //les Items du tuot
    public static final RegistryObject<Item> AMETHYST = ITEMS.register("amethyst",
            () -> new Item(new Item.Properties().group(ModItemGroup.TUTORIAL_GROUP)));

    public static final RegistryObject<Item> FIRESTONE = ITEMS.register("firestone",
            () -> new Firestone(new Item.Properties().group(ModItemGroup.TUTORIAL_GROUP).maxDamage(8)));


    public static final RegistryObject<Item> AMETHYST_SWORD = ITEMS.register("amethyst_sword",
            () -> new SwordItem(ModItemTier.AMETHYST, 2, 3f,
                    new Item.Properties().group(ModItemGroup.TUTORIAL_GROUP)));

    public static final RegistryObject<Item> AMETHYST_PICKAXE = ITEMS.register("amethyst_pickaxe",
            () -> new PickaxeItem(ModItemTier.AMETHYST, 0, -1f,
                    new Item.Properties().group(ModItemGroup.TUTORIAL_GROUP)));

    public static final RegistryObject<Item> AMETHYST_SHOVEL = ITEMS.register("amethyst_shovel",
            () -> new ShovelItem(ModItemTier.AMETHYST, 0, -1f,
                    new Item.Properties().group(ModItemGroup.TUTORIAL_GROUP)));

    public static final RegistryObject<Item> AMETHYST_AXE = ITEMS.register("amethyst_axe",
            () -> new AxeItem(ModItemTier.AMETHYST, 4, -6f,
                    new Item.Properties().group(ModItemGroup.TUTORIAL_GROUP)));

    public static final RegistryObject<Item> AMETHYST_HOE = ITEMS.register("amethyst_hoe",
            () -> new HoeItem(ModItemTier.AMETHYST, 0, 0f,
                    new Item.Properties().group(ModItemGroup.TUTORIAL_GROUP)));


    public static final RegistryObject<Item> AMETHYST_BOOTS = ITEMS.register("amethyst_boots",
            () -> new ArmorItem(ModArmorMaterial.AMETHYST, EquipmentSlotType.FEET,
                    new Item.Properties().group(ModItemGroup.TUTORIAL_GROUP)));

    public static final RegistryObject<Item> AMETHYST_CHESTPLATE = ITEMS.register("amethyst_chestplate",
            () -> new ArmorItem(ModArmorMaterial.AMETHYST, EquipmentSlotType.CHEST,
                    new Item.Properties().group(ModItemGroup.TUTORIAL_GROUP)));

    public static final RegistryObject<Item> AMETHYST_LEGGINGS = ITEMS.register("amethyst_leggings",
            () -> new ArmorItem(ModArmorMaterial.AMETHYST, EquipmentSlotType.LEGS,
                    new Item.Properties().group(ModItemGroup.TUTORIAL_GROUP)));

    public static final RegistryObject<Item> AMETHYST_HELMET = ITEMS.register("amethyst_helmet",
            () -> new ModArmorItem(ModArmorMaterial.AMETHYST, EquipmentSlotType.HEAD,
                    new Item.Properties().group(ModItemGroup.TUTORIAL_GROUP)));


    public static final RegistryObject<Item> OATS = ITEMS.register("oats",
            () -> new BlockItem(ModBlocks.OATS.get(), new Item.Properties()
                    .food(new Food.Builder().hunger(1).saturation(0.1f).fastToEat().build())
                    .group(ModItemGroup.TUTORIAL_GROUP)));

    public static final RegistryObject<Item> AMETHYST_HORSE_ARMOR = ITEMS.register("amethyst_horse_armor",
            () -> new HorseArmorItem(9, "amethyst",
                    new Item.Properties().group(ModItemGroup.TUTORIAL_GROUP)));

    public static final RegistryObject<Item> REDWOOD_SIGN = ITEMS.register("redwood_sign",
            () -> new SignItem(new Item.Properties().maxStackSize(16).group(ModItemGroup.TUTORIAL_GROUP),
                    ModBlocks.REDWOOD_SIGN.get(), ModBlocks.REDWOOD_WALL_SIGN.get()));


    public static final RegistryObject<Item> OIL_BUCKET = ITEMS.register("oil_bucket",
            () -> new BucketItem(() -> ModFluids.OIL_FLUID.get(),
                    new Item.Properties().maxStackSize(1).group(ModItemGroup.TUTORIAL_GROUP)));

    public static final RegistryObject<ModSpawnEggItem> BUFF_ZOMBIE_SPAWN_EGG = ITEMS.register("buff_zombie_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityTypes.BUFF_ZOMBIE, 0x464F56, 0x1D6336,
                    new Item.Properties().group(ModItemGroup.TUTORIAL_GROUP)));

    public static final RegistryObject<ModSpawnEggItem> PIGEON_SPAWN_EGG = ITEMS.register("pigeon_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityTypes.PIGEON, 0x879995, 0x576ABC,
                    new Item.Properties().group(ModItemGroup.TUTORIAL_GROUP)));


    public static final RegistryObject<Item> KAUPENBOW = ITEMS.register("kaupenbow",
            () -> new BowItem(new Item.Properties().group(ModItemGroup.TUTORIAL_GROUP).maxStackSize(1)));

    public static final RegistryObject<Item> KAUPENSTAFF = ITEMS.register("kaupen_staff",
            () -> new StaffItem(new Item.Properties().group(ModItemGroup.TUTORIAL_GROUP).maxStackSize(1)));

    public static final RegistryObject<Item> REDWOOD_BOAT = ITEMS.register("redwood_boat",
            () -> new ModBoatItem(new Item.Properties().group(ModItemGroup.TUTORIAL_GROUP), "redwood"));


    public static final RegistryObject<Item> BAR_BRAWL_MUSIC_DISC = ITEMS.register("bar_brawl_music_disc",
            () -> new MusicDiscItem(1, () -> ModSoundEvents.BAR_BRAWL.get(),
                    new Item.Properties().maxStackSize(1).group(ModItemGroup.TUTORIAL_GROUP)));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
