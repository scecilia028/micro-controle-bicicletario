package bicicleta;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import util.Validator;

class ValidatorTest {
    

    @Test
    void testisNullOrEmpnull() {
        assertTrue(Validator.isNullOrEmpty("null"));
        assertTrue(Validator.isNullOrEmpty(null));
        assertFalse(Validator.isNullOrEmpty("Um dois tres"));
    }

    @Test
    void testIsInRangeEnum(){
        assertTrue(Validator.isInRangeEnum("Disponivel"));
        assertFalse(Validator.isInRangeEnum("Nao disponivel"));
    }
}
