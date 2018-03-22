/**
 * 
 */
package tm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import dao.DAOReservaHostal;
import vos.ReservaHostalVO;

/**
 * @author camilo
 *
 */
public class TransactionManager {
	//----------------------------------------------------------------------------------------------------------------------------------
	// CONSTANTES
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Constante que contiene el path relativo del archivo que tiene los datos de la conexion
	 */
	private static final String CONNECTION_DATA_FILE_NAME_REMOTE = "/conexion.properties";

	/**
	 * Atributo estatico que contiene el path absoluto del archivo que tiene los datos de la conexion
	 */
	private static String CONNECTION_DATA_PATH;
		
	/**
	 * Constatne que representa el numero maximo de Bebedores que pueden haber en una ciudad
	 */
	private final static Integer CANTIDAD_MAXIMA = 345;

	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
		//----------------------------------------------------------------------------------------------------------------------------------

		/**
		 * Atributo que guarda el usuario que se va a usar para conectarse a la base de datos.
		 */
		private String user;

		/**
		 * Atributo que guarda la clave que se va a usar para conectarse a la base de datos.
		 */
		private String password;

		/**
		 * Atributo que guarda el URL que se va a usar para conectarse a la base de datos.
		 */
		private String url;

		/**
		 * Atributo que guarda el driver que se va a usar para conectarse a la base de datos.
		 */
		private String driver;
		
		/**
		 * Atributo que representa la conexion a la base de datos
		 */
		private Connection conn;

		//----------------------------------------------------------------------------------------------------------------------------------
		// METODOS DE CONEXION E INICIALIZACION
		//----------------------------------------------------------------------------------------------------------------------------------

