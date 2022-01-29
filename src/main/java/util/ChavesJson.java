package util;

public enum ChavesJson {

	 IDBICICLETA("idBicicleta"), IDTRANCA("idTranca"), IDTOTEM("idTotem"), STATUS("status"),
			 MODELO("modelo"), MARCA("marca"), ANO("ano"), ANODEFABRICACAO("anoDeFabricacao"),
					 NUMERO("numero"), LOCALIZACAO("localizacao"), ACAO("acao");
	private final String valor;
	
	private ChavesJson(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
	
}
