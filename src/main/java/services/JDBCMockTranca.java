package services;

import java.util.ArrayList;
import java.util.List;

import domain.Bicicleta;
import domain.Tranca;
import domain.TrancaStatus;
import io.javalin.http.Context;
import util.ChavesJson;
import util.Validator;

public class JDBCMockTranca {
	
	public final List<Tranca> banco = new ArrayList<>();
	public static JDBCMockBicicleta mockBike = new JDBCMockBicicleta();

    public JDBCMockTranca() {
        for (int i = 0; i < 10; i++) {
            banco.add(new Tranca(String.valueOf(i), String.valueOf(i), String.valueOf(i), "Rua ".concat(String.valueOf(i)),
            		"200".concat(String.valueOf(i)), "a".concat(String.valueOf(i)), TrancaStatus.LIVRE));
        }
    }

    public Tranca getDataByContext(Context ctx) {
        if (!Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.IDTRANCA.getValor()))) {
            return this.getDataById(ctx.queryParam(ChavesJson.IDTRANCA.getValor()));
        }  
        return null;
    }

    public Tranca getDataById(String id) {
        for (Tranca tranca : banco) {
            if (tranca.getIdTranca() != null && tranca.getIdTranca().equalsIgnoreCase(id)) {
                return tranca;
            }
        }
        return null;
    }

    public void createData(Tranca trancaUp) {
        if (this.getDataById(trancaUp.getIdTranca()) == null) {
            banco.add(trancaUp);
        }else{
            for (Tranca tranca : banco) {
                if (tranca.getIdTranca().equalsIgnoreCase(trancaUp.getIdTranca())) {
                    banco.set(banco.indexOf(tranca), trancaUp);
                }
            }
        }
    }

    public Boolean deleteData(String id) {
        for (Tranca tranca : banco) {
            if (tranca.getIdTranca().equalsIgnoreCase(id)) {
                banco.remove(tranca);                
                return true;
            }
        }
        return false;
    }

	public void updateTranca(Tranca trancaUpdate) {
		for (Tranca tranca : banco) {
	            if (tranca.getIdTranca().equalsIgnoreCase(trancaUpdate.getIdTranca())) {
	            	banco.set(banco.indexOf(tranca), trancaUpdate);
	                return;
	            }
	        }
	}

	public boolean deleteDataTrancaBike(String idTranca, String idBicicleta) {
		for (Tranca tranca: banco) {
            if (tranca.getIdTranca().equalsIgnoreCase(idTranca) && tranca.getIdBicicleta().equalsIgnoreCase(idBicicleta)) {
               tranca.setIdBicicleta(null);
               this.updateTranca(tranca);
                return true;
            }
        }
		 return false;
	}
	
	public boolean deleteDataTotemTranca(String idTotem, String idTranca) {
		for (Tranca tranca: banco) {
            if (tranca.getIdTranca().equalsIgnoreCase(idTranca) && tranca.getIdTotem().equalsIgnoreCase(idTotem)) {
            	tranca.setIdTotem(null);
                updateTranca(tranca);
                return true;
            }
        }
		 return false;
	}

	public Tranca getDataByIdOrNumero(String id) {
		for (Tranca tranca : banco) {
            if ((tranca.getIdTranca() != null || tranca.getNumero() != null) && (tranca.getNumero().equalsIgnoreCase(id) || tranca.getIdTranca().equalsIgnoreCase(id))) {
                return tranca;
            }
        }
        return null;
	}

	public List<Tranca> getListDataTrancaByTotem(String idTotem) {
		List<Tranca> trancas = new ArrayList<Tranca>();
		for (Tranca tranca : banco) {
            if (tranca.getIdTotem() != null && tranca.getIdTotem().equalsIgnoreCase(idTotem)) {
                trancas.add(tranca);
            }
        }
		return trancas.size() > 0 ? trancas : null;
	}
	
	
	public List<Bicicleta> getListDataBikeByTotem(String idTotem) {
		List<Bicicleta> bicicletas = new ArrayList<Bicicleta>();
		for (Tranca tranca : banco) {
            if (tranca.getIdTotem() != null && tranca.getIdTotem().equalsIgnoreCase(idTotem) && tranca.getIdBicicleta() != null) {
            	bicicletas.add(mockBike.getDataById(tranca.getIdBicicleta()));
            }
        }
		return bicicletas.size() > 0 ? bicicletas : null;
	}
}
