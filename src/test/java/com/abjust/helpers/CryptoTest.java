package com.abjust.helpers;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;



/*
 * Tests for {@link Crypto}
 */
public class CryptoTest {

    /*
     * The following test verifies that salt value can be set and obtained.
     */
    @Test
    public void testGetSalt() {
        assertNull(Crypto.getSalt());
        //Act
        Crypto.setSalt();
        //Assert
        assertNotNull(Crypto.getSalt());
    }
}
