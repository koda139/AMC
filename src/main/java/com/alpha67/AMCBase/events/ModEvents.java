package com.alpha67.AMCBase.events;

import com.alpha67.AMCBase.commands.ReturnHomeCommand;
import com.alpha67.AMCBase.commands.SetHomeCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;
import com.alpha67.AMCBase.AMCBase;

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
}
