package util;

import org.junit.jupiter.api.Test;

import util.ErrorResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ErrorResponseTest {
    @Test
    void testGetTitle() {
        assertNull(ErrorResponse.getTitle());
    }

    @Test
    void testGetDetails() {
        assertNull(ErrorResponse.getDetails());
    }

    @Test
    void testGetStatus() {
        assertEquals(0, ErrorResponse.getStatus());
    }

    @Test
    void testGetType() {
        assertNull(ErrorResponse.getType());
    }

}
