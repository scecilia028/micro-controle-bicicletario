package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.Tranca;
import io.javalin.plugin.json.JavalinJson;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import util.JavalinApp;


@SuppressWarnings("rawtypes")
class TotemControllerTest {

    private static JavalinApp app = new JavalinApp();
    private String totemJson = JavalinJson.toJson(ControllerTotem.mock.banco);
/*
    @BeforeAll
    static void init() {
        app.start(7010);
    }
    
    @BeforeEach
    void inicio() {
    	Tranca tranca = ControllerTranca.mock.banco.get(1);
    	tranca.setIdTotem("1");
    	tranca.setIdBicicleta("1");
    }
    
    @AfterAll
    static void afterAll(){
        app.stop();
    }
    
    @Test
    void postTotemSuccessTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/totem?idTotem=2&localizacao=rio de janeiro").asString();
        assertEquals(200, response.getStatus());
    }

    @Test
    void getAllTotemsTest() {
        HttpResponse response = Unirest.get("http://localhost:7010/totem").asString();
        assertEquals(200, response.getStatus());
        assertEquals(response.getBody(), totemJson);
    }

    @Test
    void postTotemFailParamTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/totem").asString();
        assertEquals(404, response.getStatus());
    }

    @Test
    void postTotemFailNullParamTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/totem?idTotem=null").asString();
        assertEquals(404, response.getStatus());
    }

    @Test
    void deleteTotemSuccessTest() {
        HttpResponse response = Unirest.delete("http://localhost:7010/totem/8").asString();
        assertEquals(200, response.getStatus());
    }

    @Test
    void deleteTotemFailTest() {
        HttpResponse response = Unirest.delete("http://localhost:7010/totem/asd8").asString();
        assertEquals(404, response.getStatus());
    }

	@Test
    void putTotemSuccessTest() {
        HttpResponse response = Unirest.put("http://localhost:7010/totem/4?localizacao=nova iguacu").asString();
        assertEquals(200, response.getStatus());
    }
    
    @Test
    void putTotemFailTest() {
        HttpResponse response = Unirest.put("http://localhost:7010/totem/4?campoerrado=rio de janeiro").asString();
        assertEquals(422, response.getStatus());
    }
    
    @Test
    void postTotemInTrancaFailTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/totem/integrarNaRede?idTotem=88&idTranca=98").asString();
        assertEquals(404, response.getStatus());
    }
    
    @Test
    void postTotemOutTrancaFailTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/totem/retirarDaRede?idTotem=74&idTranca=74").asString();
        assertEquals(404, response.getStatus());
    }
    
    @Test
    void getListBikeInTotemTest() {
        HttpResponse response = Unirest.get("http://localhost:7010/totem/1/bicicletas").asString();
        assertEquals(200, response.getStatus());
    }
    
    
    @Test
    void getListBikeInTotemFailTest() {
        HttpResponse response = Unirest.get("http://localhost:7010/totem/97/bicicletas").asString();
        assertEquals(404, response.getStatus());
    }
    
    @Test
    void getListTrancaInTotemTest() {
        HttpResponse response = Unirest.get("http://localhost:7010/totem/1/trancas").asString();
        assertEquals(200, response.getStatus());
    }
    
    
    @Test
    void getListTrancaInTotemFailTest() {
        HttpResponse response = Unirest.get("http://localhost:7010/totem/97/trancas").asString();
        assertEquals(404, response.getStatus());
    }
    */
}
