package util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ValidatorTest {
    

    @Test
    void testisNullOrEmpnull() {
        assertTrue(Validator.isNullOrEmpty("null"));
        assertTrue(Validator.isNullOrEmpty(null));
        assertFalse(Validator.isNullOrEmpty("Um dois tres"));
    }

    @Test
    void testIsInRangeEnum(){
        assertTrue(Validator.isInRangeEnumBicicleta("Disponivel"));
        assertFalse(Validator.isInRangeEnumBicicleta("Nao disponivel"));
    }
    
    @Test
    void testIsInRangeEnumTranca(){
        assertTrue(Validator.isInRangeEnumTranca("OCUPADA"));
        assertFalse(Validator.isInRangeEnumTranca("Nao disponivel"));
    }
    
    @Test
    void testIsInRangeEnumTrancaAcao(){
        assertTrue(Validator.isInRangeEnumTrancaAcao("TRANCAR"));
        assertFalse(Validator.isInRangeEnumTrancaAcao("Nao TRANCAR"));
    }
    
    @Test
    void testIsBicicletaAvailable(){
        assertTrue(Validator.isBicicletaAvailable("DISPONIVEL"));
        assertFalse(Validator.isBicicletaAvailable("APOSENTADA"));
    }
    
    @Test
    void testIsTrancaAvailable(){
        assertTrue(Validator.isTrancaAvailable("NOVA"));
        assertFalse(Validator.isTrancaAvailable("EM_REPARO"));
    }
    
    
    @Test
    void testIsInRangeValidChavesJson(){
        assertTrue(Validator.isInRangeValidChavesJson("status"));
        assertFalse(Validator.isInRangeValidChavesJson("asdad"));
    }
}
