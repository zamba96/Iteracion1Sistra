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
public class ViviendaUVO {

	@JsonProperty(value="direccion")
	private String direccion;
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="cuartos")
	private List<ViviendaRoomVO> cuartos;
	
	public ViviendaUVO(@JsonProperty(value="direccion")String direccion,
			@JsonProperty(value="nombre")String nombre,
			@JsonProperty(value="cuartos")List<ViviendaRoomVO> cuartos){

		this.direccion = direccion;
		this.nombre = nombre;
		this.cuartos = cuartos;
	}

	public ViviendaUVO(@JsonProperty(value="direccion")String direccion,
			@JsonProperty(value="nombre")String nombre) {
		
		this.direccion = direccion;
		this.nombre = nombre;
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
	public List<ViviendaRoomVO> getCuartos() {
		return cuartos;
	}

	/**
	 * @param cuartos the cuartos to set
	 */
	public void setCuartos(List<ViviendaRoomVO> cuartos) {
		this.cuartos = cuartos;
	}
	
	
}

