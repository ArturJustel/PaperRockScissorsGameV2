package com.abjust.gamelogic.games;

import com.abjust.gamelogic.handsignals.HandSignalLogic;
import com.abjust.gamelogic.players.BotPlayer;
import com.abjust.gamelogic.players.RealPlayer;
import com.abjust.helpers.Crypto;

/*
* The following class contains the core of the game.
*/
public class GameCore {
    private final RealPlayer realPlayer = new RealPlayer();
    private final static BotPlayer botPlayer = new BotPlayer();
    private final HandSignalLogic handSignalLogic = new HandSignalLogic();
    

    
    /*
     * The following method starts the game.
     */
    public void playTheGame()
    {
        Crypto.setSalt();
        botPlayer.botSays("   ");
        String botSignal = getBotSignal();
        botPlayer.botSays("I have made my choice and it is this "+botSignal);
        String playerSignal = getPlayerSignal();
        if(playerSignal.equals("0"))
        {
            printTheWinner();
            System.exit(0);
        }else if(playerSignal.equals("help"))
        {
            tellRules();
            return;
        }
        int gameResult = getGameResult(playerSignal,botSignal);

            if(gameResult==0)
            {
                botPlayer.botSays("It looks that we have the same, let's replay");
                playTheGame();
            }else if(gameResult==1)
            {
                botPlayer.botSays("Wow you got a point");
                realPlayer.increasePlayerScore();
            }else
            {
                botPlayer.botSays("Yay I got a point");
                botPlayer.increasePlayerScore();
            }
        printTheScore();
    }

    /*
     * The following method prints the score to the user.
     */
    private void printTheScore()
    {
        botPlayer.botSays("   ");
        int playerScore = realPlayer.getPlayerScore();
        int botScore = botPlayer.getPlayerScore();
        botPlayer.botSays("You managed to win "+playerScore+" times and I won "+botScore+" times");
    }

    /*
     * The following method shows the game winner to the user.
     */
    public void printTheWinner()
    {
        int playerScore = realPlayer.getPlayerScore();
        int botScore = botPlayer.getPlayerScore();
        if(playerScore==botScore)
        {
            botPlayer.botSays("It seems that both of us are good in this game!");
        }
        else if(playerScore>botScore)
        {
            botPlayer.botSays("Looks like you managed to beat me this time!");
            
        }else
        {
            botPlayer.botSays("I beat you this time but don't worry you can always try again!");
        }
    }

        /*
     * The following method introduces the game rules to the user.
     */
    private static void tellRules() {
        botPlayer.botSays("The game is simple. You and I will pick one of three hand signals (Paper,Rock or Scissors) at the same time.");
        botPlayer.botSays("Then we will revile our selected signal and see who wins");
        botPlayer.botSays("The winner is determined as following:");
        botPlayer.botSays("Paper beats (wraps) rock");
        botPlayer.botSays("Rock beats (blunts) scissors ");
        botPlayer.botSays("Scissors beats (cuts) paper");
        botPlayer.botSays(" ");
        botPlayer.botSays("I hope that now you understand the game. Now let's play!");
    }

    /*
     *  Gets game results (Need for better test coverage).
     *  TODO: make package protected.
     */
    public int getGameResult(String aPlayerSignal, String aBotSignal)
    {
       return handSignalLogic.revealSignals(aPlayerSignal,aBotSignal);
    }

    /*
     *  Gets player signal (Need for better test coverage).
     *  TODO: make package protected.
     */
    public String getPlayerSignal() {
        return realPlayer.pickHandSignal();
    }

    /*
     *  Gets bot signal (Need for better test coverage).
     *  TODO: make package protected.
     */
    public String getBotSignal() {
        return handSignalLogic.encryptSelection(botPlayer.pickHandSignal());
    }
}
