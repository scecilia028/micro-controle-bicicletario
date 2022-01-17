package util;

import static io.javalin.apibuilder.ApiBuilder.*;
import io.javalin.Javalin;
import controller.Controller;
import bicicleta.ControllerBicicleta;
 
public class JavalinApp {
	  private Javalin app = 
	            Javalin.create(config -> config.defaultContentType = "application/json")
	                .routes(() -> {
                	   path("/bicicleta", () -> {
	                           get(ControllerBicicleta::getBicicletaByCtx);
	                           delete(ControllerBicicleta::deleteBicicleta);
	                           post(ControllerBicicleta::postBicicleta);
	                       });
	                    });
	                    


	    public void start(int port) {
	        this.app.start(port);
	    }

	    public void stop() {
	        this.app.stop();
	    }

}
