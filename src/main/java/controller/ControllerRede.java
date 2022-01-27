package controller;

import domain.Bicicleta;
import domain.Totem;
import domain.Tranca;
import io.javalin.http.Context;
import services.JDBCMockRede;
import util.ErrorResponse;
import util.Validator;

public class ControllerRede {
	
	public static JDBCMockRede mock = new JDBCMockRede();
	
	public static void postIntegrarNaRede(Context ctx){
	   	 Bicicleta bicicleta = ControllerBicicleta.retrieveBikeByCtx(ctx);
	   	 Tranca tranca = ControllerTranca.retrieveTrancaByCtx(ctx);
	       if (
	           !Validator.isNullOrEmpty(ctx.queryParam("idTranca")) &&
	           !Validator.isNullOrEmpty(ctx.queryParam("idBicicleta")) &&
	           Validator.isBicicletaAvailable(String.valueOf(bicicleta.getStatus())) &&
	           Validator.isTrancaAvailable(String.valueOf(tranca.getStatus()))          
	       ) {
	    	   mock.createDataTrancaBike(tranca, bicicleta);	
	    	   ctx.status(200).result(ErrorResponse.VALID_DATA_MESSAGE);
			} else {
				ctx.status(422);
				ctx.result(ErrorResponse.INVALID_DATA_MESSAGE);
			}
	   }
	
	public static void postRetirarDaRede(Context ctx){
	   	 Bicicleta bicicleta = ControllerBicicleta.retrieveBikeByCtx(ctx);
	   	 Tranca tranca = ControllerTranca.retrieveTrancaByCtx(ctx);
	       if (
	           !Validator.isNullOrEmpty(ctx.queryParam("idTranca")) &&
	           !Validator.isNullOrEmpty(ctx.queryParam("idBicicleta")) 
	       ) {
	    	   mock.deleteDataTrancaBike(tranca.getIdTranca(), bicicleta.getId());	
	    	   ctx.status(200).result(ErrorResponse.VALID_DATA_MESSAGE);
			} else {
				ctx.status(422);
				ctx.result(ErrorResponse.INVALID_DATA_MESSAGE);
			}
	   }
	
	public static void postIntegrarNaRedeTranca(Context ctx){
	   	 Totem totem = ControllerTotem.retrieveTotemByCtx(ctx);
	   	 Tranca tranca = ControllerTranca.retrieveTrancaByCtx(ctx);
	       if (
	           !Validator.isNullOrEmpty(ctx.queryParam("idTranca")) &&
	           !Validator.isNullOrEmpty(ctx.queryParam("idTotem")) &&
	           Validator.isTrancaAvailable(String.valueOf(tranca.getStatus()))          
	       ) {
	    	   mock.createDataTrancaTotem(totem, tranca);	
	    	   ctx.status(200).result(ErrorResponse.VALID_DATA_MESSAGE);
			} else {
				ctx.status(422);
				ctx.result(ErrorResponse.INVALID_DATA_MESSAGE);
			}
	   }
	
	
	public static void postRetirarDaRedeTranca(Context ctx){
		 Totem totem = ControllerTotem.retrieveTotemByCtx(ctx);
	   	 Tranca tranca = ControllerTranca.retrieveTrancaByCtx(ctx);
	       if (
	           !Validator.isNullOrEmpty(ctx.queryParam("idTranca")) &&
	           !Validator.isNullOrEmpty(ctx.queryParam("idTotem")) 
	       ) {
	    	   mock.deleteDataTotemTranca(totem.getId(), tranca.getIdTranca());	
	    	   ctx.status(200).result(ErrorResponse.VALID_DATA_MESSAGE);
			} else {
				ctx.status(422);
				ctx.result(ErrorResponse.INVALID_DATA_MESSAGE);
			}
	   }
}
