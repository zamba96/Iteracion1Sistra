package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.MejoresVO;
import vos.MejoresVO;

public class DAOOperador {

	//----------------------------------------------------------------------------------------------------------------------------------
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
	public DAOOperador() {
		recursos = new ArrayList<Object>();
	}
	
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
	public ArrayList<MejoresVO> getMejoresOperadores() throws SQLException, Exception {
		ArrayList<MejoresVO> hostales = new ArrayList<MejoresVO>();

		// Aclaracion: Por simplicidad, solamente se obtienen los primeros 50
		// resultados de la consulta
		String sql = String.format("");

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			hostales.add(convertResultSetToMejores(rs));
		}
		return hostales;
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
		public MejoresVO convertResultSetToMejores(ResultSet resultSet) throws SQLException {

			String mejorO = resultSet.getString("MEJOR_OPERDADOR");
			String peorO = resultSet.getString("PEOR_OPERDADOR");
			String mejorA = resultSet.getString("MEJOR_ALOJAMIENTO");
			String peorA = resultSet.getString("PEOR_ALOJAMIENTO");
			
			Long mejorOperador = Long.parseLong(mejorO);
			Long peorOperador = Long.parseLong(peorO);
			Long mejorAlojamiento = Long.parseLong(mejorA);
			Long peorAlojamiento = Long.parseLong(peorA);
			
			MejoresVO beb = new MejoresVO();
			beb.setMejorOperador(mejorOperador);
			beb.setPeorAlojamiento(peorAlojamiento);
			beb.setPeorOperador(peorOperador);
			beb.setMejorAlojamiento(mejorAlojamiento);

			return beb;
		}
}
