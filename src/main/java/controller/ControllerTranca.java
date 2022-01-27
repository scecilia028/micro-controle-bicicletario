package controller;

import java.util.List;

import domain.Bicicleta;
import domain.Totem;
import domain.Tranca;
import domain.TrancaStatus;
import io.javalin.http.Context;
import services.JDBCMockTranca;
import util.ErrorResponse;
import util.Validator;

public class ControllerTranca {

	public static JDBCMockTranca mock = new JDBCMockTranca();
//	protected static ControllerBicicleta controllerBike = new ControllerBicicleta();

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
		if (ctx.queryParam("idTranca") != null) {
			return mock.getDataById(ctx.queryParam("idTranca"));
		}
		return null;
	}

	private static Tranca retrieveTrancaParam(Context ctx) {
		if (ctx.pathParam("idTranca") != null) {
			return mock.getDataById(ctx.pathParam("idTranca"));
		} else {
			return null;
		}
	}

	public static void deleteTranca(Context ctx) {
		Tranca tranca = retrieveTrancaByCtx(ctx);
		if (tranca != null) {
			mock.deleteData(tranca.getIdTranca());
			ctx.status(200).result(ErrorResponse.VALID_DATA_MESSAGE);
		} else {
			ctx.status(404).result(ErrorResponse.NOT_FOUND);
		}
	}

	public static void postTranca(Context ctx) {
		Tranca tranca = new Tranca();
		if (!Validator.isNullOrEmpty(ctx.queryParam("idTranca"))) {
			tranca.setIdTranca(ctx.queryParam("idTranca"));
			tranca = checkCreateTranca(ctx);

			if (tranca == null) {
				ctx.status(422);
				ctx.result(ErrorResponse.INVALID_DATA_MESSAGE);
			} else {
				mock.createData(tranca);
				ctx.status(200);
			}
		}
	}

	public static void putTranca(Context ctx) {
		Tranca tranca = retrieveTrancaParam(ctx);

		if (tranca != null) {
			tranca = checkUpdateTranca(ctx, tranca);
			if (tranca != null) {
				mock.updateTranca(tranca);
				ctx.status(200);
			} else {
				ctx.status(422).result(ErrorResponse.INVALID_DATA_MESSAGE);
			}
		} else {
			ctx.status(404).result(ErrorResponse.NOT_FOUND);
		}
	}

	private static Tranca checkCreateTranca(Context ctx) {
		Tranca tranca = new Tranca();

		if (!Validator.isNullOrEmpty(ctx.queryParam("localizacao"))
				&& !Validator.isNullOrEmpty(ctx.queryParam("status"))
				&& Validator.isInRangeEnumTranca(ctx.queryParam("status"))
				&& !Validator.isNullOrEmpty(ctx.queryParam("modelo"))
				&& !Validator.isNullOrEmpty(ctx.queryParam("anoDeFabricacao"))
				&& !Validator.isNullOrEmpty(ctx.queryParam("numero"))) {
			tranca.setLocalizacao(ctx.queryParam("localizacao"));
			tranca.setStatus(TrancaStatus.valueOf(ctx.queryParam("status").toUpperCase()));
			tranca.setModelo(ctx.queryParam("modelo"));
			tranca.setAnoDeFabricacao(ctx.queryParam("anoDeFabricacao"));
			tranca.setNumero(ctx.queryParam("numero"));
			return tranca;
		}
		return null;
	}

	private static Tranca checkUpdateTranca(Context ctx, Tranca tranca) {

		if (!Validator.isNullOrEmpty(ctx.queryParam("idBicicleta"))) {
			tranca.setIdBicicleta(ctx.queryParam("idBicicleta"));
		}
		if (!Validator.isNullOrEmpty(ctx.queryParam("localizacao"))) {
			tranca.setLocalizacao(ctx.queryParam("localizacao"));
		}
		if (!Validator.isNullOrEmpty(ctx.queryParam("status"))
				|| Validator.isInRangeEnumBicicleta(ctx.queryParam("status"))) {
			tranca.setStatus(TrancaStatus.valueOf(ctx.queryParam("status").toUpperCase()));
		}
		if (!Validator.isNullOrEmpty(ctx.queryParam("modelo"))) {
			tranca.setModelo(ctx.queryParam("modelo"));
		}
		if (!Validator.isNullOrEmpty(ctx.queryParam("anoDeFabricacao"))) {
			tranca.setAnoDeFabricacao(ctx.queryParam("anoDeFabricacao"));
		}
		if (!Validator.isNullOrEmpty(ctx.queryParam("numero"))) {
			tranca.setNumero(ctx.queryParam("numero"));
		}
		if (Validator.isNullOrEmpty(ctx.queryParam("idTotem")) && Validator.isNullOrEmpty(ctx.queryParam("localizacao"))
				&& Validator.isNullOrEmpty(ctx.queryParam("status"))
				&& !Validator.isInRangeEnumTranca(ctx.queryParam("status"))
				&& Validator.isNullOrEmpty(ctx.queryParam("modelo"))
				&& Validator.isNullOrEmpty(ctx.queryParam("anoDeFabricacao"))
				&& Validator.isNullOrEmpty(ctx.queryParam("numero"))) {
			return null;
		}

		return tranca;
	}

	public static void postStatusTranca(Context ctx) {
		Tranca tranca = retrieveTrancaParam(ctx);

		if (tranca != null) {
			if (!Validator.isNullOrEmpty(ctx.pathParam("status"))) {
				if (TrancaStatus.valueOf(ctx.pathParam("status").toUpperCase()) == null) {
					ctx.status(422).result(ErrorResponse.INVALID_DATA_MESSAGE);
					return;
				}
				tranca.setStatus(TrancaStatus.valueOf(ctx.pathParam("status").toUpperCase()));
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
		if (!Validator.isNullOrEmpty(ctx.queryParam("idTranca"))
				&& !Validator.isNullOrEmpty(ctx.queryParam("idBicicleta"))
				&& Validator.isBicicletaAvailable(String.valueOf(bicicleta.getStatus()))
				&& Validator.isTrancaAvailable(String.valueOf(tranca.getStatus()))) {
			tranca.setIdBicicleta(bicicleta.getId());
			mock.updateTranca(tranca);
			ctx.status(200).result(ErrorResponse.VALID_DATA_MESSAGE);
		} else {
			ctx.status(422);
			ctx.result(ErrorResponse.INVALID_DATA_MESSAGE);
		}
	}

	public static void postRetirarDaRede(Context ctx) {
		Bicicleta bicicleta = ControllerBicicleta.retrieveBikeByCtx(ctx);
		Tranca tranca = ControllerTranca.retrieveTrancaByCtx(ctx);
		if (!Validator.isNullOrEmpty(ctx.queryParam("idTranca"))
				&& !Validator.isNullOrEmpty(ctx.queryParam("idBicicleta"))) {
			mock.deleteDataTrancaBike(tranca.getIdTranca(), bicicleta.getId());
			ctx.status(200).result(ErrorResponse.VALID_DATA_MESSAGE);
		} else {
			ctx.status(422);
			ctx.result(ErrorResponse.INVALID_DATA_MESSAGE);
		}
	}

	public static void getTrancaByIdOrNumber(Context ctx) {
		if (!Validator.isNullOrEmpty(ctx.queryParam("idTranca"))) {
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
		if (ctx.pathParam("idTranca") != null) {
			return mock.getDataByIdOrNumero(ctx.pathParam("idTranca"));
		}
		return null;
	}

	public static void postIntegrarNaRedeTrancaTotem(Context ctx) {
		Totem totem = ControllerTotem.retrieveTotemByCtx(ctx);
		Tranca tranca = ControllerTranca.retrieveTrancaByCtx(ctx);
		if (!Validator.isNullOrEmpty(ctx.queryParam("idTranca")) && !Validator.isNullOrEmpty(ctx.queryParam("idTotem"))
				&& Validator.isTrancaAvailable(String.valueOf(tranca.getStatus()))) {
			tranca.setIdTotem(totem.getId());
			mock.updateTranca(tranca);
			ctx.status(200).result(ErrorResponse.VALID_DATA_MESSAGE);
		} else {
			ctx.status(422);
			ctx.result(ErrorResponse.INVALID_DATA_MESSAGE);
		}
	}

	public static void postRetirarDaRedeTrancaTotem(Context ctx) {
		Totem totem = ControllerTotem.retrieveTotemByCtx(ctx);
		Tranca tranca = ControllerTranca.retrieveTrancaByCtx(ctx);
		if (!Validator.isNullOrEmpty(ctx.queryParam("idTranca"))
				&& !Validator.isNullOrEmpty(ctx.queryParam("idTotem"))) {
			mock.deleteDataTotemTranca(totem.getId(), tranca.getIdTranca());
			ctx.status(200).result(ErrorResponse.VALID_DATA_MESSAGE);
		} else {
			ctx.status(422);
			ctx.result(ErrorResponse.INVALID_DATA_MESSAGE);
		}
	}

	public static void getTrancasByTotem(Context ctx) {
		if (Validator.isNullOrEmpty(ctx.pathParam("idTotem"))) {
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
		if (ctx.pathParam("idTotem") != null) {
			List<Tranca> tranca =  mock.getListDataTrancaByTotem(ctx.pathParam("idTotem"));
			if (tranca == null) {
				ctx.status(422).result(ErrorResponse.INVALID_DATA_MESSAGE);
				return null;
			}
			return tranca;
		} 
			return null;
	}
	
	
	public static void getBicicletasByTotem(Context ctx) {
		if (Validator.isNullOrEmpty(ctx.pathParam("idTotem"))) {
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
		if (ctx.pathParam("idTotem") != null) {
			return mock.getListDataBikeByTotem(ctx.pathParam("idTotem"));
		} else {
			return null;
		}
	}

}
