package com.alpha67.AMCBase.screen.mainMenu;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.MultiplayerScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;

public class util {

    public static ServerData getOrCreateServerData(String ip) {
        MultiplayerScreen scn = new MultiplayerScreen(Minecraft.getInstance().currentScreen);
        scn.init(Minecraft.getInstance(), 0, 0);
        ServerList list = scn.getServerList();
        for (int i = 0; i < list.countServers(); i++) {
            ServerData data = list.getServerData(i);
            if (data.serverIP.equals(ip)) return data;
        }
        ServerData data = new ServerData("Packmenu Managed Server", ip, false);
        list.addServerData(data);
        list.saveServerList();
        return data;
    }
}
