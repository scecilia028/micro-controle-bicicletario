package service;

import org.junit.jupiter.api.Test;

import domain.Tranca;
import domain.TrancaStatus;
import services.JDBCMockTranca;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JDBCMockTrancaTest {

    JDBCMockTranca mock;
    @BeforeEach
    void init() {
       mock = new JDBCMockTranca();
    }
    @Test
    void testGetDataByIdSuccess() {
        assertTrue(mock.getDataById("1") instanceof Tranca);
    }

    @Test
    void testGetDataByIdFailure() {
        assertFalse(mock.getDataById("") instanceof Tranca);
    }

    @Test
    void testUpdateDataSuccess() {
        int size = mock.banco.size();
        Tranca tranca = new Tranca(String.valueOf("a"), String.valueOf("a"), String.valueOf("a"), "Rua ".concat(String.valueOf("a")),
        		"200".concat(String.valueOf("a")), "a".concat(String.valueOf("a")), TrancaStatus.LIVRE);
        mock.updateTranca(tranca);
        assertEquals(size, mock.banco.size());
    }
    
    @Test
    void testUpdateDataSameInstance() {
        int size = mock.banco.size();
        Tranca tranca = new Tranca(String.valueOf("a"), String.valueOf("a"), String.valueOf("a"), "Rua ".concat(String.valueOf("a")),
        		"200".concat(String.valueOf("a")), "a".concat(String.valueOf("a")), TrancaStatus.NOVA);
        mock.updateTranca(tranca);
        assertEquals(size, mock.banco.size());
    }
    @Test
    void testDeleteDataSuccess() {
        assertTrue(mock.deleteData("1"));
    }

    void testGetDataFailure() {
        assertFalse(mock.getDataById("a") instanceof Tranca);
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