package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class MejoresVO {

	@JsonProperty(value = "mejorAlojamiento")
	private Long mejorAlojamiento;
	
	@JsonProperty(value = "peorAlojamiento")
	private Long peorAlojamiento;
	
	@JsonProperty(value = "mejorOperador")
	private Long mejorOperador;
	
	@JsonProperty(value = "peorOperador")
	private Long peorOperador;

	/**
	 * @return the mejorAlojamiento
	 */
	public Long getMejorAlojamiento() {
		return mejorAlojamiento;
	}

	/**
	 * @param mejorAlojamiento the mejorAlojamiento to set
	 */
	public void setMejorAlojamiento(Long mejorAlojamiento) {
		this.mejorAlojamiento = mejorAlojamiento;
	}

	/**
	 * @return the peorAlojamiento
	 */
	public Long getPeorAlojamiento() {
		return peorAlojamiento;
	}

	/**
	 * @param peorAlojamiento the peorAlojamiento to set
	 */
	public void setPeorAlojamiento(Long peorAlojamiento) {
		this.peorAlojamiento = peorAlojamiento;
	}

	/**
	 * @return the mejorOperador
	 */
	public Long getMejorOperador() {
		return mejorOperador;
	}

	/**
	 * @param mejorOperador the mejorOperador to set
	 */
	public void setMejorOperador(Long mejorOperador) {
		this.mejorOperador = mejorOperador;
	}

	/**
	 * @return the peorOperador
	 */
	public Long getPeorOperador() {
		return peorOperador;
	}

	/**
	 * @param peorOperador the peorOperador to set
	 */
	public void setPeorOperador(Long peorOperador) {
		this.peorOperador = peorOperador;
	}
	
	
}
