package domain;

public class Tranca {

	private String idTranca;
	private String idBicicleta;
	private String localizacao;
	private String anoDeFabricacao;
	private String modelo;
	private String numero;
	private TrancaStatus status;
	private String idTotem;
	private TrancaAcao acao;
	
	public Tranca() {
	}
	
	public Tranca(String idTranca, String numero, String localizacao, String anoDeFabricacao, String modelo,
			TrancaStatus status) {
		this.idTranca = idTranca;
		this.numero = numero;
		this.localizacao = localizacao;
		this.anoDeFabricacao = anoDeFabricacao;
		this.modelo = modelo;
		this.status = status;
	}

	public Tranca(String idTranca, String numero, String idBicicleta, String localizacao, String anoDeFabricacao, String modelo,
			TrancaStatus status) {
		this.idTranca = idTranca;
		this.numero = numero;
		this.idBicicleta = idBicicleta;
		this.localizacao = localizacao;
		this.anoDeFabricacao = anoDeFabricacao;
		this.modelo = modelo;
		this.status = status;
	}

	public String getIdTranca() {
		return idTranca;
	}

	public void setIdTranca(String idTranca) {
		this.idTranca = idTranca;
	}

	public String getIdBicicleta() {
		return idBicicleta;
	}

	public void setIdBicicleta(String idBicicleta) {
		this.idBicicleta = idBicicleta;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getAnoDeFabricacao() {
		return anoDeFabricacao;
	}

	public void setAnoDeFabricacao(String anoDeFabricacao) {
		this.anoDeFabricacao = anoDeFabricacao;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public TrancaStatus getStatus() {
		return status;
	}

	public void setStatus(TrancaStatus status) {
		this.status = status;
	}

	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getIdTotem() {
		return idTotem;
	}

	public void setIdTotem(String idTotem) {
		this.idTotem = idTotem;
	}

	public TrancaAcao getAcao() {
		return acao;
	}

	public void setAcao(TrancaAcao acao) {
		this.acao = acao;
	}
	
}
