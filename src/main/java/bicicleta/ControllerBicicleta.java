package bicicleta;

import io.javalin.http.Context;
import util.ErrorResponse;
import util.Validator;

public class ControllerBicicleta {

	 static JDBCMock mock = new JDBCMock();

	    private ControllerBicicleta(){
	    	
	    	
	    }

	    public static void getBicicletaByCtx(Context ctx) {
	        Bicicleta bike = retrieveBikeByCtx(ctx);
	        
	        if (bike != null) {
	            ctx.status(200);
	            ctx.json(bike);
	        }else{
	            ctx.status(400);
	            ctx.json(mock.banco);
	        }
	    }
	    private static Bicicleta retrieveBikeByCtx(Context ctx) {
	        if (ctx.queryParam("id") != null) {
	            return mock.getDataById(ctx.queryParam("id"));
	        } else if (ctx.queryParam("code") != null) {
	            return mock.getDataByCode(Integer.parseInt(ctx.queryParam("code")));
	        }else{
	            return null;
	        }
	    }

	    public static void postBicicleta(Context ctx) {
	        String status = ctx.queryParam("status");
	        if (
	            Validator.isNullOrEmpty(ctx.queryParam("id"))     ||
	            Validator.isNullOrEmpty(ctx.queryParam("code"))   ||
	            Validator.isNullOrEmpty(status) ||
	            ! Validator.isInRangeEnum(status)
	        ) {
	            ctx.status(400);
	            ctx.result(ErrorResponse.BAD_REQUEST);
	        }else{
	            Bicicleta bicicleta = new Bicicleta(
	                ctx.queryParam("id"), 
	                Integer.decode(ctx.queryParam("code")), 
	                BicicletaStatus.valueOf(status.toUpperCase())
	                );
	            mock.updateData(bicicleta);
	            ctx.status(200);
	        }
	    }

	    public static void deleteBicicleta(Context ctx) {
	        Bicicleta bike = retrieveBikeByCtx(ctx);
	        if (bike != null) {
	            mock.deleteData(bike.getId());
	            ctx.status(200);
	            ctx.result("Delete Executado");
	        }else{
	            ctx.status(400);
	            ctx.result("Delete Não Executado");
	        }
	    }
}
