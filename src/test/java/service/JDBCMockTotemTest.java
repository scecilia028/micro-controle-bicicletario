package service;

import org.junit.jupiter.api.Test;

import domain.Totem;
import services.JDBCMockTotem;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JDBCMockTotemTest {

    JDBCMockTotem mock;
    @BeforeEach
    void init() {
       mock = new JDBCMockTotem();
    }
    @Test
    void testGetDataByIdSuccess() {
        assertTrue(mock.getDataById("1") instanceof Totem);
    }

    @Test
    void testGetDataByIdFailure() {
        assertFalse(mock.getDataById("") instanceof Totem);
    }

    @Test
    void testUpdateDataSuccess() {
        int size = mock.banco.size();
        Totem totem = new Totem(String.valueOf("a"), "Rua ".concat(String.valueOf("a")));
        mock.updateTotem(totem);
        assertEquals(size, mock.banco.size());
    }
    
    @Test
    void testUpdateDataSameInstance() {
        int size = mock.banco.size();
        Totem totem = new Totem(String.valueOf("a"), "Rua ".concat(String.valueOf("a")));
        mock.updateTotem(totem);
        assertEquals(size, mock.banco.size());
    }
    @Test
    void testDeleteDataSuccess() {
        assertTrue(mock.deleteData("1"));
    }

    void testGetDataFailure() {
        assertFalse(mock.getDataById("a") instanceof Totem);
    }

    @Test
    void testDeleteDataFailure() {
        assertFalse(mock.deleteData("a"));
    }
}