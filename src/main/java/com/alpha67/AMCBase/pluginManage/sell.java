package com.alpha67.AMCBase.pluginManage;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class sell {

    public static void sell(String type) {

        double stone = getStone();
        double wood = getWood();
        double gold = getGold();
        double diamond = getDiamond();

        double stoneR = getStoneR();
        double woodR = getWoodR();
        double goldR = getGoldR();
        double diamondR = getDiamondR();

        System.out.println("sellllll: " + type);

        if(type.contains("tone"))
        {
            System.out.println("stone + 1 " + stone);
            stone = stone+1;
            stoneR = stoneR+1;
        }
        else if(type.contains("wood"))
        {
            wood = wood+1;
            woodR = woodR+1;
        }
        else if(type.contains("gold"))
        {
            gold = gold+1;
            goldR = goldR+1;
        }
        else if(type.contains("diamond"))
        {
            diamond = diamond+1;
            diamondR = diamondR+1;
        }

        JSONObject jsonObject = new JSONObject();
        //Inserting key-value pairs into the json object
        jsonObject.put("stone", stone);
        jsonObject.put("wood", wood);
        jsonObject.put("gold", gold);
        jsonObject.put("diamond", diamond);

        jsonObject.put("stoneR", stoneR);
        jsonObject.put("woodR", woodR);
        jsonObject.put("goldR", goldR);
        jsonObject.put("diamondR", diamondR);


        try {
            FileWriter file = new FileWriter("communication-alpha/count.json");
            file.write(jsonObject.toJSONString());
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static double getStone()
    {
        try {
            Object ob = new JSONParser().parse(new FileReader("communication-alpha/count.json"));
            JSONObject js = (JSONObject) ob;
            double stone = (double) js.get("stone");
            return stone;
        } catch (Exception e)
        {
            double stone = 0;
            return stone;
        }
    }

    private static double getWood()
    {
        try {
            Object ob = new JSONParser().parse(new FileReader("communication-alpha/count.json"));
            JSONObject js = (JSONObject) ob;
            double stone = (double) js.get("wood");
            return stone;
        } catch (Exception e)
        {
            double stone = 0;
            return stone;
        }
    }

    private static double getGold()
    {
        try {
            Object ob = new JSONParser().parse(new FileReader("communication-alpha/count.json"));
            JSONObject js = (JSONObject) ob;
            double stone = (double) js.get("gold");
            return stone;
        } catch (Exception e)
        {
            double stone = 0;
            return stone;
        }
    }

    private static double getDiamond()
    {
        try {
            Object ob = new JSONParser().parse(new FileReader("communication-alpha/count.json"));
            JSONObject js = (JSONObject) ob;
            double stone = (double) js.get("diamond");
            return stone;
        } catch (Exception e)
        {
            double stone = 0;
            return stone;
        }
    }



    private static double getStoneR()
    {
        try {
            Object ob = new JSONParser().parse(new FileReader("communication-alpha/count.json"));
            JSONObject js = (JSONObject) ob;
            double stone = (double) js.get("stoneR");
            return stone;
        } catch (Exception e)
        {
            double stone = 0;
            return stone;
        }
    }

    private static double getWoodR()
    {
        try {
            Object ob = new JSONParser().parse(new FileReader("communication-alpha/count.json"));
            JSONObject js = (JSONObject) ob;
            double stone = (double) js.get("woodR");
            return stone;
        } catch (Exception e)
        {
            double stone = 0;
            return stone;
        }
    }

    private static double getGoldR()
    {
        try {
            Object ob = new JSONParser().parse(new FileReader("communication-alpha/count.json"));
            JSONObject js = (JSONObject) ob;
            double stone = (double) js.get("goldR");
            return stone;
        } catch (Exception e)
        {
            double stone = 0;
            return stone;
        }
    }

    private static double getDiamondR()
    {
        try {
            Object ob = new JSONParser().parse(new FileReader("communication-alpha/count.json"));
            JSONObject js = (JSONObject) ob;
            double stone = (double) js.get("diamondR");
            return stone;
        } catch (Exception e)
        {
            double stone = 0;
            return stone;
        }
    }


}
