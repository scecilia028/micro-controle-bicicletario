package bicicleta;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import util.ErrorResponse;
import util.JavalinApp;
import io.javalin.plugin.json.JavalinJson;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;



class BicicletaControllerTest {

    private static JavalinApp app = new JavalinApp();
    private String bikeJson = JavalinJson.toJson(ControllerBicicleta.mock.banco);

    @BeforeAll
    static void init() {
        app.start(7010);
    }
    
    @AfterAll
    static void afterAll(){
        app.stop();
    }
    
    @Test
    void postBicicletaSuccessTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/bicicleta?id=2&code=2&status=disponivel").asString();
        assertEquals(200, response.getStatus());
    }

    @Test
    void getAllBicicletasTest() {
        HttpResponse response = Unirest.get("http://localhost:7010/bicicleta").asString();
        assertEquals(200, response.getStatus());
        assertEquals(response.getBody(), bikeJson);
    }

    @Test
    void getBicicletaByCodeTest() {
        String bike = JavalinJson.toJson(ControllerBicicleta.mock.banco.get(1));
        HttpResponse response = Unirest.get("http://localhost:7010/bicicleta?code=1").asString();
        assertEquals(200, response.getStatus());
        assertEquals(response.getBody(), bike);
    }

    @SuppressWarnings("rawtypes")
	@Test
    void getBicicletaByIdTest() {
        String bike = JavalinJson.toJson(ControllerBicicleta.mock.banco.get(1));
        HttpResponse response = Unirest.get("http://localhost:7010/bicicleta?id=1").asString();
        assertEquals(200, response.getStatus());
        assertEquals(response.getBody(), bike);
    }

    @Test
    void postBicicletaFailTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/bicicleta?id=2&status=disponivel").asString();
        assertEquals(400, response.getStatus());
    }

    @Test
    void postBicicletaFailNoParamTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/bicicleta").asString();
        assertEquals(400, response.getStatus());
    }

    @Test
    void postBicicletaFailNullParamTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/bicicleta?id=null").asString();
        assertEquals(400, response.getStatus());
    }

    @Test
    void postBicicletaFailErrorParamTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/bicicleta?status=russo").asString();
        assertEquals(400, response.getStatus());
    }

    @Test
    void postBicicletaFailNullCodeParamTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/bicicleta?code=null").asString();
        assertEquals(400, response.getStatus());
    }
    
    @Test
    void postBicicletaFailNullStatusParamTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/bicicleta?status=null").asString();
        assertEquals(400, response.getStatus());
    }

    @Test
    void postBicicletaFailNullAllParamTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/bicicleta?id=null&code=null&status=null").asString();
        assertEquals(400, response.getStatus());
    }

    @Test
    void postBicicletaFailStatusNullParamTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/bicicleta?id=1&code=1&status=null")
                .asString();
        assertEquals(400, response.getStatus());
    }

    @Test
    void postBicicletaFailStatusInexistenteParamTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/bicicleta?id=1&code=1&status=TIMBR").asString();
        assertEquals(400, response.getStatus());
    }

    @Test
    void deleteBicicletaSuccessTest() {
        HttpResponse response = Unirest.delete("http://localhost:7010/bicicleta?id=8").asString();
        assertEquals(200, response.getStatus());
    }

    @Test
    void deleteBicicletaFailTest() {
        HttpResponse response = Unirest.delete("http://localhost:7010/bicicleta?code=-8").asString();
        assertEquals(400, response.getStatus());
    }


//    @Test
//    void getStatusBicicletaByIdTest() {
//        String bike = JavalinJson.toJson(ControllerBicicleta.mock.banco.get(1).getStatus());
//        HttpResponse response = Unirest.get("http://localhost:7010/statusBicicleta?id=1").asString();
//        assertEquals(200, response.getStatus());
//        assertEquals(response.getBody(), bike);
//    }

//    @Test
//    void getStatusBicicletaByCodeTest() {
//        String bike = JavalinJson.toJson(ControllerBicicleta.mock.banco.get(1).getStatus());
//        HttpResponse response = Unirest.get("http://localhost:7010/statusBicicleta?code=1").asString();
//        assertEquals(200, response.getStatus());
//        assertEquals(response.getBody(), bike);
//    }

//    @Test
//    void getStatusBicicletaByCodeFailureTest() {
//        HttpResponse response = Unirest.get("http://localhost:7010/statusBicicleta").asString();
//        assertEquals(400, response.getStatus());
//        assertEquals(ErrorResponse.BAD_REQUEST,response.getBody());
//    }

//    @Test
//    void postStatusBicicletaSuccessTest() {
//        HttpResponse response = Unirest.post("http://localhost:7010/statusBicicleta?id=3&code=3&status=disponivel").asString();
//        assertEquals(200, response.getStatus());
//    }
    
//    @Test
//    void postStatusBicicletaOnlyIDSuccessTest() {
//        HttpResponse response = Unirest.post("http://localhost:7010/statusBicicleta?id=3&status=disponivel")
//                .asString();
//        assertEquals(200, response.getStatus());
//    }

//    @Test
//    void postStatusBicicletaOnlyCodeSuccessTest() {
//        HttpResponse response = Unirest.post("http://localhost:7010/statusBicicleta?code=3&status=em_uso").asString();
//        assertEquals(200, response.getStatus());
//    }

//    @Test
//    void postStatusBicicletaFailTest() {
//        HttpResponse response = Unirest.post("http://localhost:7010/statusBicicleta?id=3").asString();
//        assertEquals(400, response.getStatus());
//    }
    
//    @Test
//    void postStatusBicicletaNoBikeFailTest() {
//        HttpResponse response = Unirest.post("http://localhost:7010/statusBicicleta?id=TIMBR").asString();
//        assertEquals(400, response.getStatus());
//    }

//    @Test
//    void postStatusBicicletaNoRangeStatusFailTest() {
//        HttpResponse response = Unirest.post("http://localhost:7010/statusBicicleta?code=3&status=TIMBR").asString();
//        assertEquals(400, response.getStatus());
//    }


}
