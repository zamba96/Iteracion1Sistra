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
public class HostalVO {

	@JsonProperty(value="apertura")
	private Integer apertura;
	
	@JsonProperty(value="cierre")
	private Integer cierre;
	
	@JsonProperty(value="desayuno")
	private Boolean desayuno;
	
	@JsonProperty(value="direccion")
	private String direccion;
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	private List<HostalRoomVO> cuartos;
	
	public HostalVO(@JsonProperty(value="apertura")Integer apertura,
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
	
	public HostalVO(@JsonProperty(value="apertura")Integer apertura,
			@JsonProperty(value="cierre")Integer cierre,
			@JsonProperty(value="desayuno")Boolean desayuno,
			@JsonProperty(value="direccion")String direccion,
			@JsonProperty(value="nombre")String nombre,
			@JsonProperty(value="cuartos")List<HostalRoomVO> cuartos) {

		this.apertura = apertura;
		this.cierre = cierre;
		this.desayuno = desayuno;
		this.direccion = direccion;
		this.nombre = nombre;
		this.cuartos = cuartos;
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
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the cuartos
	 */
	public List<HostalRoomVO> getCuartos() {
		return cuartos;
	}

	/**
	 * @param cuartos the cuartos to set
	 */
	public void setCuartos(List<HostalRoomVO> cuartos) {
		this.cuartos = cuartos;
	}
	
	
}
