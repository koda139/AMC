package com.alpha67.amcbase.Events;

import sunrisesunset.SunriseSunset;
import net.minecraft.world.GameRules;
import net.minecraft.world.IWorld;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.*;

public class SunSet {

    @SubscribeEvent
    public void TickEvent(TickEvent.WorldTickEvent event)
    {

        if (event.phase == TickEvent.Phase.END) {

            IWorld world = event.world;

            //((ServerWorld) world).setWeatherParameters(5000,5000,true,false);
            //System.out.println("weather");

            boolean rule = ((ServerWorld) world).getGameRules().getBoolean(GameRules.RULE_DAYLIGHT);
            //((ServerWorld) world).setWeatherParameters();


            if (!rule) {

                Date date = new Date();
                Date date2 = new Date();
                Calendar[] sunriseSunset = SunriseSunset.getSunriseSunset(Calendar.getInstance(), 48.490129, 7.664418);

                Calendar Set = sunriseSunset[0];
                Calendar Rise = sunriseSunset[1];

                int SetMi = Set.get(Calendar.MINUTE);
                int SetH = Set.get(Calendar.HOUR_OF_DAY);
                int RiseMi = Set.get(Calendar.MINUTE);
                int RiseH = Set.get(Calendar.HOUR);
                SetH = SetH + 12;

                RiseMi = RiseH * 60 + RiseMi;
                SetMi = SetH * 60 + SetMi;

                //minecraft day = 24000 tick
                //SunRise = 0 tick real word = RiseMi
                //SunSet = 12541 tick real word = SetMi

                int SunRiseTick = RiseMi * 24000 / 1440;
                int SetMiTick = SetMi * 24000 / 1440;


                Date date3 = new Date();   // given date
                Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
                calendar.setTime(date3);   // assigns calendar to given date
                calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
                calendar.get(Calendar.HOUR);        // gets hour in 12h format
                calendar.get(Calendar.MONTH);


                int realTime = calendar.get(Calendar.HOUR) * 60 + isPm() * 60 + calendar.get(Calendar.MINUTE);

                int morning = (RiseMi / realTime) * 12541;

                float day = (float) SetMi / realTime * 24000;



                if (world instanceof ServerWorld) {

                    if (calendar.get(Calendar.AM_PM) == Calendar.PM) {
                        ((ServerWorld) world).setDayTime((int) day);
                    } else {
                        ((ServerWorld) world).setDayTime((int) morning);
                    }


                }

            }
        }


    }

    private static int isPm() {
        Date date3 = new Date();   // given date
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(date3);   // assigns calendar to given date
        calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
        calendar.get(Calendar.HOUR);        // gets hour in 12h format
        calendar.get(Calendar.MONTH);

        boolean isPM;

        if (calendar.get(Calendar.AM_PM) == Calendar.PM)
        {
            return 12;
        }

        else
        {
            return 0;
        }
    }
    

    }


