/**
 * 
 */
package vos;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author camilo
 *
 */
public class ReservaHostalVO {

	@JsonProperty(value="fechaI")
	private String fechaI;
	
	@JsonProperty(value="fechaF")
	private String fechaF;
	
	@JsonProperty(value="cuarto")
	private HostalRoomVO cuarto;
	
	@JsonProperty(value="usuario")
	private UsuarioVO usuario;
	 
	
	public ReservaHostalVO(@JsonProperty(value="fechaI")String fechaI,
			@JsonProperty(value="fechaF")String fechaF,
			@JsonProperty(value="cuarto")HostalRoomVO cuarto,
			@JsonProperty(value="usuario")UsuarioVO usuario
			) {

		this.fechaI = fechaI;
		this.fechaF = fechaF;
		this.cuarto = cuarto;
		this.usuario = usuario;
	}

	public ReservaHostalVO(@JsonProperty(value="fechaI")String fechaI,
			@JsonProperty(value="fechaF")String fechaF,
			@JsonProperty(value="cuarto")HostalRoomVO cuarto) {
		
		this.fechaI = fechaI;
		this.fechaF = fechaF;
		this.cuarto = cuarto;
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
	public HostalRoomVO getCuarto() {
		return cuarto;
	}

	/**
	 * @param cuarto the cuarto to set
	 */
	public void setCuarto(HostalRoomVO cuarto) {
		this.cuarto = cuarto;
	}

	/**
	 * @return the usuario
	 */
	public UsuarioVO getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}

	
}
