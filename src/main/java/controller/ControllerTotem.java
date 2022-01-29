package controller;

import domain.Totem;
import io.javalin.http.Context;
import services.JDBCMockTotem;
import util.ChavesJson;
import util.ErrorResponse;
import util.Validator;

public class ControllerTotem {

	public static JDBCMockTotem mock = new JDBCMockTotem();

	public ControllerTotem() {
	}

	public static void getTotem(Context ctx) {
		 if (mock.banco != null) {
			ctx.status(200);
			ctx.json(mock.banco);
			return;
		} else {
			ctx.status(404).result(ErrorResponse.NOT_FOUND);
			return;
		}
	}
	
	public static void getTotemByCtx(Context ctx) {
		Totem totem = retrieveTotemParam(ctx);

		if (totem != null) {
			ctx.status(200);
			ctx.json(totem);
			return;
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
		if (ctx.queryParam(ChavesJson.IDTOTEM.getValor()) != null) {
			return mock.getDataById(ctx.queryParam(ChavesJson.IDTOTEM.getValor()));
		}
		return null;
	}
	
	 private static Totem retrieveTotemParam(Context ctx) {
	        if (ctx.pathParam(ChavesJson.IDTOTEM.getValor()) != null) {
	            return mock.getDataById(ctx.pathParam(ChavesJson.IDTOTEM.getValor()));
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
		if (Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.IDTOTEM.getValor())) || Validator.isNullOrEmpty("")
				||   !Validator.checkKeysValidByCtx(ctx)) {
			ctx.status(422);
			ctx.result(ErrorResponse.INVALID_DATA_MESSAGE);
			return;
		} else {
			Totem totem = checkCreateTotem(ctx);
			if(totem == null) {
				ctx.status(404).result(ErrorResponse.NOT_FOUND);
			}
			mock.updateData(totem);
			ctx.status(200).result(ErrorResponse.VALID_DATA_MESSAGE);
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
