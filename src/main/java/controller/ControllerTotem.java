package controller;

import domain.Totem;
import io.javalin.http.Context;
import services.JDBCMockTotem;
import util.ErrorResponse;
import util.Validator;

public class ControllerTotem {

	public static JDBCMockTotem mock = new JDBCMockTotem();

	public ControllerTotem() {
	}

	public static void getTotemByCtx(Context ctx) {
		Totem totem = retrieveTotemParam(ctx);

		if (totem != null) {
			ctx.status(200);
			ctx.json(totem);
		} else if (totem == null && mock.banco != null) {
			ctx.status(200);
			ctx.json(mock.banco);
			return;
		} else {
			ctx.status(404).result(ErrorResponse.NOT_FOUND);
			return;
		}
	}

	protected static Totem retrieveTotemByCtx(Context ctx) {
		if (ctx.queryParam("idTotem") != null) {
			return mock.getDataById(ctx.queryParam("idTotem"));
		}
		return null;
	}
	
	 private static Totem retrieveTotemParam(Context ctx) {
	        if (ctx.pathParam("idTotem") != null) {
	            return mock.getDataById(ctx.pathParam("idTotem"));
	        }  else{
	            return null;
	        }
	    }

	public static void deleteTotem(Context ctx) {
		Totem totem = retrieveTotemParam(ctx);
		if (totem != null) {
			mock.deleteData(totem.getId());
			ctx.status(200).result(ErrorResponse.VALID_DATA_MESSAGE);
		} else {
			ctx.status(404).result(ErrorResponse.NOT_FOUND);
		}
	}

	public static void postTotem(Context ctx) {
		if (Validator.isNullOrEmpty(ctx.queryParam("idTotem")) || Validator.isNullOrEmpty("")) {
			ctx.status(422);
			ctx.result(ErrorResponse.INVALID_DATA_MESSAGE);
		} else {
			Totem totem = checkCreateTotem(ctx);
			if(totem == null) {
            	ctx.status(422);
                ctx.result(ErrorResponse.INVALID_DATA_MESSAGE);	
            }
			mock.updateData(totem);
			ctx.status(200);
		}
	}

	public static void putTotem(Context ctx) {
		Totem totem = retrieveTotemParam(ctx);

		if (totem != null) {
		     totem = checkUpdateTotem(ctx, totem);
	   		 if(totem != null) {
	    		  mock.updateTotem(totem);	
	       		  ctx.status(200);
	   		 }else {
	   			 ctx.status(422).result(ErrorResponse.INVALID_DATA_MESSAGE);
	   		 }
	   	 } else {
	   		 ctx.status(404).result(ErrorResponse.NOT_FOUND);
	   	 }
	}

	private static Totem checkCreateTotem(Context ctx) {
		Totem totem = new Totem();

		if (!Validator.isNullOrEmpty(ctx.queryParam("idTotem")) &&
			!Validator.isNullOrEmpty(ctx.queryParam("localizacao"))) {
			totem.setId(ctx.queryParam("idTotem"));
			totem.setLocalizacao(ctx.queryParam("localizacao"));
			return totem;
		} 
			return null;
	}
	
	private static Totem checkUpdateTotem(Context ctx, Totem totem) {
		if (!Validator.isNullOrEmpty(ctx.queryParam("localizacao"))) {
			totem.setLocalizacao(ctx.queryParam("localizacao"));
			return totem;
		} 
			return null;
	}

}
