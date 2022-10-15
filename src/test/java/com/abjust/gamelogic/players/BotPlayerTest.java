package com.abjust.gamelogic.players;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BotPlayerTest {
    @Spy BotPlayer botPlayerSpy = new BotPlayer();

    @Test
    void testBotSays() {
        //Arrange
        doNothing().when(botPlayerSpy).botSays(anyString());
        //Act
        botPlayerSpy.botSays("Test");
        //Assert
        verify(botPlayerSpy).botSays(anyString());
    }

    @Test
    void testPickHandSignal() {
        //Act
        String result = botPlayerSpy.pickHandSignal();
        //Assert
        assertNotNull(result);
    }
}
