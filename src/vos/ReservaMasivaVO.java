package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ReservaMasivaVO {

	//sadjsdnkas
	@JsonProperty(value = "id")
	public Long id;
	
	@JsonProperty(value = "cantidad")
	public Integer cantidad;
	
	@JsonProperty(value = "descripcion")
	public String descripcion;

	@JsonProperty(value = "fechaInicio")
	public String fechaInicio;
	
	@JsonProperty(value = "fechaFin")
	public String fechaFin;
	
	@JsonProperty(value = "tipo")
	public String tipo;
	
	public ReservaMasivaVO() {
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the cantidad
	 */
	public Integer getCantidad() {
		return cantidad;
	}

	/**
	 * @return the descrpcion
	 */
	public String getDescrpcion() {
		return descripcion;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @param descrpcion the descrpcion to set
	 */
	public void setDescrpcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	
}
