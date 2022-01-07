package util;

import bicicleta.BicicletaStatus;

public final class Validator {
	private Validator() {
		throw new IllegalStateException("ErrorResponse");
	}

	public static boolean isNullOrEmpty(String value) {
		return (value == null || value.equalsIgnoreCase("null"));
	}

	public static boolean isInRangeEnum(String value) {
		try {
			BicicletaStatus.valueOf(value.toUpperCase());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
