package vos;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class InmuebleVO extends AlojamientoVO{


	//ATRIBUTOS
	@JsonProperty(value="amoblado")
	private Boolean amoblado;

	@JsonProperty(value="servicios")
	private Boolean servicios;

	@JsonProperty(value="cable")
	private Boolean cable;

	@JsonProperty(value="administracion")
	private Boolean administracion;

	@JsonProperty(value="direccion")
	private String direccion;

	@JsonProperty(value="dueno")
	private ClienteVO dueno;

	public InmuebleVO() {
		super();
	}
	
	/*
	@JsonCreator
	public InmuebleVO(@JsonProperty(value="amoblado") Boolean amoblado, 
			@JsonProperty(value="servicios")Boolean servicios,
			@JsonProperty(value="cable") Boolean cable,
			@JsonProperty(value="administracion") Boolean administracion,
			@JsonProperty(value="precio") Integer precio,
			@JsonProperty(value="direccion") String direccion,
			@JsonProperty(value="dueno") ClienteVO dueno) 
	{
		this.amoblado = amoblado;
		this.servicios = servicios;
		this.cable = cable;
		this.administracion = administracion;
		this.precio = precio;
		this.direccion = direccion;
		this.dueno = dueno;
	}

	public InmuebleVO(@JsonProperty(value="amoblado") Boolean amoblado, 
			@JsonProperty(value="servicios")Boolean servicios,
			@JsonProperty(value="cable") Boolean cable,
			@JsonProperty(value="administracion") Boolean administracion,
			@JsonProperty(value="precio") Integer precio,
			@JsonProperty(value="direccion") String direccion) 
	{
		this.amoblado = amoblado;
		this.servicios = servicios;
		this.cable = cable;
		this.administracion = administracion;
		this.precio = precio;
		this.direccion = direccion;
	}

	*/
	
	//Getters And Setters
	public Boolean getAmoblado() {
		return amoblado;
	}

	public void setAmoblado(Boolean amoblado) {
		this.amoblado = amoblado;
	}

	public Boolean getServicios() {
		return servicios;
	}

	public void setServicios(Boolean servicios) {
		this.servicios = servicios;
	}

	public Boolean getCable() {
		return cable;
	}

	public void setCable(Boolean cable) {
		this.cable = cable;
	}

	public Boolean getAdministracion() {
		return administracion;
	}

	public void setAdministracion(Boolean administracion) {
		this.administracion = administracion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public ClienteVO getDueno() {
		return dueno;
	}

	public void setDueno(ClienteVO dueno) {
		this.dueno = dueno;
	}








}
