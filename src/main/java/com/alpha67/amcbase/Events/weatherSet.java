package com.alpha67.amcbase.Events;

import net.minecraft.world.GameRules;
import net.minecraft.world.IWorld;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.commons.lang3.ObjectUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class weatherSet {

    int tick;

    @SubscribeEvent
    public void TickEvent(TickEvent.WorldTickEvent event)  {

        //System.out.println(tick);

        if (tick == 400) {

            tick = 0;

            IWorld world = event.world;

            ((ServerWorld) world).setWeatherParameters(0,10000,true,true);
            System.out.println("weather");


            URL url = null;
            try {
                url = new URL("https://api.openweathermap.org/data/2.5/weather?lat=48.493463&lon=7.671563&appid=2cc86b7ad3a7409392ad96eb1dc61bd5");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();
                Scanner scan = new Scanner(url.openStream());
                //System.out.println(scan.nextLine());


                String string = scan.nextLine();

                System.out.println(string);

                if (string.contains("Clear") == true) {
                    ((ServerWorld) world).setWeatherParameters(10000,0,false,false);
                    System.out.println("no cloud");
                } else if (string.contains("Rain") == true) {
                    System.out.println("it's rain");
                    ((ServerWorld) world).setWeatherParameters(0,10000,true,false);
                } else if (string.contains("Clouds") == true) {
                    System.out.println("it's cload");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        else
        {
            tick = tick+1;
        }
    }

}
