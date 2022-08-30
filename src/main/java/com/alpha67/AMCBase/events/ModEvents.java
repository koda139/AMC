package com.alpha67.AMCBase.events;

import com.alpha67.AMCBase.commands.ReturnHomeCommand;
import com.alpha67.AMCBase.commands.SetHomeCommand;
import com.alpha67.AMCBase.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.server.command.ConfigCommand;
import com.alpha67.AMCBase.AMCBase;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Mod.EventBusSubscriber(modid = AMCBase.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new SetHomeCommand(event.getDispatcher());
        new ReturnHomeCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onPlayerCloneEvent(PlayerEvent.Clone event) {
        if(!event.getOriginal().getEntityWorld().isRemote()) {
            event.getPlayer().getPersistentData().putIntArray(AMCBase.MOD_ID + "homepos",
                    event.getOriginal().getPersistentData().getIntArray(AMCBase.MOD_ID + "homepos"));
        }
    }

   @SubscribeEvent
    public static void onPlayerConnect(PlayerEvent.PlayerLoggedInEvent event) {

        String uuid = event.getPlayer().getUniqueID().toString();
        System.out.println("2");

        try {
            Object ob = new JSONParser().parse(new FileReader("communication-alpha/"+uuid+".json"));
            JSONObject js = (JSONObject) ob;
            //double player = (double) js.get("player");
            System.out.println("1");
        } catch (Exception e)
        {

            JSONObject jsonObject = new JSONObject();
            //Inserting key-value pairs into the json object
            jsonObject.put("player", event.getPlayer().getUniqueID().toString());
            System.out.println("3");

            try {
                FileWriter file = new FileWriter("communication-alpha/"+uuid+".json");
                file.write(jsonObject.toJSONString());
                file.close();

            } catch (IOException ev) {
                ev.printStackTrace();
            }
            System.out.println("4");


            ItemStack _setstack = new ItemStack(ModItems.FIVE_HUNDRED_CF.get());
            _setstack.setCount((int) 10);
            ItemHandlerHelper.giveItemToPlayer(event.getPlayer(), _setstack);

        }
    }
}
