package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class VecinoRoomVO extends AlojamientoVO{

	@JsonProperty(value="habiataciones")
	private Integer habitaciones;
	
	@JsonProperty(value="banos")
	private Integer banos;

	
	@JsonProperty(value="menaje")
	private String menaje;
	
	@JsonProperty(value="direccion")
	private String direccion;
	
	@JsonProperty(value="dueno")
	private VecinoVO dueno;
	/*
	public VecinoRoomVO(@JsonProperty(value="habiataciones") Integer habitaciones, 
			@JsonProperty(value="banos") Integer banos,
			@JsonProperty(value="precio") Integer precio, 
			@JsonProperty(value="menaje") String menaje, 
			@JsonProperty(value="direccion") String direccion, 
			@JsonProperty(value="dueno") VecinoVO dueno)
	{
		this.habitaciones = habitaciones;
		this.banos = banos;
		this.precio = precio;
		this.menaje = menaje;
		this.direccion = direccion;
		this.dueno = dueno;
	}
	
	public VecinoRoomVO(@JsonProperty(value="habiataciones") Integer habitaciones, 
			@JsonProperty(value="banos") Integer banos,
			@JsonProperty(value="precio") Integer precio, 
			@JsonProperty(value="menaje") String menaje, 
			@JsonProperty(value="direccion") String direccion)
	{
		this.habitaciones = habitaciones;
		this.banos = banos;
		this.precio = precio;
		this.menaje = menaje;
		this.direccion = direccion;
	}*/

	public VecinoRoomVO(){
		super();
	}
	
	public Integer getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(Integer habitaciones) {
		this.habitaciones = habitaciones;
	}

	public Integer getBanos() {
		return banos;
	}

	public void setBanos(Integer banos) {
		this.banos = banos;
	}

	public String getMenaje() {
		return menaje;
	}

	public void setMenaje(String menaje) {
		this.menaje = menaje;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public VecinoVO getDueno() {
		return dueno;
	}

	public void setDueno(VecinoVO dueno) {
		this.dueno = dueno;
	}
	
}
