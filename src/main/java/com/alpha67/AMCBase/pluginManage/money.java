package com.alpha67.AMCBase.pluginManage;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class money {

    public static void giveMoney(String UUID, double sellPrice)
    {

        try {
            Object ob = new JSONParser().parse(new FileReader("communication-alpha/playerData/"+UUID+".json"));

            JSONObject js = (JSONObject) ob;

            double pastMoney = (double) js.get("money");

            double newMoney = pastMoney + sellPrice;

            JSONObject jsonObject = new JSONObject();
            //Inserting key-value pairs into the json object

            System.out.println("give money to " + "communication-alpha/playerData/"+UUID+".json"+ "money : " + newMoney+ "add: "+ sellPrice);

            jsonObject.put("money", newMoney);
            jsonObject.put("modification", true);
            try {
                FileWriter file = new FileWriter("communication-alpha/playerData/" + UUID + ".json");
                file.write(jsonObject.toJSONString());
                file.close();

            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static boolean removeMoney(String UUID, int sellPrice)
    {
        try
        {
            Object ob = new JSONParser().parse(new FileReader("communication-alpha/playerData/"+UUID+".json"));

            JSONObject js = (JSONObject) ob;

            double pastMoney = (double) js.get("money");

            double newMoney = pastMoney - sellPrice;

            JSONObject jsonObject = new JSONObject();
            //Inserting key-value pairs into the json object
            if (newMoney > 0) {
                jsonObject.put("money", newMoney);
                jsonObject.put("modification", true);
                try {
                    FileWriter file = new FileWriter("communication-alpha/playerData/" + UUID + ".json");
                    file.write(jsonObject.toJSONString());
                    file.close();
                    return true;

                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            } else
            {
                return false;
            }
        }
        catch (Exception e)
        {
            return false;
        }



    }

    public static double getMoney(String UUID)
    {

        try {
            Object ob = new JSONParser().parse(new FileReader("communication-alpha/playerData/" + UUID + ".json"));

            JSONObject js = (JSONObject) ob;

            double money = (double) js.get("money");

            return money;

        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return -1;
        }


    }


}
