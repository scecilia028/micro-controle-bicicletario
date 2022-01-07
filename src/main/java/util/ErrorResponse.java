package util;

import java.util.Map;

public class ErrorResponse {
	 	
		private static String title;
	    private static int status;
	    private static String type;
	    private static Map<String, String> details;
	    
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

	    public static final String BAD_REQUEST = "Preenchimento Errado!";

}
