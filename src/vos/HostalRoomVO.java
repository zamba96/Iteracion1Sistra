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
public class HostalRoomVO {

	@JsonProperty(value = "id")
	private Long id;
	
	@JsonProperty(value="cuarto")
	private Integer cuarto;
	
	@JsonProperty(value="capacidad")
	private Integer capacidad;
	
	@JsonProperty(value="precio")
	private Double precio;
	
	@JsonProperty(value="hostal")
	private Long hostal;
	
	@JsonProperty(value="reservas")
	private List<Long> reservas;
	
	public HostalRoomVO(
			@JsonProperty(value="cuarto")Integer cuarto,
			@JsonProperty(value="capacidad")Integer capacidad,
			@JsonProperty(value="precio")Double precio,
			@JsonProperty(value="hostal")Long hostal,
			@JsonProperty(value="nombre")String nombre,
			@JsonProperty(value="reservas")List<Long> reservas){
		this.cuarto = cuarto;
		this.capacidad = capacidad;
		this.hostal = hostal;
		this.precio = precio;
		this.reservas = reservas;
	}
	
	public HostalRoomVO(
			@JsonProperty(value="cuarto")Integer cuarto,
			@JsonProperty(value="capacidad")Integer capacidad,
			@JsonProperty(value="precio")Double precio,
			@JsonProperty(value="hostal")Long hostal,
			@JsonProperty(value="nombre")String nombre){
		this.cuarto = cuarto;
		this.capacidad = capacidad;
		this.hostal = hostal;
		this.precio = precio;
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
	 * @return the cuarto
	 */
	public Integer getCuarto() {
		return cuarto;
	}

	/**
	 * @param cuarto the cuarto to set
	 */
	public void setCuarto(Integer cuarto) {
		this.cuarto = cuarto;
	}

	/**
	 * @return the capacidad
	 */
	public Integer getCapacidad() {
		return capacidad;
	}

	/**
	 * @param capacidad the capacidad to set
	 */
	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	/**
	 * @return the precio
	 */
	public Double getPrecio() {
		return precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	/**
	 * @return the hostal
	 */
	public Long getHostal() {
		return hostal;
	}

	/**
	 * @param hostal the hostal to set
	 */
	public void setHostal(Long hostal) {
		this.hostal = hostal;
	}

	/**
	 * @return the reservas
	 */
	public List<Long> getReservas() {
		return reservas;
	}

	/**
	 * @param reservas the reservas to set
	 */
	public void setReservas(List<Long> reservas) {
		this.reservas = reservas;
	}
	
	
}
