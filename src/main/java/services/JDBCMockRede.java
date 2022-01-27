package services;

import java.util.ArrayList;

import domain.Bicicleta;
import domain.Rede;
import domain.Totem;
import domain.Tranca;

public class JDBCMockRede {
	
	/** lista de bicicletas */
	public ArrayList<Rede> banco = new ArrayList<>();

	    public JDBCMockRede() {
	        for (int i = 0; i < 2; i++) {
	        	Rede rede = new Rede();
	        	rede.setIdTranca(""+i);
	        	rede.setIdBicicleta(""+i);
	            banco.add(rede);
	        }
	    }

//	    public Bicicleta getDataByContext(Context ctx) {
//	        if (!Validator.isNullOrEmpty(ctx.queryParam("id"))) {
//	            return this.getDataById(ctx.queryParam("id"));
//	        } else {
//	            return this.getDataById(ctx.queryParam("code"));
//	        }
//	    }

	 	/**
	 	 * @param idTranca
	 	 * @param idBicicleta
	 	 * @return retorna uma bicicleta pertencente a uma tranca
	 	 */
	    public Rede getIdTrancaBikeById(String idTranca, String idBicicleta) {
	        for (Rede rede : banco) {
	            if (rede.getIdTranca().equalsIgnoreCase(idTranca) && rede.getIdBicicleta().equalsIgnoreCase(idBicicleta)) {
	                return rede;
	            }
	        }
	        return null;
	    }
	    
	    public void createDataTrancaBike(Tranca tranca, Bicicleta bike) {
	        if (this.getIdTrancaBikeById(tranca.getIdTranca(), bike.getId()) == null) {
	        	Rede rede = new Rede();
	        	rede.setIdTranca(tranca.getIdTranca());
	        	rede.setIdBicicleta(bike.getId());
	            banco.add(rede);
	        }
	    }
	    
		public Boolean deleteDataTrancaBike(String idTranca, String idBicicleta) {
			for (Rede rede : banco) {
	            if (rede.getIdTranca().equalsIgnoreCase(idTranca) && rede.getIdBicicleta().equalsIgnoreCase(idBicicleta)) {
	                banco.remove(rede);                
	                return Boolean.TRUE;
	            }
	        }
			 return Boolean.FALSE;
		}
	    
	    /**
	     * @param idTotem
	     * @param idTranca
	     * @return retorna uma tranca pertencente a um totem
	     */
	    public Rede getIdTotemTrancaById(String idTotem, String idTranca) {
	        for (Rede rede : banco) {
	            if (rede.getIdTranca().equalsIgnoreCase(idTranca) && rede.getIdTotem().equalsIgnoreCase(idTotem)) {
	                return rede;
	            }
	        }
	        return null;
	    }

		public void createDataTrancaTotem(Totem totem, Tranca tranca) {
			if (this.getIdTotemTrancaById(totem.getId(), tranca.getIdTranca()) == null) {
	        	Rede rede = new Rede();
	        	rede.setIdTranca(tranca.getIdTranca());
	        	rede.setIdTotem(totem.getId());
	            banco.add(rede);
	        }
		}

		public Boolean deleteDataTotemTranca(String idTotem, String idTranca) {
			for (Rede rede : banco) {
	            if (rede.getIdTranca().equalsIgnoreCase(idTranca) && rede.getIdTotem().equalsIgnoreCase(idTotem)) {
	                banco.remove(rede);                
	                return Boolean.TRUE;
	            }
	        }
	        return Boolean.FALSE;
		}
	    
	    
/*
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
		*/
}
