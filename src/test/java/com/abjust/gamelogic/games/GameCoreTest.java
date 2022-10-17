package com.abjust.gamelogic.games;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.abjust.gamelogic.handsignals.HandSignalLogic;
import com.abjust.gamelogic.players.BotPlayer;
import com.abjust.gamelogic.players.RealPlayer;

@ExtendWith(MockitoExtension.class)
public class GameCoreTest {
    @Mock
    HandSignalLogic handSignalLogicMock;
    @Mock
    RealPlayer realPlayerMock;
    @Mock
    BotPlayer botPlayerMock;
    @Spy
    GameCore testGame = new GameCore();

    @BeforeEach
    public void init(){
        testGame.setupTheGame(handSignalLogicMock, realPlayerMock, botPlayerMock);
    }

    @Test
    void testExitTheGame() {
        //Arrange
        doReturn("2").when(botPlayerMock).pickHandSignal();
        doReturn("2").when(handSignalLogicMock).encryptSelection(anyString());
        doReturn("0").when(realPlayerMock).pickHandSignal();
        //Act
        testGame.playTheGame();
        //Assert
        verify(testGame).printTheWinner();
        verify(botPlayerMock,times(3)).botSays(anyString());
    }

    @Test
    void testPlayerWins() {
        //Arrange
        doReturn("2").when(botPlayerMock).pickHandSignal();
        doReturn("2").when(handSignalLogicMock).encryptSelection(anyString());
        doReturn("1").when(realPlayerMock).pickHandSignal();
        doReturn(1).when(handSignalLogicMock).revealSignals("1", "2");
        //Act
        testGame.playTheGame();
        //Assert
        verify(botPlayerMock,times(5)).botSays(anyString());
        verify(botPlayerMock).botSays("Wow you got a point");
        verify(realPlayerMock).increasePlayerScore();
        verify(testGame).printTheScore();
    }

    @Test
    void testBotWins() {
        //Arrange
        doReturn("2").when(botPlayerMock).pickHandSignal();
        doReturn("2").when(handSignalLogicMock).encryptSelection(anyString());
        doReturn("1").when(realPlayerMock).pickHandSignal();
        doReturn(-1).when(handSignalLogicMock).revealSignals("1", "2");
        //Act
        testGame.playTheGame();
        //Assert
        verify(botPlayerMock,times(5)).botSays(anyString());
        verify(botPlayerMock).botSays("Yay I got a point");
        verify(botPlayerMock).increasePlayerScore();
        verify(testGame).printTheScore();
    }

    @Test
    void testHelp() {
        //Arrange
        doReturn("2").when(botPlayerMock).pickHandSignal();
        doReturn("2").when(handSignalLogicMock).encryptSelection(anyString());
        doReturn("help").when(realPlayerMock).pickHandSignal();
        //Act
        testGame.playTheGame();
        //Assert
        verify(botPlayerMock,times(10)).botSays(anyString());
    }

    @Test 
    void printBothWin()
    {
        //Arrange
        doReturn(1).when(realPlayerMock).getPlayerScore();
        doReturn(1).when(botPlayerMock).getPlayerScore();
        //Act
        testGame.printTheWinner();
        //Verify
        verify(realPlayerMock).getPlayerScore();
        verify(botPlayerMock).getPlayerScore();
        verify(botPlayerMock).botSays("It seems that both of us are good in this game!");
    }

    @Test 
    void printPlayerWin()
    {
        //Arrange
        doReturn(2).when(realPlayerMock).getPlayerScore();
        doReturn(1).when(botPlayerMock).getPlayerScore();
        //Act
        testGame.printTheWinner();
        //Verify
        verify(realPlayerMock).getPlayerScore();
        verify(botPlayerMock).getPlayerScore();
        verify(botPlayerMock).botSays("Looks like you managed to beat me this time!");
    }

    @Test 
    void printBotWin()
    {
        //Arrange
        doReturn(1).when(realPlayerMock).getPlayerScore();
        doReturn(2).when(botPlayerMock).getPlayerScore();
        //Act
        testGame.printTheWinner();
        //Verify
        verify(realPlayerMock).getPlayerScore();
        verify(botPlayerMock).getPlayerScore();
        verify(botPlayerMock).botSays("I beat you this time but don't worry you can always try again!");
    }
}
