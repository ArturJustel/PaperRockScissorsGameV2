package com.abjust.gamelogic.players;

import java.util.Random;

/*
 * The following class is a subclass of {@link Player} and it is used for bot players.
 */
public class BotPlayer extends Player {

    /*
     *  Modified method that let's  a bot to select a hand signal.
     */
    @Override public String pickHandSignal()
    {
        Random random = new Random();
        return String.valueOf(random.nextInt(3)+1);
    }

    /*
     *  The following method prints a bot sentence.
     */
    public void botSays(String aSentence)
    {
        System.out.println(aSentence);
    }
}
