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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import dao.DAOHotelRoom;
import dao.DAOInmueble;
import dao.DAOReserva;
import dao.DAOReservaMasiva;
import dao.DAOCliente;
import dao.DAOVecinoRoom;
import vos.HotelRoomVO;
import vos.ReservaVO;
import vos.ReservaMasivaVO;
import vos.VecinoRoomVO;

/**
 * @author camilo
 *
 */
public class TransactionManager {
	// ----------------------------------------------------------------------------------------------------------------------------------
	// CONSTANTES
	// ----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Constante que contiene el path relativo del archivo que tiene los datos
	 * de la conexion
	 */
	private static final String CONNECTION_DATA_FILE_NAME_REMOTE = "/conexion.properties";

	/**
	 * Atributo estatico que contiene el path absoluto del archivo que tiene los
	 * datos de la conexion
	 */
	private static String CONNECTION_DATA_PATH;

	/**
	 * Constatne que representa el numero maximo de Bebedores que pueden haber
	 * en una ciudad
	 */
	private final static Integer CANTIDAD_MAXIMA = 345;

	// ----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	// ----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Atributo que guarda el usuario que se va a usar para conectarse a la base
	 * de datos.
	 */
	private String user;

	/**
	 * Atributo que guarda la clave que se va a usar para conectarse a la base
	 * de datos.
	 */
	private String password;

	/**
	 * Atributo que guarda el URL que se va a usar para conectarse a la base de
	 * datos.
	 */
	private String url;

	/**
	 * Atributo que guarda el driver que se va a usar para conectarse a la base
	 * de datos.
	 */
	private String driver;

	/**
	 * Atributo que representa la conexion a la base de datos
	 */
	private Connection conn;

