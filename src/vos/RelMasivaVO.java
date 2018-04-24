package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class RelMasivaVO {

	@JsonProperty(value = "id")
	public Long id;
	
	@JsonProperty(value = "IdReservaHotel")
    public Long IdReservaHotel;
    
	@JsonProperty(value = "IdReservaHostal")
    public Long IdReservaHostal;
    
	@JsonProperty(value = "IdContratoInmueble")
    public Long IdContratoInmueble;
    
	@JsonProperty(value = "IdContratoResidencia")
    public Long IdContratoResidencia;
    
	@JsonProperty(value = "IdContratoVecino")
    public Long IdContratoVecino;
	
	public RelMasivaVO() {
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the idReservaHotel
	 */
	public Long getIdReservaHotel() {
		return IdReservaHotel;
	}

	/**
	 * @return the idReservaHostal
	 */
	public Long getIdReservaHostal() {
		return IdReservaHostal;
	}

	/**
	 * @return the idContratoInmueble
	 */
	public Long getIdContratoInmueble() {
		return IdContratoInmueble;
	}

	/**
	 * @return the idContratoResidencia
	 */
	public Long getIdContratoResidencia() {
		return IdContratoResidencia;
	}

	/**
	 * @return the idContratoVecino
	 */
	public Long getIdContratoVecino() {
		return IdContratoVecino;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param idReservaHotel the idReservaHotel to set
	 */
	public void setIdReservaHotel(Long idReservaHotel) {
		IdReservaHotel = idReservaHotel;
	}

	/**
	 * @param idReservaHostal the idReservaHostal to set
	 */
	public void setIdReservaHostal(Long idReservaHostal) {
		IdReservaHostal = idReservaHostal;
	}

	/**
	 * @param idContratoInmueble the idContratoInmueble to set
	 */
	public void setIdContratoInmueble(Long idContratoInmueble) {
		IdContratoInmueble = idContratoInmueble;
	}

	/**
	 * @param idContratoResidencia the idContratoResidencia to set
	 */
	public void setIdContratoResidencia(Long idContratoResidencia) {
		IdContratoResidencia = idContratoResidencia;
	}

	/**
	 * @param idContratoVecino the idContratoVecino to set
	 */
	public void setIdContratoVecino(Long idContratoVecino) {
		IdContratoVecino = idContratoVecino;
	}
	
	
}
