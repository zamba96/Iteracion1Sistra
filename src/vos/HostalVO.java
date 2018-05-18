/**
 * 
 */
package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author camilo
 *
 */
public class HostalVO extends OperadorVO{

	
	
	@JsonProperty(value="apertura")
	private Integer apertura;
	
	@JsonProperty(value="cierre")
	private Integer cierre;
	
	@JsonProperty(value="desayuno")
	private Boolean desayuno;
	
	
	private List<Long> cuartos;
	/*
	public HostalVO(
			@JsonProperty(value="apertura")Integer apertura,
			@JsonProperty(value="cierre")Integer cierre,
			@JsonProperty(value="desayuno")Boolean desayuno,
			@JsonProperty(value="direccion")String direccion,
			@JsonProperty(value="nombre")String nombre) {
		this.apertura = apertura;
		this.cierre = cierre;
		this.desayuno = desayuno;
		this.direccion = direccion;
		this.nombre = nombre;
	}
	
	public HostalVO(
			@JsonProperty(value="apertura")Integer apertura,
			@JsonProperty(value="cierre")Integer cierre,
			@JsonProperty(value="desayuno")Boolean desayuno,
			@JsonProperty(value="direccion")String direccion,
			@JsonProperty(value="nombre")String nombre,
			@JsonProperty(value="cuartos")List<Long> cuartos) {
		this.apertura = apertura;
		this.cierre = cierre;
		this.desayuno = desayuno;
		this.direccion = direccion;
		this.nombre = nombre;
		this.cuartos = cuartos;
	}
	*/
	public HostalVO(){
		super();
	}


	/**
	 * @return the apertura
	 */
	public Integer getApertura() {
		return apertura;
	}

	/**
	 * @param apertura the apertura to set
	 */
	public void setApertura(Integer apertura) {
		this.apertura = apertura;
	}

	/**
	 * @return the cierre
	 */
	public Integer getCierre() {
		return cierre;
	}

	/**
	 * @param cierre the cierre to set
	 */
	public void setCierre(Integer cierre) {
		this.cierre = cierre;
	}

	/**
	 * @return the desayuno
	 */
	public Boolean getDesayuno() {
		return desayuno;
	}

	/**
	 * @param desayuno the desayuno to set
	 */
	public void setDesayuno(Boolean desayuno) {
		this.desayuno = desayuno;
	}

	/**
	 * @return the cuartos
	 */
	public List<Long> getCuartos() {
		return cuartos;
	}

	/**
	 * @param cuartos the cuartos to set
	 */
	public void setCuartos(List<Long> cuartos) {
		this.cuartos = cuartos;
	}
	
	
}
