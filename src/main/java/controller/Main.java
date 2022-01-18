package controller;

import util.JavalinApp;

public class Main {

	private final static String PATH = "bicicleta";
	private final static String NOT_FOUND_MESSAGE = "Não encontrado";
	private final static String INVALID_DATA_MESSAGE = "Dados Inválidos";
	
	 public static void main(String[] args) {
	        JavalinApp app = new JavalinApp();
	        app.start(getHerokuAssignedPort());
	    }
	 
	    private static int getHerokuAssignedPort() {
	        String herokuPort = System.getenv("PORT");
	        if (herokuPort != null) {
	          return Integer.parseInt(herokuPort);
	        }
	        return 7000;
//	    	Javalin app = Javalin.create().start(getHerokuAssignedPort());
//	        app.get(PATH, ctx -> {
//	        	String queryParam = ctx.queryParam("id");
//				if (queryParam == null) {
//					ctx.status(422).json(Error.builder().codigo("422").mensagem(INVALID_DATA_MESSAGE));
//					return;
//				}
//				Ciclista ciclista = DBOService.getCiclista(queryParam);
//				if (ciclista == null) {
//					ctx.status(404).json(Error.builder().codigo("404").mensagem(NOT_FOUND_MESSAGE));
//					return;
//				}
//				ctx.status(200).json(ciclista);
//			});
//	        app.post(PATH, ctx -> {
//	        	Ciclista ciclista = ctx.bodyAsClass(Ciclista.class);
//	        	if (ciclista == null) {
//	        		ctx.status(405).json(Error.builder().codigo("405").mensagem(INVALID_DATA_MESSAGE));
//	        	}
//	        	if (DBOService.createCiclista(ciclista)) {
//	        		ctx.status(201).json(ciclista);
//	        	} else {
//					ctx.status(405).json(Error.builder().codigo("405").mensagem(INVALID_DATA_MESSAGE));
//	        	}
//	        });

	      }

}
