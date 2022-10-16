package com.abjust.gamelogic.players;

import com.abjust.gamelogic.handsignals.IHandSignals;
import com.abjust.helpers.InputHelper;

/*
 *  The following class is a super class for players.
 */
public class Player implements IHandSignals{
    private int playerScore=0;
    private InputHelper inputHelper = new InputHelper();

    /*
     * Get the player score.
     */
    public int getPlayerScore()
    {
        return this.playerScore;
    }

    /*
     *  Increase player score by 1.
     */
    public void increasePlayerScore()
    {
        playerScore++;
    }

    /*
     *  Let player select a hand signal.
     */
    public String pickHandSignal()
    {
        String userInput = getInput();
        if(userInput.charAt(0)=='p')
        {
            return PAPER_VALUE;
        }else if(userInput.charAt(0)=='r')
        {
            return ROCK_VALUE;
        }else if(userInput.charAt(0)=='s')
        {
            return SCISSORS_VALUE;
        }else if(userInput.charAt(0)=='b')
        {
            return EXIT_VALUE;
        }else if(userInput.charAt(0)=='h')
        {
            return HELP_VALUE;
        }
        return pickHandSignal();
    }

    /*
     * Gets user input with the help of {@InputHelper}
     */
    String getInput()
    {
        return inputHelper.getUserInput("Pick your hand signal: (Paper | Rock | Scissors)");
    }
}
