package com.alpha67.AMCBase;

import com.alpha67.AMCBase.init.ModBlocks;
import com.alpha67.AMCBase.block.ModWoodTypes;
import com.alpha67.AMCBase.init.ModContainers;
import com.alpha67.AMCBase.data.recipes.ModRecipeTypes;
import com.alpha67.AMCBase.init.ModEntityTypes;
import com.alpha67.AMCBase.entity.render.BuffZombieRenderer;
import com.alpha67.AMCBase.entity.render.ModBoatRenderer;
import com.alpha67.AMCBase.entity.render.PigeonRenderer;
import com.alpha67.AMCBase.init.ModFluids;
import com.alpha67.AMCBase.init.ModItems;
import com.alpha67.AMCBase.network.ButtonATM;
import com.alpha67.AMCBase.network.ButtonPacket;
import com.alpha67.AMCBase.network.ButtonMarket;
import com.alpha67.AMCBase.paintings.ModPaintings;
import com.alpha67.AMCBase.init.ModTileEntities;
import com.alpha67.AMCBase.screen.ATMBlockScreen;
import com.alpha67.AMCBase.screen.CoalGeneratorScreen;
import com.alpha67.AMCBase.screen.CompressorBlockScreen;
import com.alpha67.AMCBase.screen.LightningChannelerScreen;
import com.alpha67.AMCBase.screen.mainMenu.mainMenuScreen;
import com.alpha67.AMCBase.screen.market.DiamondMarketScreen;
import com.alpha67.AMCBase.screen.market.GoldMarketScreen;
import com.alpha67.AMCBase.screen.market.StoneMarketScreen;
import com.alpha67.AMCBase.screen.market.WoodMarketScreen;
import com.alpha67.AMCBase.util.ModItemModelProperties;
import com.alpha67.AMCBase.util.ModSoundEvents;
import com.alpha67.AMCBase.world.biome.ModBiomes;
import com.alpha67.AMCBase.world.biome.ModBiomesDatapack;
import com.alpha67.AMCBase.world.gen.ModBiomeGeneration;
import com.alpha67.AMCBase.world.structure.ModStructures;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.block.WoodType;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.tileentity.SignTileEntityRenderer;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(AMCBase.MOD_ID)
public class AMCBase {
    public static final String MOD_ID = "amcbase";

