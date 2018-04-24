package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ContratoInmuebleVO {


	@JsonProperty(value = "IDINMUEBLE")
	private Long IdInmueble;

	@JsonProperty(value = "Id")
	private Long Id;

	@JsonProperty(value = "precioTotal")
	private Long precioTotal;

	@JsonProperty(value = "fechaInicio")
	private String fechaInicio;

	@JsonProperty(value = "fechaFinal")
	private String fechaFinal;

	@JsonProperty(value = "usuario")
	private UsuarioVO usuario;

	@JsonProperty(value = "inmueble")
	private InmuebleVO inmueble;

	/**
	 * JSON EXAMPLE:
	 * {
	 * 		"usrCedula":"cedulaUsuario",
	 * 		"ownerCedula":"cedulaDueno",
	 * 		"direccion":"direccionInmuele",
	 * 		"fechaInicio":"fechaInicioContrato",
	 * 		"fechaFinal":"fechaFinContrato",
	 * 		"precioTotal":"precio"
	 * 		
	 */
	
	@JsonProperty(value = "usrCedula")
	private String usrCedula;
	
	public Long getId() {
		return Id;
	}

	public void setId(Long Id) {
		this.Id = Id;
	}

	public Long getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(Long precioTotal) {
		this.precioTotal = precioTotal;
	}

	public String getUsrCedula() {
		return usrCedula;
	}

	public void setUsrCedula(String usrCedula) {
		this.usrCedula = usrCedula;
	}

	public Long getIdInmueble() {
		return IdInmueble;
	}

	public void setIdInmueble(Long idInmueble) {
		IdInmueble = idInmueble;
	}

	public InmuebleVO getInmueble() {
		return inmueble;
	}

	public void setInmueble(InmuebleVO inmueble) {
		this.inmueble = inmueble;
	}
	
	public ContratoInmuebleVO() {
		
	}
	/*
	public ContratoInmuebleVO(@JsonProperty(value = "fechaInicio")String fechaInicio,
			@JsonProperty(value = "fechaFinal") String fechaFinal,
			@JsonProperty(value = "usuario") UsuarioVO usuario,
			@JsonProperty(value = "inmueble") InmuebleVO inmueble,
			@JsonProperty(value = "Id") Long Id,
			@JsonProperty(value = "precioTotal") Long precioTotal)
	{
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.usuario = usuario;
		this.inmueble = inmueble;
		this.Id = Id;
		this.precioTotal = precioTotal;
	}
	*/
	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public UsuarioVO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}





}
