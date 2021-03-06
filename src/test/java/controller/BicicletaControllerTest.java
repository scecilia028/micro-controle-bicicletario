package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.Bicicleta;
import domain.BicicletaStatus;
import domain.Tranca;
import io.javalin.plugin.json.JavalinJson;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import services.JDBCMockBicicleta;
import util.JavalinApp;


@SuppressWarnings("rawtypes")
class BicicletaControllerTest {

    private static JavalinApp app = new JavalinApp();
    private String bikeJson = JavalinJson.toJson(ControllerBicicleta.mock.banco);
    private final static String URL_HEROKU = "https://sistema-bicicletario.herokuapp.com";

    @BeforeAll
    static void init() {
        app.start(7010);
    }
    
    @BeforeEach
    void inicio() {
    	Bicicleta bike = ControllerBicicleta.mock.banco.get(1);
    	bike.setMarca("caloi");
    	bike.setStatus(BicicletaStatus.APOSENTADA);
    	Tranca tranca = ControllerTranca.mock.banco.get(4);
    	tranca.setIdTotem("1");
    	tranca.setIdBicicleta("1");
    }
    
    
    @AfterAll
    static void afterAll(){
        app.stop();
    }
    
    @Test
    void postBicicletaSuccessTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/bicicleta?idBicicleta=2&marca=caloi&modelo=algum&ano=2010&numero=2&status=disponivel").asString();
        assertEquals(200, response.getStatus());
    }

    @Test
    void getAllBicicletasTest() {
        HttpResponse response = Unirest.get("http://localhost:7010/bicicleta").asString();
        assertEquals(200, response.getStatus());
    }

	@Test
    void getBicicletaByIdTest() {
        String bike = JavalinJson.toJson(ControllerBicicleta.mock.banco.get(1));
        HttpResponse response = Unirest.get("http://localhost:7010/bicicleta?idBicicleta=1").asString();
        assertEquals(200, response.getStatus());
        assertEquals(response.getBody(), bike);
    }

	@Test
	void postBicicletaFailParam422Test() {
	  HttpResponse response = Unirest.post("http://localhost:7010/bicicleta?idBicicleta=232&marca=caloi&modelo=algum&ano=2010&sadadad=2&status=disponivel&ccc=aaaa").asString();
	  assertEquals(422, response.getStatus());
    }
	  
	@Test
	void postBicicletaFailParamEmptyMarcaTest() {
	   HttpResponse response = Unirest.post("http://localhost:7010/bicicleta?idBicicleta=232&marca=&modelo=algum&ano=2010&numero=232&status=disponivel").asString();
	   assertEquals(422, response.getStatus());
	}
	
	@Test
	void postBicicletaFailParamEmptyAnoTest() {
	   HttpResponse response = Unirest.post("http://localhost:7010/bicicleta?idBicicleta=232&marca=ss&modelo=algum&ano=&numero=232&status=disponivel").asString();
	   assertEquals(422, response.getStatus());
	}
	
	@Test
	void postBicicletaFailParamEmptyModeloTest() {
	   HttpResponse response = Unirest.post("http://localhost:7010/bicicleta?idBicicleta=232&marca=sss&modelo=&ano=2010&sadadad=2&status=disponivel&ccc=aaaa").asString();
	   assertEquals(422, response.getStatus());
	}
	
    @Test
    void postBicicletaFailParamsTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/bicicleta?idsdf=2&status=disponivel").asString();
        assertEquals(404, response.getStatus());
    }

    @Test
    void postBicicletaFailParamTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/bicicleta").asString();
        assertEquals(404, response.getStatus());
    }

    @Test
    void postBicicletaFailNullParamTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/bicicleta?idBicicleta=null").asString();
        assertEquals(404, response.getStatus());
    }

    @Test
    void postBicicletaFailErrorParamTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/bicicleta?status=russo").asString();
        assertEquals(404, response.getStatus());
    }

    @Test
    void postBicicletaFailNullStatusParamTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/bicicleta?status=null").asString();
        assertEquals(404, response.getStatus());
    }

    @Test
    void postBicicletaFailNullAllParamTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/bicicleta?idBicicleta=null&marca=null&status=null").asString();
        assertEquals(404, response.getStatus());
    }

    @Test
    void postBicicletaFailStatusInexistenteParamTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/bicicleta?idBicicleta=2&marca=caloi&modelo=algum&ano=2010&numero=2&status=TIMBR").asString();
        assertEquals(404, response.getStatus());
    }
    
    @Test
    void postBicicletaFailCampoInexistenteParamTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/bicicleta?idBicicleta=2&campoerrado=caloi&modelo=algum&ano=2010&numero=2&status=TIMBR").asString();
        assertEquals(404, response.getStatus());
    }

    @Test
    void deleteBicicletaSuccessTest() {
        HttpResponse response = Unirest.delete("http://localhost:7010/bicicleta/1").asString();
        assertEquals(200, response.getStatus());
    }

    @Test
    void deleteBicicletaFailTest() {
        HttpResponse response = Unirest.delete("http://localhost:7010/bicicleta/asd8").asString();
        assertEquals(404, response.getStatus());
    }

    @Test
    void postStatusBicicletaSuccessTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/bicicleta/1/status/em_reparo").asString();
        assertEquals(200, response.getStatus());
    }
    
    @Test
    void postStatusBicicletaFailTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/bicicleta/2/status/null").asString();
        assertEquals(422, response.getStatus());
    }
    
    @Test
    void postStatusBicicletaFail404Test() {
        HttpResponse response = Unirest.post("http://localhost:7010/bicicleta/sssss/status/NOVA").asString();
        assertEquals(404, response.getStatus());
    }

	@Test
    void putBicicletaSuccessTest() {
        HttpResponse response = Unirest.put("http://localhost:7010/bicicleta/4?marca=caloiNova&modelo=algum&ano=2010&numero=2&status=EM_USO").asString();
        assertEquals(200, response.getStatus());
    }
    
    @Test
    void putBicicletaFailTest() {
        HttpResponse response = Unirest.put("http://localhost:7010/bicicleta/4?marca=caloiNova&modelo=algum&numero=2&status=EM_USO&campoerrado=rio de janeiro").asString();
        assertEquals(422, response.getStatus());
    }
    
    @Test
    void postBicicletaOutTrancaTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/bicicleta/retirarDaRede?idTranca=4").asString();
        assertEquals(200, response.getStatus());
    }
    
    @Test
    void postBicicletaOutTrancaFailTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/bicicleta/retirarDaRede?idTasdad=4&idBicicleta=1").asString();
        assertEquals(404, response.getStatus());
    }
    
    @Test
    void postBicicletaInTrancaTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/bicicleta/integrarNaRede?idTranca=3&idBicicleta=3").asString();
        assertEquals(200, response.getStatus());
    }
    
    @Test
    void postBicicletaInTrancaFailTest() {
        HttpResponse response = Unirest.post("http://localhost:7010/bicicleta/integrarNaRede?idsadma=4&idBicicleta=1").asString();
        assertEquals(404, response.getStatus());
    }
    
    @Test
    void getBicicletaFailByIdTest() {
        HttpResponse response = Unirest.get("http://localhost:7010/bicicleta/sdjfnksdf").asString();
        assertEquals(404, response.getStatus());
    }
    
    @Test
    void getGetBicicletaByIdTest() {
    	JDBCMockBicicleta mock = new JDBCMockBicicleta();
        assertEquals(mock.banco.get(0), ControllerBicicleta.retrieveBikeById("0"));
    }
    
    @Test
    void getGetBicicletaFailByIdTest() {
        assertEquals(null, ControllerBicicleta.retrieveBikeById("null"));
    }
    
    @Test
    void getBicicletaByIdSuccessTest() {
        HttpResponse response = Unirest.get("http://localhost:7010/bicicleta/5").asString();
        assertEquals(200, response.getStatus());
    }
    
    @Test
    void getBicicletaFail2ByCtxTest() {
        HttpResponse response = Unirest.get("http://localhost:7010/bicicleta?idBicicleta=ndjskfnks&modelo=asdjak").asString();
        assertEquals(404, response.getStatus());
    }

	public String getBikeJson() {
		return bikeJson;
	}

	public void setBikeJson(String bikeJson) {
		this.bikeJson = bikeJson;
	}
	

    @Test
    void postHBicicletaFailNullStatusParamTest() {
        HttpResponse response = Unirest.post(URL_HEROKU + "/bicicleta?status=null").asString();
        assertEquals(404, response.getStatus());
    }
    
    @Test
    void getHBicicletaByIdSuccessTest() {
        HttpResponse response = Unirest.get(URL_HEROKU+"/bicicleta/5").asString();
        assertEquals(200, response.getStatus());
    }
    
    @Test
    void postHBicicletaInTrancaTest() {
        HttpResponse response = Unirest.post(URL_HEROKU+"/bicicleta/integrarNaRede?idTranca=7&idBicicleta=7").asString();
        assertEquals(200, response.getStatus());
    }
}
