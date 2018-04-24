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

import dao.DAOContratoInmueble;
import dao.DAOContratoVecino;
import dao.DAOContratoVivienda;
import dao.DAOHotelRoom;
import dao.DAOInmueble;
import dao.DAOReservaHostal;
import dao.DAOReservaHotel;
import dao.DAOReservaMasiva;
import dao.DAOUsuario;
import dao.DAOVecinoRoom;
import vos.ContratoInmuebleVO;
import vos.ContratoVecinoVO;
import vos.ContratoViviendaVO;
import vos.HotelRoomVO;
import vos.ReservaHostalVO;
import vos.ReservaHotelVO;
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
	public List<ReservaHotelVO> hacerReservaHotelMasiva(int cantidad, Date inicio, Date fin) {
		ArrayList<ReservaHotelVO> list = new ArrayList<>();
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
	 * Metodo que modela la transaccion que retorna todos los reservasHostales
	 * de la base de datos. <br/>
	 * 
	 * @return List<ReservaHostalVO> - Lista de reservasHostales que contiene el
	 *         resultado de la consulta.
	 * @throws Exception
	 *             - Cualquier error que se genere durante la transaccion
	 */
	public List<ReservaHostalVO> getReservasHostales() throws Exception {
		DAOReservaHostal daoReservaHostal = new DAOReservaHostal();
		List<ReservaHostalVO> reservasHostales;
		try {
			this.conn = darConexion();
			daoReservaHostal.setConn(conn);

			// Por simplicidad, solamente se obtienen los primeros 50 resultados
			// de la consulta
			reservasHostales = daoReservaHostal.getReservasHostales();
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
				daoReservaHostal.cerrarRecursos();
				if (this.conn != null) {
					this.conn.close();
				}
			} catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return reservasHostales;
	}

	/**
	 * Metodo que modela la transaccion que busca el reservaHostal en la base de
	 * datos que tiene el ID dado por parametro. <br/>
	 * 
	 * @param name
	 *            -id del reservaHostal a buscar. id != null
	 * @return ReservaHostalVO - ReservaHostalVO que se obtiene como resultado
	 *         de la consulta.
	 * @throws Exception
	 *             - cualquier error que se genere durante la transaccion
	 */
	public ReservaHostalVO getReservaHostal(Long id) throws Exception {
		DAOReservaHostal daoReservaHostal = new DAOReservaHostal();
		ReservaHostalVO reservaHostal = null;
		try {
			this.conn = darConexion();
			daoReservaHostal.setConn(conn);
			reservaHostal = daoReservaHostal.getReservaHostal(id);
			if (reservaHostal == null) {
				throw new Exception(
						"El reservaHostal con id = " + id + " no se encuentra persistido en la base de datos.");
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
				daoReservaHostal.cerrarRecursos();
				if (this.conn != null) {
					this.conn.close();
				}
			} catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return reservaHostal;
	}

	/**
	 * Metodo que modela la transaccion que agrega un reservaHostal a la base de
	 * datos. <br/>
	 * <b> post: </b> se ha agregado el reservaHostal que entra como parametro
	 * <br/>
	 * 
	 * @param reservaHostal
	 *            - el reservaHostal a agregar. reservaHostal != null
	 * @throws Exception
	 *             - Cualquier error que se genere agregando el reservaHostal
	 */
	public void addReservaHostal(ReservaHostalVO reserva) throws Exception {
		System.out.println(reserva);
		DAOReservaHostal daoReservaHostal = new DAOReservaHostal();
		try {
			this.conn = darConexion();
			daoReservaHostal.setConn(conn);
			daoReservaHostal.addReservaHostal(reserva);
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
				daoReservaHostal.cerrarRecursos();
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
	 * reservaHostal que entra por parametro.<br/>
	 * Solamente se actualiza si existe el reservaHostal en la Base de Datos
	 * <br/>
	 * <b> post: </b> se ha actualizado el reservaHostal que entra como
	 * parametro <br/>
	 * 
	 * @param reservaHostal
	 *            - ReservaHostalVO a actualizar. reservaHostal != null
	 * @throws Exception
	 *             - Cualquier error que se genere actualizando al
	 *             reservaHostal.
	 */
	public void updateReservaHostal(ReservaHostalVO reservaHostal) throws Exception {
		DAOReservaHostal daoReservaHostal = new DAOReservaHostal();
		try {
			this.conn = darConexion();
			daoReservaHostal.setConn(conn);
			reservaHostal = daoReservaHostal.getReservaHostal(reservaHostal.getId());
			if (reservaHostal == null) {
				throw new Exception(
						"El reservaHostal con el id seleccionado no se encuentra persistido en la base de datos.");
			} else
				daoReservaHostal.updateReservaHostal(reservaHostal);
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
				daoReservaHostal.cerrarRecursos();
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
	 * reservaHostal que entra por parametro. <br/>
	 * Solamente se actualiza si existe el reservaHostal en la Base de Datos
	 * <br/>
	 * <b> post: </b> se ha eliminado el reservaHostal que entra por parametro
	 * <br/>
	 * 
	 * @param ReservaHostalVO
	 *            - reservaHostal a eliminar. reservaHostal != null
	 * @throws Exception
	 *             - Cualquier error que se genere eliminando al reservaHostal.
	 */
	public void deleteReservaHostal(ReservaHostalVO reservaHostal) throws Exception {
		DAOReservaHostal daoReservaHostal = new DAOReservaHostal();
		try {
			this.conn = darConexion();
			daoReservaHostal.setConn(conn);
			reservaHostal = daoReservaHostal.getReservaHostal(reservaHostal.getId());
			if (reservaHostal == null) {
				throw new Exception(
						"El reservaHostal con el id seleccionado no se encuentra persistido en la base de datos.");
			} else
				daoReservaHostal.deleteReservaHostal(reservaHostal);
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
				daoReservaHostal.cerrarRecursos();
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
	 * Metodo que modela la transaccion que retorna todos los reservasHostales
	 * de la base de datos. <br/>
	 * 
	 * @return List<ReservaHostalVO> - Lista de reservasHostales que contiene el
	 *         resultado de la consulta.
	 * @throws Exception
	 *             - Cualquier error que se genere durante la transaccion
	 */
	public List<ReservaHotelVO> getReservasHoteles() throws Exception {
		DAOReservaHotel daoReservaHostal = new DAOReservaHotel();
		List<ReservaHotelVO> reservasHoteles;
		try {
			this.conn = darConexion();
			daoReservaHostal.setConn(conn);

			// Por simplicidad, solamente se obtienen los primeros 50 resultados
			// de la consulta
			reservasHoteles = daoReservaHostal.getReservasHoteles();
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
				daoReservaHostal.cerrarRecursos();
				if (this.conn != null) {
					this.conn.close();
				}
			} catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return reservasHoteles;
	}

	/**
	 * Metodo que modela la transaccion que busca el reservaHostal en la base de
	 * datos que tiene el ID dado por parametro. <br/>
	 * 
	 * @param name
	 *            -id del reservaHostal a buscar. id != null
	 * @return ReservaHostalVO - ReservaHostalVO que se obtiene como resultado
	 *         de la consulta.
	 * @throws Exception
	 *             - cualquier error que se genere durante la transaccion
	 */
	public ReservaHotelVO getReservaHotel(Long id) throws Exception {
		DAOReservaHotel daoReservaHotel = new DAOReservaHotel();
		ReservaHotelVO reservaHotel = null;
		try {
			this.conn = darConexion();
			daoReservaHotel.setConn(conn);
			reservaHotel = daoReservaHotel.getReservaHotel(id);
			if (reservaHotel == null) {
				throw new Exception("El reservaHostal con la fecha inicial para el usuario = " + id
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
				daoReservaHotel.cerrarRecursos();
				if (this.conn != null) {
					this.conn.close();
				}
			} catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return reservaHotel;
	}

	/**
	 * Metodo que modela la transaccion que agrega un reservaHostal a la base de
	 * datos. <br/>
	 * <b> post: </b> se ha agregado el reservaHostal que entra como parametro
	 * <br/>
	 * 
	 * @param reservaHostal
	 *            - el reservaHostal a agregar. reservaHostal != null
	 * @throws Exception
	 *             - Cualquier error que se genere agregando el reservaHostal
	 */
	public void addReservaHotel(ReservaHotelVO reserva) throws Exception {

		DAOReservaHotel daoReservaHotel = new DAOReservaHotel();
		try {
			this.conn = darConexion();
			daoReservaHotel.setConn(conn);
			daoReservaHotel.addReservaHotel(reserva);
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
				daoReservaHotel.cerrarRecursos();
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
	 * reservaHostal que entra por parametro.<br/>
	 * Solamente se actualiza si existe el reservaHostal en la Base de Datos
	 * <br/>
	 * <b> post: </b> se ha actualizado el reservaHostal que entra como
	 * parametro <br/>
	 * 
	 * @param reservaHostal
	 *            - ReservaHostalVO a actualizar. reservaHostal != null
	 * @throws Exception
	 *             - Cualquier error que se genere actualizando al
	 *             reservaHostal.
	 */
	public void updateReservaHotel(ReservaHotelVO reservaHotel) throws Exception {
		DAOReservaHotel daoReservaHotel = new DAOReservaHotel();
		try {
			this.conn = darConexion();
			daoReservaHotel.setConn(conn);
			reservaHotel = daoReservaHotel.getReservaHotel(reservaHotel.getId());
			if (reservaHotel == null) {
				throw new Exception(
						"El reservaHostal con el id seleccionado no se encuentra persistido en la base de datos.");
			} else
				daoReservaHotel.updateReservaHotel(reservaHotel);
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
				daoReservaHotel.cerrarRecursos();
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
	 * reservaHostal que entra por parametro. <br/>
	 * Solamente se actualiza si existe el reservaHostal en la Base de Datos
	 * <br/>
	 * <b> post: </b> se ha eliminado el reservaHostal que entra por parametro
	 * <br/>
	 * 
	 * @param ReservaHostalVO
	 *            - reservaHostal a eliminar. reservaHostal != null
	 * @throws Exception
	 *             - Cualquier error que se genere eliminando al reservaHostal.
	 */
	public void deleteReservaHotel(ReservaHotelVO reservaHotel) throws Exception {
		DAOReservaHotel daoReservaHotel = new DAOReservaHotel();
		try {
			this.conn = darConexion();
			daoReservaHotel.setConn(conn);
			reservaHotel = daoReservaHotel.getReservaHotel(reservaHotel.getId());
			if (reservaHotel == null) {
				throw new Exception(
						"El reservaHostal con el id seleccionado no se encuentra persistido en la base de datos.");
			} else
				daoReservaHotel.deleteReservaHostal(reservaHotel);
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
				daoReservaHotel.cerrarRecursos();
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
	 * Metodo que modela la transaccion que retorna todos los reservasHostales
	 * de la base de datos. <br/>
	 * 
	 * @return List<ReservaHostalVO> - Lista de reservasHostales que contiene el
	 *         resultado de la consulta.
	 * @throws Exception
	 *             - Cualquier error que se genere durante la transaccion
	 */
	public List<ContratoViviendaVO> getContratosViviendas() throws Exception {
		DAOContratoVivienda daoContratoVivienda = new DAOContratoVivienda();
		List<ContratoViviendaVO> contratosViviendas;
		try {
			this.conn = darConexion();
			daoContratoVivienda.setConn(conn);

			// Por simplicidad, solamente se obtienen los primeros 50 resultados
			// de la consulta
			contratosViviendas = daoContratoVivienda.getContratosVivienda();
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
				daoContratoVivienda.cerrarRecursos();
				if (this.conn != null) {
					this.conn.close();
				}
			} catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return contratosViviendas;
	}

	/**
	 * Metodo que modela la transaccion que busca el reservaHostal en la base de
	 * datos que tiene el ID dado por parametro. <br/>
	 * 
	 * @param name
	 *            -id del reservaHostal a buscar. id != null
	 * @return ReservaHostalVO - ReservaHostalVO que se obtiene como resultado
	 *         de la consulta.
	 * @throws Exception
	 *             - cualquier error que se genere durante la transaccion
	 */
	public ContratoViviendaVO getContratoVivienda(Long id) throws Exception {
		DAOContratoVivienda daoContratoVivienda = new DAOContratoVivienda();
		ContratoViviendaVO contratoVivienda = null;
		try {
			this.conn = darConexion();
			daoContratoVivienda.setConn(conn);
			contratoVivienda = daoContratoVivienda.getContratoVivienda(id);
			if (contratoVivienda == null) {
				throw new Exception("El reservaHostal con la fecha inicial para el usuario = " + id
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
				daoContratoVivienda.cerrarRecursos();
				if (this.conn != null) {
					this.conn.close();
				}
			} catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return contratoVivienda;
	}

	/**
	 * Metodo que modela la transaccion que agrega un reservaHostal a la base de
	 * datos. <br/>
	 * <b> post: </b> se ha agregado el reservaHostal que entra como parametro
	 * <br/>
	 * 
	 * @param reservaHostal
	 *            - el reservaHostal a agregar. reservaHostal != null
	 * @throws Exception
	 *             - Cualquier error que se genere agregando el reservaHostal
	 */
	public void addContratoVivienda(ContratoViviendaVO contrato) throws Exception {

		DAOContratoVivienda daoContratoVivienda = new DAOContratoVivienda();
		try {
			this.conn = darConexion();
			daoContratoVivienda.setConn(conn);
			daoContratoVivienda.addContratoVivienda(contrato);
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
				daoContratoVivienda.cerrarRecursos();
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
	 * reservaHostal que entra por parametro.<br/>
	 * Solamente se actualiza si existe el reservaHostal en la Base de Datos
	 * <br/>
	 * <b> post: </b> se ha actualizado el reservaHostal que entra como
	 * parametro <br/>
	 * 
	 * @param reservaHostal
	 *            - ReservaHostalVO a actualizar. reservaHostal != null
	 * @throws Exception
	 *             - Cualquier error que se genere actualizando al
	 *             reservaHostal.
	 */
	public void updateContratoVivienda(ContratoViviendaVO contratoVivienda) throws Exception {
		DAOContratoVivienda daoContratoVivienda = new DAOContratoVivienda();
		try {
			this.conn = darConexion();
			daoContratoVivienda.setConn(conn);
			contratoVivienda = daoContratoVivienda.getContratoVivienda(contratoVivienda.getId());
			if (contratoVivienda == null) {
				throw new Exception(
						"El reservaHostal con el id seleccionado no se encuentra persistido en la base de datos.");
			} else
				daoContratoVivienda.updateContratoVivienda(contratoVivienda);
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
				daoContratoVivienda.cerrarRecursos();
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
	 * reservaHostal que entra por parametro. <br/>
	 * Solamente se actualiza si existe el reservaHostal en la Base de Datos
	 * <br/>
	 * <b> post: </b> se ha eliminado el reservaHostal que entra por parametro
	 * <br/>
	 * 
	 * @param ReservaHostalVO
	 *            - reservaHostal a eliminar. reservaHostal != null
	 * @throws Exception
	 *             - Cualquier error que se genere eliminando al reservaHostal.
	 */
	public void deleteContratoVivienda(ContratoViviendaVO contratoVivienda) throws Exception {
		DAOContratoVivienda daoContratoVivienda = new DAOContratoVivienda();
		try {
			this.conn = darConexion();
			daoContratoVivienda.setConn(conn);
			contratoVivienda = daoContratoVivienda.getContratoVivienda(contratoVivienda.getId());
			if (contratoVivienda == null) {
				throw new Exception(
						"El reservaHostal con el id seleccionado no se encuentra persistido en la base de datos.");
			} else
				daoContratoVivienda.deleteContratoVivienda(contratoVivienda);
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
				daoContratoVivienda.cerrarRecursos();
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

	public List<ContratoInmuebleVO> getContratosInmuebles() throws Exception {
		DAOContratoInmueble daoContratoInmueble = new DAOContratoInmueble();
		List<ContratoInmuebleVO> contratosInmuebles;
		try {
			this.conn = darConexion();
			daoContratoInmueble.setConn(conn);

			// Por simplicidad, solamente se obtienen los primeros 50 resultados
			// de la consulta
			contratosInmuebles = daoContratoInmueble.getContratosInmueble();
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
				daoContratoInmueble.cerrarRecursos();
				if (this.conn != null) {
					this.conn.close();
				}
			} catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return contratosInmuebles;
	}

	public ContratoInmuebleVO getContratoInmueble(String fechaI, String fechaF, String usuario) throws Exception {
		DAOContratoInmueble daoContratoInmueble = new DAOContratoInmueble();
		ContratoInmuebleVO contratoInmueble = null;
		try {
			this.conn = darConexion();
			daoContratoInmueble.setConn(conn);
			contratoInmueble = daoContratoInmueble.getContratoInmueble(fechaI, fechaF, usuario);
			if (contratoInmueble == null) {
				throw new Exception("El reservaHostal con la fecha inicial para el usuario = " + fechaI + ", " + usuario
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
				daoContratoInmueble.cerrarRecursos();
				if (this.conn != null) {
					this.conn.close();
				}
			} catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return contratoInmueble;
	}

	public void addContratoInmueble(ContratoInmuebleVO contrato) throws Exception {
		DAOContratoInmueble daoContratoInmueble = new DAOContratoInmueble();
		DAOInmueble daoInmueble = new DAOInmueble();
		DAOUsuario daoUssr = new DAOUsuario();
		try {
			this.conn = darConexion();
			daoInmueble.setConn(conn);
			daoUssr.setConn(conn);
			contrato.setInmueble(daoInmueble.getInmueble(contrato.getIdInmueble()));
			contrato.setUsuario(daoUssr.getUsuario(contrato.getUsrCedula()));
			daoContratoInmueble.setConn(conn);
			daoContratoInmueble.addContratoInmueble(contrato);
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
				daoContratoInmueble.cerrarRecursos();
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

	public void deleteContratoInmueble(ContratoInmuebleVO contratoInmueble) throws Exception {
		DAOContratoInmueble daoContratoInmueble = new DAOContratoInmueble();
		try {
			this.conn = darConexion();
			daoContratoInmueble.setConn(conn);
			contratoInmueble = daoContratoInmueble.getContratoInmueble(contratoInmueble.getFechaInicio(),
					contratoInmueble.getFechaFinal(), contratoInmueble.getUsuario().getCedula());
			if (contratoInmueble == null) {
				throw new Exception(
						"El reservaHostal con el id seleccionado no se encuentra persistido en la base de datos.");
			} else
				daoContratoInmueble.deleteContratoInmueble(contratoInmueble);
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
				daoContratoInmueble.cerrarRecursos();
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

	public List<ContratoVecinoVO> getContratosVecinos() throws Exception {
		DAOContratoVecino daoContratoVecino = new DAOContratoVecino();
		List<ContratoVecinoVO> contratosVecinos;
		try {
			this.conn = darConexion();
			daoContratoVecino.setConn(conn);

			// Por simplicidad, solamente se obtienen los primeros 50 resultados
			// de la consulta
			contratosVecinos = daoContratoVecino.getContratosVecino();
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
				daoContratoVecino.cerrarRecursos();
				if (this.conn != null) {
					this.conn.close();
				}
			} catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return contratosVecinos;
	}

	public ContratoVecinoVO getContratoVecino(String fechaI, String fechaF, String usuario) throws Exception {
		DAOContratoVecino daoContratoVecino = new DAOContratoVecino();
		ContratoVecinoVO contratoVecino = null;
		try {
			this.conn = darConexion();
			daoContratoVecino.setConn(conn);
			contratoVecino = daoContratoVecino.getContratoVecino(fechaI, fechaF, usuario);
			if (contratoVecino == null) {
				throw new Exception("El reservaHostal con la fecha inicial para el usuario = " + fechaI + ", " + usuario
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
				daoContratoVecino.cerrarRecursos();
				if (this.conn != null) {
					this.conn.close();
				}
			} catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return contratoVecino;
	}

	public void addContratoVecino(ContratoVecinoVO contrato) throws Exception {

		DAOContratoVecino daoContratoVecino = new DAOContratoVecino();
		try {
			this.conn = darConexion();
			daoContratoVecino.setConn(conn);
			daoContratoVecino.addContratoVecino(contrato);
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
				daoContratoVecino.cerrarRecursos();
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

	public void deleteContratoVecino(ContratoVecinoVO contratoVecino) throws Exception {
		DAOContratoVecino daoContratoVecino = new DAOContratoVecino();
		try {
			this.conn = darConexion();
			daoContratoVecino.setConn(conn);
			contratoVecino = daoContratoVecino.getContratoVecino(contratoVecino.getFechaInicio(),
					contratoVecino.getFechaFin(), contratoVecino.getUsuario().getCedula());
			if (contratoVecino == null) {
				throw new Exception(
						"El reservaHostal con el id seleccionado no se encuentra persistido en la base de datos.");
			} else
				daoContratoVecino.deleteContratoVecino(contratoVecino);
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
				daoContratoVecino.cerrarRecursos();
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
				throw new Exception("El reservaHostal con la fecha inicial para el usuario = " + id
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
						"El reservaHostal con el id seleccionado no se encuentra persistido en la base de datos.");
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
