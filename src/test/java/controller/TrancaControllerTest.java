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
import services.JDBCMockTranca;
import util.JavalinApp;


@SuppressWarnings("rawtypes")
class TrancaControllerTest {

    private static JavalinApp app = new JavalinApp();
    private String trancaJson = JavalinJson.toJson(ControllerTranca.mock.banco);

    @BeforeAll
    static void init() {
        app.start(7010);
    }
    
    @BeforeEach
    void inicio() {
    	Tranca tranca = ControllerTranca.mock.banco.get(4);
    	tranca.setIdTotem("1");
    	tranca.setIdBicicleta("1");
    }
    
    @AfterAll
    static void afterAll(){
        app.stop();
    }
    
    @Test
    void postTrancaSuccessTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/tranca?idTranca=4&numero=4&modelo=xr&anoDeFabricacao=2022&status=ocupada&localizacao=rj").asString();
        assertEquals(200, response.getStatus());
    }
    
    @Test
    void postTrancaFailParamModeloTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/tranca?idTranca=411&numero=411&modelo=&anoDeFabricacao=2022&status=ocupada&localizacao=rj").asString();
        assertEquals(422, response.getStatus());
    }

    @Test
    void getAllTrancasTest() {
        HttpResponse response = Unirest.get("http://localhost:7010/tranca").asString();
        assertEquals(200, response.getStatus());
        assertEquals(response.getBody(), trancaJson);
    }
    
    @Test
    void getIdTrancaTest() {
        HttpResponse response = Unirest.get("http://localhost:7010/tranca?idTranca=2").asString();
        assertEquals(200, response.getStatus());
    }

	@Test
    void getTrancaByIdTest() {
        HttpResponse response = Unirest.get("http://localhost:7010/tranca/1").asString();
        assertEquals(200, response.getStatus());
    }

    @Test
    void postTrancaFailParamsTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/tranca?idTrancasade=2&numero=2&anoDeFabricacao=2022&modelo=alguma&status=livre&localizacao=rio de janeiro").asString();
        assertEquals(404, response.getStatus());
    }

    @Test
    void postTrancaFailNullParamTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/tranca?idTranca=null").asString();
        assertEquals(404, response.getStatus());
    }

    @Test
    void deleteTrancaSuccessTest() {
        HttpResponse response = Unirest.delete("http://localhost:7010/tranca/8").asString();
        assertEquals(200, response.getStatus());
    }

    @Test
    void deleteTrancaFailTest() {
        HttpResponse response = Unirest.delete("http://localhost:7010/tranca?asd8").asString();
        assertEquals(404, response.getStatus());
    }

	@Test
    void putTrancaSuccessTest() {
        HttpResponse response = Unirest.put("http://localhost:7010/tranca/4?numero=4&anoDeFabricacao=2022&modelo=alguma&status=ocupada&localizacao=nova iguacu").asString();
        assertEquals(200, response.getStatus());
    }
    
    @Test
    void putTrancaFailTest() {
        HttpResponse response = Unirest.put("http://localhost:7010/tranca/4?campoerrado=4&anoDeFabricacao=2022&modelo=alguma&status=ocupada&localizacao=nova iguacu").asString();
        assertEquals(422, response.getStatus());
    }
    
    @Test
    void postTrancaInTotemTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/tranca/integrarNaRede?idTranca=2&idTotem=2").asString();
        assertEquals(200, response.getStatus());
    }
    
    @Test
    void postTrancaOutTotemTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/tranca/retirarDaRede?idTranca=4&idTotem=1").asString();
        assertEquals(200, response.getStatus());
    }
    
    @Test
    void postTrancaInTotemFailTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/tranca/integrarNaRede?idTranca=88&idTotem=98").asString();
        assertEquals(404, response.getStatus());
    }
    
    @Test
    void postTrancaOutTrancaFailTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/tranca/retirarDaRede?idTranca=74&idTotsfsdfem=74").asString();
        assertEquals(404, response.getStatus());
    }
    
    @Test
    void getBikeInTrancaTest() {
        HttpResponse response = Unirest.get("http://localhost:7010/tranca/4/bicicleta").asString();
        assertEquals(200, response.getStatus());
    }
    
    
    @Test
    void getBikeInTrancaFailTest() {
        HttpResponse response = Unirest.get("http://localhost:7010/tranca/97/bicicleta?").asString();
        assertEquals(404, response.getStatus());
    }
    
    @Test
    void postTrancaStatusTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/tranca/2/status/TRANCAR").asString();
        assertEquals(200, response.getStatus());
    }
    
    @Test
    void postTrancaStatusFailTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/tranca/2/status/travarisso").asString();
        assertEquals(422, response.getStatus());
    }

    @Test
    void getTrancaFailTest() {
        HttpResponse response = Unirest.get("http://localhost:7010/tranca/husahahu").asString();
        assertEquals(500, response.getStatus());
    }
    
    @Test
    void postTrancaFailWithoutParamTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/tranca?idTranca=12numero=12&anoDeFabricacao=null&status=ocupada&localizacao=rj").asString();
        assertEquals(422, response.getStatus());
    }
    
    @Test
    void getTrancaFail404Test() {
        HttpResponse response = Unirest.get("http://localhost:7010/tranca?idTranca=jnsdknf").asString();
        assertEquals(404, response.getStatus());
    }
    
    @Test
    void getRetrieveSameIdTrancaTest() {
    	JDBCMockTranca mock = new JDBCMockTranca();
    	assertEquals(mock.banco.get(2).getIdTranca(), ControllerTranca.retrieveTrancaByParamIdOrNumber("2").getIdTranca());
    }
    
    @Test
    void getRetrieveSameModeloTrancaTest() {
    	JDBCMockTranca mock = new JDBCMockTranca();
    	assertEquals(mock.banco.get(2).getModelo(), ControllerTranca.retrieveTrancaByParamIdOrNumber("2").getModelo());
    }
    
    
    
}
