package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.ReservaMasivaVO;
import vos.InmuebleVO;
import vos.UsuarioVO;

public class DAOReservaMasiva {
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
	public DAOReservaMasiva() {
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
	public ArrayList<ReservaMasivaVO> getReservasMasivas() throws SQLException, Exception {
		ArrayList<ReservaMasivaVO> contratos = new ArrayList<ReservaMasivaVO>();

		// Aclaracion: Por simplicidad, solamente se obtienen los primeros 50
		// resultados de la consulta
		String sql = String.format("SELECT * FROM RESERVASMASIVAS WHERE ROWNUM <= 50", USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			contratos.add(convertResultSetToReservaMasiva(rs));
		}
		return contratos;
	}

	/**
	 * Metodo que obtiene la informacion del contrato en la Base de Datos que
	 * tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * 
	 * @param id
	 *            el identificador del contrato
	 * @return la informacion del contrato que cumple con los criterios de la
	 *         sentecia SQL Null si no existe el contrato conlos criterios
	 *         establecidos
	 * @throws SQLException
	 *             SQLException Genera excepcion si hay error en la conexion o
	 *             en la consulta SQL
	 * @throws Exception
	 *             Si se genera un error dentro del metodo.
	 */
	public ReservaMasivaVO getReservaMasiva(Long id)
			throws SQLException, Exception {
		ReservaMasivaVO contrato = null;

		String sql = String.format(
				"SELECT * FROM RESERVASMASIVAS WHERE ID = '%2$s'",
				USUARIO, id);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if (rs.next()) {
			contrato = convertResultSetToReservaMasiva(rs);
		}

		return contrato;
	}

	/**
	 * Metodo que agregar la informacion de un nuevo contrato en la Base de
	 * Datos a partir del parametro ingresado<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * 
	 * @param contrato
	 *            ReservaMasivaVO que desea agregar a la Base de Datos
	 * @throws SQLException
	 *             SQLException Genera excepcion si hay error en la conexion o
	 *             en la consulta SQL
	 * @throws Exception
	 *             Si se genera un error dentro del metodo.
	 */
	public void addReservaMasiva(ReservaMasivaVO contrato) throws SQLException, Exception {

		String sql = String.format(
				"INSERT INTO RESERVASMASIVAS (CANTIDAD, DESCRIPCION, TIPO) VALUES ('%2$s', '%3$s', '%4$s')", USUARIO,
				contrato.getCantidad(), contrato.getDescrpcion(), contrato.getTipo());
		System.out.println(sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}

	public void deleteReservaMasiva(ReservaMasivaVO contrato) throws SQLException, Exception {

		String sql = String.format("DELETE FROM RESERVASMASIVAS WHERE ID = %2$d", USUARIO, contrato.getId());

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
	 * tabla CONTRATOSINMUEBLES) en una instancia de la clase ReservaMasivaVO.
	 * 
	 * @param resultSet
	 *            ResultSet con la informacion de un contrato que se obtuvo de
	 *            la base de datos.
	 * @return ReservaMasivaVO cuyos atributos corresponden a los valores
	 *         asociados a un registro particular de la tabla
	 *         CONTRATOSINMUEBLES.
	 * @throws SQLException
	 *             Si existe algun problema al extraer la informacion del
	 *             ResultSet.
	 */
	public ReservaMasivaVO convertResultSetToReservaMasiva(ResultSet resultSet) throws SQLException {

		try {

			String tipo = resultSet.getString("TIPO");
			String descripcion = resultSet.getString("DESCRIPCION");
			String cantidadS = resultSet.getString("CANTIDAD");
			String IdS = resultSet.getString("ID");

			Long Id = Long.parseLong(IdS);
			Integer cantidad = Integer.parseInt(cantidadS);

			ReservaMasivaVO beb = new ReservaMasivaVO();
			beb.setTipo(tipo);
			beb.setId(Id);
			beb.setCantidad(cantidad);
			beb.setDescrpcion(descripcion);
			beb.setTipo(tipo);

			return beb;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
