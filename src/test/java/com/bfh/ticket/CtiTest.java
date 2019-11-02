package com.bfh.ticket;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CtiTest {

    private Cti cti;

    @BeforeAll
    static void init() {
        try {
            System.loadLibrary("ticket_recognition_jcpp");
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    @BeforeEach
    void setUp() {
        cti = new Cti();
    }

    @Test
    void cti_createMatcherAndDelete_matcherCreatedd() {
        // Act
        Matcher matcher = cti.matcher(Algorithms.SIFT.name());

        // Assert
        assertNotNull(matcher);

        matcher.delete();
    }
}
