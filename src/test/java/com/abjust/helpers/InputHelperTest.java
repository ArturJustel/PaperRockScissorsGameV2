package com.abjust.helpers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.abjust.helpers.errormessages.ErrorMessages;

@ExtendWith(MockitoExtension.class)
public class InputHelperTest {
   @Spy InputHelper inputHeplerSpy = new InputHelper();

    @Test
    void testGetCorrectUserInput() {
    //Arrange
    String input = "Test";
    doNothing().when(inputHeplerSpy).botSays("Start");
    doReturn(input).when(inputHeplerSpy).readLine();
    //Act
    inputHeplerSpy.getUserInput("Start");
    //Assert
    verify(inputHeplerSpy).botSays(anyString());
    verify(inputHeplerSpy).convertIntoLowerCase(input);
    }

    @Test
    void testGetEmptyUserInput() {
    //Arrange
    doNothing().when(inputHeplerSpy).botSays("Start");
    doReturn("").when(inputHeplerSpy).readLine();
    //Act
    inputHeplerSpy.getUserInput("Start");
    //Assert
    verify(inputHeplerSpy).botSays("Start");
    verify(inputHeplerSpy,times(10)).botSays("Could you re-enter your answer?");
    verify(inputHeplerSpy,times(10)).botSays(ErrorMessages.ERROR_NO_INPUT_WAS_PROVIDED);
    verify(inputHeplerSpy,times(10)).getUserInput("Could you re-enter your answer?");
    verify(inputHeplerSpy).botSays(ErrorMessages.ERROR_SUSPICIOUS_BEHAVIOR);
    }
}