		/**
		 * <b>Metodo Contructor de la Clase ParranderosTransactionManager</b> <br/>
		 * <b>Postcondicion: </b>	Se crea un objeto  ParranderosTransactionManager,
		 * 						 	Se inicializa el path absoluto del archivo de conexion,
		 * 							Se inicializna los atributos para la conexion con la Base de Datos
		 * @param contextPathP Path absoluto que se encuentra en el servidor del contexto del deploy actual
		 * @throws IOException Se genera una excepcion al tener dificultades con la inicializacion de la conexion<br/>
		 * @throws ClassNotFoundException 
		 */
		public TransactionManager(String contextPathP) {
			
			try {
				CONNECTION_DATA_PATH = contextPathP + CONNECTION_DATA_FILE_NAME_REMOTE;
				initializeConnectionData();
			} 
			catch (ClassNotFoundException e) {			
				e.printStackTrace();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}

		/**
		 * Metodo encargado de inicializar los atributos utilizados para la conexion con la Base de Datos.<br/>
		 * <b>post: </b> Se inicializan los atributos para la conexion<br/>
		 * @throws IOException Se genera una excepcion al no encontrar el archivo o al tener dificultades durante su lectura<br/>
		 * @throws ClassNotFoundException 
		 */
		private void initializeConnectionData() throws IOException, ClassNotFoundException {

			FileInputStream fileInputStream = new FileInputStream(new File(TransactionManager.CONNECTION_DATA_PATH));
			Properties properties = new Properties();
			
			properties.load(fileInputStream);
			fileInputStream.close();
			
			this.url = properties.getProperty("url");
			this.user = properties.getProperty("usuario");
			this.password = properties.getProperty("clave");
			this.driver = properties.getProperty("driver");
			
			//Class.forName(driver);
		}

		/**
		 * Metodo encargado de generar una conexion con la Base de Datos.<br/>
		 * <b>Precondicion: </b>Los atributos para la conexion con la Base de Datos han sido inicializados<br/>
		 * @return Objeto Connection, el cual hace referencia a la conexion a la base de datos
		 * @throws SQLException Cualquier error que se pueda llegar a generar durante la conexion a la base de datos
		 */
		private Connection darConexion() throws SQLException {
			System.out.println("[PARRANDEROS APP] Attempting Connection to: " + url + " - By User: " + user);
			return DriverManager.getConnection(url, user, password);
		}

		
		//----------------------------------------------------------------------------------------------------------------------------------
		// METODOS TRANSACCIONALES
		//----------------------------------------------------------------------------------------------------------------------------------
		
		/**
		 * Metodo que modela la transaccion que retorna todos los reservasHostales de la base de datos. <br/>
		 * @return List<ReservaHostalVO> - Lista de reservasHostales que contiene el resultado de la consulta.
		 * @throws Exception -  Cualquier error que se genere durante la transaccion
		 */
		public List<ReservaHostalVO> getAllReservasHostales() throws Exception {
			DAOReservaHostal daoReservaHostal = new DAOReservaHostal();
			List<ReservaHostalVO> reservasHostales;
			try 
			{
				this.conn = darConexion();
				daoReservaHostal.setConn(conn);
				
				//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
				reservasHostales = daoReservaHostal.getReservasHostales();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoReservaHostal.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return reservasHostales;
		}
		
		/**
		 * Metodo que modela la transaccion que busca el reservaHostal en la base de datos que tiene el ID dado por parametro. <br/>
		 * @param name -id del reservaHostal a buscar. id != null
		 * @return ReservaHostalVO - ReservaHostalVO que se obtiene como resultado de la consulta.
		 * @throws Exception -  cualquier error que se genere durante la transaccion
		 */
		public ReservaHostalVO getReservaHostal(String fechaI, String fechaF,String usuario) throws Exception {
			DAOReservaHostal daoReservaHostal = new DAOReservaHostal();
			ReservaHostalVO reservaHostal = null;
			try 
			{
				this.conn = darConexion();
				daoReservaHostal.setConn(conn);
				reservaHostal = daoReservaHostal.getReservaHostal(fechaI, fechaF, usuario);
				if(reservaHostal == null)
				{
					throw new Exception("El reservaHostal con la fecha inicial para el usuario = " + fechaI + ", " + usuario + " no se encuentra persistido en la base de datos.");				
				}
			} 
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoReservaHostal.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return reservaHostal;
		}
		

		/**
		 * Metodo que modela la transaccion que agrega un reservaHostal a la base de datos. <br/>
		 * <b> post: </b> se ha agregado el reservaHostal que entra como parametro <br/>
		 * @param reservaHostal - el reservaHostal a agregar. reservaHostal != null
		 * @throws Exception - Cualquier error que se genere agregando el reservaHostal
		 */
		public void addReservaHostal(ReservaHostalVO reserva) throws Exception 
		{
			
			DAOReservaHostal daoReservaHostal = new DAOReservaHostal( );
			try
			{
				this.conn = darConexion();
				daoReservaHostal.setConn(conn);
				daoReservaHostal.addReservaHostal(reserva);
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoReservaHostal.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		
		/**
		 * Metodo que modela la transaccion que actualiza en la base de datos al reservaHostal que entra por parametro.<br/>
		 * Solamente se actualiza si existe el reservaHostal en la Base de Datos <br/>
		 * <b> post: </b> se ha actualizado el reservaHostal que entra como parametro <br/>
		 * @param reservaHostal - ReservaHostalVO a actualizar. reservaHostal != null
		 * @throws Exception - Cualquier error que se genere actualizando al reservaHostal.
		 */
		public void updateReservaHostal(ReservaHostalVO reservaHostal) throws Exception 
		{
			DAOReservaHostal daoReservaHostal = new DAOReservaHostal( );
			try
			{
				this.conn = darConexion();
				daoReservaHostal.setConn(conn);
				reservaHostal = daoReservaHostal.getReservaHostal(reservaHostal.getFechaI(),reservaHostal.getFechaF(),reservaHostal.getUsuario().getCedula());
				if(reservaHostal == null)
				{
					throw new Exception("El reservaHostal con el id seleccionado no se encuentra persistido en la base de datos.");				
				}
				else daoReservaHostal.updateReservaHostal(reservaHostal);
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoReservaHostal.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		/**
		 * Metodo que modela la transaccion que elimina de la base de datos al reservaHostal que entra por parametro. <br/>
		 * Solamente se actualiza si existe el reservaHostal en la Base de Datos <br/>
		 * <b> post: </b> se ha eliminado el reservaHostal que entra por parametro <br/>
		 * @param ReservaHostalVO - reservaHostal a eliminar. reservaHostal != null
		 * @throws Exception - Cualquier error que se genere eliminando al reservaHostal.
		 */
		public void deleteReservaHostal(ReservaHostalVO reservaHostal) throws Exception 
		{
			DAOReservaHostal daoReservaHostal = new DAOReservaHostal( );
			try
			{
				this.conn = darConexion();
				daoReservaHostal.setConn( conn );
				reservaHostal = daoReservaHostal.getReservaHostal(reservaHostal.getFechaI(),reservaHostal.getFechaF(),reservaHostal.getUsuario().getCedula());
				if(reservaHostal == null)
				{
					throw new Exception("El reservaHostal con el id seleccionado no se encuentra persistido en la base de datos.");				
				}
				else daoReservaHostal.deleteReservaHostal(reservaHostal);
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoReservaHostal.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}


}
