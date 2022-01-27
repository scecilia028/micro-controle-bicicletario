package controller;

import domain.Bicicleta;
import domain.BicicletaStatus;
import io.javalin.http.Context;
import services.JDBCMockBicicleta;
import util.ErrorResponse;
import util.Validator;

public class ControllerBicicleta {

	public static JDBCMockBicicleta mock = new JDBCMockBicicleta();

		//TODO testar isso aqui
	    public static void getBicicletaByParamId(Context ctx) { 
	        Bicicleta bike = retrieveBikeParam(ctx);
	        
	        if (bike != null) {
	            ctx.status(200);
	            ctx.json(bike);
	        }else if (bike == null && mock.banco != null) {
				ctx.status(200);
				ctx.json(mock.banco);
				return;
			} else {
				ctx.status(404).result(ErrorResponse.NOT_FOUND);
				return;
			}
	    }
	    
	    public static void getBicicletaByCtx(Context ctx) {
	        Bicicleta bike = retrieveBikeByCtx(ctx);
	        
	        if (bike != null) {
	            ctx.status(200);
	            ctx.json(bike);
	        }else if (bike == null && mock.banco != null) {
				ctx.status(200);
				ctx.json(mock.banco);
				return;
			} else {
				ctx.status(404).result(ErrorResponse.NOT_FOUND);
				return;
			}
	    }
	    
	    private static Bicicleta retrieveBikeParam(Context ctx) {
	        if (ctx.pathParam("idBicicleta") != null) {
	            return mock.getDataById(ctx.pathParam("idBicicleta"));
	        }  else{
	            return null;
	        }
	    }
	  
	    protected static Bicicleta retrieveBikeByCtx(Context ctx) {
	        if (ctx.queryParam("idBicicleta") != null) {
	            return mock.getDataById(ctx.queryParam("idBicicleta"));
	        }  else{
	            return null;
	        }
	    }

	    public static void postBicicleta(Context ctx) {
	        String status = ctx.queryParam("status");
	        Bicicleta bicicleta = new Bicicleta();
	        if (
	            Validator.isNullOrEmpty(ctx.queryParam("idBicicleta"))     ||
	            Validator.isNullOrEmpty(status) ||
	            !Validator.isInRangeEnumBicicleta(status)
	        ) {
	        	bicicleta = checkCreateBicicleta(ctx);
	        	
				if (bicicleta == null) {
					ctx.status(422);
					ctx.result(ErrorResponse.INVALID_DATA_MESSAGE);
				} else {
					mock.updateData(bicicleta);
					ctx.status(200);
				}
	        }
	    }
	    
	    public static void putBicicleta(Context ctx) {
	    	 Bicicleta bicicleta = retrieveBikeParam(ctx);
	    	 
	    	 if (bicicleta != null) {
	    		 bicicleta = checkUpdateBicicleta(ctx, bicicleta);
	    		 if(bicicleta != null) {
		    		  mock.updateBicicleta(bicicleta);	
		       		  ctx.status(200);
	    		 }else {
	    			 ctx.status(422).result(ErrorResponse.INVALID_DATA_MESSAGE);
	    		 }
	    	 } else {
	    		 ctx.status(404).result(ErrorResponse.NOT_FOUND);
	    	 }
	    }

		private static Bicicleta checkCreateBicicleta(Context ctx) {
			Bicicleta bicicleta = new Bicicleta();
			  if(!Validator.isNullOrEmpty(ctx.queryParam("idBicicleta")) &&
				 !Validator.isNullOrEmpty(ctx.queryParam("status")) &&
				 Validator.isInRangeEnumBicicleta(ctx.queryParam("status")) &&
				 !Validator.isNullOrEmpty(ctx.queryParam("marca")) &&
				 !Validator.isNullOrEmpty(ctx.queryParam("modelo"))	&&
				 !Validator.isNullOrEmpty(ctx.queryParam("ano")) &&	  
				 !Validator.isNullOrEmpty(ctx.queryParam("numero")) &&
				 !Validator.isNullOrEmpty(ctx.queryParam("localizacao"))
				) {
				  bicicleta.setId(ctx.queryParam("idBicicleta"));
				  bicicleta.setStatus(BicicletaStatus.valueOf(ctx.queryParam("status").toUpperCase()));
				  bicicleta.setMarca(ctx.queryParam("marca"));
				  bicicleta.setModelo(ctx.queryParam("modelo"));
				  bicicleta.setAno(ctx.queryParam("ano"));
				  bicicleta.setNumero(Integer.parseInt(ctx.queryParam("numero")));
				  bicicleta.setLocalizacao(ctx.queryParam("localizacao"));
				  return bicicleta;
			  }
			return null;
		}
		
