package controller;

import io.javalin.http.Context;

public class Controller {
	
	private Controller(){}

    public static void getEcho(Context ctx) {
        String echo = ctx.pathParam("200");
        ctx.result(echo +" "+ echo +" "+ echo);
        ctx.status(200);
    }

    public static void getRoot(Context ctx) {
        ctx.status(200);
        ctx.result(" 200");
    }


}
