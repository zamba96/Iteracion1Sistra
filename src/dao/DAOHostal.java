/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.HostalVO;



/**
 * @author camilo
 *
 */
public class DAOHostal {
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
		public DAOHostal() {
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
		public ArrayList<HostalVO> getHostales() throws SQLException, Exception {
			ArrayList<HostalVO> hostales = new ArrayList<HostalVO>();

			//Aclaracion: Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			String sql = String.format("SELECT * FROM HOSTALES WHERE ROWNUM <= 50", USUARIO);

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				hostales.add(convertResultSetToHostal(rs));
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
		public HostalVO getHostal(String nombre) throws SQLException, Exception 
		{
			HostalVO hostal = null;

			String sql = String.format("SELECT * FROM HOSTALES WHERE NOMBRE = %1$d", USUARIO, nombre); 

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			if(rs.next()) {
				hostal = convertResultSetToHostal(rs);
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
		public void addHostal(HostalVO hostal) throws SQLException, Exception {

			String sql = String.format("INSERT INTO HOSTALES (APERTURA, CIERRE, DESAYUNO, DIRECCION,NOMBRE,CUARTOS) VALUES (%1$s, '%2$s', '%3$s', '%4$s','%5$s','%6$s')", 
										USUARIO, 
										hostal.getApertura(), 
										hostal.getCierre(), 
										hostal.getDesayuno(), 
										hostal.getDireccion(),
										hostal.getNombre(),
										hostal.getCuartos()
										);
			System.out.println(sql);

			PreparedStatement prepStmt = conn.prepareStatement(sql);
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
		public void updateHostal(HostalVO hostal) throws SQLException, Exception {

			StringBuilder sql = new StringBuilder();
			sql.append(String.format("UPDATE HOSTALES SET ", USUARIO));
			sql.append(String.format("APERTURA = '%1$s' AND CIERRE = '%2$s' AND DESAYUNO = '%3$s' AND DIRECCION = '%4$s' AND NOMBRE = '%5$s' AND CUARTOS = '%6$s' ",
					hostal.getApertura(), 
					hostal.getCierre(),
					hostal.getDesayuno(),
					hostal.getDireccion(),
					hostal.getNombre(),
					hostal.getCuartos()));
			
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
		public void deleteHostal(HostalVO hostal) throws SQLException, Exception {

			String sql = String.format("DELETE FROM HOSTALES WHERE NOMBRE = %1$d", USUARIO, hostal.getNombre());

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
		public HostalVO convertResultSetToHostal(ResultSet resultSet) throws SQLException {
		
			Integer apertura = resultSet.getInt("APERTURA");
			Integer cierre = resultSet.getInt("CIERRE");
			Integer desayunoI = resultSet.getInt("DESAYUNO");
			String direccion = resultSet.getString("DIRECCION");
			String nombre = resultSet.getString("NOMBRE");
			
			boolean desayuno = desayunoI == 1;
			
			HostalVO beb = new HostalVO(apertura, cierre, desayuno, direccion,nombre);

			return beb;
		}
}
