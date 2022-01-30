package controller;

import domain.Totem;
import io.javalin.http.Context;
import services.JDBCMockTotem;
import util.ChavesJson;
import util.ErrorResponse;
import util.Validator;

public class ControllerTotem {

	public static final JDBCMockTotem mock = new JDBCMockTotem();

	public static void getTotem(Context ctx) {
			ctx.status(200);
			ctx.json(mock.banco);
	}
	
	public static void getTotemByCtx(Context ctx) {
		if(ctx.queryParam(ChavesJson.IDTOTEM.getValor()) == null) {
    		getTotem(ctx);
    	}else {
        Totem totem = retrieveTotemByCtx(ctx);
        
        if (totem != null) {
            ctx.status(200);
            ctx.json(totem);
        } else {
			ctx.status(404).result(ErrorResponse.NOT_FOUND);
		}
       }
	}

	protected static Totem retrieveTotemByCtx(Context ctx) {
			return mock.getDataById(ctx.queryParam(ChavesJson.IDTOTEM.getValor()));
	}
	
	 private static Totem retrieveTotemParam(Context ctx) {
	            return mock.getDataById(ctx.pathParam(ChavesJson.IDTOTEM.getValor()));
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
		if (Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.IDTOTEM.getValor())) || Validator.isNullOrEmpty("")) {
			ctx.status(404).result(ErrorResponse.NOT_FOUND);
		} else if(!Validator.checkKeysValidByCtx(ctx)) {
			ctx.status(422);
			ctx.result(ErrorResponse.INVALID_DATA_MESSAGE);
		}else {
			Totem totem = checkCreateTotem(ctx);
			if(totem != null) {
				mock.updateData(totem);
				ctx.status(200).result(ErrorResponse.VALID_DATA_MESSAGE);
			}
		}
	}

	public static void putTotem(Context ctx) {
		Totem totem = retrieveTotemParam(ctx);

		if (totem != null) {
			if(!Validator.checkKeysValidByCtx(ctx)) {
				 ctx.status(422).result(ErrorResponse.INVALID_DATA_MESSAGE);
				 return;
			}
		     totem = checkUpdateTotem(ctx, totem);
	   		 if(totem != null) {
	    		  mock.updateTotem(totem);	
	       		  ctx.status(200).result(ErrorResponse.VALID_DATA_MESSAGE);
	   		 }
	   	 } else {
	   		 ctx.status(404).result(ErrorResponse.NOT_FOUND);
	   	 }
	}

	private static Totem checkCreateTotem(Context ctx) {
		Totem totem = new Totem();

		if (!Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.IDTOTEM.getValor())) &&
			!Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.LOCALIZACAO.getValor()))) {
			totem.setId(ctx.queryParam(ChavesJson.IDTOTEM.getValor()));
			totem.setLocalizacao(ctx.queryParam(ChavesJson.LOCALIZACAO.getValor()));
			return totem;
		} 
			return null;
	}
	
	private static Totem checkUpdateTotem(Context ctx, Totem totem) {
		if (!Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.LOCALIZACAO.getValor()))) {
			totem.setLocalizacao(ctx.queryParam(ChavesJson.LOCALIZACAO.getValor()));
			return totem;
		} 
			return null;
	}

}
