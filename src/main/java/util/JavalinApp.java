package util;

import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.put;

import controller.ControllerBicicleta;
import controller.ControllerTotem;
import controller.ControllerTranca;
import io.javalin.Javalin;
 
public class JavalinApp {
	private static final String pathParamIdBicicleta = "/bicicleta/:idBicicleta";
	private static final String pathParamIdTranca = "/tranca/:idTranca";
	private static final String pathParamIdTotem = "/totem/:idTotem";
	
	  private Javalin app = 
        Javalin.create(config -> config.defaultContentType = "application/json")
        .routes(() -> {
        	 get("/bicicleta", ControllerBicicleta::getBicicletaByCtx);
        	 post("/bicicleta", ControllerBicicleta::postBicicleta);
             put(pathParamIdBicicleta, ControllerBicicleta::putBicicleta);
             get(pathParamIdBicicleta, ControllerBicicleta::getBicicletaByParamId);
        	 delete(pathParamIdBicicleta, ControllerBicicleta::deleteBicicleta);
             post("/bicicleta/:idBicicleta/status/:status", ControllerBicicleta::postStatusBicicleta);
             
	         post("/bicicleta/integrarNaRede", ControllerTranca::postIntegrarNaRede);  
	         post("/bicicleta/retirarDaRede", ControllerTranca::postRetirarDaRede); 
             
             get("/totem", ControllerTotem::getTotem);
        	 post("/totem", ControllerTotem::postTotem);
             put(pathParamIdTotem, ControllerTotem::putTotem);
             delete(pathParamIdTotem, ControllerTotem::deleteTotem);
             
             get("/totem/:idTotem/trancas", ControllerTranca::getTrancasByTotem);
             get("/totem/:idTotem/bicicletas", ControllerTranca::getBicicletasByTotem);
             
             post("/tranca", ControllerTranca::postTranca);
             get("/tranca", ControllerTranca::getTrancaByCtx);
             get(pathParamIdTranca, ControllerTranca::getTrancaByIdOrNumber);
             delete(pathParamIdTranca, ControllerTranca::deleteTranca);
             put(pathParamIdTranca, ControllerTranca::putTranca);
             post("/tranca/:idTranca/status/:acao", ControllerTranca::postStatusTranca);
             
             post("/tranca/integrarNaRede", ControllerTranca::postIntegrarNaRedeTrancaTotem);
             post("/tranca/retirarDaRede", ControllerTranca::postRetirarDaRedeTrancaTotem);
             get("/tranca/:idTranca/bicicleta", ControllerTranca::getBikeByTranca);
           });     
	  	
	    public void start(int port) {
	        this.app.start(port);
	    }

	    public void stop() {
	        this.app.stop();
	    }

}
