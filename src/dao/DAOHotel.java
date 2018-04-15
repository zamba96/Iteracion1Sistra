/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.HotelVO;

/**
 * @author camilo
 *
 */
public class DAOHotel {
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
			public DAOHotel() {
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
			public ArrayList<HotelVO> getHoteles() throws SQLException, Exception {
				ArrayList<HotelVO> hoteles = new ArrayList<HotelVO>();

				//Aclaracion: Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
				String sql = String.format("SELECT * FROM %1$s.HOTEL WHERE ROWNUM <= 50", USUARIO);

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				while (rs.next()) {
					hoteles.add(convertResultSetToHotel(rs));
				}
				return hoteles;
			}
			
			

			/**
			 * Metodo que obtiene la informacion del hotel en la Base de Datos que tiene el identificador dado por parametro<br/>
			 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/> 
			 * @param id el identificador del hotel
			 * @return la informacion del hotel que cumple con los criterios de la sentecia SQL
			 * 			Null si no existe el hotel conlos criterios establecidos
			 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
			 * @throws Exception Si se genera un error dentro del metodo.
			 */
			public HotelVO getHotel(String nombre) throws SQLException, Exception 
			{
				HotelVO hotel = null;

				String sql = String.format("SELECT * FROM %1$s.HOTEL WHERE NOMBRE = %2$d", USUARIO, nombre); 

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				if(rs.next()) {
					hotel = convertResultSetToHotel(rs);
				}

				return hotel;
			}
			
			/**
			 * Metodo que agregar la informacion de un nuevo hotel en la Base de Datos a partir del parametro ingresado<br/>
			 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
			 * @param hotel Bebedor que desea agregar a la Base de Datos
			 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
			 * @throws Exception Si se genera un error dentro del metodo.
			 */
			public void addHotel(HotelVO hotel) throws SQLException, Exception {

				String sql = String.format("INSERT INTO %1$s.HOTEL (DIRECCION, DESAYUNO, RESTAURANTE, PISCINA,PARQUEADERO,HORARIO,NOMBRE,CUARTOS) VALUES (%2$s, '%3$s', '%4$s', '%5$s','%6$s','%7$s')", 
											USUARIO, 
											hotel.getDireccion(), 
											hotel.getDesayuno(),
											hotel.getRestaurante(), 
											hotel.getPiscina(),
											hotel.getParqueadero(), 
											hotel.getHorario(),
											hotel.getNombre(),
											hotel.getCuartos()
											);
				System.out.println(sql);

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();

			}
			
			/**
			 * Metodo que actualiza la informacion del hotel en la Base de Datos que tiene el identificador dado por parametro<br/>
			 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
			 * @param hotel Bebedor que desea actualizar a la Base de Datos
			 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
			 * @throws Exception Si se genera un error dentro del metodo.
			 */
			public void updateHotel(HotelVO hotel) throws SQLException, Exception {

				StringBuilder sql = new StringBuilder();
				sql.append(String.format("UPDATE %s.HOTEL SET ", USUARIO));
				sql.append(String.format("DIRECCION = '%1$s' AND DESAYUNO = '%2$s' AND RESTAURANTE = '%3$s' AND PISCINA = '%4$s' AND PARQUEADERO = '%5$s' AND HORARIO = '%6$s' AND NOMBRE = '%7$s' AND CUARTOS = '%8$s' ",
						hotel.getDireccion(),
						hotel.getDesayuno(), 
						hotel.getRestaurante(),
						hotel.getPiscina(),
						hotel.getParqueadero(),
						hotel.getHorario(),
						hotel.getNombre(),
						hotel.getCuartos()));
				
				System.out.println(sql);
				
				PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
				recursos.add(prepStmt);
				prepStmt.executeQuery();
			}

			/**
			 * Metodo que actualiza la informacion del hotel en la Base de Datos que tiene el identificador dado por parametro<br/>
			 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
			 * @param hotel Bebedor que desea actualizar a la Base de Datos
			 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
			 * @throws Exception Si se genera un error dentro del metodo.
			 */
			public void deleteHotel(HotelVO hotel) throws SQLException, Exception {

				String sql = String.format("DELETE FROM %1$s.HOTEL WHERE NOMBRE = %2$d", USUARIO, hotel.getNombre());

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
			 * @param resultSet ResultSet con la informacion de un hotel que se obtuvo de la base de datos.
			 * @return Bebedor cuyos atributos corresponden a los valores asociados a un registro particular de la tabla BEBEDORES.
			 * @throws SQLException Si existe algun problema al extraer la informacion del ResultSet.
			 */
			public HotelVO convertResultSetToHotel(ResultSet resultSet) throws SQLException {
			
				String direccion = resultSet.getString("DIRECCION");
				Integer desayunoI = resultSet.getInt("DESAYUNO");
				Integer restauranteI = resultSet.getInt("RESTAURANTE");
				Integer piscinaI = resultSet.getInt("PISCINA");
				Integer parqueaderoI = resultSet.getInt("PARQUEDAERO");
				Integer horarioI = resultSet.getInt("HORARIO");
				String nombre = resultSet.getString("NOMBRE");
				
				boolean desayuno = desayunoI == 1;
				boolean restaurante = restauranteI ==1;
				boolean piscina = piscinaI==1;
				boolean parqueadero = parqueaderoI==1;
				boolean horario = horarioI==1;
				
				HotelVO beb = new HotelVO(desayuno, restaurante, piscina, direccion,nombre,parqueadero,horario);

				return beb;
			}
}
