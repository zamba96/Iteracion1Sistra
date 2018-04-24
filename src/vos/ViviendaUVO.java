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

	@JsonProperty(value = "id")
	private Long id;
	
	@JsonProperty(value="direccion")
	private String direccion;
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="cuartos")
	private List<Long> cuartos;
	/*
	public ViviendaUVO(@JsonProperty(value="direccion")String direccion,
			@JsonProperty(value="nombre")String nombre,
			@JsonProperty(value="cuartos")List<Long> cuartos){

		this.direccion = direccion;
		this.nombre = nombre;
		this.cuartos = cuartos;
	}

	public ViviendaUVO(@JsonProperty(value="direccion")String direccion,
			@JsonProperty(value="nombre")String nombre) {
		
		this.direccion = direccion;
		this.nombre = nombre;
	}
	*/
	
	public ViviendaUVO() {
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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

