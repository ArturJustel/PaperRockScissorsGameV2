package com.abjust.gamelogic.handsignals;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HandSignalLogicTest implements IHandSignals{
    @Spy HandSignalLogic handSignalLogicMock = new HandSignalLogic(); 

    @Test
    void testRevealPaperVsRockSignals() {
        //Arrange
        int expectedResult = 1;
        doReturn("3").when(handSignalLogicMock).encryptSelection(SCISSORS_VALUE);
        doReturn("2").when(handSignalLogicMock).encryptSelection(ROCK_VALUE);
        doReturn("1").when(handSignalLogicMock).encryptSelection(PAPER_VALUE);
        //Act
        int result = handSignalLogicMock.revealSignals(PAPER_VALUE, ROCK_VALUE);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testRevealPaperVsScissorsSignals() {
        //Arrange
        int expectedResult = -1;
        doReturn("3").when(handSignalLogicMock).encryptSelection(SCISSORS_VALUE);
        doReturn("2").when(handSignalLogicMock).encryptSelection(ROCK_VALUE);
        doReturn("1").when(handSignalLogicMock).encryptSelection(PAPER_VALUE);
        //Act
        int result = handSignalLogicMock.revealSignals(PAPER_VALUE, SCISSORS_VALUE);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testRevealScissorsVsRockSignals() {
        //Arrange
        int expectedResult = -1;
        doReturn("3").when(handSignalLogicMock).encryptSelection(SCISSORS_VALUE);
        doReturn("2").when(handSignalLogicMock).encryptSelection(ROCK_VALUE);
        doReturn("1").when(handSignalLogicMock).encryptSelection(PAPER_VALUE);
        //Act
        int result = handSignalLogicMock.revealSignals(SCISSORS_VALUE, ROCK_VALUE);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testRevealSameSignals() {
        //Arrange
        int expectedResult = 0;
        doReturn("3").when(handSignalLogicMock).encryptSelection(SCISSORS_VALUE);
        doReturn("2").when(handSignalLogicMock).encryptSelection(ROCK_VALUE);
        doReturn("1").when(handSignalLogicMock).encryptSelection(PAPER_VALUE);
        //Act
        int result = handSignalLogicMock.revealSignals(PAPER_VALUE, PAPER_VALUE);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testEncryption() {
        //Arrange
        doReturn("123").when(handSignalLogicMock).getSalt();
        //Act
        String result = handSignalLogicMock.encryptSelection(SCISSORS_VALUE);
        //Assert
        assertNotNull(result);
    }
}
