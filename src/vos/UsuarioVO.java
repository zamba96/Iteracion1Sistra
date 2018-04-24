package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class UsuarioVO {

	
	@JsonProperty(value = "cedula")
	private String cedula;
	
	@JsonProperty(value = "nombre")
	private String nombre;
	
	@JsonProperty(value = "fechaNacimiento")
	private String fechaNacimiento;

	public UsuarioVO() {
		
	}
	/*
	public UsuarioVO(@JsonProperty(value = "cedula") String cedula, 
					 @JsonProperty(value = "nombre") String nombre,
					 @JsonProperty(value = "fechaNacimiento") String fechaNacimiento)
	{
		this.cedula = cedula;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
	}
	*/
	
	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
}
