/**
 * 
 */
package vos;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author camilo
 *
 */
public abstract class AlojamientoVO {

	@JsonProperty(value = "id")
	private Long id;

	@JsonProperty(value = "capacidad")
	private Integer capacidad;

	@JsonProperty(value = "precio")
	private Integer precio;

	/**
	 * 
	 */
	public AlojamientoVO() {

	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the capacidad
	 */
	public Integer getCapacidad() {
		return capacidad;
	}

	/**
	 * @param capacidad
	 *            the capacidad to set
	 */
	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	/**
	 * @return the precio
	 */
	public Integer getPrecio() {
		return precio;
	}

	/**
	 * @param precio
	 *            the precio to set
	 */
	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

}
