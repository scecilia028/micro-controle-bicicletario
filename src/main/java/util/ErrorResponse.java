package util;

import java.util.Map;

public class ErrorResponse {
	 	
		private static String title;
	    private static int status;
	    private static String type;
	    private static Map<String, String> details;
	    public static final String NOT_FOUND = "Não encontrado.";
	    public static  final  String INVALID_DATA_MESSAGE = "Dados Inválidos.";
	    public static  final  String VALID_DATA_MESSAGE = "Dados atualizados.";
	    public static  final  String INVALID_TRANCA_STATUS_MESSAGE = "Tranca ou bicicleta indisponível.";
	    
	    private ErrorResponse() {
	    throw new IllegalStateException("ErrorResponse");
	    }
	    
	    public static String getTitle() {
	        return title;
	    }
	    public static Map<String, String> getDetails() {
	        return details;
	    }
	    public static int getStatus() {
	        return status;
	    }
	    public static String getType() {
	        return type;
	    }

}
