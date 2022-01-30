package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BicicletaTest {
    Bicicleta bicicleta;

    @BeforeEach
    void init() {
       bicicleta = new Bicicleta("123",
                                 "Caloi",
                                 "Monark",
                                 "2020",
                                 123,
                                 BicicletaStatus.DISPONIVEL);
    }

    @Test
    void testGetId() {
        String id = bicicleta.getId();
        assertEquals("123", id);
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
        assertEquals("2020", ano);
    }

    @Test
    void testSetAno(){
        bicicleta.setAno("2021");
        String ano = bicicleta.getAno();
        assertEquals("2021", ano);
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
    
    @Test
    void testClassEquals() {
       Bicicleta bike = new Bicicleta("123",
               "Caloi",
               "Monark",
               "2020",
               123,
               BicicletaStatus.DISPONIVEL);
        assertEquals(true, bicicleta.getClass().equals(bike.getClass()));
    }
    
    @Test
    void testClassNotEquals() {
       Bicicleta bike = new Bicicleta("555",
               "Caloi",
               "Monark",
               "2020",
               233652165,
               BicicletaStatus.DISPONIVEL);
        assertEquals(false, bicicleta.getId().equals(bike.getId()));
    }
    
    @Test
    void testObjNull() {
       Bicicleta bike = null;
       assertEquals(false, bicicleta.equals(bike));
    }
    
}
