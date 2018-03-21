package vos;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author camilo
 *
 */
public class ContratoViviendaVO {

	@JsonProperty(value="fechaI")
	private String fechaI;
	
	@JsonProperty(value="fechaF")
	private String fechaF;
	
	@JsonProperty(value="cuarto")
	private ViviendaRoomVO cuarto;
	
	/*
	 * @JsonProperty(value="usuario")
	private UsuarioVO usuario;
	 */
	
	public ContratoViviendaVO(@JsonProperty(value="fechaI")String fechaI,
			@JsonProperty(value="fechaF")String fechaF,
			@JsonProperty(value="cuarto")ViviendaRoomVO cuarto
			//,@JsonProperty(value="usuario")Usuario usuario
			) {

		this.fechaI = fechaI;
		this.fechaF = fechaF;
		this.cuarto = cuarto;
		//this.usuario = usuario;
	}

	/**
	 * @return the fechaI
	 */
	public String getFechaI() {
		return fechaI;
	}

	/**
	 * @param fechaI the fechaI to set
	 */
	public void setFechaI(String fechaI) {
		this.fechaI = fechaI;
	}

	/**
	 * @return the fechaF
	 */
	public String getFechaF() {
		return fechaF;
	}

	/**
	 * @param fechaF the fechaF to set
	 */
	public void setFechaF(String fechaF) {
		this.fechaF = fechaF;
	}

	/**
	 * @return the cuarto
	 */
	public ViviendaRoomVO getCuarto() {
		return cuarto;
	}

	/**
	 * @param cuarto the cuarto to set
	 */
	public void setCuarto(ViviendaRoomVO cuarto) {
		this.cuarto = cuarto;
	}
	
	
}
