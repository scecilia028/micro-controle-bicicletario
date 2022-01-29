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
	  private Javalin app = 
        Javalin.create(config -> config.defaultContentType = "application/json")
        .routes(() -> {
        	 get("/bicicleta", ControllerBicicleta::getBicicletaByCtx);
        	 post("/bicicleta", ControllerBicicleta::postBicicleta);
             put("/bicicleta/:idBicicleta", ControllerBicicleta::putBicicleta);
             get("/bicicleta/:idBicicleta", ControllerBicicleta::getBicicletaByParamId);
        	 delete("/bicicleta/:idBicicleta", ControllerBicicleta::deleteBicicleta);
             post("/bicicleta/:idBicicleta/status/:status", ControllerBicicleta::postStatusBicicleta);
             
	         post("/bicicleta/integrarNaRede", ControllerTranca::postIntegrarNaRede);  
	         post("/bicicleta/retirarDaRede", ControllerTranca::postRetirarDaRede); 
             
             get("/totem", ControllerTotem::getTotem);
        	 post("/totem", ControllerTotem::postTotem);
             put("/totem/:idTotem", ControllerTotem::putTotem);
             delete("/totem/:idTotem", ControllerTotem::deleteTotem);
             
             get("/totem/:idTotem/trancas", ControllerTranca::getTrancasByTotem);
             get("/totem/:idTotem/bicicletas", ControllerTranca::getBicicletasByTotem);
             
             post("/tranca", ControllerTranca::postTranca);
             get("/tranca", ControllerTranca::getTrancaByCtx);
             get("/tranca/:idTranca", ControllerTranca::getTrancaByIdOrNumber);
             delete("/tranca/:idTranca", ControllerTranca::deleteTranca);
             put("/tranca/:idTranca", ControllerTranca::putTranca);
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
