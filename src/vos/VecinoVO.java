package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class VecinoVO extends PersonaVO{

	public VecinoVO(){
		super();
	}
	
	@JsonProperty(value = "idOperador")
	private Long idOperador;

	/**
	 * @return the id
	 */
	public Long getId() {
		return idOperador;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.idOperador = id;
	}
	
	
	
	
}
