package com.abjust.gamelogic.players;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.abjust.gamelogic.handsignals.IHandSignals;

@ExtendWith(MockitoExtension.class)
public class PlayerTest implements IHandSignals{
    @Spy Player playerMock = new Player();

    @Test
    void testIncreasePlayerScore() {
        //Arrange
        Player testPlayer = new Player();
        assertEquals(0,testPlayer.getPlayerScore());
        //Act
        testPlayer.increasePlayerScore();
        //Assert
        assertEquals(1, testPlayer.getPlayerScore());
    }

    @Test
    void testPickPaperSignal() {
        //Arrange
        doReturn("paper").when(playerMock).getInput();
        
        //Act
        String result = playerMock.pickHandSignal();

        //Assert
        verify(playerMock).getInput();
        assertEquals(PAPER_VALUE, result);
    }

    @Test
    void testPickRockSignal() {
        //Arrange
        doReturn("rock").when(playerMock).getInput();
        
        //Act
        String result = playerMock.pickHandSignal();

        //Assert
        verify(playerMock).getInput();
        assertEquals(ROCK_VALUE, result);
    }

    @Test
    void testPickScissorSignal() {
        //Arrange
        doReturn("scissor").when(playerMock).getInput();
        
        //Act
        String result = playerMock.pickHandSignal();

        //Assert
        verify(playerMock).getInput();
        assertEquals(SCISSORS_VALUE, result);
    }

    @Test
    void testPickExitSignal() {
        //Arrange
        doReturn("break").when(playerMock).getInput();
        
        //Act
        String result = playerMock.pickHandSignal();

        //Assert
        verify(playerMock).getInput();
        assertEquals(EXIT_VALUE, result);
    }

    @Test
    void testPickHelpSignal() {
        //Arrange
        doReturn("help").when(playerMock).getInput();
        
        //Act
        String result = playerMock.pickHandSignal();

        //Assert
        verify(playerMock).getInput();
        assertEquals(HELP_VALUE, result);
    }
}
