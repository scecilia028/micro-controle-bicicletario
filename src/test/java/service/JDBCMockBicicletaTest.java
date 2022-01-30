package service;

import org.junit.jupiter.api.Test;

import domain.Bicicleta;
import domain.BicicletaStatus;
import services.JDBCMockBicicleta;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JDBCMockBicicletaTest {

    JDBCMockBicicleta mock;
    @BeforeEach
    void init() {
       mock = new JDBCMockBicicleta();
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
    void testUpdateDataSuccess() {
        int size = mock.banco.size();
        Bicicleta bikObject = new Bicicleta("1", BicicletaStatus.DISPONIVEL);
        mock.updateData(bikObject);
        assertEquals(size, mock.banco.size());
    }
    
    @Test
    void testUpdateDataNewInstance() {
        int size = mock.banco.size();
        Bicicleta bikObject = new Bicicleta("a", BicicletaStatus.DISPONIVEL);
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
    
    @Test
    void testGetDataById() {
        assertEquals(mock.banco.get(0), mock.getDataById("0"));
    }
    
    @Test
    void testGetDataFailById() {
        assertEquals(null, mock.getDataById("asda"));
    }
}