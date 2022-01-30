package services;

import java.util.ArrayList;
import java.util.List;

import domain.Totem;

public class JDBCMockTotem {
	
	public final List<Totem> banco = new ArrayList<>();

    public JDBCMockTotem() {
        for (int i = 0; i < 10; i++) {
            banco.add(new Totem(String.valueOf(i), "Rua ".concat(String.valueOf(i))));
        }
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
