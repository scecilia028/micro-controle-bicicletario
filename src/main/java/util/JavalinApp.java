package util;

import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

import bicicleta.ControllerBicicleta;
import io.javalin.Javalin;
 
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
