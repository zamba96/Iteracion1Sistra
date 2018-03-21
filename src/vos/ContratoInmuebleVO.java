package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ContratoInmuebleVO {

	@JsonProperty(value = "fechaInicio")
	private String fechaInicio;

	@JsonProperty(value = "fechaFinal")
	private String fechaFinal;

	@JsonProperty(value = "usuario")
	private UsuarioVO usuario;

	public ContratoInmuebleVO(@JsonProperty(value = "fechaInicio")String fechaInicio,
			@JsonProperty(value = "fechaFinal") String fechaFinal,
			@JsonProperty(value = "usuario") UsuarioVO usuario)
	{
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.usuario = usuario;
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
