package com.alpha67.amcbase;

import com.alpha67.amcbase.Events.SunSet;
import com.alpha67.amcbase.Events.breakBlock;
import com.alpha67.amcbase.Events.weatherSet;
import com.alpha67.amcbase.network.GenericMessage;
import com.alpha67.amcbase.network.packetTest;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.MixinEnvironment;

import java.util.Optional;

import static net.java.games.input.Controller.PortType.NETWORK;
import static org.spongepowered.asm.mixin.MixinEnvironment.*;

@Mod(AMCBase.MODID)
public class AMCBase {


    public static final String MODID ="amcbase";

    public static final Logger LOGGER = LogManager.getLogger(AMCBase.class);

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(AMCBase.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public AMCBase() {
        FMLJavaModLoadingContext.get().getModEventBus().register(this);


        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MinecraftForge.EVENT_BUS.register(new SunSet());
        MinecraftForge.EVENT_BUS.register(new weatherSet());
        MinecraftForge.EVENT_BUS.register(new breakBlock());

    }


    public void setup(FMLCommonSetupEvent e)
    {
        int index = 0;
        //PACKET_HANDLER.registerMessage(index, packetTest.class, packetTest::encode, packetTest::decode, packetTest::handle);
        PACKET_HANDLER.registerMessage(0, packetTest.class, packetTest::encode, packetTest::decode, packetTest::handle);


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

    public static final ItemGroup ITEM_GROUP = new ItemGroup(AMCBase.MODID) {

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.ACACIA_LEAVES);
        }
    };

    public static void init(final FMLCommonSetupEvent event) {

    }





    //pour mcreator



    }






