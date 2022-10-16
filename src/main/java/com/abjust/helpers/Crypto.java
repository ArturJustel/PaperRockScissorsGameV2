package com.abjust.helpers;

import java.util.Random;

/*
* The following class contains salt setter and getter that are used in the game.
*/
public class Crypto {
    
    private static String salt;

    /*
    * The following method sets the new random salt value.
    */
    public static void setSalt()
    {
        salt= String.valueOf(new Random().nextInt(100));
    }

    /*
    * The following method gets current salt value.
    */
    public static String getSalt()
    {
        return salt;
    }
}
