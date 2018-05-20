package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vos.ClienteVO;
import vos.FechasVO;

public class DAOCliente {
	// ----------------------------------------------------------------------------------------------------------------------------------
	// CONSTANTES
	// ----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Constante para indicar el usuario Oracle del estudiante
	 */
	public final static String CLIENTE = "ISIS2304A171810";

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
	public DAOCliente() {
		recursos = new ArrayList<Object>();
	}

	// ----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE COMUNICACION CON LA BASE DE DATOS
	// ----------------------------------------------------------------------------------------------------------------------------------

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
	public ArrayList<ClienteVO> getClientes() throws SQLException, Exception {
		ArrayList<ClienteVO> hostales = new ArrayList<ClienteVO>();

		// Aclaracion: Por simplicidad, solamente se obtienen los primeros 50
		// resultados de la consulta
		String sql = String.format("SELECT * FROM Cliente WHERE ROWNUM <= 50", CLIENTE);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			hostales.add(convertResultSetToCliente(rs));
		}
		return hostales;
	}

	/**
	 * Metodo que obtiene la informacion del hostal en la Base de Datos que
	 * tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * 
	 * @param id
	 *            el identificador del hostal
	 * @return la informacion del hostal que cumple con los criterios de la
	 *         sentecia SQL Null si no existe el hostal conlos criterios
	 *         establecidos
	 * @throws SQLException
	 *             SQLException Genera excepcion si hay error en la conexion o
	 *             en la consulta SQL
	 * @throws Exception
	 *             Si se genera un error dentro del metodo.
	 */
	public ClienteVO getCliente(String cedula) throws SQLException, Exception {
		ClienteVO hostal = null;

		String sql = String.format("SELECT * FROM CLIENTE WHERE CEDULA = %2$s", CLIENTE, cedula);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if (rs.next()) {
			hostal = convertResultSetToCliente(rs);
		}

		return hostal;
	}

	/**
	 * Metodo que agregar la informacion de un nuevo hostal en la Base de Datos
	 * a partir del parametro ingresado<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * 
	 * @param hostal
	 *            Bebedor que desea agregar a la Base de Datos
	 * @throws SQLException
	 *             SQLException Genera excepcion si hay error en la conexion o
	 *             en la consulta SQL
	 * @throws Exception
	 *             Si se genera un error dentro del metodo.
	 */
	public void addCliente(ClienteVO hostal) throws SQLException, Exception {

		String sql = String.format(
				"INSERT INTO CLIENTE (CEDULA, NOMBRE, FECHANACIMIENTO) VALUES (%2$s, '%3$s', '%4$s')", CLIENTE,
				hostal.getCedula(), hostal.getNombre(), hostal.getFechaNacimiento());
		System.out.println(sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}

	/**
	 * Metodo que actualiza la informacion del usuario en la Base de Datos que
	 * tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * 
	 * @param hostal
	 *            Bebedor que desea actualizar a la Base de Datos
	 * @throws SQLException
	 *             SQLException Genera excepcion si hay error en la conexion o
	 *             en la consulta SQL
	 * @throws Exception
	 *             Si se genera un error dentro del metodo.
	 */
	public void updateCliente(ClienteVO usuario) throws SQLException, Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(String.format("UPDATE CLIENTE SET ", CLIENTE));
		sql.append(String.format("CEDULA = '%1$s' AND NOMBRE = '%2$s' AND FECHANACIMIENTO = '%3$s'",
				usuario.getCedula(), usuario.getNombre(), usuario.getFechaNacimiento()));

		System.out.println(sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Metodo que actualiza la informacion del hostal en la Base de Datos que
	 * tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * 
	 * @param hostal
	 *            Bebedor que desea actualizar a la Base de Datos
	 * @throws SQLException
	 *             SQLException Genera excepcion si hay error en la conexion o
	 *             en la consulta SQL
	 * @throws Exception
	 *             Si se genera un error dentro del metodo.
	 */
	public void deleteCliente(ClienteVO hostal) throws SQLException, Exception {

		String sql = String.format("DELETE FROM CLIENTE WHERE CEDULA = %1$s", CLIENTE, hostal.getCedula());

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	public List<ClienteVO> getCosumoPorFecha(FechasVO fechas) {
		List<ClienteVO> clientes = new ArrayList<>();
		String fechaI = fechas.getFechaI();
		String fechaF = fechas.getFechaF();
		String cliente = fechas.getCliente();
		String tipo = fechas.getTipo();
		try {
			String cons = "";
			if(cliente != null){
				cons = "Order by Persona."+cliente;
			}
			String sql = String.format(
					"Select * "
					+ "From Cliente inner join Persona "
					+ "on Cliente.CEDULA = Persona.CEDULA "
					+ "Where Cliente.CEDULA in (Select Cedula "
					+ "From Reserva inner join Alojamiento "
					+ "On Reserva.CUARTO = Alojamiento.ID "
					+ "Where (FECHAINICIO Between '%1$s' AND '%2$s' "
					+ "Or FECHAFIN Between '%1$s' AND '%2$s') "
					+ "AND Alojamiento.DTYPE = '%3$s') "+cons,
					fechaI, fechaF,tipo);
			System.out.println(sql);
			PreparedStatement prepStmt;
			prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				clientes.add(convertResultSetToCliente(rs));
			}
			return clientes;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<ClienteVO> getCosumoPorFechaNo(FechasVO fechas) {
		List<ClienteVO> clientes = new ArrayList<>();
		String fechaI = fechas.getFechaI();
		String fechaF = fechas.getFechaF();
		String cliente = fechas.getCliente();
		String tipo = fechas.getTipo();
		try {
			String cons = "";
			if(cliente != null){
				cons = "Order by Persona."+cliente;
			}
			String sql = String.format(
					"Select * "
					+ "From Cliente inner join Persona "
					+ "on Cliente.CEDULA = Persona.CEDULA "
					+ "Where Cliente.CEDULA not in (Select Cedula "
					+ "From Reserva inner join Alojamiento "
					+ "On Reserva.CUARTO = Alojamiento.ID "
					+ "Where (FECHAINICIO Between '%1$s' AND '%2$s' "
					+ "Or FECHAFIN Between '%1$s' AND '%2$s') "
					+ "AND Alojamiento.DTYPE = '%3$s') "+cons,
					fechaI, fechaF,tipo);
			PreparedStatement prepStmt;
			prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				clientes.add(convertResultSetToCliente(rs));
			}
			return clientes;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<ClienteVO> getBuenosClientes( ) {
		List<ClienteVO> clientes = new ArrayList<>();
		try {
			String sql = String.format("");
			PreparedStatement prepStmt;
			prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				clientes.add(convertResultSetToCliente(rs));
			}
			return clientes;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
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
	 * tabla BEBEDORES) en una instancia de la clase Bebedor.
	 * 
	 * @param resultSet
	 *            ResultSet con la informacion de un hostal que se obtuvo de la
	 *            base de datos.
	 * @return Bebedor cuyos atributos corresponden a los valores asociados a un
	 *         registro particular de la tabla BEBEDORES.
	 * @throws SQLException
	 *             Si existe algun problema al extraer la informacion del
	 *             ResultSet.
	 */
	public ClienteVO convertResultSetToCliente(ResultSet resultSet) throws SQLException {

		String cedula = resultSet.getString("CEDULA");
		String nombre = resultSet.getString("NOMBRE");
		String fechaNacimiento = resultSet.getString("FECHANACIMIENTO");

		ClienteVO beb = new ClienteVO();
		beb.setCedula(cedula);
		beb.setFechaNacimiento(fechaNacimiento);
		beb.setNombre(nombre);

		return beb;
	}
}