	// ----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE CONEXION E INICIALIZACION
	// ----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * <b>Metodo Contructor de la Clase ParranderosTransactionManager</b> <br/>
	 * <b>Postcondicion: </b> Se crea un objeto ParranderosTransactionManager,
	 * Se inicializa el path absoluto del archivo de conexion, Se inicializna
	 * los atributos para la conexion con la Base de Datos
	 * 
	 * @param contextPathP
	 *            Path absoluto que se encuentra en el servidor del contexto del
	 *            deploy actual
	 * @throws IOException
	 *             Se genera una excepcion al tener dificultades con la
	 *             inicializacion de la conexion<br/>
	 * @throws ClassNotFoundException
	 */
	public TransactionManager(String contextPathP) {

		try {
			CONNECTION_DATA_PATH = contextPathP + CONNECTION_DATA_FILE_NAME_REMOTE;
			initializeConnectionData();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo encargado de inicializar los atributos utilizados para la conexion
	 * con la Base de Datos.<br/>
	 * <b>post: </b> Se inicializan los atributos para la conexion<br/>
	 * 
	 * @throws IOException
	 *             Se genera una excepcion al no encontrar el archivo o al tener
	 *             dificultades durante su lectura<br/>
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

		// Class.forName(driver);
	}

	/**
	 * Metodo encargado de generar una conexion con la Base de Datos.<br/>
	 * <b>Precondicion: </b>Los atributos para la conexion con la Base de Datos
	 * han sido inicializados<br/>
	 * 
	 * @return Objeto Connection, el cual hace referencia a la conexion a la
	 *         base de datos
	 * @throws SQLException
	 *             Cualquier error que se pueda llegar a generar durante la
	 *             conexion a la base de datos
	 */
	private Connection darConexion() throws SQLException {
		System.out.println("[PARRANDEROS APP] Attempting Connection to: " + url + " - By User: " + user);
		return DriverManager.getConnection(url, user, password);
	}

	// ----------------------------------------------------------------------------------------------------------------------------------
	// METODOS TRANSACCIONALES
	// ----------------------------------------------------------------------------------------------------------------------------------

	// REQ RESERVA MASIVA
	/**
	 * 
	 */
	public List<ReservaVO> hacerReservaHotelMasiva(int cantidad, Date inicio, Date fin) {
		ArrayList<ReservaVO> list = new ArrayList<>();
		DAOHotelRoom daoHR = new DAOHotelRoom();
		daoHR.setConn(conn);

		try {
			ArrayList<HotelRoomVO> rooms = daoHR.getHotelRooms();
			for (HotelRoomVO room : rooms) {

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * Metodo que modela la transaccion que retorna todos los reservases
	 * de la base de datos. <br/>
	 * 
	 * @return List<ReservaVO> - Lista de reservases que contiene el
	 *         resultado de la consulta.
	 * @throws Exception
	 *             - Cualquier error que se genere durante la transaccion
	 */
	public List<ReservaVO> getReservases() throws Exception {
		DAOReserva daoReserva = new DAOReserva();
		List<ReservaVO> reservases;
		try {
			this.conn = darConexion();
			daoReserva.setConn(conn);

			// Por simplicidad, solamente se obtienen los primeros 50 resultados
			// de la consulta
			reservases = daoReserva.getReservases();
		} catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				daoReserva.cerrarRecursos();
				if (this.conn != null) {
					this.conn.close();
				}
			} catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return reservases;
	}

	/**
	 * Metodo que modela la transaccion que busca el reserva en la base de
	 * datos que tiene el ID dado por parametro. <br/>
	 * 
	 * @param name
	 *            -id del reserva a buscar. id != null
	 * @return ReservaVO - ReservaVO que se obtiene como resultado
	 *         de la consulta.
	 * @throws Exception
	 *             - cualquier error que se genere durante la transaccion
	 */
	public ReservaVO getReserva(Long id) throws Exception {
		DAOReserva daoReserva = new DAOReserva();
		ReservaVO reserva = null;
		try {
			this.conn = darConexion();
			daoReserva.setConn(conn);
			reserva = daoReserva.getReserva(id);
			if (reserva == null) {
				throw new Exception(
						"El reserva con id = " + id + " no se encuentra persistido en la base de datos.");
			}
		} catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				daoReserva.cerrarRecursos();
				if (this.conn != null) {
					this.conn.close();
				}
			} catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return reserva;
	}

	/**
	 * Metodo que modela la transaccion que agrega un reserva a la base de
	 * datos. <br/>
	 * <b> post: </b> se ha agregado el reserva que entra como parametro
	 * <br/>
	 * 
	 * @param reserva
	 *            - el reserva a agregar. reserva != null
	 * @throws Exception
	 *             - Cualquier error que se genere agregando el reserva
	 */
	public void addReserva(ReservaVO reserva) throws Exception {
		System.out.println(reserva);
		DAOReserva daoReserva = new DAOReserva();
		try {
			this.conn = darConexion();
			daoReserva.setConn(conn);
			daoReserva.addReserva(reserva);
		} catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				daoReserva.cerrarRecursos();
				if (this.conn != null) {
					this.conn.close();
				}
			} catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * Metodo que modela la transaccion que actualiza en la base de datos al
	 * reserva que entra por parametro.<br/>
	 * Solamente se actualiza si existe el reserva en la Base de Datos
	 * <br/>
	 * <b> post: </b> se ha actualizado el reserva que entra como
	 * parametro <br/>
	 * 
	 * @param reserva
	 *            - ReservaVO a actualizar. reserva != null
	 * @throws Exception
	 *             - Cualquier error que se genere actualizando al
	 *             reserva.
	 */
	public void updateReserva(ReservaVO reserva) throws Exception {
		DAOReserva daoReserva = new DAOReserva();
		try {
			this.conn = darConexion();
			daoReserva.setConn(conn);
			reserva = daoReserva.getReserva(reserva.getId());
			if (reserva == null) {
				throw new Exception(
						"El reserva con el id seleccionado no se encuentra persistido en la base de datos.");
			} else
				daoReserva.updateReserva(reserva);
		} catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				daoReserva.cerrarRecursos();
				if (this.conn != null) {
					this.conn.close();
				}
			} catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * Metodo que modela la transaccion que elimina de la base de datos al
	 * reserva que entra por parametro. <br/>
	 * Solamente se actualiza si existe el reserva en la Base de Datos
	 * <br/>
	 * <b> post: </b> se ha eliminado el reserva que entra por parametro
	 * <br/>
	 * 
	 * @param ReservaVO
	 *            - reserva a eliminar. reserva != null
	 * @throws Exception
	 *             - Cualquier error que se genere eliminando al reserva.
	 */
	public void deleteReserva(ReservaVO reserva) throws Exception {
		DAOReserva daoReserva = new DAOReserva();
		try {
			this.conn = darConexion();
			daoReserva.setConn(conn);
			reserva = daoReserva.getReserva(reserva.getId());
			if (reserva == null) {
				throw new Exception(
						"El reserva con el id seleccionado no se encuentra persistido en la base de datos.");
			} else
				daoReserva.deleteReserva(reserva);
		} catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				daoReserva.cerrarRecursos();
				if (this.conn != null) {
					this.conn.close();
				}
			} catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	

	public List<ReservaMasivaVO> getReservasMasivas() throws Exception {
		DAOReservaMasiva daoReservaMasiva = new DAOReservaMasiva();
		List<ReservaMasivaVO> ReservaMasiva;
		try {
			this.conn = darConexion();
			daoReservaMasiva.setConn(conn);

			// Por simplicidad, solamente se obtienen los primeros 50 resultados
			// de la consulta
			ReservaMasiva = daoReservaMasiva.getReservasMasivas();
		} catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				daoReservaMasiva.cerrarRecursos();
				if (this.conn != null) {
					this.conn.close();
				}
			} catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return ReservaMasiva;
	}

	public ReservaMasivaVO getReservaMasiva(Long id) throws Exception {
		DAOReservaMasiva daoReservaMasiva = new DAOReservaMasiva();
		ReservaMasivaVO ReservaMasiva = null;
		try {
			this.conn = darConexion();
			daoReservaMasiva.setConn(conn);
			ReservaMasiva = daoReservaMasiva.getReservaMasiva(id);
			if (ReservaMasiva == null) {
				throw new Exception("El reserva con la fecha inicial para el usuario = " + id
						+ " no se encuentra persistido en la base de datos.");
			}
		} catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				daoReservaMasiva.cerrarRecursos();
				if (this.conn != null) {
					this.conn.close();
				}
			} catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return ReservaMasiva;
	}

	public void addReservaMasiva(ReservaMasivaVO contrato) throws Exception {

		DAOReservaMasiva daoReservaMasiva = new DAOReservaMasiva();
		try {
			this.conn = darConexion();
			daoReservaMasiva.setConn(conn);
			daoReservaMasiva.addReservaMasiva(contrato);
		} catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				daoReservaMasiva.cerrarRecursos();
				if (this.conn != null) {
					this.conn.close();
				}
			} catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}

	}

	public void deleteReservaMasiva(ReservaMasivaVO ReservaMasiva) throws Exception {
		DAOReservaMasiva daoReservaMasiva = new DAOReservaMasiva();
		try {
			this.conn = darConexion();
			daoReservaMasiva.setConn(conn);
			ReservaMasiva = daoReservaMasiva.getReservaMasiva(ReservaMasiva.getId());
			if (ReservaMasiva == null) {
				throw new Exception(
						"El reserva con el id seleccionado no se encuentra persistido en la base de datos.");
			} else
				daoReservaMasiva.deleteReservaMasiva(ReservaMasiva);
		} catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				daoReservaMasiva.cerrarRecursos();
				if (this.conn != null) {
					this.conn.close();
				}
			} catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}

	}
	
	

}
