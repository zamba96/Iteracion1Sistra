package vos;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author camilo
 *
 */
public class ContratoViviendaVO {

	@JsonProperty(value = "id")
	private Long id;
	
	@JsonProperty(value="fechaI")
	private String fechaI;
	
	@JsonProperty(value="fechaF")
	private String fechaF;
	
	@JsonProperty(value="cuarto")
	private Long cuarto;
	
	@JsonProperty(value="relacionado")
	private Long relacionado;
	
	/*
	public ContratoViviendaVO(@JsonProperty(value="fechaI")String fechaI,
			@JsonProperty(value="fechaF")String fechaF,
			@JsonProperty(value="cuarto")Long cuarto,
			@JsonProperty(value="usuario")Long relacionado
			) {
		this.fechaI = fechaI;
		this.fechaF = fechaF;
		this.cuarto = cuarto;
		this.relacionado = relacionado;
	}

	public ContratoViviendaVO(
			@JsonProperty(value="fechaI")String fechaI,
			@JsonProperty(value="fechaF")String fechaF,
			@JsonProperty(value="cuarto")Long cuarto) {
		
		this.fechaI = fechaI;
		this.fechaF = fechaF;
		this.cuarto = cuarto;
	}
	*/
	
	public ContratoViviendaVO(){
		
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
	public Long getCuarto() {
		return cuarto;
	}

	/**
	 * @param cuarto the cuarto to set
	 */
	public void setCuarto(Long cuarto) {
		this.cuarto = cuarto;
	}

	/**
	 * @return the usuario
	 */
	public Long getUsuario() {
		return relacionado;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Long relacionado) {
		this.relacionado = relacionado;
	}
	
	
}
