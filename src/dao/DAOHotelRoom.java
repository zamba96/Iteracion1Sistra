/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.HotelRoomVO;
import vos.HotelVO;

/**
 * @author camilo
 *
 */
public class DAOHotelRoom {
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
			public DAOHotelRoom() {
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
			public ArrayList<HotelRoomVO> getHotelRooms() throws SQLException, Exception {
				ArrayList<HotelRoomVO> cuartos = new ArrayList<HotelRoomVO>();

				//Aclaracion: Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
				String sql = String.format("SELECT * FROM %1$s.HOTELROOMS WHERE ROWNUM <= 50", USUARIO);

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				while (rs.next()) {
					cuartos.add(convertResultSetToHotelRoom(rs));
				}
				return cuartos;
			}
			
			
			/**
			 * Metodo que obtiene la informacion del hotelroom en la Base de Datos que tiene el identificador dado por parametro<br/>
			 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/> 
			 * @param id el identificador del hotelroom
			 * @return la informacion del hotelroom que cumple con los criterios de la sentecia SQL
			 * 			Null si no existe el hotelroom conlos criterios establecidos
			 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
			 * @throws Exception Si se genera un error dentro del metodo.
			 */
			public HotelRoomVO findRoomByHotel(String hotel) throws SQLException, Exception 
			{
				HotelRoomVO hotelroom = null;

				String sql = String.format("SELECT * FROM %1$s.HOTELROOMS WHERE HOTEL = %2$d", USUARIO, hotel); 

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				if(rs.next()) {
					hotelroom = convertResultSetToHotelRoom(rs);
				}

				return hotelroom;
			}
			
			/**
			 * Metodo que obtiene la informacion del hotelroom en la Base de Datos que tiene el identificador dado por parametro<br/>
			 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/> 
			 * @param id el identificador del hotelroom
			 * @return la informacion del hotelroom que cumple con los criterios de la sentecia SQL
			 * 			Null si no existe el hotelroom conlos criterios establecidos
			 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
			 * @throws Exception Si se genera un error dentro del metodo.
			 */
			public HotelRoomVO getHotelRoom(String hotel,Integer cuarto) throws SQLException, Exception 
			{
				HotelRoomVO hotelroom = null;

				String sql = String.format("SELECT * FROM %1$s.HOTELROOMS WHERE HOTEL = %2$d AND CUARTO = %3$d" , USUARIO, hotel,cuarto); 

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				if(rs.next()) {
					hotelroom = convertResultSetToHotelRoom(rs);
				}

				return hotelroom;
			}
			
			/**
			 * Metodo que agregar la informacion de un nuevo hotelroom en la Base de Datos a partir del parametro ingresado<br/>
			 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
			 * @param hotelroom HotelRoomVO que desea agregar a la Base de Datos
			 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
			 * @throws Exception Si se genera un error dentro del metodo.
			 */
			public void addHotelRoom(HotelRoomVO hotelroom) throws SQLException, Exception {

				String sql = String.format("INSERT INTO %1$s.HOTELROOMS (BANERA, YAKUZI, SALA, COCINA, CABLE, PRECIO, HOTEL, TIPO, CAPACIDAD, CUARTO, RESERVAS) "
						+ "VALUES (%2$s, '%3$s', '%4$s', '%5$s','%6$s', %7$s, '%8$s', '%9$s', '%10$s','%11$s','%12$s')", 
											USUARIO, 
											hotelroom.getBanera(), 
											hotelroom.getYakuzi(),
											hotelroom.getSala(), 
											hotelroom.getCocina(),
											hotelroom.getCable(),
											hotelroom.getPrecio(), 
											hotelroom.getHotel(),
											hotelroom.getTipo(),
											hotelroom.getCapacidad(), 
											hotelroom.getCuarto(),
											hotelroom.getReservas());
				System.out.println(sql);

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();

			}
			
			/**
			 * Metodo que actualiza la informacion del hotelroom en la Base de Datos que tiene el identificador dado por parametro<br/>
			 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
			 * @param hotelroom HotelRoomVO que desea actualizar a la Base de Datos
			 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
			 * @throws Exception Si se genera un error dentro del metodo.
			 */
			public void updateHotelRoom(HotelRoomVO hotelroom) throws SQLException, Exception {

				StringBuilder sql = new StringBuilder();
				sql.append(String.format("UPDATE %s.HOTELROOMS SET ", USUARIO));
				sql.append(String.format("BANERA = '%1$s' AND YAKUZI = '%2$s' AND SALA = '%3$s' AND COCINA = '%4$s' AND CABLE = '%5$s' AND PRECIO = '%6$s' "
						+ "AND HOTEL = '%7$s' AND TIPO = '%8$s' AND CAPACIDAD = '%9$s' AND CUARTO = '%10$s' AND RESERVAS = '%11$s' ",
						hotelroom.getBanera(), 
						hotelroom.getYakuzi(),
						hotelroom.getSala(), 
						hotelroom.getCocina(),
						hotelroom.getCable(),
						hotelroom.getPrecio(), 
						hotelroom.getHotel(),
						hotelroom.getTipo(),
						hotelroom.getCapacidad(), 
						hotelroom.getCuarto(),
						hotelroom.getReservas()));
				
				System.out.println(sql);
				
				PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
				recursos.add(prepStmt);
				prepStmt.executeQuery();
			}

			/**
			 * Metodo que actualiza la informacion del hotelroom en la Base de Datos que tiene el identificador dado por parametro<br/>
			 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
			 * @param hotelroom HotelRoomVO que desea actualizar a la Base de Datos
			 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
			 * @throws Exception Si se genera un error dentro del metodo.
			 */
			public void deleteHotelRoom(HotelRoomVO hotelroom) throws SQLException, Exception {

				String sql = String.format("DELETE FROM %1$s.HOTELROOMS WHERE HOSTAL = %2$d AND CUARTO = %3$d", USUARIO, hotelroom.getHotel(),hotelroom.getCuarto());

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
			 * Metodo que transforma el resultado obtenido de una consulta SQL (sobre la tabla HOTELROOMS) en una instancia de la clase HotelRoomVO.
			 * @param resultSet ResultSet con la informacion de un hotelroom que se obtuvo de la base de datos.
			 * @return HotelRoomVO cuyos atributos corresponden a los valores asociados a un registro particular de la tabla HOTELROOMS.
			 * @throws SQLException Si existe algun problema al extraer la informacion del ResultSet.
			 */
			public HotelRoomVO convertResultSetToHotelRoom(ResultSet resultSet) throws SQLException {
			
				DAOHotel dao = new DAOHotel();
				HotelVO hotel;
				try {
					Integer baneraI = resultSet.getInt("BANERA");
					Integer yakuziI = resultSet.getInt("YAKUZI");
					Integer salaI = resultSet.getInt("SALA");
					Integer cocinaI = resultSet.getInt("COCINA");
					Integer cableI = resultSet.getInt("CABLE");
					Double precio = resultSet.getDouble("PRECIO");
					String hotelS = resultSet.getString("HOTEL");
					String tipo = resultSet.getString("TIPO");
					Integer capacidad = resultSet.getInt("CAPACIDAD");
					Integer cuarto = resultSet.getInt("CUARTO");

					boolean banera = baneraI ==1;
					boolean yakuzi = yakuziI ==1;
					boolean sala = salaI ==1;
					boolean cocina = cocinaI ==1;
					boolean cable = cableI ==1;

					
					hotel = dao.getHotel(hotelS);
					HotelRoomVO hostR = new HotelRoomVO(banera, yakuzi, sala, cocina, cable, precio, hotel, tipo, capacidad, cuarto);
					return hostR;

				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}

				

			}
}
