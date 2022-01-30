package util;

import domain.BicicletaStatus;
import domain.TrancaAcao;
import domain.TrancaStatus;
import io.javalin.http.Context;

public final class Validator {
	private Validator() {
		throw new IllegalStateException("ErrorResponse");
	}

	public static boolean isNullOrEmpty(String value) {
		return (value == null || value.equalsIgnoreCase("null"));
	}

	public static boolean isInRangeEnumBicicleta(String value) {
		try {
			BicicletaStatus.valueOf(value.toUpperCase());
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public static boolean isBicicletaAvailable(String value) {
			BicicletaStatus bikeStatus = BicicletaStatus.valueOf(value.toUpperCase());
			if(bikeStatus == BicicletaStatus.DISPONIVEL ||
			   bikeStatus == BicicletaStatus.EM_USO || 
			   bikeStatus == BicicletaStatus.NOVA) {
				return true;
			}
		 return false;
	}
	
	public static boolean isTrancaAvailable(String value) {
		TrancaStatus tranca = TrancaStatus.valueOf(value.toUpperCase());
		if(tranca == TrancaStatus.NOVA || tranca == TrancaStatus.LIVRE) {
			return true;
		}
	 return false;
}
	
	public static boolean isInRangeEnumTranca(String value) {
		try {
			TrancaStatus.valueOf(value.toUpperCase());
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public static boolean isInRangeEnumTrancaAcao(String value) {
		try {
			TrancaAcao.valueOf(value.toUpperCase());
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public static boolean isInRangeValidChavesJson(String value) {
		try {
			ChavesJson.valueOf(value.toUpperCase());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean checkKeysValidByCtx(Context ctx) {
		for (String key : ctx.queryParamMap().keySet()) {
			if (!isInRangeValidChavesJson(key)) {
				return false;
			}
		}
			return true;
	}

}
