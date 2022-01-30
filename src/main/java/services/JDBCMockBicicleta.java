package services;

import java.util.ArrayList;
import java.util.List;

import domain.Bicicleta;
import domain.BicicletaStatus;

public class JDBCMockBicicleta {
	
	/** lista de bicicletas */
	public final List<Bicicleta> banco = new ArrayList<>();

	    public JDBCMockBicicleta() {
	        for (int i = 0; i < 10; i++) {
	            banco.add(new Bicicleta(String.valueOf(i), BicicletaStatus.NOVA));
	        }
	    }

	    public Bicicleta getDataById(String id) {
	        for (Bicicleta bicicleta : banco) {
	            if (bicicleta.getId() != null && id != null && bicicleta.getId().equalsIgnoreCase(id)) {
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
	            if (bicicleta.getId() != null && bicicleta.getId().equals(id)) {
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
