package domain;

public class Bicicleta {
	
		private String id;
	    private String marca;
	    private String modelo;
	    private String ano;
	    private Integer numero;
	    private BicicletaStatus status;
	    private Integer code;
	    private String localizacao;
	    private boolean podeEditar;
		private boolean podeRemover;
	    
	    /*
	     * Apagar Code e Adc campos podeEditar podeRemover
	     */
	    
	    public Bicicleta() {
	    	
		}
	    
		public Bicicleta(String id, Integer code, BicicletaStatus status) {
			super();
			this.id = id;
			this.code = code;
			this.status = status;
		}
		
		public Bicicleta(String id, Integer code, String marca, String modelo, String ano, Integer numero, BicicletaStatus status) {
			super();
			this.id = id;
			this.code = code;
			this.marca = marca;
			this.modelo = modelo;
			this.ano = ano;
			this.numero = numero;
			this.status = status;
		}
		
		public Bicicleta(String id, Integer code, String marca, String modelo, String ano, Integer numero, BicicletaStatus status, String localizacao, boolean podeEditar, boolean podeRemover) {
			super();
			this.id = id;
			this.code = code;
			this.marca = marca;
			this.modelo = modelo;
			this.ano = ano;
			this.numero = numero;
			this.status = status;
			this.localizacao = localizacao;
			this.podeEditar = podeEditar;
			this.podeRemover = podeRemover;
					
		}

		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getMarca() {
			return marca;
		}
		public void setMarca(String marca) {
			this.marca = marca;
		}
		public String getModelo() {
			return modelo;
		}
		public void setModelo(String modelo) {
			this.modelo = modelo;
		}
		public String getAno() {
			return ano;
		}
		public void setAno(String ano) {
			this.ano = ano;
		}
		public Integer getNumero() {
			return numero;
		}
		public void setNumero(Integer numero) {
			this.numero = numero;
		}
		public BicicletaStatus getStatus() {
			return status;
		}
		public void setStatus(BicicletaStatus status) {
			this.status = status;
		}

		public Integer getCode() {
			return code;
		}

		public void setCode(Integer code) {
			this.code = code;
		}

		public String getLocalizacao() {
			return localizacao;
		}

		public void setLocalizacao(String localizacao) {
			this.localizacao = localizacao;
		}

		public boolean isPodeEditar() {
			return podeEditar;
		}

		public void setPodeEditar(boolean podeEditar) {
			this.podeEditar = podeEditar;
		}

		public boolean isPodeRemover() {
			return podeRemover;
		}

		public void setPodeRemover(boolean podeRemover) {
			this.podeRemover = podeRemover;
		}
}
