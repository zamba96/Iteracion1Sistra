package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.ViviendaUVO;

/**
 * 
 * @author camilo
 *
 */
public class DAOViviendaU {
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
			public DAOViviendaU() {
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
			public ArrayList<ViviendaUVO> getViviendas() throws SQLException, Exception {
				ArrayList<ViviendaUVO> viviendas = new ArrayList<ViviendaUVO>();

				//Aclaracion: Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
				String sql = String.format("SELECT * FROM VIVIENDAU WHERE ROWNUM <= 50", USUARIO);

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				while (rs.next()) {
					viviendas.add(convertResultSetToVivienda(rs));
				}
				return viviendas;
			}
			
			

			/**
			 * Metodo que obtiene la informacion del vivienda en la Base de Datos que tiene el identificador dado por parametro<br/>
			 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/> 
			 * @param id el identificador del vivienda
			 * @return la informacion del vivienda que cumple con los criterios de la sentecia SQL
			 * 			Null si no existe el vivienda conlos criterios establecidos
			 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
			 * @throws Exception Si se genera un error dentro del metodo.
			 */
			public ViviendaUVO getVivienda(Long id) throws SQLException, Exception 
			{
				ViviendaUVO vivienda = null;

				String sql = String.format("SELECT * FROM VIVIENDAU WHERE ID = %1$s", USUARIO, id); 

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				if(rs.next()) {
					vivienda = convertResultSetToVivienda(rs);
				}

				return vivienda;
			}
			
			/**
			 * Metodo que agregar la informacion de un nuevo vivienda en la Base de Datos a partir del parametro ingresado<br/>
			 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
			 * @param vivienda Bebedor que desea agregar a la Base de Datos
			 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
			 * @throws Exception Si se genera un error dentro del metodo.
			 */
			public void addVivienda(ViviendaUVO vivienda) throws SQLException, Exception {

				String sql = String.format("INSERT INTO VIVIENDAU (ID,DIRECCION, NOMBRE, CUARTOS) VALUES (%1$s, %2$s, '%3$s', '%4$s')", 
											USUARIO, 
											vivienda.getId(),
											vivienda.getDireccion(),
											vivienda.getNombre(),
											vivienda.getCuartos()
											);
				System.out.println(sql);

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();

			}
			
			/**
			 * Metodo que actualiza la informacion del vivienda en la Base de Datos que tiene el identificador dado por parametro<br/>
			 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
			 * @param vivienda Bebedor que desea actualizar a la Base de Datos
			 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
			 * @throws Exception Si se genera un error dentro del metodo.
			 */
			public void updateHostal(ViviendaUVO vivienda) throws SQLException, Exception {

				StringBuilder sql = new StringBuilder();
				sql.append(String.format("UPDATE %s.VIVIENDAU SET ", USUARIO));
				sql.append(String.format("DIRECCION = '%1$s' AND NOMBRE = '%2$s' AND CUARTOS = '%3$s' ",
						vivienda.getDireccion(),
						vivienda.getNombre(),
						vivienda.getCuartos()));
				
				System.out.println(sql);
				
				PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
				recursos.add(prepStmt);
				prepStmt.executeQuery();
			}

			/**
			 * Metodo que actualiza la informacion del vivienda en la Base de Datos que tiene el identificador dado por parametro<br/>
			 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
			 * @param vivienda Bebedor que desea actualizar a la Base de Datos
			 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
			 * @throws Exception Si se genera un error dentro del metodo.
			 */
			public void deleteHostal(ViviendaUVO vivienda) throws SQLException, Exception {

				String sql = String.format("DELETE FROM VIVIENDAU WHERE ID = %1$d", USUARIO, vivienda.getId());

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
			 * @param resultSet ResultSet con la informacion de un vivienda que se obtuvo de la base de datos.
			 * @return Bebedor cuyos atributos corresponden a los valores asociados a un registro particular de la tabla BEBEDORES.
			 * @throws SQLException Si existe algun problema al extraer la informacion del ResultSet.
			 */
			public ViviendaUVO convertResultSetToVivienda(ResultSet resultSet) throws SQLException {
			
				String direccion = resultSet.getString("DIRECCION");
				String nombre = resultSet.getString("NOMBRE");
				Long id = resultSet.getLong("ID");

				
				ViviendaUVO beb = new ViviendaUVO();
				beb.setDireccion(direccion);
				beb.setNombre(nombre);
				beb.setId(id);

				return beb;
			}
}
