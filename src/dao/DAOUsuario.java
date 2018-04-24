package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.UsuarioVO;

public class DAOUsuario {
	//----------------------------------------------------------------------------------------------------------------------------------
	// CONSTANTES
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Constante para indicar el usuario Oracle del estudiante
	 */
	public final static String USUARIO = "ISIS2304A171810";


	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Arraylits de recursos que se usan para la ejecucion de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexion a la base de datos
	 */
	private Connection conn;

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE INICIALIZACION
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Metodo constructor de la clase DAOBebedor <br/>
	 */
	public DAOUsuario() {
		recursos = new ArrayList<Object>();
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE COMUNICACION CON LA BASE DE DATOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Metodo que obtiene la informacion de todos los bebedores en la Base de Datos <br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * @return	lista con la informacion de todos los bebedores que se encuentran en la Base de Datos
	 * @throws SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public ArrayList<UsuarioVO> getUsuarios() throws SQLException, Exception {
		ArrayList<UsuarioVO> hostales = new ArrayList<UsuarioVO>();

		//Aclaracion: Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
		String sql = String.format("SELECT * FROM USUARIOS WHERE ROWNUM <= 50", USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			hostales.add(convertResultSetToUsuario(rs));
		}
		return hostales;
	}



	/**
	 * Metodo que obtiene la informacion del hostal en la Base de Datos que tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/> 
	 * @param id el identificador del hostal
	 * @return la informacion del hostal que cumple con los criterios de la sentecia SQL
	 * 			Null si no existe el hostal conlos criterios establecidos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public UsuarioVO getUsuario(String cedula) throws SQLException, Exception 
	{
		UsuarioVO hostal = null;

		String sql = String.format("SELECT * FROM USUARIO WHERE CEDULA = %2$s", USUARIO, cedula); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			hostal = convertResultSetToUsuario(rs);
		}

		return hostal;
	}

	/**
	 * Metodo que agregar la informacion de un nuevo hostal en la Base de Datos a partir del parametro ingresado<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param hostal Bebedor que desea agregar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void addUsuario(UsuarioVO hostal) throws SQLException, Exception {

		String sql = String.format("INSERT INTO USUARIO (CEDULA, NOMBRE, FECHANACIMIENTO) VALUES (%2$s, '%3$s', '%4$s')", 
				USUARIO, 
				hostal.getCedula(), 
				hostal.getNombre(),
				hostal.getFechaNacimiento() 
				);
		System.out.println(sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}

	/**
	 * Metodo que actualiza la informacion del usuario en la Base de Datos que tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param hostal Bebedor que desea actualizar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void updateUsuario(UsuarioVO usuario) throws SQLException, Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(String.format("UPDATE USUARIO SET ", USUARIO));
		sql.append(String.format("CEDULA = '%1$s' AND NOMBRE = '%2$s' AND FECHANACIMIENTO = '%3$s'"
				,usuario.getCedula(), usuario.getNombre(), usuario.getFechaNacimiento()));

		System.out.println(sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Metodo que actualiza la informacion del hostal en la Base de Datos que tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param hostal Bebedor que desea actualizar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void deleteUsuario(UsuarioVO hostal) throws SQLException, Exception {

		String sql = String.format("DELETE FROM USUARIO WHERE CEDULA = %2$d", USUARIO, hostal.getCedula());

		System.out.println(sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}



	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS AUXILIARES
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Metodo encargado de inicializar la conexion del DAO a la Base de Datos a partir del parametro <br/>
	 * <b>Postcondicion: </b> el atributo conn es inicializado <br/>
	 * @param connection la conexion generada en el TransactionManager para la comunicacion con la Base de Datos
	 */
	public void setConn(Connection connection){
		this.conn = connection;
	}

	/**
	 * Metodo que cierra todos los recursos que se encuentran en el arreglo de recursos<br/>
	 * <b>Postcondicion: </b> Todos los recurso del arreglo de recursos han sido cerrados.
	 */
	public void cerrarRecursos() {
		for(Object ob : recursos){
			if(ob instanceof PreparedStatement)
				try {
					((PreparedStatement) ob).close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
	}

	/**
	 * Metodo que transforma el resultado obtenido de una consulta SQL (sobre la tabla BEBEDORES) en una instancia de la clase Bebedor.
	 * @param resultSet ResultSet con la informacion de un hostal que se obtuvo de la base de datos.
	 * @return Bebedor cuyos atributos corresponden a los valores asociados a un registro particular de la tabla BEBEDORES.
	 * @throws SQLException Si existe algun problema al extraer la informacion del ResultSet.
	 */
	public UsuarioVO convertResultSetToUsuario(ResultSet resultSet) throws SQLException {

		String cedula = resultSet.getString("CEDULA");
		String nombre = resultSet.getString("NOMBRE");
		String fechaNacimiento = resultSet.getString("FECHANACIMIENTO");

		
		
		UsuarioVO beb = new UsuarioVO();
		beb.setCedula(cedula);
		beb.setFechaNacimiento(fechaNacimiento);
		beb.setNombre(nombre);

		return beb;
	}

	public UsuarioVO getUsuarioSimple(String cedula) {
		// TODO Auto-generated method stub
		return null;
	}
}
