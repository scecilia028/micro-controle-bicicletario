package services;

import java.util.ArrayList;

import domain.Bicicleta;
import domain.BicicletaStatus;
import io.javalin.http.Context;
import util.ChavesJson;
import util.Validator;

public class JDBCMockBicicleta {
	
	/** lista de bicicletas */
	public ArrayList<Bicicleta> banco = new ArrayList<>();

	    public JDBCMockBicicleta() {
	        for (int i = 0; i < 10; i++) {
	            banco.add(new Bicicleta(String.valueOf(i), BicicletaStatus.DISPONIVEL));
	        }
	    }

	    public Bicicleta getDataByContext(Context ctx) {
	        if (!Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.IDBICICLETA.getValor()))) {
	            return this.getDataById(ctx.queryParam(ChavesJson.IDBICICLETA.getValor()));
	        }
	        return null;
	    }

	    public Bicicleta getDataById(String id) {
	        for (Bicicleta bicicleta : banco) {
	            if (bicicleta.getId().equalsIgnoreCase(id)) {
	                return bicicleta;
	            }
	        }
	        return null;
	    }

	    public void updateData(Bicicleta bike) {
	        if (this.getDataById(bike.getId()) == null) {
	            banco.add(bike);
	        }else{
	            for (Bicicleta bicicleta : banco) {
	                if (bicicleta.getId().equalsIgnoreCase(bike.getId())) {
	                    banco.set(banco.indexOf(bicicleta), bike);
	                }
	            }
	        }
	    }

	    public Boolean deleteData(String id) {
	        for (Bicicleta bicicleta : banco) {
	            if (bicicleta.getId().equalsIgnoreCase(id)) {
	                banco.remove(bicicleta);                
	                return true;
	            }
	        }
	        return false;
	    }

		public void updateBicicleta(Bicicleta bicicletaUpdate) {
			
			for (Bicicleta bicicleta : banco) {
		            if (bicicleta.getId().equalsIgnoreCase(bicicletaUpdate.getId())) {
		            	banco.set(banco.indexOf(bicicleta), bicicletaUpdate);
		                return;
		            }
		        }
		}
		
}
