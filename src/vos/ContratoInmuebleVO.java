package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ContratoInmuebleVO {

	
	@JsonProperty(value = "fechaInicio")
	private String fechaInicio;

	@JsonProperty(value = "fechaFinal")
	private String fechaFinal;

	@JsonProperty(value = "usuario")
	private UsuarioVO usuario;
	
	@JsonProperty(value = "inmueble")
	private InmuebleVO inmueble;

	public InmuebleVO getInmueble() {
		return inmueble;
	}

	public void setInmueble(InmuebleVO inmueble) {
		this.inmueble = inmueble;
	}

	public ContratoInmuebleVO(@JsonProperty(value = "fechaInicio")String fechaInicio,
			@JsonProperty(value = "fechaFinal") String fechaFinal,
			@JsonProperty(value = "usuario") UsuarioVO usuario,
			@JsonProperty(value = "inmueble") InmuebleVO inmueble)
	{
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.usuario = usuario;
		this.inmueble = inmueble;
	}

	public ContratoInmuebleVO(@JsonProperty(value = "fechaInicio")String fechaInicio,
			@JsonProperty(value = "fechaFinal") String fechaFinal)
	{
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
	}

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
