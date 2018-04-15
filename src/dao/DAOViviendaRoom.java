/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.ViviendaRoomVO;
import vos.ViviendaUVO;

/**
 * @author camilo
 *
 */
public class DAOViviendaRoom {
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
	public DAOViviendaRoom() {
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
	public ArrayList<ViviendaRoomVO> getViviendaRooms() throws SQLException, Exception {
		ArrayList<ViviendaRoomVO> cuartos = new ArrayList<ViviendaRoomVO>();

		//Aclaracion: Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
		String sql = String.format("SELECT * FROM %1$s.VIVIENDAROOM WHERE ROWNUM <= 50", USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			cuartos.add(convertResultSetToViviendaRoom(rs));
		}
		return cuartos;
	}
	
	
	/**
	 * Metodo que obtiene la informacion del viviendaroom en la Base de Datos que tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/> 
	 * @param id el identificador del viviendaroom
	 * @return la informacion del viviendaroom que cumple con los criterios de la sentecia SQL
	 * 			Null si no existe el viviendaroom conlos criterios establecidos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public ViviendaRoomVO findRoomByVivienda(String vivienda) throws SQLException, Exception 
	{
		ViviendaRoomVO viviendaroom = null;

		String sql = String.format("SELECT * FROM %1$s.VIVIENDAROOM WHERE VIVIENDA = %2$d", USUARIO, vivienda); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			viviendaroom = convertResultSetToViviendaRoom(rs);
		}

		return viviendaroom;
	}
	
	/**
	 * Metodo que obtiene la informacion del viviendaroom en la Base de Datos que tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/> 
	 * @param id el identificador del viviendaroom
	 * @return la informacion del viviendaroom que cumple con los criterios de la sentecia SQL
	 * 			Null si no existe el viviendaroom conlos criterios establecidos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public ViviendaRoomVO getViviendaRoom(String vivienda,Integer cuarto) throws SQLException, Exception 
	{
		ViviendaRoomVO viviendaroom = null;

		String sql = String.format("SELECT * FROM %1$s.VIVIENDAROOM WHERE VIVIENDA = %2$d AND CUARTO = %3$d" , USUARIO, vivienda,cuarto); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			viviendaroom = convertResultSetToViviendaRoom(rs);
		}

		return viviendaroom;
	}
	
	/**
	 * Metodo que agregar la informacion de un nuevo viviendaroom en la Base de Datos a partir del parametro ingresado<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param viviendaroom ViviendaRoomVO que desea agregar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void addViviendaRoom(ViviendaRoomVO viviendaroom) throws SQLException, Exception {

		String sql = String.format("INSERT INTO %1$s.VIVIENDAROOM (COMPARTIDA, RESTAURANTE, SALADEESTUDIO, "
				+ "SALADEESPARCIMIENTO, GYM, PRECIO, VIVIENDA, CUARTO, RESERVAS) "
				+ "VALUES (%2$s, '%3$s', '%4$s', '%5$s','%6$s', %7$s, '%8$s', '%9$s', '%10$s')", 
									USUARIO, 
									viviendaroom.getCompartida(), 
									viviendaroom.getRestaurante(),
									viviendaroom.getSalaDeEstudio(), 
									viviendaroom.getSalaDeEsparcimiento(),
									viviendaroom.getGym(),
									viviendaroom.getPrecio(), 
									viviendaroom.getVivienda(),
									viviendaroom.getCuarto(),
									viviendaroom.getReservas());
		System.out.println(sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	/**
	 * Metodo que actualiza la informacion del viviendaroom en la Base de Datos que tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param viviendaroom ViviendaRoomVO que desea actualizar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void updateViviendaRoom(ViviendaRoomVO viviendaroom) throws SQLException, Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(String.format("UPDATE %s.VIVIENDAROOM SET ", USUARIO));
		sql.append(String.format("COMPARTIDA = '%1$s' AND RESTAURANTE = '%2$s' AND SALADEESTUDIO = '%3$s' AND SALADEESPARCIMIENTO = '%4$s' AND GYM = '%5$s' AND PRECIO = '%6$s' "
				+ "AND VIVIENDA = '%7$s' AND CUARTO = '%8$s' AND RESERVAS = '%9$s' ",
				viviendaroom.getCompartida(), 
				viviendaroom.getRestaurante(),
				viviendaroom.getSalaDeEstudio(), 
				viviendaroom.getSalaDeEsparcimiento(),
				viviendaroom.getGym(),
				viviendaroom.getPrecio(), 
				viviendaroom.getVivienda(),
				viviendaroom.getCuarto(),
				viviendaroom.getReservas()));
		
		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Metodo que actualiza la informacion del viviendaroom en la Base de Datos que tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param viviendaroom ViviendaRoomVO que desea actualizar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void deleteViviendaRoom(ViviendaRoomVO viviendaroom) throws SQLException, Exception {

		String sql = String.format("DELETE FROM %1$s.VIVIENDAROOM WHERE VIVIENDA = %2$d AND CUARTO = %3$d", USUARIO, viviendaroom.getVivienda(),viviendaroom.getCuarto());

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
	 * Metodo que transforma el resultado obtenido de una consulta SQL (sobre la tabla VIVIENDAROOM) en una instancia de la clase ViviendaRoomVO.
	 * @param resultSet ResultSet con la informacion de un viviendaroom que se obtuvo de la base de datos.
	 * @return ViviendaRoomVO cuyos atributos corresponden a los valores asociados a un registro particular de la tabla VIVIENDAROOM.
	 * @throws SQLException Si existe algun problema al extraer la informacion del ResultSet.
	 */
	public ViviendaRoomVO convertResultSetToViviendaRoom(ResultSet resultSet) throws SQLException {
	
		DAOViviendaU dao = new DAOViviendaU();
		ViviendaUVO vivienda;
		try {
			Integer compartida = resultSet.getInt("COMPARTIDA");
			Double restaurante = resultSet.getDouble("RESTAURANTE");
			Double salaDeEstudio = resultSet.getDouble("SALADEESTUDIO");
			Double salaDeEsparcimiento = resultSet.getDouble("SALADEESPARCIMIENTO");
			Double gym = resultSet.getDouble("GYM");
			Double precio = resultSet.getDouble("PRECIO");
			String viviendaS = resultSet.getString("VIVIENDA");
			Long cuarto = resultSet.getLong("CUARTO");


			
			vivienda = dao.getVivienda(viviendaS);
			ViviendaRoomVO hostR = new ViviendaRoomVO(compartida, restaurante, salaDeEstudio, salaDeEsparcimiento, gym, precio, vivienda.getId(), cuarto);
			return hostR;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		

	}
}
