package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class RelMasivasVO {

	@JsonProperty(value = "id")
	public Integer id;
	
	@JsonProperty(value = "IdReservaHotel")
    public Integer IdReservaHotel;
    
	@JsonProperty(value = "IdReservaHostal")
    public Integer IdReservaHostal;
    
	@JsonProperty(value = "IdContratoInmueble")
    public Integer IdContratoInmueble;
    
	@JsonProperty(value = "IdContratoResidencia")
    public Integer IdContratoResidencia;
    
	@JsonProperty(value = "IdContratoVecino")
    public Integer IdContratoVecino;
	
	public RelMasivasVO() {
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return the idReservaHotel
	 */
	public Integer getIdReservaHotel() {
		return IdReservaHotel;
	}

	/**
	 * @return the idReservaHostal
	 */
	public Integer getIdReservaHostal() {
		return IdReservaHostal;
	}

	/**
	 * @return the idContratoInmueble
	 */
	public Integer getIdContratoInmueble() {
		return IdContratoInmueble;
	}

	/**
	 * @return the idContratoResidencia
	 */
	public Integer getIdContratoResidencia() {
		return IdContratoResidencia;
	}

	/**
	 * @return the idContratoVecino
	 */
	public Integer getIdContratoVecino() {
		return IdContratoVecino;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @param idReservaHotel the idReservaHotel to set
	 */
	public void setIdReservaHotel(Integer idReservaHotel) {
		IdReservaHotel = idReservaHotel;
	}

	/**
	 * @param idReservaHostal the idReservaHostal to set
	 */
	public void setIdReservaHostal(Integer idReservaHostal) {
		IdReservaHostal = idReservaHostal;
	}

	/**
	 * @param idContratoInmueble the idContratoInmueble to set
	 */
	public void setIdContratoInmueble(Integer idContratoInmueble) {
		IdContratoInmueble = idContratoInmueble;
	}

	/**
	 * @param idContratoResidencia the idContratoResidencia to set
	 */
	public void setIdContratoResidencia(Integer idContratoResidencia) {
		IdContratoResidencia = idContratoResidencia;
	}

	/**
	 * @param idContratoVecino the idContratoVecino to set
	 */
	public void setIdContratoVecino(Integer idContratoVecino) {
		IdContratoVecino = idContratoVecino;
	}
	
	
}
