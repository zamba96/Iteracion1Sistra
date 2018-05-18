/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.ReservaVO;

/**
 * @author camilo
 *
 */
public class DAOReserva {

	// ----------------------------------------------------------------------------------------------------------------------------------
	// CONSTANTES
	// ----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Constante para indicar el usuario Oracle del estudiante
	 */
	public final static String USUARIO = "ISIS2304A171810";

	// ----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	// ----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Arraylits de recursos que se usan para la ejecucion de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexion a la base de datos
	 */
	private Connection conn;

	// ----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE INICIALIZACION
	// ----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Metodo constructor de la clase DAOBebedor <br/>
	 */
	public DAOReserva() {
		recursos = new ArrayList<Object>();
	}

	/**
	 * Metodo que obtiene la informacion de todos los bebedores en la Base de
	 * Datos <br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * 
	 * @return lista con la informacion de todos los bebedores que se encuentran
	 *         en la Base de Datos
	 * @throws SQLException
	 *             Genera excepcion si hay error en la conexion o en la consulta
	 *             SQL
	 * @throws Exception
	 *             Si se genera un error dentro del metodo.
	 */
	public ArrayList<ReservaVO> getReservases() throws SQLException, Exception {
		ArrayList<ReservaVO> reservas = new ArrayList<ReservaVO>();

		// Aclaracion: Por simplicidad, solamente se obtienen los primeros 50
		// resultados de la consulta
		String sql = String.format("SELECT * FROM RESERVAHOSTAL WHERE ROWNUM <= 50", USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			reservas.add(convertResultSetToReserva(rs));
		}
		return reservas;
	}

	/**
	 * Metodo que obtiene la informacion del reserva en la Base de Datos que
	 * tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * 
	 * @param id
	 *            el identificador del reserva
	 * @return la informacion del reserva que cumple con los criterios de la
	 *         sentecia SQL Null si no existe el reserva conlos criterios
	 *         establecidos
	 * @throws SQLException
	 *             SQLException Genera excepcion si hay error en la conexion o
	 *             en la consulta SQL
	 * @throws Exception
	 *             Si se genera un error dentro del metodo.
	 */
	public ReservaVO getReserva(Long id) throws SQLException, Exception {
		ReservaVO reserva = null;

		String sql = String.format("SELECT * FROM RESERVAHOSTAL WHERE Id = '%2$s'", USUARIO, id);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if (rs.next()) {
			reserva = convertResultSetToReserva(rs);
		}

		return reserva;
	}

	/**
	 * Metodo que agregar la informacion de un nuevo reserva en la Base de Datos
	 * a partir del parametro ingresado<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * 
	 * @param reserva
	 *            ReservaVO que desea agregar a la Base de Datos
	 * @throws SQLException
	 *             SQLException Genera excepcion si hay error en la conexion o
	 *             en la consulta SQL
	 * @throws Exception
	 *             Si se genera un error dentro del metodo.
	 */
	public void addReserva(ReservaVO reserva) throws SQLException, Exception {

		String sql = String.format(
				"INSERT INTO RESERVAHOSTAL (FECHAINICIO, FECHAFIN, IDHOSTALROOM, USUARIO) VALUES (%2$s, '%3$s', '%4$s', '%5$s')",
				USUARIO, reserva.getFechaI(), reserva.getFechaF(), reserva.getCuarto(), reserva.getUsuario());
		System.out.println(sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Metodo que actualiza la informacion del reserva en la Base de Datos que
	 * tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * 
	 * @param reserva
	 *            ReservaVO que desea actualizar a la Base de Datos
	 * @throws SQLException
	 *             SQLException Genera excepcion si hay error en la conexion o
	 *             en la consulta SQL
	 * @throws Exception
	 *             Si se genera un error dentro del metodo.
	 */
	public void updateReserva(ReservaVO reserva) throws SQLException, Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(String.format("UPDATE RESERVAHOSTAL SET ", USUARIO));
		sql.append(String.format("FECHAINICIO = '%2$s' AND FECHAFIN = '%3$s' AND CUARTO = '%4$s' AND USUARIO = '%5$s' ",
				reserva.getFechaI(), reserva.getFechaF(), reserva.getCuarto()));

		System.out.println(sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Metodo que actualiza la informacion del reserva en la Base de Datos que
	 * tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * 
	 * @param reserva
	 *            ReservaVO que desea actualizar a la Base de Datos
	 * @throws SQLException
	 *             SQLException Genera excepcion si hay error en la conexion o
	 *             en la consulta SQL
	 * @throws Exception
	 *             Si se genera un error dentro del metodo.
	 */
	public void deleteReserva(ReservaVO reserva) throws SQLException, Exception {

		String sql = String.format("DELETE FROM RESERVAHOSTAL WHERE ID = %2$d", USUARIO, reserva.getId());

		System.out.println(sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	// ----------------------------------------------------------------------------------------------------------------------------------
	// METODOS AUXILIARES
	// ----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Metodo encargado de inicializar la conexion del DAO a la Base de Datos a
	 * partir del parametro <br/>
	 * <b>Postcondicion: </b> el atributo conn es inicializado <br/>
	 * 
	 * @param connection
	 *            la conexion generada en el TransactionManager para la
	 *            comunicacion con la Base de Datos
	 */
	public void setConn(Connection connection) {
		this.conn = connection;
	}

	/**
	 * Metodo que cierra todos los recursos que se encuentran en el arreglo de
	 * recursos<br/>
	 * <b>Postcondicion: </b> Todos los recurso del arreglo de recursos han sido
	 * cerrados.
	 */
	public void cerrarRecursos() {
		for (Object ob : recursos) {
			if (ob instanceof PreparedStatement)
				try {
					((PreparedStatement) ob).close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
	}

	/**
	 * Metodo que transforma el resultado obtenido de una consulta SQL (sobre la
	 * tabla RESERVAHOSTAL) en una instancia de la clase ReservaVO.
	 * 
	 * @param resultSet
	 *            ResultSet con la informacion de un reserva que se obtuvo de la
	 *            base de datos.
	 * @return ReservaVO cuyos atributos corresponden a los valores
	 *         asociados a un registro particular de la tabla RESERVAHOSTAL.
	 * @throws SQLException
	 *             Si existe algun problema al extraer la informacion del
	 *             ResultSet.
	 */
	public ReservaVO convertResultSetToReserva(ResultSet resultSet) throws SQLException {

		try {
			String fechaI = resultSet.getString("FECHAINICIO");
			String fechaF = resultSet.getString("FECHAFIN");
			String cuartoS = resultSet.getString("CUARTO");
			String ids = resultSet.getString("ID");
			Long usuario = resultSet.getLong("USUARIO");

			Long id = Long.parseLong(ids);
			Long cuarto = Long.parseLong(cuartoS);

			ReservaVO beb = new ReservaVO();
			beb.setCuarto(cuarto);
			beb.setFechaF(fechaF);
			beb.setFechaI(fechaI);
			beb.setId(id);
			beb.setUsuario(usuario);

			return beb;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
