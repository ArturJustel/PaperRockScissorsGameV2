package com.abjust.gamelogic.games;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GameCoreTest {
    @Spy GameCore testGame = new GameCore();

    @Test
    void testExitTheGame() {
        //Arrange
        doReturn("2").when(testGame).getBotSignal();
        doReturn("0").when(testGame).getPlayerSignal();
        //Act
        testGame.playTheGame();
        //Assert
        verify(testGame).printTheWinner();
    }

    @Test
    void testPlayerWins() {
        //Arrange
        doReturn("1").when(testGame).getBotSignal();
        doReturn("2").when(testGame).getPlayerSignal();
        doReturn(1).when(testGame).getGameResult("2", "1");
        //Act
        testGame.playTheGame();
    }

    @Test
    void testBotWins() {
        //Arrange
        doReturn("2").when(testGame).getBotSignal();
        doReturn("1").when(testGame).getPlayerSignal();
        doReturn(-1).when(testGame).getGameResult("1", "2");
        //Act
        testGame.playTheGame();
    }

    @Test
    void testHelp() {
        //Arrange
        doReturn("2").when(testGame).getBotSignal();
        doReturn("help").when(testGame).getPlayerSignal();
        //Act
        testGame.playTheGame();
    }
}
