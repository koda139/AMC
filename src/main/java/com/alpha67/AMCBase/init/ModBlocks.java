package com.alpha67.AMCBase.init;

import com.alpha67.AMCBase.block.ModWoodTypes;
import com.alpha67.AMCBase.block.custom.*;
import com.alpha67.AMCBase.block.custom.trees.RedwoodTree;
import com.alpha67.AMCBase.block.testBlock;
import com.alpha67.AMCBase.block.tileBlock.ATMBlock;
import com.alpha67.AMCBase.block.tileBlock.CoalGeneratorBlock;
import com.alpha67.AMCBase.block.tileBlock.LightningChannelerBlock;
import com.alpha67.AMCBase.block.tileBlock.market.DiamondMarketBlock;
import com.alpha67.AMCBase.block.tileBlock.market.GoldMarketBlock;
import com.alpha67.AMCBase.block.tileBlock.market.StoneMarketBlock;
import com.alpha67.AMCBase.block.tileBlock.compresorBlock;
import com.alpha67.AMCBase.block.tileBlock.market.WoodMarketBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import com.alpha67.AMCBase.AMCBase;
//import net.tutorialsbykaupenjoe.tutorialmod.block.custom.*;
import com.alpha67.AMCBase.item.ModItemGroup;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS_TUTO
            = DeferredRegister.create(ForgeRegistries.BLOCKS, AMCBase.MOD_ID);

    public static final DeferredRegister<Block> BLOCKS
            = DeferredRegister.create(ForgeRegistries.BLOCKS, AMCBase.MOD_ID);

    //AMC mod
    public static final RegistryObject<Block> ALPHARIUM_LOGO = registerBlock("alpharium_logo",
            () -> new Block(AbstractBlock.Properties.create(Material.ROCK)
                    .harvestLevel(2).setRequiresTool().harvestTool(ToolType.PICKAXE).hardnessAndResistance(5f)), 64);

    public static final RegistryObject<Block> ATM = registerBlock("atm",
            () -> new ATMBlock(AbstractBlock.Properties.create(Material.IRON)
                    .harvestLevel(1).hardnessAndResistance(5f)), 64);

    public static final RegistryObject<Block> COPPER_BLOCK = registerBlock("copper_block",
            () -> new Block(AbstractBlock.Properties.create(Material.IRON)
                    .harvestLevel(1).hardnessAndResistance(3f)), 64);

    public static final RegistryObject<Block> LITHIUM_BLOCK = registerBlockTuto("lithium_block",
            () -> new Block(AbstractBlock.Properties.create(Material.ROCK)
                    .harvestLevel(2).setRequiresTool().harvestTool(ToolType.PICKAXE).hardnessAndResistance(5f)));

    public static final RegistryObject<Block> STONE_MARKET = registerBlock("stone_market",
            () -> new StoneMarketBlock(AbstractBlock.Properties.create(Material.IRON)
                    .harvestLevel(1).hardnessAndResistance(5f)), 64);
    public static final RegistryObject<Block> WOOD_MARKET = registerBlock("wood_market",
            () -> new WoodMarketBlock(AbstractBlock.Properties.create(Material.IRON)
                    .harvestLevel(1).hardnessAndResistance(5f)), 64);
    public static final RegistryObject<Block> GOLD_MARKET = registerBlock("gold_market",
            () -> new GoldMarketBlock(AbstractBlock.Properties.create(Material.IRON)
                    .harvestLevel(1).hardnessAndResistance(5f)), 64);
    public static final RegistryObject<Block> DIAMOND_MARKET = registerBlock("diamond_market",
            () -> new DiamondMarketBlock(AbstractBlock.Properties.create(Material.IRON)
                    .harvestLevel(1).hardnessAndResistance(5f)), 64);

    public static final RegistryObject<Block> COMPRESSOR_BLOCK = registerBlock("compressor_block",
            () -> new compresorBlock(AbstractBlock.Properties.create(Material.IRON)
                    .harvestLevel(1).hardnessAndResistance(5f)), 64);

    public static final RegistryObject<Block> ANTENNA = registerBlock("antenna",
            () -> new antennaBlock(AbstractBlock.Properties.create(Material.IRON).notSolid().hardnessAndResistance(3F, 3F)), 64);

    public static final RegistryObject<Block> ELECTRIC_BANK = registerBlock("electric_bank",
            () -> new palletBlock(AbstractBlock.Properties.create(Material.IRON).notSolid().hardnessAndResistance(4F, 3F)), 64);

    public static final RegistryObject<Block> STONE_PALLET = registerBlock("stone_pallet",
            () -> new palletBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(4).hardnessAndResistance(5F, 3F)), 1);

    public static final RegistryObject<Block> WOOD_PALLET = registerBlock("wood_pallet",
            () -> new palletBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(4).hardnessAndResistance(5F, 3F)), 1);

    public static final RegistryObject<Block> GOLD_PALLET = registerBlock("gold_pallet",
            () -> new palletBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(4).hardnessAndResistance(5F, 3F)), 1);

    public static final RegistryObject<Block> DIAMOND_PALLET = registerBlock("diamond_pallet",
            () -> new palletBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(5).hardnessAndResistance(5F, 3F)), 1);

    public static final RegistryObject<Block> COAL_GENERATOR = registerBlock("coal_generator",
            () -> new CoalGeneratorBlock(AbstractBlock.Properties.create(Material.ROCK).harvestLevel(1).hardnessAndResistance(5F, 3F)), 64);


    //monerai
    public static final RegistryObject<Block> COPPER_ORE = registerBlock("copper_ore",
            () -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3F, 3F).harvestLevel(2).harvestTool(ToolType.PICKAXE)), 64);

    public static final RegistryObject<Block> LITHIUM_ORE = registerBlock("lithium_ore",
            () -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3F, 3F).harvestLevel(2).harvestTool(ToolType.PICKAXE)), 64);

    public static final RegistryObject<Block> RUBY_ORE = registerBlock("ruby_ore",
            () -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3F, 3F).harvestLevel(2).harvestTool(ToolType.PICKAXE)), 64);

    public static final RegistryObject<Block> RUBY_ORE_NETHER = registerBlock("ruby_ore_nether",
            () -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3F, 3F).harvestLevel(2).harvestTool(ToolType.PICKAXE)), 64);



    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, int maxStack) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, maxStack);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block, int maxStack) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().group(AMCBase.AMCBase).maxStackSize(maxStack)));
    }



    //tutoriel mod
    public static final RegistryObject<Block> AMETHYST_ORE = registerBlockTuto("amethyst_ore",
            () -> new Block(AbstractBlock.Properties.create(Material.ROCK)
                    .harvestLevel(2).setRequiresTool().harvestTool(ToolType.PICKAXE).hardnessAndResistance(5f)));

    public static final RegistryObject<Block> AMETHYST_BLOCK = registerBlockTuto("amethyst_block",
            () -> new Block(AbstractBlock.Properties.create(Material.ROCK)
                    .harvestLevel(2).setRequiresTool().harvestTool(ToolType.PICKAXE).hardnessAndResistance(8f)));

    public static final RegistryObject<Block> FIRESTONE_BLOCK = registerBlockTuto("firestone_block",
            () -> new FirestoneBlock(AbstractBlock.Properties.create(Material.IRON)
                    .harvestLevel(2).setRequiresTool().harvestTool(ToolType.PICKAXE).hardnessAndResistance(6f)));


    public static final RegistryObject<Block> AMETHYST_STAIRS = registerBlockTuto("amethyst_stairs",
            () -> new StairsBlock(() -> AMETHYST_BLOCK.get().getDefaultState(),
                    AbstractBlock.Properties.create(Material.IRON).harvestLevel(3).harvestTool(ToolType.PICKAXE).setRequiresTool()));

    public static final RegistryObject<Block> AMETHYST_FENCE = registerBlockTuto("amethyst_fence",
            () -> new FenceBlock(AbstractBlock.Properties.create(Material.IRON)
                    .harvestLevel(2).setRequiresTool().harvestTool(ToolType.PICKAXE).hardnessAndResistance(6f)));

    public static final RegistryObject<Block> AMETHYST_FENCE_GATE = registerBlockTuto("amethyst_fence_gate",
            () -> new FenceGateBlock(AbstractBlock.Properties.create(Material.IRON)
                    .harvestLevel(2).setRequiresTool().harvestTool(ToolType.PICKAXE).hardnessAndResistance(6f)));

    public static final RegistryObject<Block> AMETHYST_SLAB = registerBlockTuto("amethyst_slab",
            () -> new SlabBlock(AbstractBlock.Properties.create(Material.IRON)
                    .harvestLevel(2).setRequiresTool().harvestTool(ToolType.PICKAXE).hardnessAndResistance(6f)));

    public static final RegistryObject<Block> AMETHYST_BUTTON = registerBlockTuto("amethyst_button",
            () -> new StoneButtonBlock(AbstractBlock.Properties.create(Material.IRON)
                    .harvestLevel(2).setRequiresTool().harvestTool(ToolType.PICKAXE).hardnessAndResistance(6f).doesNotBlockMovement()));

    public static final RegistryObject<Block> AMETHYST_PRESSURE_PLATE = registerBlockTuto("amethyst_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.create(Material.IRON)
                    .harvestLevel(2).setRequiresTool().harvestTool(ToolType.PICKAXE).hardnessAndResistance(6f)));

    public static final RegistryObject<Block> AMETHYST_DOOR = registerBlockTuto("amethyst_door",
            () -> new DoorBlock(AbstractBlock.Properties.create(Material.IRON).harvestLevel(2).setRequiresTool()
                    .harvestTool(ToolType.PICKAXE).hardnessAndResistance(6f).notSolid()));

    public static final RegistryObject<Block> AMETHYST_TRAPDOOR = registerBlockTuto("amethyst_trapdoor",
            () -> new TrapDoorBlock(AbstractBlock.Properties.create(Material.IRON).harvestLevel(2).setRequiresTool()
                    .harvestTool(ToolType.PICKAXE).hardnessAndResistance(6f).notSolid()));

    public static final RegistryObject<Block> OATS = BLOCKS_TUTO.register("oats_crop",
            () -> new OatsBlock(AbstractBlock.Properties.from(Blocks.WHEAT)));


    public static final RegistryObject<Block> REDWOOD_LOG = registerBlockTuto("redwood_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.OAK_LOG)));

    public static final RegistryObject<Block> REDWOOD_WOOD = registerBlockTuto("redwood_wood",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.OAK_WOOD)));

    public static final RegistryObject<Block> STRIPPED_REDWOOD_LOG = registerBlockTuto("stripped_redwood_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.STRIPPED_OAK_LOG)));

    public static final RegistryObject<Block> STRIPPED_REDWOOD_WOOD = registerBlockTuto("stripped_redwood_wood",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.STRIPPED_OAK_WOOD)));

    public static final RegistryObject<Block> REDWOOD_PLANKS = registerBlockTuto("redwood_planks",
            () -> new Block(AbstractBlock.Properties.from(Blocks.OAK_PLANKS)));


    public static final RegistryObject<Block> REDWOOD_LEAVES = registerBlockTuto("redwood_leaves",
            () -> new LeavesBlock(AbstractBlock.Properties.create(Material.LEAVES).hardnessAndResistance(0.2f)
                .tickRandomly().sound(SoundType.PLANT).notSolid()));

    public static final RegistryObject<Block> REDWOOD_SAPLING = registerBlockTuto("redwood_sapling",
            () -> new SaplingBlock(new RedwoodTree(), AbstractBlock.Properties.from(Blocks.OAK_SAPLING)));

    public static final RegistryObject<Block> HYACINTH = registerBlockTuto("hyacinth",
            () -> new FlowerBlock(Effects.HASTE, 2, AbstractBlock.Properties.from(Blocks.DANDELION)));

    public static final RegistryObject<Block> LIGHTNING_CHANNELER = registerBlockTuto("lightning_channeler",
            () -> new LightningChannelerBlock(AbstractBlock.Properties.create(Material.IRON)));

    public static final RegistryObject<Block> REDWOOD_SIGN = BLOCKS_TUTO.register("redwood_sign",
            () -> new ModStandingSignBlock(AbstractBlock.Properties.create(Material.IRON), ModWoodTypes.REDWOOD));

    public static final RegistryObject<Block> REDWOOD_WALL_SIGN = BLOCKS_TUTO.register("redwood_wall_sign",
            () -> new ModWallSignBlock(AbstractBlock.Properties.create(Material.IRON), ModWoodTypes.REDWOOD));


    public static final RegistryObject<Block> KAUPEN_ALTAR = registerBlockTuto("kaupen_altar",
            () -> new KaupenAltarBlock(AbstractBlock.Properties.create(Material.IRON).notSolid()));



    private static <T extends Block>RegistryObject<T> registerBlockTuto(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS_TUTO.register(name, block);
        registerBlockItemTuto(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItemTuto(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().group(ModItemGroup.TUTORIAL_GROUP)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS_TUTO.register(eventBus);
        BLOCKS.register(eventBus);
    }

}
