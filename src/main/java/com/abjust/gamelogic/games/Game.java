package com.abjust.gamelogic.games;



/*
* The following class is a subclass of {@link GameCore}.
*/
public class Game extends GameCore{
    boolean keepPlaying = true;

    @Override
    public boolean playTheGame() {
        while(keepPlaying){
            keepPlaying = super.playTheGame();
        }
        return false;
    }
}
