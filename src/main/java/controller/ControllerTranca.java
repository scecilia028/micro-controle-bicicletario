package controller;

import java.util.List;

import domain.Bicicleta;
import domain.Totem;
import domain.Tranca;
import domain.TrancaAcao;
import domain.TrancaStatus;
import io.javalin.http.Context;
import services.JDBCMockTranca;
import util.ChavesJson;
import util.ErrorResponse;
import util.Validator;

public class ControllerTranca {

	public static JDBCMockTranca mock = new JDBCMockTranca();

	public ControllerTranca() {
	}

	public static void getTrancaByCtx(Context ctx) {
		Tranca tranca = retrieveTrancaByCtx(ctx);

		if (tranca != null) {
			ctx.status(200);
			ctx.json(tranca);
			return;
		} else if (tranca == null && mock.banco != null) {
			ctx.status(200);
			ctx.json(mock.banco);
			return;
		} else {
			ctx.status(404).result(ErrorResponse.NOT_FOUND);
			return;
		}
	}

	protected static Tranca retrieveTrancaByCtx(Context ctx) {
		if (ctx.queryParam(ChavesJson.IDTRANCA.getValor()) != null) {
			return mock.getDataById(ctx.queryParam(ChavesJson.IDTRANCA.getValor()));
		}
		return null;
	}

	private static Tranca retrieveTrancaParam(Context ctx) {
		if (ctx.pathParam(ChavesJson.IDTRANCA.getValor()) != null) {
			return mock.getDataById(ctx.pathParam(ChavesJson.IDTRANCA.getValor()));
		} else {
			return null;
		}
	}

	public static void deleteTranca(Context ctx) {
		Tranca tranca = retrieveTrancaParam(ctx);
		if (tranca != null) {
			mock.deleteData(tranca.getIdTranca());
			ctx.status(200).result(ErrorResponse.VALID_DATA_MESSAGE);
		} else {
			ctx.status(404).result(ErrorResponse.NOT_FOUND);
		}
	}

