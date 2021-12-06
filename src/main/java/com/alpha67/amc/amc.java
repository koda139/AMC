package com.alpha67.amc;


import com.alpha67.amc.koda.BlockInitKoda;
import com.alpha67.amc.koda.ItemInitKoda;
import com.alpha67.amc.vultorio.init.BlockInitVultorio;
import com.alpha67.amc.vultorio.init.ItemInitVultorio;
import net.minecraft.block.Block;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

@Mod(amc.MODID)
public class amc {

    public static final String MODID ="amc";

    public static final Logger LOGGER = LogManager.getLogger(amc.class);
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation("amc", "amc"), () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
    public AmcModElements elements;
    public amc() {
        elements = new AmcModElements();
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientLoad);
        MinecraftForge.EVENT_BUS.register(new AmcModFMLBusEvents(this));

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        //décracation class de vultorio
        ItemInitVultorio.register(eventBus);
        BlockInitVultorio.register(eventBus);

        //déclaration class de koda
        ItemInitKoda.register(eventBus);
        BlockInitKoda.register(eventBus);
    }


    public void setup(FMLCommonSetupEvent e)
    {

    }

    @SubscribeEvent
    public void clientSetup(FMLClientSetupEvent e)
    {

    }

    @SubscribeEvent
    public void onClientSetup(final FMLClientSetupEvent e){
        e.getMinecraftSupplier().get().execute(this::updateTitle);
    }

    private void updateTitle(){
        String Name = "Alpha67";
        final MainWindow window = Minecraft.getInstance().getWindow();
        window.setTitle(Name);


    }





    //pour mcreator
    private void init(FMLCommonSetupEvent event) {
        elements.getElements().forEach(element -> element.init(event));
    }

    public void clientLoad(FMLClientSetupEvent event) {
        elements.getElements().forEach(element -> element.clientLoad(event));
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(elements.getBlocks().stream().map(Supplier::get).toArray(Block[]::new));
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(elements.getItems().stream().map(Supplier::get).toArray(Item[]::new));
    }

    @SubscribeEvent
    public void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
        event.getRegistry().registerAll(elements.getEntities().stream().map(Supplier::get).toArray(EntityType[]::new));
    }

    @SubscribeEvent
    public void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
        event.getRegistry().registerAll(elements.getEnchantments().stream().map(Supplier::get).toArray(Enchantment[]::new));
    }

    @SubscribeEvent
    public void registerSounds(RegistryEvent.Register<net.minecraft.util.SoundEvent> event) {
        elements.registerSounds(event);
    }
    private static class AmcModFMLBusEvents {
        private final amc parent;
        AmcModFMLBusEvents(amc parent) {
            this.parent = parent;
        }

        @SubscribeEvent
        public void serverLoad(FMLServerStartingEvent event) {
            this.parent.elements.getElements().forEach(element -> element.serverLoad(event));
        }
    }


    }






