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
public class ViviendaRoomVO extends AlojamientoVO{


	
	@JsonProperty(value="compartida")
	private Integer compartida;
	
	@JsonProperty(value="restaurante")
	private Double restaurante;
	
	@JsonProperty(value="SalaDeEstudio")
	private Double SalaDeEstudio;
	
	@JsonProperty(value="SalaDeEsparcimiento")
	private Double SalaDeEsparcimiento;
	
	@JsonProperty(value="gym")
	private Double gym;
	
	
	@JsonProperty(value="vivienda")
	private Long vivienda;
	
	@JsonProperty(value="cuarto")
	private Long cuarto;
	
	@JsonProperty(value="reservas")
	private List<Long> reservas;
	/*
	public ViviendaRoomVO(
			@JsonProperty(value="compartida")Integer compartida,
			@JsonProperty(value="restaurante")Double restaurante,
			@JsonProperty(value="SalaDeEstudio")Double SalaDeEstudio,
			@JsonProperty(value="SalaDeEsparcimiento")Double SalaDeEsparcimiento,
			@JsonProperty(value="gym")Double gym,
			@JsonProperty(value="precio")Double precio,
			@JsonProperty(value="vivienda")Long vivienda,
			@JsonProperty(value="cuarto")Long cuarto,
			@JsonProperty(value="reservas")List<Long> reservas) {
		this.compartida = compartida;
		this.restaurante = restaurante;
		this.SalaDeEstudio = SalaDeEstudio;
		this.SalaDeEsparcimiento = SalaDeEsparcimiento;
		this.gym = gym;
		this.precio = precio;
		this.vivienda = vivienda;
		this.cuarto = cuarto;
		this.reservas = reservas;
	}

	public ViviendaRoomVO(
			@JsonProperty(value="compartida")Integer compartida,
			@JsonProperty(value="restaurante")Double restaurante,
			@JsonProperty(value="SalaDeEstudio")Double SalaDeEstudio,
			@JsonProperty(value="SalaDeEsparcimiento")Double SalaDeEsparcimiento,
			@JsonProperty(value="gym")Double gym,
			@JsonProperty(value="precio")Double precio,
			@JsonProperty(value="vivienda")Long vivienda,
			@JsonProperty(value="cuarto")Long cuarto) {
		this.compartida = compartida;
		this.restaurante = restaurante;
		this.SalaDeEstudio = SalaDeEstudio;
		this.SalaDeEsparcimiento = SalaDeEsparcimiento;
		this.gym = gym;
		this.precio = precio;
		this.vivienda = vivienda;
		this.cuarto = cuarto;
	}
	*/
	public ViviendaRoomVO() {
		super();
	}


	/**
	 * @return the compartida
	 */
	public Integer getCompartida() {
		return compartida;
	}

	/**
	 * @param compartida the compartida to set
	 */
	public void setCompartida(Integer compartida) {
		this.compartida = compartida;
	}

	/**
	 * @return the restaurante
	 */
	public Double getRestaurante() {
		return restaurante;
	}

	/**
	 * @param restaurante the restaurante to set
	 */
	public void setRestaurante(Double restaurante) {
		this.restaurante = restaurante;
	}

	/**
	 * @return the salaDeEstudio
	 */
	public Double getSalaDeEstudio() {
		return SalaDeEstudio;
	}

	/**
	 * @param salaDeEstudio the salaDeEstudio to set
	 */
	public void setSalaDeEstudio(Double salaDeEstudio) {
		SalaDeEstudio = salaDeEstudio;
	}

	/**
	 * @return the salaDeEsparcimiento
	 */
	public Double getSalaDeEsparcimiento() {
		return SalaDeEsparcimiento;
	}

	/**
	 * @param salaDeEsparcimiento the salaDeEsparcimiento to set
	 */
	public void setSalaDeEsparcimiento(Double salaDeEsparcimiento) {
		SalaDeEsparcimiento = salaDeEsparcimiento;
	}

	/**
	 * @return the gym
	 */
	public Double getGym() {
		return gym;
	}

	/**
	 * @param gym the gym to set
	 */
	public void setGym(Double gym) {
		this.gym = gym;
	}

	/**
	 * @return the vivienda
	 */
	public Long getVivienda() {
		return vivienda;
	}

	/**
	 * @param vivienda the vivienda to set
	 */
	public void setVivienda(Long vivienda) {
		this.vivienda = vivienda;
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

	/**
	 * @return the cuarto
	 */
	public Long getCuarto() {
		return cuarto;
	}

	/**
	 * @param cuarto the cuarto to set
	 */
	public void setCuarto(Long cuarto) {
		this.cuarto = cuarto;
	}
	
	
}
