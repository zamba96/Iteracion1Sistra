package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class PersonaVO {

	
	@JsonProperty(value = "cedula")
	private String cedula;
	
	@JsonProperty(value = "nombre")
	private String nombre;
	
	@JsonProperty(value = "fechaNacimiento")
	private String fechaNacimiento;

	public PersonaVO() {
		
	}
	
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
