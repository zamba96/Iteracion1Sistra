package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.TransactionManager;
import vos.ContratoVecinoVO;
import vos.VecinoRoomVO;

@Path("vcontratosVecinos")
public class ContratoVecinoService {

	//----------------------------------------------------------------------------------------------------------------------------------
		// ATRIBUTOS
		//----------------------------------------------------------------------------------------------------------------------------------

		/**
		 * Atributo que usa la anotacion @Context para tener el ServletContext de la conexion actual.
		 */
		@Context
		private ServletContext context;

		//----------------------------------------------------------------------------------------------------------------------------------
		// METODOS DE INICIALIZACION
		//----------------------------------------------------------------------------------------------------------------------------------
		/**
		 * Metodo que retorna el path de la carpeta WEB-INF/ConnectionData en el deploy actual dentro del servidor.
		 * @return path de la carpeta WEB-INF/ConnectionData en el deploy actual.
		 */
		private String getPath() {
			return context.getRealPath("WEB-INF/ConnectionData");
		}


		private String doErrorMessage(Exception e){
			return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
		}

		//----------------------------------------------------------------------------------------------------------------------------------
		// METODOS REST
		//----------------------------------------------------------------------------------------------------------------------------------

		/**
		 * Metodo GET que trae a todos los inmuebles en la Base de datos. <br/>
		 * <b>Precondicion: </b> el archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
		 * <b>URL: </b> http://localhost:8080/TutorialParranderos/rest/inmuebles <br/>
		 * @return	<b>Response Status 200</b> - JSON que contiene a todos los inmuebles que estan en la Base de Datos <br/>
		 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
		 */			
		@GET
		@Produces({ MediaType.APPLICATION_JSON })
		public Response getContratosVecinos() {

			try {
				TransactionManager tm = new TransactionManager(getPath());

				List<ContratoVecinoVO> inmuebles;
				//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
				inmuebles = tm.getContratosVecinos();
				return Response.status(200).entity(inmuebles).build();
			} 
			catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
		}

		/**
		 * Metodo GET que trae al contrato en la Base de Datos con el ID dado por parametro <br/>
		 * <b>Precondicion: </b> el archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
		 * <b>URL: </b> http://localhost:8080/TutorialParranderos/rest/inmuebles/{id} <br/>
		 * @return	<b>Response Status 200</b> - JSON ContratoVecinoVO que contiene al contrato cuyo ID corresponda al parametro <br/>
		 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
		 */
		@GET
		@Path( "{fechaI}/{fechaF}/{usuario}" )
		@Produces( { MediaType.APPLICATION_JSON } )
		public Response getBebedorById( @PathParam( "fechaI" ) String fechaI, 
				@PathParam( "fechaF" ) String fechaF,@PathParam( "usuario" ) String usuario)
		{
			try{
				TransactionManager tm = new TransactionManager( getPath( ) );

				ContratoVecinoVO contrato = tm.getContratoVecino(fechaI, fechaF, usuario);
				return Response.status( 200 ).entity( contrato ).build( );			
			}
			catch( Exception e )
			{
				return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
			}
		}


		/**
		 * Metodo que recibe un contrato en formato JSON y lo agrega a la Base de Datos <br/>
		 * <b>Precondicion: </b> El archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
		 * <b>Postcondicion: </b> Se agrega a la Base de datos la informacion correspondiente al contrato. <br/>
		 * <b>URL: </b> http://localhost:8080/TutorialParranderos/rest/inmuebles <br/>
		 * @param contrato JSON con la informacion del contrato que se desea agregar
		 * @return	<b>Response Status 200</b> - JSON que contiene al contrato que ha sido agregado <br/>
		 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
		 */

		@POST
		@Consumes({ MediaType.APPLICATION_JSON })
		@Produces({MediaType.APPLICATION_JSON})
		public Response addContratoVecino(ContratoVecinoVO contrato) {
			try {
				TransactionManager tm = new TransactionManager(getPath());
				tm.addContratoVecino(contrato);
				return Response.status( 200 ).entity( contrato ).build( );			
			} catch (Exception e) {
				return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
			}
		}	

		/**
		 * Metodo que recibe un contrato en formato JSON y lo agrega a la Base de Datos <br/>
		 * <b>Precondicion: </b> El archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
		 * <b>Postcondicion: </b> Se actualiza la Base de datos con la informacion correspondiente al contrato.<br/>
		 * @param contrato JSON con la informacion del contrato que se desea agregar
		 * @return	<b>Response Status 200</b> - JSON que contiene al contrato que se desea modificar <br/>
		 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
		 */


		@PUT
		@Consumes({ MediaType.APPLICATION_JSON })
		@Produces({MediaType.APPLICATION_JSON})
		public Response updateBebedor(ContratoVecinoVO contrato) {
			try {
				TransactionManager tm = new TransactionManager(getPath());
				//tm.updateContratoVecino(contrato);
				return Response.status( 200 ).entity( contrato ).build( );
			} catch (Exception e) {
				return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
			}
		}

		/**
		 * Metodo que recibe un contrato en formato JSON y lo elimina de la Base de Datos <br/>
		 * <b>Precondicion: </b> El archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
		 * <b>Postcondicion: </b> Se elimina de la Base de datos al contrato con la informacion correspondiente.<br/>
		 * <b>URL: </b> http://localhost:8080/TutorialParranderos/rest/inmuebles <br/>
		 * @param contrato JSON con la informacion del contrato que se desea eliminar
		 * @return	<b>Response Status 200</b> - JSON que contiene al contrato que se desea eliminar <br/>
		 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
		 */
		@DELETE
		@Consumes({ MediaType.APPLICATION_JSON })
		@Produces({MediaType.APPLICATION_JSON})
		public Response deleteBebedor(ContratoVecinoVO contrato) {
			try {
				TransactionManager tm = new TransactionManager(getPath());
				tm.deleteContratoVecino(contrato);
				return Response.status( 200 ).entity( contrato ).build( );
			} catch (Exception e) {
				return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
			}
		}
	
}
