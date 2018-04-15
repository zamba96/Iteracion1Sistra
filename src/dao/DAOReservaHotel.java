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
import vos.ReservaHotelVO;

/**
 * @author camilo
 *
 */
public class DAOReservaHotel {
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
			public DAOReservaHotel() {
				recursos = new ArrayList<Object>();
			}
			
			
			/**
			 * Metodo que obtiene la informacion de todos los bebedores en la Base de Datos <br/>
			 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
			 * @return	lista con la informacion de todos los bebedores que se encuentran en la Base de Datos
			 * @throws SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
			 * @throws Exception Si se genera un error dentro del metodo.
			 */
			public ArrayList<ReservaHotelVO> getReservasHoteles() throws SQLException, Exception {
				ArrayList<ReservaHotelVO> reservas = new ArrayList<ReservaHotelVO>();

				//Aclaracion: Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
				String sql = String.format("SELECT * FROM RESERVAHOTEL WHERE ROWNUM <= 50", USUARIO);

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				while (rs.next()) {
					reservas.add(convertResultSetToReservaHotel(rs));
				}
				return reservas;
			}
			
			/**
			 * Metodo que obtiene la informacion del reserva en la Base de Datos que tiene el identificador dado por parametro<br/>
			 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/> 
			 * @param id el identificador del reserva
			 * @return la informacion del reserva que cumple con los criterios de la sentecia SQL
			 * 			Null si no existe el reserva conlos criterios establecidos
			 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
			 * @throws Exception Si se genera un error dentro del metodo.
			 */
			public ReservaHotelVO getReservaHotel(String fechaI,String fechaF, String usuario) throws SQLException, Exception 
			{
				ReservaHotelVO reserva = null;

				String sql = String.format("SELECT * FROM RESERVAHOTEL WHERE FECHAINICIO = '%1$s' AND FECHAFIN = '%2$s' AND USUARIO = '%3$s'", USUARIO ); 

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				if(rs.next()) {
					reserva = convertResultSetToReservaHotel(rs);
				}

				return reserva;
			}
			
			/**
			 * Metodo que agregar la informacion de un nuevo reserva en la Base de Datos a partir del parametro ingresado<br/>
			 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
			 * @param reserva ReservaHotelVO que desea agregar a la Base de Datos
			 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
			 * @throws Exception Si se genera un error dentro del metodo.
			 */
			public void addReservaHotel(ReservaHotelVO reserva) throws SQLException, Exception {

				String sql = String.format("INSERT INTO RESERVAHOTEL (FECHAINICIO, FECHAFIN, CUARTO, USUARIO) VALUES (%1$s, '%2$s', '%3$s', '%4$s')", 
											USUARIO, 
											reserva.getFechaI(), 
											reserva.getFechaF(),
											reserva.getCuarto(), 
											reserva.getUsuario());
				System.out.println(sql);

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();

			}
			
			/**
			 * Metodo que actualiza la informacion del reserva en la Base de Datos que tiene el identificador dado por parametro<br/>
			 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
			 * @param reserva ReservaHotelVO que desea actualizar a la Base de Datos
			 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
			 * @throws Exception Si se genera un error dentro del metodo.
			 */
			public void updateReservaHotel(ReservaHotelVO reserva) throws SQLException, Exception {

				StringBuilder sql = new StringBuilder();
				sql.append(String.format("UPDATE %s.RESERVAHOTEL SET ", USUARIO));
				sql.append(String.format("FECHAINICIO = '%1$s' AND FECHAFIN = '%2$s' AND CUARTO = '%3$s' AND USUARIO = '%4$s' ", reserva.getFechaI(), reserva.getFechaF(), reserva.getCuarto()));
				
				System.out.println(sql);
				
				PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
				recursos.add(prepStmt);
				prepStmt.executeQuery();
			}

			/**
			 * Metodo que actualiza la informacion del reserva en la Base de Datos que tiene el identificador dado por parametro<br/>
			 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
			 * @param reserva ReservaHotelVO que desea actualizar a la Base de Datos
			 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
			 * @throws Exception Si se genera un error dentro del metodo.
			 */
			public void deleteReservaHostal(ReservaHotelVO reserva) throws SQLException, Exception {

				String sql = String.format("DELETE FROM RESERVAHOTEL WHERE FECHAI = %1$d AND FECHAFIN = %2$d AND USUARIO = %3$d", USUARIO,
						reserva.getFechaI(),
						reserva.getFechaF(),
						reserva.getUsuario());

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
			 * Metodo que transforma el resultado obtenido de una consulta SQL (sobre la tabla RESERVAHOTEL) en una instancia de la clase ReservaHotelVO.
			 * @param resultSet ResultSet con la informacion de un reserva que se obtuvo de la base de datos.
			 * @return ReservaHotelVO cuyos atributos corresponden a los valores asociados a un registro particular de la tabla RESERVAHOTEL.
			 * @throws SQLException Si existe algun problema al extraer la informacion del ResultSet.
			 */
			public ReservaHotelVO convertResultSetToReservaHotel(ResultSet resultSet) throws SQLException {
			
				DAOHotelRoom dao = new DAOHotelRoom();
				HotelRoomVO cuarto;
				try {
					String fechaI = resultSet.getString("FECHAINICIO");
					String fechaF = resultSet.getString("FECHAFIN");
					Integer cuartoS = resultSet.getInt("CUARTO");
					String hotelS = resultSet.getString("HOTEL");
					cuarto = dao.getHotelRoom(hotelS, cuartoS);
					
					ReservaHotelVO beb = new ReservaHotelVO(fechaI, fechaF, cuarto.getId());
					return beb;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
				
			}
}
