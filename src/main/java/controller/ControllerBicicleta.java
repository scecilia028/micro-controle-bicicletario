package controller;

import domain.Bicicleta;
import domain.BicicletaStatus;
import io.javalin.http.Context;
import services.JDBCMockBicicleta;
import util.ChavesJson;
import util.ErrorResponse;
import util.Validator;

public class ControllerBicicleta {

	public static final JDBCMockBicicleta mock = new JDBCMockBicicleta();

	private ControllerBicicleta() {
		throw new IllegalStateException("Utility class");
	}
	
	public static void getBicicleta(Context ctx) { 
			ctx.status(200);
			ctx.json(mock.banco);
    }

    public static void getBicicletaByParamId(Context ctx) { 
        Bicicleta bike = retrieveBikeParam(ctx);
        
        if (bike != null) {
            ctx.status(200);
            ctx.json(bike);
        }else {
			ctx.status(404).result(ErrorResponse.NOT_FOUND);
		}
    }
    
    public static void getBicicletaByCtx(Context ctx) {
    	if(ctx.queryParam(ChavesJson.IDBICICLETA.getValor()) == null) {
    		getBicicleta(ctx);
    	}else {
        Bicicleta bike = retrieveBikeByCtx(ctx);
        
        if (bike != null) {
            ctx.status(200);
            ctx.json(bike);
        } else {
			ctx.status(404).result(ErrorResponse.NOT_FOUND);
		}
       }
    }
    
    private static Bicicleta retrieveBikeParam(Context ctx) {
            return mock.getDataById(ctx.pathParam(ChavesJson.IDBICICLETA.getValor()));
    }
  
    protected static Bicicleta retrieveBikeByCtx(Context ctx) {
            return mock.getDataById(ctx.queryParam(ChavesJson.IDBICICLETA.getValor()));
    }

    public static void postBicicleta(Context ctx) {
        String status = ctx.queryParam(ChavesJson.STATUS.getValor());
         
        if(Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.IDBICICLETA.getValor()))     ||
	     Validator.isNullOrEmpty(status) || 
	     !Validator.isInRangeEnumBicicleta(status) ) {
        	ctx.status(404).result(ErrorResponse.NOT_FOUND);
			return;
       
        }else if (!Validator.checkKeysValidByCtx(ctx)) {
        	ctx.status(422);
			ctx.result(ErrorResponse.INVALID_DATA_MESSAGE);
			return;
        }	
        Bicicleta bicicleta = checkCreateBicicleta(ctx);
        	
		if (bicicleta != null) {
			mock.updateData(bicicleta);
			ctx.status(200).result(ErrorResponse.VALID_DATA_MESSAGE);
		}
    }
    
    
    public static void putBicicleta(Context ctx) {
    	 Bicicleta bicicleta = retrieveBikeParam(ctx);
    	 if (bicicleta != null) {
    		 if(!Validator.checkKeysValidByCtx(ctx)) {
    			 ctx.status(422).result(ErrorResponse.INVALID_DATA_MESSAGE);
    			 return;
    		 }
    		 bicicleta = checkUpdateBicicleta(ctx, bicicleta);
    		 if(bicicleta != null) {
	    		  mock.updateBicicleta(bicicleta);	
	       		  ctx.status(200).result(ErrorResponse.VALID_DATA_MESSAGE);
    		 } 
    	 } else {
    		 ctx.status(404).result(ErrorResponse.NOT_FOUND);
    	 }
    }

	private static Bicicleta checkCreateBicicleta(Context ctx) {
		Bicicleta bicicleta = new Bicicleta();
		  if(!Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.IDBICICLETA.getValor())) &&
			 !Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.STATUS.getValor())) &&
			 Validator.isInRangeEnumBicicleta(ctx.queryParam(ChavesJson.STATUS.getValor())) &&
			 !Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.MARCA.getValor())) &&
			 !Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.MODELO.getValor()))	&&
			 !Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.ANO.getValor())) &&	  
			 !Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.NUMERO.getValor()))
			) {
			  bicicleta.setId(ctx.queryParam(ChavesJson.IDBICICLETA.getValor()));
			  bicicleta.setStatus(BicicletaStatus.valueOf(ctx.queryParam(ChavesJson.STATUS.getValor()).toUpperCase()));
			  bicicleta.setMarca(ctx.queryParam(ChavesJson.MARCA.getValor()));
			  bicicleta.setModelo(ctx.queryParam(ChavesJson.MODELO.getValor()));
			  bicicleta.setAno(ctx.queryParam(ChavesJson.ANO.getValor()));
			  bicicleta.setNumero(Integer.parseInt(ctx.queryParam(ChavesJson.NUMERO.getValor())));
			  return bicicleta;
		  }
		return null;
	}
	
	private static Bicicleta checkUpdateBicicleta(Context ctx, Bicicleta bicicleta) {
		  if(!Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.STATUS.getValor())) &&  Validator.isInRangeEnumBicicleta(ctx.queryParam(ChavesJson.STATUS.getValor()))) {
			  bicicleta.setStatus(BicicletaStatus.valueOf(ctx.queryParam(ChavesJson.STATUS.getValor()).toUpperCase()));
		  }
		  if(!Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.MARCA.getValor()))) {
			  bicicleta.setMarca(ctx.queryParam(ChavesJson.MARCA.getValor()));
		  }
		  if(!Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.MODELO.getValor()))) {
			  bicicleta.setModelo(ctx.queryParam(ChavesJson.MODELO.getValor()));
		  }
		  if(!Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.ANO.getValor()))) {
			  bicicleta.setAno(ctx.queryParam(ChavesJson.ANO.getValor()));
		  }
		  if(!Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.NUMERO.getValor()))) {
			  bicicleta.setNumero(Integer.parseInt(ctx.queryParam(ChavesJson.NUMERO.getValor())));
		  }
		  if(Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.STATUS.getValor())) &&
			 !Validator.isInRangeEnumBicicleta(ctx.queryParam(ChavesJson.STATUS.getValor())) &&
			 Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.MARCA.getValor())) &&
			 Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.MODELO.getValor()))	&&
			 Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.ANO.getValor())) &&	  
			 Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.NUMERO.getValor()))
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
    		 if(!Validator.isNullOrEmpty(ctx.pathParam(ChavesJson.STATUS.getValor()))) {
				  if(BicicletaStatus.valueOf(ctx.pathParam(ChavesJson.STATUS.getValor()).toUpperCase()) == null) {
					  ctx.status(422).result(ErrorResponse.INVALID_DATA_MESSAGE);
					  return;
				  }
				  bicicleta.setStatus(BicicletaStatus.valueOf(ctx.pathParam(ChavesJson.STATUS.getValor()).toUpperCase()));
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
