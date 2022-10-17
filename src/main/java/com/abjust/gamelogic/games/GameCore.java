package com.abjust.gamelogic.games;

import com.abjust.gamelogic.handsignals.HandSignalLogic;
import com.abjust.gamelogic.players.BotPlayer;
import com.abjust.gamelogic.players.RealPlayer;
import com.abjust.helpers.Crypto;

/*
* The following class contains the core of the game.
*/
public class GameCore {
    private RealPlayer realPlayer;
    private BotPlayer botPlayer;
    private HandSignalLogic handSignalLogic;
    

    /**
     * The following method setups the game.
     * 
     * @param aHandSignalLogic
     * @param aRealPlayer
     * @param aBotPlayer
     */
    public void setupTheGame(HandSignalLogic aHandSignalLogic,RealPlayer aRealPlayer, BotPlayer aBotPlayer)
    {
        this.handSignalLogic = aHandSignalLogic;
        this.realPlayer = aRealPlayer;
        this.botPlayer = aBotPlayer;
    }

    
    /*
     * The following method starts the game.
     */
    public boolean playTheGame()
    {
        Crypto.setSalt();
        botPlayer.botSays("   ");
        String botSignal = handSignalLogic.encryptSelection(botPlayer.pickHandSignal());
        botPlayer.botSays("I have made my choice and it is this "+botSignal);
        String playerSignal = realPlayer.pickHandSignal();
        if(playerSignal.equals("0"))
        {
            printTheWinner();
            return false;
        }else if(playerSignal.equals("help"))
        {
            tellRules();
            return true;
        }
        int gameResult = handSignalLogic.revealSignals(playerSignal,botSignal);

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
        return true;
    }

    /*
     * The following method prints the score to the user.
     */
    void printTheScore()
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
    private void tellRules() {
        botPlayer.botSays("The game is simple. You and I will pick one of three hand signals (Paper,Rock or Scissors) at the same time.");
        botPlayer.botSays("Then we will revile our selected signal and see who wins");
        botPlayer.botSays("The winner is determined as following:");
        botPlayer.botSays("Paper beats (wraps) rock");
        botPlayer.botSays("Rock beats (blunts) scissors ");
        botPlayer.botSays("Scissors beats (cuts) paper");
        botPlayer.botSays(" ");
        botPlayer.botSays("I hope that now you understand the game. Now let's play!");
    }
}
