package com.alpha67.AMCBase.util;

import net.minecraft.util.Util;

import java.net.URI;
import java.net.URISyntaxException;

public class openURL {
    public static void openURL(String URL)
    {
        try
        {
            Util.getOSType().openURI(new URI(URL));
            System.out.println("open discord");
        }
        catch(URISyntaxException e)
        {
            e.printStackTrace();
        }
    }
}
