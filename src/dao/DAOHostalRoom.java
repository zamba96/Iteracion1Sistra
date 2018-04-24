/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.HostalRoomVO;
import vos.HostalVO;


/**
 * @author camilo
 *
 */
public class DAOHostalRoom {
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
		public DAOHostalRoom() {
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
		public ArrayList<HostalRoomVO> getHostalRooms() throws SQLException, Exception {
			ArrayList<HostalRoomVO> cuartos = new ArrayList<HostalRoomVO>();

			//Aclaracion: Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			String sql = String.format("SELECT * FROM HOSTALROOM WHERE ROWNUM <= 50", USUARIO);

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				cuartos.add(convertResultSetToHostalRoom(rs));
			}
			return cuartos;
		}
		
		
		/**
		 * Metodo que obtiene la informacion del hostalroom en la Base de Datos que tiene el identificador dado por parametro<br/>
		 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/> 
		 * @param id el identificador del hostalroom
		 * @return la informacion del hostalroom que cumple con los criterios de la sentecia SQL
		 * 			Null si no existe el hostalroom conlos criterios establecidos
		 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
		 * @throws Exception Si se genera un error dentro del metodo.
		 */
		public HostalRoomVO findRoomByHostal(String hotel) throws SQLException, Exception 
		{
			HostalRoomVO hostalroom = null;

			String sql = String.format("SELECT * FROM HOSTALROOM WHERE HOSTAL = %1$s", USUARIO, hotel); 

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			if(rs.next()) {
				hostalroom = convertResultSetToHostalRoom(rs);
			}

			return hostalroom;
		}
		
		/**
		 * Metodo que obtiene la informacion del hostalroom en la Base de Datos que tiene el identificador dado por parametro<br/>
		 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/> 
		 * @param id el identificador del hostalroom
		 * @return la informacion del hostalroom que cumple con los criterios de la sentecia SQL
		 * 			Null si no existe el hostalroom conlos criterios establecidos
		 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
		 * @throws Exception Si se genera un error dentro del metodo.
		 */
		public HostalRoomVO getHostalRoom(Long id) throws SQLException, Exception 
		{
			HostalRoomVO hostalroom = null;

			String sql = String.format("SELECT * FROM HOSTALROOM WHERE ID = %1$s" , USUARIO, id); 

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			if(rs.next()) {
				hostalroom = convertResultSetToHostalRoom(rs);
			}

			return hostalroom;
		}
		
		/**
		 * Metodo que agregar la informacion de un nuevo hostalroom en la Base de Datos a partir del parametro ingresado<br/>
		 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
		 * @param hostalroom HostalRoomVO que desea agregar a la Base de Datos
		 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
		 * @throws Exception Si se genera un error dentro del metodo.
		 */
		public void addHostalRoom(HostalRoomVO hostalroom) throws SQLException, Exception {

			String sql = String.format("INSERT INTO HOSTALROOM (ID,CUARTO, CAPACIDAD, PRECIO, HOSTAL,RESERVAS) VALUES (%2$s, '%3$s', '%4$s', '%5$s','%6$s','%7$s')", 
										USUARIO, 
										hostalroom.getId(),
										hostalroom.getCuarto(), 
										hostalroom.getCapacidad(),
										hostalroom.getPrecio(), 
										hostalroom.getHostal(),
										hostalroom.getReservas());
			System.out.println(sql);

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();

		}
		
		/**
		 * Metodo que actualiza la informacion del hostalroom en la Base de Datos que tiene el identificador dado por parametro<br/>
		 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
		 * @param hostalroom HostalRoomVO que desea actualizar a la Base de Datos
		 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
		 * @throws Exception Si se genera un error dentro del metodo.
		 */
		public void updateHostalRoom(HostalRoomVO hostalroom) throws SQLException, Exception {

			StringBuilder sql = new StringBuilder();
			sql.append(String.format("UPDATE HOSTALROOM SET ", USUARIO));
			sql.append(String.format("CUARTO = '%1$s' AND CAPACIDAD = '%2$s' AND PRECIO = '%3$s' AND HOSTAL = '%4$s' AND RESERVAS = '%5$s' ",
					hostalroom.getCuarto(),hostalroom.getCapacidad(),hostalroom.getPrecio(),hostalroom.getHostal(),
					hostalroom.getReservas()));
			
			System.out.println(sql);
			
			PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
			recursos.add(prepStmt);
			prepStmt.executeQuery();
		}

		/**
		 * Metodo que actualiza la informacion del hostalroom en la Base de Datos que tiene el identificador dado por parametro<br/>
		 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
		 * @param hostalroom HostalRoomVO que desea actualizar a la Base de Datos
		 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
		 * @throws Exception Si se genera un error dentro del metodo.
		 */
		public void deleteHostalRoom(HostalRoomVO hostalroom) throws SQLException, Exception {

			String sql = String.format("DELETE FROM HOSTALROOM WHERE ID = %1$s", USUARIO, hostalroom.getId());

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
		 * Metodo que transforma el resultado obtenido de una consulta SQL (sobre la tabla HOSTALROOM) en una instancia de la clase HostalRoomVO.
		 * @param resultSet ResultSet con la informacion de un hostalroom que se obtuvo de la base de datos.
		 * @return HostalRoomVO cuyos atributos corresponden a los valores asociados a un registro particular de la tabla HOSTALROOM.
		 * @throws SQLException Si existe algun problema al extraer la informacion del ResultSet.
		 */
		public HostalRoomVO convertResultSetToHostalRoom(ResultSet resultSet) throws SQLException {
		
			
			try {
				Integer cuarto = resultSet.getInt("CUARTO");
				Integer capacidad = resultSet.getInt("CAPACIDAD");
				Double precio = resultSet.getDouble("PRECIO");
				String hostalS = resultSet.getString("HOSTAL");
				String ids = resultSet.getString("ID");
				
				Long id = Long.parseLong(ids);
				Long hostal = Long.parseLong(hostalS);
				
				HostalRoomVO hostR = new HostalRoomVO();
				hostR.setCapacidad(capacidad);
				hostR.setCuarto(cuarto);
				hostR.setHostal(hostal);
				hostR.setPrecio(precio);
				hostR.setId(id);
				
				return hostR;

			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}

			

		}
}
