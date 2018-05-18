package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class HotelVO extends OperadorVO{

	
	@JsonProperty(value="desayuno")
	private Boolean desayuno;
	
	@JsonProperty(value="restaurante")
	private Boolean restaurante;
	
	@JsonProperty(value="piscina")
	private Boolean piscina;
	
	@JsonProperty(value="parquedero")
	private Boolean parqueadero;
	
	@JsonProperty(value="horario")
	private Boolean horario;
	
	@JsonProperty(value="cuartos")
	private List<Long> cuartos;
	/*
	public HotelVO(
			@JsonProperty(value="desayuno")Boolean desayuno,
			@JsonProperty(value="restaurante")Boolean restaurante,
			@JsonProperty(value="piscina")Boolean piscina,
			@JsonProperty(value="direccion")String direccion,
			@JsonProperty(value="nombre")String nombre,
			@JsonProperty(value="parqueadero")Boolean parqueadero,
			@JsonProperty(value="horario")Boolean horario,
			@JsonProperty(value="cuartos")List<Long> cuartos) 
	{
		this.restaurante = restaurante;
		this.desayuno = desayuno;
		this.piscina = piscina;
		this.direccion = direccion;
		this.nombre = nombre;
		this.parqueadero = parqueadero;
		this.horario = horario;
		this.cuartos = cuartos;
	}
	*/
	public HotelVO() {
		super();
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
	 * @return the restaurante
	 */
	public Boolean getRestaurante() {
		return restaurante;
	}

	/**
	 * @param restaurante the restaurante to set
	 */
	public void setRestaurante(Boolean restaurante) {
		this.restaurante = restaurante;
	}

	/**
	 * @return the piscina
	 */
	public Boolean getPiscina() {
		return piscina;
	}

	/**
	 * @param piscina the piscina to set
	 */
	public void setPiscina(Boolean piscina) {
		this.piscina = piscina;
	}

	/**
	 * @return the parqueadero
	 */
	public Boolean getParqueadero() {
		return parqueadero;
	}

	/**
	 * @param parqueadero the parqueadero to set
	 */
	public void setParqueadero(Boolean parqueadero) {
		this.parqueadero = parqueadero;
	}

	/**
	 * @return the horario
	 */
	public Boolean getHorario() {
		return horario;
	}

	/**
	 * @param horario the horario to set
	 */
	public void setHorario(Boolean horario) {
		this.horario = horario;
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
