package bicicleta;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JDBCMockTest
 */
class JDBCMockTest {

    JDBCMock mock;
    @BeforeEach
    void init() {
       mock = new JDBCMock();
    }
    @Test
    void testGetDataByIdSuccess() {
        assertTrue(mock.getDataById("1") instanceof Bicicleta);
    }

    @Test
    void testGetDataByIdFailure() {
        assertFalse(mock.getDataById("") instanceof Bicicleta);
    }

    @Test
    void testGetDataByCodeSuccess() {
        assertTrue(mock.getDataByCode(1) instanceof Bicicleta);
    }
    @Test
    void testUpdateDataSuccess() {
        int size = mock.banco.size();
        Bicicleta bikObject = new Bicicleta("1", 1, BicicletaStatus.DISPONIVEL);
        mock.updateData(bikObject);
        assertEquals(size, mock.banco.size());
    }
    
    @Test
    void testUpdateDataNewInstance() {
        int size = mock.banco.size();
        Bicicleta bikObject = new Bicicleta("a", 152, BicicletaStatus.DISPONIVEL);
        mock.updateData(bikObject);
        assertEquals(size+1, mock.banco.size());
    }
    @Test
    void testDeleteDataSuccess() {
        assertTrue(mock.deleteData("1"));
    }

    void testGetDataFailure() {
        assertFalse(mock.getDataById("a") instanceof Bicicleta);
    }



    @Test
    void testDeleteDataFailure() {
        assertFalse(mock.deleteData("a"));
    }
}