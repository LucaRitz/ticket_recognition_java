package com.bfh.ticket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CtiTest {

    private Cti cti;

    @BeforeEach
    void setUp() {
        cti = new Cti();
    }

    @Test
    void helloWorld() {
        // Act
        String answer = cti.helloWorld();

        // Assert
        assertEquals("Hello World!ZZ", answer);
    }
}
