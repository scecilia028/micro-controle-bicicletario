package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TotemTest {
    Totem totem;

    @BeforeEach
    void init() {
       totem = new Totem("1", "RJ");
    }
 
    @Test
    void testGetId() {
        String id = totem.getId();
        assertEquals("1", id);
    }
    
    @Test
    void testGetLocalizacao() {
    	String local = totem.getLocalizacao();
        assertEquals("RJ", local);
    }

    @Test
    void testSetLocalizacao(){
        totem.setLocalizacao("RJ bairro");
        String local = totem.getLocalizacao();
        assertEquals("RJ bairro", local);
    }
}
