package com.abjust.gamelogic.players;

import java.util.Random;

/*
 * The following class is a subclass of {@link Player} and it is used for bot players.
 */
public class BotPlayer extends Player {
    private Random random = new Random();

    /*
     *  Modified method that let's  a bot to select a hand signal.
     */
    @Override public String pickHandSignal()
    {
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
