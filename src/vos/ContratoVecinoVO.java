package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ContratoVecinoVO {

	@JsonProperty(value="fechaInicio")
	private String fechaInicio;
	
	@JsonProperty(value="fechaFin")
	private String fechaFin;
	
	@JsonProperty(value="fechaFin")
	private UsuarioVO usuario;
	
	@JsonProperty(value="room")
	private VecinoRoomVO room;
	
	/*
	public ContratoVecinoVO(@JsonProperty(value="fechaInicio") String fechaInicio,
			@JsonProperty(value="fechaFin") String fechaFin, 
			@JsonProperty(value="fechaFin") UsuarioVO usuario,
			@JsonProperty(value="room") VecinoRoomVO room)
	{
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.usuario = usuario;
		this.room = room;
	}
	
	public ContratoVecinoVO(@JsonProperty(value="fechaInicio") String fechaInicio,
			@JsonProperty(value="fechaFin") String fechaFin)
	{
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
	*/
	public ContratoVecinoVO() {
		
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public UsuarioVO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}

	public VecinoRoomVO getRoom() {
		return room;
	}

	public void setRoom(VecinoRoomVO room) {
		this.room = room;
	}
	
	
}
