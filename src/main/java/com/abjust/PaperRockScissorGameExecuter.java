package com.abjust;

import com.abjust.gamelogic.games.Game;
import com.abjust.helpers.InputHelper;

/*
 *  The following class executes the paper-rock-scissor game.
 */
public class PaperRockScissorGameExecuter
{
    private static InputHelper inputHelper = new InputHelper();
    public static void main(String[] args) {
        Game gameLogic = new Game();
        intro();
        gameLogic.playTheGame();
    }

    /*
     * The following method provides a small introduction to a user.
     */
    private static void intro() {
        inputHelper.botSays("  ");
        inputHelper.botSays("Hi there! I am Paper-Rock-Scissor Bot and I would like to play a game with you!");
        inputHelper.botSays("You can stop the game at any moment by saying break. Also if you don't know this game just ask for help");
 
    }
}