	public static void postTranca(Context ctx) {
		  String status = ctx.queryParam(ChavesJson.STATUS.getValor());
	         
	        if(Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.IDTRANCA.getValor()))     ||
		     Validator.isNullOrEmpty(status) || 
		     !Validator.isInRangeEnumTranca(status) ) {
	        	ctx.status(404).result(ErrorResponse.NOT_FOUND);
				return;
	       
	        }else if (!Validator.checkKeysValidByCtx(ctx)) {
	        	ctx.status(422);
				ctx.result(ErrorResponse.INVALID_DATA_MESSAGE);
				return;
	        }	
	        Tranca tranca = checkCreateTranca(ctx);
	        	
			if (tranca != null) {
				mock.updateTranca(null);
				ctx.status(200).result(ErrorResponse.VALID_DATA_MESSAGE);
				return;
			}
	}

	public static void putTranca(Context ctx) {
		Tranca tranca = retrieveTrancaParam(ctx);
		if (tranca != null) {
			if(!Validator.checkKeysValidByCtx(ctx)) {
   			 ctx.status(422).result(ErrorResponse.INVALID_DATA_MESSAGE);
   			 return;
   		 }
			tranca = checkUpdateTranca(ctx, tranca);
			if (tranca != null) {
				mock.updateTranca(tranca);
				ctx.status(200).result(ErrorResponse.VALID_DATA_MESSAGE);
			}
		} else {
			ctx.status(404).result(ErrorResponse.NOT_FOUND);
		}
	}

	private static Tranca checkCreateTranca(Context ctx) {
		Tranca tranca = new Tranca();

		if (!Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.LOCALIZACAO.getValor()))
				&& !Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.STATUS.getValor()))
				&& Validator.isInRangeEnumTranca(ctx.queryParam(ChavesJson.STATUS.getValor()))
				&& !Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.MODELO.getValor()))
				&& !Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.ANODEFABRICACAO.getValor()))
				&& !Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.NUMERO.getValor()))) {
			tranca.setLocalizacao(ctx.queryParam(ChavesJson.LOCALIZACAO.getValor()));
			tranca.setStatus(TrancaStatus.valueOf(ctx.queryParam(ChavesJson.STATUS.getValor()).toUpperCase()));
			tranca.setModelo(ctx.queryParam(ChavesJson.MODELO.getValor()));
			tranca.setAnoDeFabricacao(ctx.queryParam(ChavesJson.ANODEFABRICACAO.getValor()));
			tranca.setNumero(ctx.queryParam(ChavesJson.NUMERO.getValor()));
			return tranca;
		}
		return null;
	}

	private static Tranca checkUpdateTranca(Context ctx, Tranca tranca) {

		if (!Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.IDBICICLETA.getValor()))) {
			tranca.setIdBicicleta(ctx.queryParam(ChavesJson.IDBICICLETA.getValor()));
		}
		if (!Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.LOCALIZACAO.getValor()))) {
			tranca.setLocalizacao(ctx.queryParam(ChavesJson.LOCALIZACAO.getValor()));
		}
		if (!Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.STATUS.getValor()))
				|| Validator.isInRangeEnumBicicleta(ctx.queryParam(ChavesJson.STATUS.getValor()))) {
			tranca.setStatus(TrancaStatus.valueOf(ctx.queryParam(ChavesJson.STATUS.getValor()).toUpperCase()));
		}
		if (!Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.MODELO.getValor()))) {
			tranca.setModelo(ctx.queryParam(ChavesJson.MODELO.getValor()));
		}
		if (!Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.ANODEFABRICACAO.getValor()))) {
			tranca.setAnoDeFabricacao(ctx.queryParam(ChavesJson.ANODEFABRICACAO.getValor()));
		}
		if (!Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.NUMERO.getValor()))) {
			tranca.setNumero(ctx.queryParam(ChavesJson.NUMERO.getValor()));
		}
		if (Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.IDTOTEM.getValor())) && Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.LOCALIZACAO.getValor()))
				&& Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.STATUS.getValor()))
				&& !Validator.isInRangeEnumTranca(ctx.queryParam(ChavesJson.STATUS.getValor()))
				&& Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.MODELO.getValor()))
				&& Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.ANODEFABRICACAO.getValor()))
				&& Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.NUMERO.getValor()))) {
			return null;
		}

		return tranca;
	}

	public static void postStatusTranca(Context ctx) {
		Tranca tranca = retrieveTrancaParam(ctx);

		if (tranca != null) {
			if (!Validator.isNullOrEmpty(ctx.pathParam("acao"))) {
				if (!Validator.isInRangeEnumTrancaAcao(ctx.pathParam("acao").toUpperCase()) || TrancaAcao.valueOf(ctx.pathParam("acao").toUpperCase()) == null) {
					ctx.status(422).result(ErrorResponse.INVALID_DATA_MESSAGE);
					return;
				} 
				  tranca.setAcao(TrancaAcao.valueOf(ctx.pathParam(ChavesJson.ACAO.getValor()).toUpperCase()));
			}
			mock.updateTranca(tranca);
			ctx.status(200).result(ErrorResponse.VALID_DATA_MESSAGE);
		} else {
			ctx.status(404).result(ErrorResponse.NOT_FOUND);
		}
	}

	public static void getBikeByTranca(Context ctx) {
		Tranca tranca = retrieveTrancaByParamIdOrNumber(ctx);

		if (tranca != null && tranca.getIdBicicleta() != null) {
//			if (tranca.getStatus() != TrancaStatus.LIVRE || tranca.getStatus() != TrancaStatus.NOVA) {
//				ctx.status(422).result(ErrorResponse.INVALID_TRANCA_STATUS_MESSAGE);
//				return;
//			}
			Bicicleta bike = ControllerBicicleta.retrieveTrancaBikeById(tranca.getIdBicicleta());
			ctx.status(200);
			ctx.json(bike);
			return;
		} else {
			ctx.status(404).result(ErrorResponse.NOT_FOUND);
			return;
		}
	}

	public static void postIntegrarNaRede(Context ctx) {
		Bicicleta bicicleta = ControllerBicicleta.retrieveBikeByCtx(ctx);
		Tranca tranca = ControllerTranca.retrieveTrancaByCtx(ctx);
		if (!Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.IDTRANCA.getValor()))
				&& !Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.IDBICICLETA.getValor()))
				&& Validator.isBicicletaAvailable(String.valueOf(bicicleta.getStatus()))
				&& Validator.isTrancaAvailable(String.valueOf(tranca.getStatus()))
				&& Validator.checkKeysValidByCtx(ctx)) {
			tranca.setIdBicicleta(bicicleta.getId());
			mock.updateTranca(tranca);
			ctx.status(200).result(ErrorResponse.VALID_DATA_MESSAGE);
		} else {
			if(tranca == null || bicicleta == null) {
				ctx.status(404).result(ErrorResponse.VALID_DATA_MESSAGE);
				return;
			}
			ctx.status(422);
			ctx.result(ErrorResponse.INVALID_DATA_MESSAGE);
		}
	}

	public static void postRetirarDaRede(Context ctx) {
		Tranca tranca = ControllerTranca.retrieveTrancaByCtx(ctx);
		
		if(tranca == null || tranca.getIdBicicleta() == null || tranca.getIdBicicleta() == ctx.queryParam(ChavesJson.IDBICICLETA.getValor())) {
			ctx.status(404).result(ErrorResponse.NOT_FOUND);
			return;
		}
		if (!Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.IDTRANCA.getValor()))
				&& !Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.IDBICICLETA.getValor()))
				&& Validator.checkKeysValidByCtx(ctx)) {
			mock.deleteDataTrancaBike(tranca.getIdTranca(), tranca.getIdBicicleta());
			ctx.status(200).result(ErrorResponse.VALID_DATA_MESSAGE);
		} else {
			ctx.status(422);
			ctx.result(ErrorResponse.INVALID_DATA_MESSAGE);
		}
	}

	public static void getTrancaByIdOrNumber(Context ctx) {
		if (!Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.IDTRANCA.getValor()))) {
			ctx.status(422).result(ErrorResponse.INVALID_DATA_MESSAGE);
			return;
		}
		Tranca tranca = retrieveTrancaByParamIdOrNumber(ctx);
		if (tranca != null) {
			ctx.status(200);
			ctx.json(tranca);
			return;
		} else {
			ctx.status(404).result(ErrorResponse.NOT_FOUND);
			return;
		}
	}

	private static Tranca retrieveTrancaByParamIdOrNumber(Context ctx) {
		if (ctx.pathParam(ChavesJson.IDTRANCA.getValor()) != null) {
			return mock.getDataByIdOrNumero(ctx.pathParam(ChavesJson.IDTRANCA.getValor()));
		}
		return null;
	}

	public static void postIntegrarNaRedeTrancaTotem(Context ctx) {
		Totem totem = ControllerTotem.retrieveTotemByCtx(ctx);
		Tranca tranca = ControllerTranca.retrieveTrancaByCtx(ctx);
		
		if(tranca == null || totem == null) {
			ctx.status(404).result(ErrorResponse.NOT_FOUND);
			return;
		}
		if (!Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.IDTRANCA.getValor())) 
			&& !Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.IDTOTEM.getValor()))
			&& Validator.checkKeysValidByCtx(ctx) 
			&& Validator.isTrancaAvailable(String.valueOf(tranca.getStatus()))) {
			
			tranca.setIdTotem(totem.getId());
			mock.updateTranca(tranca);
			ctx.status(200).result(ErrorResponse.VALID_DATA_MESSAGE);
			return;
		} else {
			ctx.status(422);
			ctx.result(ErrorResponse.INVALID_DATA_MESSAGE);
		}
	}

	public static void postRetirarDaRedeTrancaTotem(Context ctx) {
		Tranca tranca = ControllerTranca.retrieveTrancaByCtx(ctx);
	
		if(tranca == null || tranca.getIdTotem() == null || tranca.getIdTotem() == ctx.queryParam(ChavesJson.IDTOTEM.getValor()) ) {
			ctx.status(404).result(ErrorResponse.NOT_FOUND);
			return;
		}
		if (!Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.IDTRANCA.getValor()))
				&& !Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.IDTOTEM.getValor()))
				&& Validator.checkKeysValidByCtx(ctx)) {
			mock.deleteDataTotemTranca(tranca.getIdTotem(), tranca.getIdTranca());
			ctx.status(200).result(ErrorResponse.VALID_DATA_MESSAGE);
		} else {
			ctx.status(422);
			ctx.result(ErrorResponse.INVALID_DATA_MESSAGE);
		}
	}

	public static void getTrancasByTotem(Context ctx) {
		if (Validator.isNullOrEmpty(ctx.pathParam(ChavesJson.IDTOTEM.getValor()))) {
			ctx.status(422).result(ErrorResponse.INVALID_DATA_MESSAGE);
			return;
		}
		List<Tranca> trancas = retrieveTrancasByTotemParam(ctx);
		if (trancas != null) {
			ctx.status(200);
			ctx.json(trancas);
			return;
		} else {
			ctx.status(404).result(ErrorResponse.NOT_FOUND);
			return;
		}
	}

	private static List<Tranca> retrieveTrancasByTotemParam(Context ctx) {
		if (ctx.pathParam(ChavesJson.IDTOTEM.getValor()) != null) {
			List<Tranca> tranca =  mock.getListDataTrancaByTotem(ctx.pathParam(ChavesJson.IDTOTEM.getValor()));
			if (tranca == null) {
				ctx.status(422).result(ErrorResponse.INVALID_DATA_MESSAGE);
				return null;
			}
			return tranca;
		} 
			return null;
	}
	
	
	public static void getBicicletasByTotem(Context ctx) {
		if (Validator.isNullOrEmpty(ctx.pathParam(ChavesJson.IDTOTEM.getValor()))) {
			ctx.status(422).result(ErrorResponse.INVALID_DATA_MESSAGE);
			return;
		}
		List<Bicicleta> bicicletas = retrieveBicicletasByTotemParam(ctx);
		if (bicicletas != null) {
			ctx.status(200);
			ctx.json(bicicletas);
			return;
		} else {
			ctx.status(404).result(ErrorResponse.NOT_FOUND);
			return;
		}
	}
	
	private static List<Bicicleta> retrieveBicicletasByTotemParam(Context ctx) {
		if (ctx.pathParam(ChavesJson.IDTOTEM.getValor()) != null) {
			return mock.getListDataBikeByTotem(ctx.pathParam(ChavesJson.IDTOTEM.getValor()));
		} else {
			return null;
		}
	}

}
