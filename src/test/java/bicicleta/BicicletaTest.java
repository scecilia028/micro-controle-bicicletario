package bicicleta;

import org.junit.jupiter.api.Test;

import domain.Bicicleta;
import domain.BicicletaStatus;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
class BicicletaTest {
    Bicicleta bicicleta;

    @BeforeEach
    void init() {
       bicicleta = new Bicicleta("123",
                                 123, 
                                 "Caloi",
                                 "Monark",
                                 "1990",
                                 123,
                                 BicicletaStatus.DISPONIVEL);
    }
    /**
     * Rigorous Test.
     */
    @Test
    void testGetId() {
        String id = bicicleta.getId();
        assertEquals("123", id);
    }
    @Test
    void testGetCode() {
        Integer code = bicicleta.getCode();
        assertEquals(123, code);
    }
    @Test
    void testGetStatus() {
        BicicletaStatus status = bicicleta.getStatus();
        assertEquals(BicicletaStatus.DISPONIVEL, status);
    }
    @Test
    void testSetStatus() {
        BicicletaStatus status = bicicleta.getStatus();
        assertEquals(BicicletaStatus.DISPONIVEL, status);
        bicicleta.setStatus(BicicletaStatus.EM_USO);
        status = bicicleta.getStatus();
        assertEquals(BicicletaStatus.EM_USO, status);
    }

    @Test
    void testGetAno() {
    	String ano = bicicleta.getAno();
        assertEquals(1990, ano);
    }

    @Test
    void testSetAno(){
        bicicleta.setAno("1991");
        String ano = bicicleta.getAno();
        assertEquals(1991, ano);
    }

    @Test
    void testGetMarca(){
        String marca = bicicleta.getMarca();
        assertEquals("Caloi", marca);
    }

    @Test
    void testSetMarca() {
        bicicleta.setMarca("Venzo");
        String marca = bicicleta.getMarca();
        assertEquals("Venzo", marca);
    }

    @Test
    void testGetModelo() {
        String modelo = bicicleta.getModelo();
        assertEquals("Monark", modelo);
    }

    @Test
    void testSetModelo() {
        bicicleta.setModelo("evo");
        String modelo = bicicleta.getModelo();
        assertEquals("evo", modelo);
    }
}
