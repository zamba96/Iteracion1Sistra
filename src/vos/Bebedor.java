/**-------------------------------------------------------------------
 * ISIS2304 - Sistemas Transaccionales
 * Departamento de Ingenieria de Sistemas
 * Universidad de los Andes
 * Bogota, Colombia
 * 
 * Actividad: Tutorial Parranderos: Arquitectura
 * Autores:
 * 			Santiago Cortes Fernandez	-	s.cortes@uniandes.edu.co
 * 			Juan David Vega Guzman		-	jd.vega11@uniandes.edu.co
 * -------------------------------------------------------------------
 */
package vos;

import org.codehaus.jackson.annotate.*;

/**
 * @author Santiago Cortes Fernandez 	- 	s.cortes@uniandes.edu.co
 * @author Juan David Vega Guzman		-	jd.vega11@uniandes.edu.co
 * Clase que representa a los Bebebores del modelo Parranderos
 */
public class Bebedor {

	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Id del bebedor
	 */
	@JsonProperty(value="id")
	private Long id;

	/**
	 * Nombre del bebedor
	 */
	@JsonProperty(value="nombre")
	private String nombre;

	/**
	 * Presupuesto del bebedor. Puede ser Alto, Medio o Bajo.
	 */
	@JsonProperty(value="presupuesto")
	private String presupuesto;
	
	/**
	 * Ciudad del bebedor
	 */

	@JsonProperty(value="ciudad")
	private String ciudad;
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Metodo constructor de la clase Bebedor
	 * <b>post: </b> Crea el bebedor con los valores que entran por parametro
	 * @param id - Id del bebedor.
	 * @param nombre - Nombre del bebedor.
	 * @param presupuesto - Presupuesto del bebedor.
	 * @param ciudad - Ciudad del bebedor.
	 */
	public Bebedor(@JsonProperty(value="id")Long id,@JsonProperty(value="nombre")String nombre,@JsonProperty(value="presupuesto")String presupuesto,@JsonProperty(value="ciudad")String ciudad) {

		this.id = id;
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.ciudad = ciudad;
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE LA CLASE
	//----------------------------------------------------------------------------------------------------------------------------------
	

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the presupuesto
	 */
	public String getPresupuesto() {
		return presupuesto;
	}

	/**
	 * @param presupuesto the presupuesto to set
	 */
	public void setPresupuesto(String presupuesto) {
		this.presupuesto = presupuesto;
	}

	/**
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * @param ciudad the ciudad to set
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	
	
	
	
}
