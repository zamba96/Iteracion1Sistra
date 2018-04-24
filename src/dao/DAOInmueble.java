package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.InmuebleVO;
import vos.UsuarioVO;

public class DAOInmueble {

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
	 * Metodo constructor de la clase DAOInmuebl <br/>
	 */
	public DAOInmueble(){
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
	public ArrayList<InmuebleVO> getInmuebles() throws SQLException, Exception {
		ArrayList<InmuebleVO> inmuebles = new ArrayList<InmuebleVO>();

		//Aclaracion: Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
		String sql = String.format("SELECT * FROM INMUEBLE WHERE ROWNUM <= 50", USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			inmuebles.add(convertResultSetToInmueble(rs));
		}
		return inmuebles;
	}



	/**
	 * Metodo que obtiene la informacion del inmueble en la Base de Datos que tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/> 
	 * @param id el identificador del inmueble
	 * @return la informacion del inmueble que cumple con los criterios de la sentecia SQL
	 * 			Null si no existe el inmueble conlos criterios establecidos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public InmuebleVO getInmueble(Long id) throws SQLException, Exception 
	{
		InmuebleVO inmueble = null;

		String sql = String.format("SELECT * FROM INMUEBLE WHERE ID = '%2$s'", USUARIO, id); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		System.out.println(sql);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			inmueble = convertResultSetToInmueble(rs);
		}

		return inmueble;
	}

	/**
	 * Metodo que agregar la informacion de un nuevo inmueble en la Base de Datos a partir del parametro ingresado<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param inmueble Bebedor que desea agregar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void addInmueble(InmuebleVO inmueble) throws SQLException, Exception {

		String sql = String.format("INSERT INTO INMUEBLE (AMOBLADO, SERVICIOS, CABLE, ADMINISTRACION, PRECIO, DIRECCION, DUENO) VALUES (%2$s, '%3$s', '%4$s', '%5$s','%6$s','%7$s')", 
				USUARIO, 
				inmueble.getAmoblado(),
				inmueble.getServicios(),
				inmueble.getCable(),
				inmueble.getAdministracion(),
				inmueble.getPrecio(), 
				inmueble.getDireccion(),
				inmueble.getDueno().getCedula()
				);
		System.out.println(sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}

	/**
	 * Metodo que actualiza la informacion del inmueble en la Base de Datos que tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param inmueble Bebedor que desea actualizar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void updateInmueble(InmuebleVO inmueble) throws SQLException, Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(String.format("UPDATE INMUEBLE SET ", USUARIO));
		sql.append(String.format("AMOBLADO = '%1$s' AND SERVICIOS = '%2$s' AND CABLE = '%3$s' AND ADMINISTRACION = '%4$s' AND PRECIO = '%5$s' AND DIRECCION = '%6$s' AND DUENO = '%7$s'"
				, inmueble.getAmoblado(), inmueble.getServicios(), inmueble.getCable(),inmueble.getAdministracion(),inmueble.getPrecio(),inmueble.getDireccion(), inmueble.getDueno().getCedula()));

		System.out.println(sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Metodo que actualiza la informacion del inmueble en la Base de Datos que tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param inmueble Bebedor que desea actualizar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void deleteInmueble(InmuebleVO inmueble) throws SQLException, Exception {

		String sql = String.format("DELETE FROM INMUEBLE WHERE DIRECCION = %2$d AND USUARIO = %3$s", USUARIO, inmueble.getDireccion(), inmueble.getDueno().getCedula());

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
	 * @param resultSet ResultSet con la informacion de un inmueble que se obtuvo de la base de datos.
	 * @return Bebedor cuyos atributos corresponden a los valores asociados a un registro particular de la tabla BEBEDORES.
	 * @throws SQLException Si existe algun problema al extraer la informacion del ResultSet.
	 */
	public InmuebleVO convertResultSetToInmueble(ResultSet resultSet) throws SQLException {
		
		DAOUsuario daoUSSR = new DAOUsuario();
		
		Integer amobladoI = resultSet.getInt("AMOBLADO");
		Integer serviciosI = resultSet.getInt("SERVICIOS");
		Integer cableI = resultSet.getInt("CABLE");
		Integer administracionI = resultSet.getInt("ADMINISTRACION");
		Integer precio = resultSet.getInt("PRECIO");
		String direccion = resultSet.getString("DIRECCION");
		String cedula = resultSet.getString("DUENO");
		

		boolean amoblado = (amobladoI == 1)? true : false;
		boolean servicios = (serviciosI == 1)? true : false;
		boolean cable = (cableI == 1)? true : false;
		boolean administracion = (administracionI == 1)? true : false;
		UsuarioVO dueno;
		try {
			daoUSSR.setConn(conn);
			dueno = daoUSSR.getUsuario(cedula);
			InmuebleVO beb = new InmuebleVO(); 
			beb.setAdministracion(administracion);
			beb.setAmoblado(amoblado);
			beb.setCable(cable);
			beb.setDireccion(direccion);
			beb.setDueno(dueno);
			beb.setPrecio(precio);
			beb.setServicios(servicios);
			return beb;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}

}
