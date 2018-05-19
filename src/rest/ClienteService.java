package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.TransactionManager;
import vos.ClienteVO;
import vos.FechasVO;

@Path("clientes")
public class ClienteService {


	// ----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	// ----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Atributo que usa la anotacion @Context para tener el ServletContext de la
	 * conexion actual.
	 */
	@Context
	private ServletContext context;

	// ----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE INICIALIZACION
	// ----------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Metodo que retorna el path de la carpeta WEB-INF/ConnectionData en el
	 * deploy actual dentro del servidor.
	 * 
	 * @return path de la carpeta WEB-INF/ConnectionData en el deploy actual.
	 */
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}

	private String doErrorMessage(Exception e) {
		return "{ \"ERROR\": \"" + e.getMessage() + "\"}";
	}

	// ----------------------------------------------------------------------------------------------------------------------------------
	// METODOS REST
	// ----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Metodo GET que trae a todos los bebedores en la Base de datos. <br/>
	 * <b>Precondicion: </b> el archivo <em>'conectionData'</em> ha sido
	 * inicializado con las credenciales del usuario <br/>
	 * <b>URL: </b> http://localhost:8080/TutorialParranderos/rest/bebedores
	 * <br/>
	 * 
	 * @return <b>Response Status 200</b> - JSON que contiene a todos los
	 *         bebedores que estan en la Base de Datos <br/>
	 *         <b>Response Status 500</b> - Excepcion durante el transcurso de
	 *         la transaccion
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/Consumo")
	public Response getConsumoClientes(FechasVO fechas) {

		try {
			TransactionManager tm = new TransactionManager(getPath());

			List<ClienteVO> bebedores;
			// Por simplicidad, solamente se obtienen los primeros 50 resultados
			// de la consulta
			bebedores = tm.getConsumoClientes(fechas);
			return Response.status(200).entity(bebedores).build();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	
	/**
	 * Metodo GET que trae a todos los bebedores en la Base de datos. <br/>
	 * <b>Precondicion: </b> el archivo <em>'conectionData'</em> ha sido
	 * inicializado con las credenciales del usuario <br/>
	 * <b>URL: </b> http://localhost:8080/TutorialParranderos/rest/bebedores
	 * <br/>
	 * 
	 * @return <b>Response Status 200</b> - JSON que contiene a todos los
	 *         bebedores que estan en la Base de Datos <br/>
	 *         <b>Response Status 500</b> - Excepcion durante el transcurso de
	 *         la transaccion
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/NoConsumo")
	public Response getConsumoClientesNo(FechasVO fechas) {

		try {
			TransactionManager tm = new TransactionManager(getPath());

			List<ClienteVO> bebedores;
			// Por simplicidad, solamente se obtienen los primeros 50 resultados
			// de la consulta
			bebedores = tm.getConsumoClientesNo(fechas);
			return Response.status(200).entity(bebedores).build();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
}
