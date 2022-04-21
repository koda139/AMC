package com.alpha67.amcbase.Events;

import com.alpha67.amcbase.AMCBase;
import com.alpha67.amcbase.network.packetTest;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.NetworkManager;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.network.NetworkDirection;

public class breakBlock {

    public int ticksSinceLastPulse = 0;

    @SubscribeEvent
    public void blockBrealEvent (BlockEvent.BreakEvent e)
    {
        PlayerEntity player = e.getPlayer();

        System.out.println("block break");
        //AMCBase.PACKET_HANDLER.sendToServer("salut");
    }

    @SubscribeEvent
    public void clientTickEvent(TickEvent.ClientTickEvent event)
    {

            ticksSinceLastPulse++;

            if (ticksSinceLastPulse > 300) {
                //System.out.println("tick event");
                //AMCBase.PACKET_HANDLER.sendToServer(new packetTest("test"));
                ticksSinceLastPulse = 0;
            }


    }

}


