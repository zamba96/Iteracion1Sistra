package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class HotelRoomVO {

	@JsonProperty(value="banera")
	private Boolean banera;
	
	@JsonProperty(value="yakuzi")
	private Boolean yakuzi;
	
	@JsonProperty(value="sala")
	private Boolean sala;
	
	@JsonProperty(value="cocina")
	private Boolean cocina;
	
	@JsonProperty(value="cable")
	private Boolean cable;
	 
	@JsonProperty(value="precio")
	private Double precio;
	
	@JsonProperty(value="hotel")
	private HotelVO hotel;
	
	@JsonProperty(value="tipo")
	private String tipo;
	
	@JsonProperty(value="capacidad")
	private Integer capacidad;
	
	@JsonProperty(value="cuarto")
	private Integer cuarto;
	
	@JsonProperty(value="reservas")
	private List<ReservaHotelVO> reservas;
	
	public HotelRoomVO(@JsonProperty(value="banera")Boolean banera,
			@JsonProperty(value="yakuzi")Boolean yakuzi,
			@JsonProperty(value="sala")Boolean sala,
			@JsonProperty(value="cocina")Boolean cocina,
			@JsonProperty(value="cable")Boolean cable,
			@JsonProperty(value="precio")Double precio,
			@JsonProperty(value="hotel")HotelVO hotel,
	        @JsonProperty(value="tipo")String tipo,
	        @JsonProperty(value="capacidad")Integer capacidad,
	        @JsonProperty(value="cuarto")Integer cuarto,
	        @JsonProperty(value="reservas")List<ReservaHotelVO> reservas){

		this.banera = banera;
		this.yakuzi = yakuzi;
		this.sala = sala;
		this.cocina = cocina;
		this.cable = cable;
		this.precio = precio;
		this.hotel = hotel;
		this.tipo = tipo;
		this.capacidad = capacidad;
		this.cuarto = cuarto;
		this.reservas = reservas;
	}

	public HotelRoomVO(@JsonProperty(value="banera")Boolean banera,
			@JsonProperty(value="yakuzi")Boolean yakuzi,
			@JsonProperty(value="sala")Boolean sala,
			@JsonProperty(value="cocina")Boolean cocina,
			@JsonProperty(value="cable")Boolean cable,
			@JsonProperty(value="precio")Double precio,
			@JsonProperty(value="hotel")HotelVO hotel,
	        @JsonProperty(value="tipo")String tipo,
	        @JsonProperty(value="capacidad")Integer capacidad,
	        @JsonProperty(value="cuarto")Integer cuarto) {
		
		this.banera = banera;
		this.yakuzi = yakuzi;
		this.sala = sala;
		this.cocina = cocina;
		this.cable = cable;
		this.precio = precio;
		this.hotel = hotel;
		this.tipo = tipo;
		this.capacidad = capacidad;
		this.cuarto = cuarto;
	}

	/**
	 * @return the banera
	 */
	public Boolean getBanera() {
		return banera;
	}

	/**
	 * @param banera the banera to set
	 */
	public void setBanera(Boolean banera) {
		this.banera = banera;
	}

	/**
	 * @return the yakuzi
	 */
	public Boolean getYakuzi() {
		return yakuzi;
	}

	/**
	 * @param yakuzi the yakuzi to set
	 */
	public void setYakuzi(Boolean yakuzi) {
		this.yakuzi = yakuzi;
	}

	/**
	 * @return the sala
	 */
	public Boolean getSala() {
		return sala;
	}

	/**
	 * @param sala the sala to set
	 */
	public void setSala(Boolean sala) {
		this.sala = sala;
	}

	/**
	 * @return the cocina
	 */
	public Boolean getCocina() {
		return cocina;
	}

	/**
	 * @param cocina the cocina to set
	 */
	public void setCocina(Boolean cocina) {
		this.cocina = cocina;
	}

	/**
	 * @return the cable
	 */
	public Boolean getCable() {
		return cable;
	}

	/**
	 * @param cable the cable to set
	 */
	public void setCable(Boolean cable) {
		this.cable = cable;
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
	 * @return the hotel
	 */
	public HotelVO getHotel() {
		return hotel;
	}

	/**
	 * @param hotel the hotel to set
	 */
	public void setHotel(HotelVO hotel) {
		this.hotel = hotel;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	 * @return the reservas
	 */
	public List<ReservaHotelVO> getReservas() {
		return reservas;
	}

	/**
	 * @param reservas the reservas to set
	 */
	public void setReservas(List<ReservaHotelVO> reservas) {
		this.reservas = reservas;
	}
	
	
}
