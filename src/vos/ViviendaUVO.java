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
public class ViviendaUVO extends OperadorVO{

	
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
		super();
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

