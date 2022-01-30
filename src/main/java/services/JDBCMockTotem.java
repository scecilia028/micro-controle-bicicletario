package services;

import java.util.ArrayList;
import java.util.List;

import domain.Totem;
import io.javalin.http.Context;
import util.ChavesJson;
import util.Validator;

public class JDBCMockTotem {
	
	public List<Totem> banco = new ArrayList<>();

    public JDBCMockTotem() {
        for (int i = 0; i < 10; i++) {
            banco.add(new Totem(String.valueOf(i), "Rua ".concat(String.valueOf(i))));
        }
    }

    public Totem getDataByContext(Context ctx) {
        if (!Validator.isNullOrEmpty(ctx.queryParam(ChavesJson.IDTOTEM.getValor()))) {
            return this.getDataById(ctx.queryParam(ChavesJson.IDTOTEM.getValor()));
        }  
        return null;
    }

    public Totem getDataById(String id) {
        for (Totem Totem : banco) {
            if (Totem.getId().equalsIgnoreCase(id)) {
                return Totem;
            }
        }
        return null;
    }

    public void updateData(Totem totemUp) {
        if (this.getDataById(totemUp.getId()) == null) {
            banco.add(totemUp);
        }else{
            for (Totem totem : banco) {
                if (totem.getId().equalsIgnoreCase(totemUp.getId())) {
                    banco.set(banco.indexOf(totem), totemUp);
                }
            }
        }
    }

    public Boolean deleteData(String id) {
        for (Totem totem : banco) {
            if (totem.getId().equalsIgnoreCase(id)) {
                banco.remove(totem);                
                return true;
            }
        }
        return false;
    }

	public void updateTotem(Totem totemUpdate) {
		for (Totem totem : banco) {
	            if (totem.getId().equalsIgnoreCase(totemUpdate.getId())) {
	            	banco.set(banco.indexOf(totem), totemUpdate);
	                return;
	            }
	        }
	}

}