		private static Bicicleta checkUpdateBicicleta(Context ctx, Bicicleta bicicleta) {
			 
			  if(!Validator.isNullOrEmpty(ctx.queryParam("status")) &&  Validator.isInRangeEnumBicicleta(ctx.queryParam("status"))) {
				  bicicleta.setStatus(BicicletaStatus.valueOf(ctx.queryParam("status").toUpperCase()));
			  }
			  if(!Validator.isNullOrEmpty(ctx.queryParam("marca"))) {
				  bicicleta.setMarca(ctx.queryParam("marca"));
			  }
			  if(!Validator.isNullOrEmpty(ctx.queryParam("modelo"))) {
				  bicicleta.setModelo(ctx.queryParam("modelo"));
			  }
			  if(!Validator.isNullOrEmpty(ctx.queryParam("ano"))) {
				  bicicleta.setAno(ctx.queryParam("ano"));
			  }
			  if(!Validator.isNullOrEmpty(ctx.queryParam("numero"))) {
				  bicicleta.setNumero(Integer.parseInt(ctx.queryParam("numero")));
			  }
			  if(!Validator.isNullOrEmpty(ctx.queryParam("localizacao"))) {
				  bicicleta.setLocalizacao(ctx.queryParam("localizacao"));
			  }
			  if(Validator.isNullOrEmpty(ctx.queryParam("status")) &&
				 !Validator.isInRangeEnumBicicleta(ctx.queryParam("status")) &&
				 Validator.isNullOrEmpty(ctx.queryParam("marca")) &&
				 Validator.isNullOrEmpty(ctx.queryParam("modelo"))	&&
				 Validator.isNullOrEmpty(ctx.queryParam("ano")) &&	  
				 Validator.isNullOrEmpty(ctx.queryParam("numero")) &&
				 Validator.isNullOrEmpty(ctx.queryParam("localizacao"))
				) {
				  return null;
			  }
			return bicicleta;
		}

	    public static void deleteBicicleta(Context ctx) {
	        Bicicleta bicicleta = retrieveBikeParam(ctx);
	        if (bicicleta != null) {
	            mock.deleteData(bicicleta.getId());
	            ctx.status(200).result(ErrorResponse.VALID_DATA_MESSAGE);
	        }else{
	        	ctx.status(404).result(ErrorResponse.NOT_FOUND);
	         }
	    }
	    
	    public static void postStatusBicicleta(Context ctx) {
	    	 Bicicleta bicicleta = retrieveBikeParam(ctx);
	    	 
	    	 if (bicicleta != null) {
	    		 if(!Validator.isNullOrEmpty(ctx.pathParam("status"))) {
					  if(BicicletaStatus.valueOf(ctx.pathParam("status").toUpperCase()) == null) {
						  ctx.status(422).result(ErrorResponse.INVALID_DATA_MESSAGE);
						  return;
					  }
					  bicicleta.setStatus(BicicletaStatus.valueOf(ctx.pathParam("status").toUpperCase()));
				  }
	    		  mock.updateBicicleta(bicicleta);	
	    		  ctx.status(200).result(ErrorResponse.VALID_DATA_MESSAGE);
	    	 } else {
	    		 ctx.status(404).result(ErrorResponse.NOT_FOUND);
	    	 }
	    }

		protected Bicicleta retrieveBikeById(String idBicicleta) {
			if (idBicicleta != null) {
				return mock.getDataById(idBicicleta);
			} else {
				return null;
			}
		}
		
		protected static Bicicleta retrieveTrancaBikeById(String idBicicleta) {
			if (idBicicleta != null) {
				return mock.getDataById(idBicicleta);
			} else {
				return null;
			}
		}
		
}