    public static final ItemGroup AMCBase = new ItemGroup("AMCBase") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.AMC_LOGO.get());
        }
    };

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public Path icon16 = Paths.get("mods/icon16.png");
    public Path icon32 = Paths.get("mods/icon32.png");

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public AMCBase() {

        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        //MinecraftForge.EVENT_BUS.register(this);

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);

        ModTileEntities.register(eventBus);
        ModContainers.register(eventBus);

        ModStructures.register(eventBus);
        ModFluids.register(eventBus);
        ModRecipeTypes.register(eventBus);
        ModSoundEvents.register(eventBus);

        ModEntityTypes.register(eventBus);
        ModBiomes.register(eventBus);
        ModBiomesDatapack.register(eventBus);
        ModPaintings.register(eventBus);

        eventBus.addListener(this::setup);
        // Register the enqueueIMC method for modloading
        // Register the processIMC method for modloading
        eventBus.addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        eventBus.addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
       // MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {

        PACKET_HANDLER.registerMessage(0, ButtonPacket.class, ButtonPacket::encode, ButtonPacket::decode, ButtonPacket::handle);
        PACKET_HANDLER.registerMessage(1, ButtonMarket.class, ButtonMarket::toBytes, ButtonMarket::new, ButtonMarket::handle);
        PACKET_HANDLER.registerMessage(2, ButtonATM.class, ButtonATM::toBytes, ButtonATM::new, ButtonATM::handle);


        event.enqueueWork(() -> {
            AxeItem.BLOCK_STRIPPING_MAP = new ImmutableMap.Builder<Block, Block>().putAll(AxeItem.BLOCK_STRIPPING_MAP)
                    .put(ModBlocks.REDWOOD_LOG.get(), ModBlocks.STRIPPED_REDWOOD_LOG.get())
                    .put(ModBlocks.REDWOOD_WOOD.get(), ModBlocks.STRIPPED_REDWOOD_WOOD.get()).build();

            ModStructures.setupStructures();
            WoodType.register(ModWoodTypes.REDWOOD);

            ModBiomeGeneration.generateBiomes();

            EntitySpawnPlacementRegistry.register(ModEntityTypes.BUFF_ZOMBIE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                    Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawn);
            EntitySpawnPlacementRegistry.register(ModEntityTypes.PIGEON.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                    Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
        });
    }

    private void doClientStuff(final FMLClientSetupEvent event) {

        event.getMinecraftSupplier().get().execute(this::updateTitle);
        MinecraftForge.EVENT_BUS.addListener(this::onOpenGui);

        event.enqueueWork(() -> {

            //render transparent block
            RenderTypeLookup.setRenderLayer(ModBlocks.AMETHYST_DOOR.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.AMETHYST_TRAPDOOR.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.OATS.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.REDWOOD_LEAVES.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.REDWOOD_SAPLING.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.HYACINTH.get(), RenderType.getCutout());

            RenderTypeLookup.setRenderLayer(ModBlocks.ANTENNA.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ATM.get(), RenderType.getCutout());

            RenderTypeLookup.setRenderLayer(ModBlocks.STONE_PALLET.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.WOOD_PALLET.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.GOLD_PALLET.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.DIAMOND_PALLET.get(), RenderType.getTranslucent());


            ScreenManager.registerFactory(ModContainers.LIGHTNING_CHANNELER_CONTAINER.get(),
                    LightningChannelerScreen::new);

            ScreenManager.registerFactory(ModContainers.STONE_MARKET_CONTAINER.get(),
                    StoneMarketScreen::new);
            ScreenManager.registerFactory(ModContainers.WOOD_MARKET_CONTAINER.get(),
                    WoodMarketScreen::new);
            ScreenManager.registerFactory(ModContainers.GOLD_MARKET_CONTAINER.get(),
                    GoldMarketScreen::new);
            ScreenManager.registerFactory(ModContainers.DIAMOND_MARKET_CONTAINER.get(),
                    DiamondMarketScreen::new);

            ScreenManager.registerFactory(ModContainers.COMPRESSOR_BLOCK_CONTAINER.get(),
                    CompressorBlockScreen::new);

            ScreenManager.registerFactory(ModContainers.ATM_BLOCK_CONTAINER.get(),
                    ATMBlockScreen::new);

            ScreenManager.registerFactory(ModContainers.COAL_GENERATOR_CONTAINER.get(),
                    CoalGeneratorScreen::new);

            ClientRegistry.bindTileEntityRenderer(ModTileEntities.SIGN_TILE_ENTITIES.get(),
                    SignTileEntityRenderer::new);
            Atlases.addWoodType(ModWoodTypes.REDWOOD);

            RenderTypeLookup.setRenderLayer(ModFluids.OIL_FLUID.get(), RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(ModFluids.OIL_BLOCK.get(), RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(ModFluids.OIL_FLOWING.get(), RenderType.getTranslucent());

            ModItemModelProperties.makeBow(ModItems.KAUPENBOW.get());

            RenderTypeLookup.setRenderLayer(ModBlocks.KAUPEN_ALTAR.get(), RenderType.getCutout());
        });

        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.BUFF_ZOMBIE.get(), BuffZombieRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.PIGEON.get(), PigeonRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.REDWOOD_BOAT.get(), ModBoatRenderer::new);
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onOpenGui(GuiOpenEvent event)
    {
        if(event.getGui() != null && event.getGui().getClass() == MainMenuScreen.class)
        {
            event.setGui(new mainMenuScreen(true));
        }
    }


    private void processIMC(final InterModProcessEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    private void updateTitle(){
        String Name = "Capitalium Factory";
        String currentPath = System.getProperty("user.dir");
        final MainWindow window = Minecraft.getInstance().getMainWindow();
        window.setWindowTitle(Name);
        window.setWindowIcon(readIcon16(), readIcon32());


    }


    public InputStream readIcon16() {
        try {
            return Files.newInputStream(icon16, StandardOpenOption.READ);
        } catch (final IOException e) {
            throw new RuntimeException("CustomWindowTitle could not open the specified 16x16 icon: " + icon16, e);
        }
    }

    public InputStream readIcon32() {
        try {
            return Files.newInputStream(icon32, StandardOpenOption.READ);
        } catch (final IOException e) {
            throw new RuntimeException("CustomWindowTitle could not open the specified 32x32 icon: " + icon16, e);
        }
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}
