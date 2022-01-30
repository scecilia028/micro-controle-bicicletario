package domain;

public class Bicicleta {
	
		private String id;
	    private String marca;
	    private String modelo;
	    private String ano;
	    private Integer numero;
	    private BicicletaStatus status;

		public Bicicleta() {
	    	
		}
	    
		public Bicicleta(String id, BicicletaStatus status) {
			super();
			this.id = id;
			this.status = status;
		}
		
		public Bicicleta(String id, String marca, String modelo, String ano, Integer numero, BicicletaStatus status) {
			super();
			this.id = id;
			this.marca = marca;
			this.modelo = modelo;
			this.ano = ano;
			this.numero = numero;
			this.status = status;
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
		
		@Override
		public boolean equals(Object obj) {
				
			 if (obj == null) {
		            return false;
		        }
			
			if (obj.getClass() != this.getClass()) {
	            return false;
	        }
		
	        final Bicicleta other = (Bicicleta) obj;
	        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
	            return false;
	        }
	        return true;
		}
		
		@Override
		public int hashCode() {
			return super.hashCode();
		}
}
