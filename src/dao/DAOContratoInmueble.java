package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.ContratoInmuebleVO;
import vos.InmuebleVO;
import vos.UsuarioVO;

public class DAOContratoInmueble {
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
	public DAOContratoInmueble() {
		recursos = new ArrayList<Object>();
	}


	/**
	 * Metodo que obtiene la informacion de todos los bebedores en la Base de Datos <br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * @return	lista con la informacion de todos los bebedores que se encuentran en la Base de Datos
	 * @throws SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public ArrayList<ContratoInmuebleVO> getContratosInmueble() throws SQLException, Exception {
		ArrayList<ContratoInmuebleVO> contratos = new ArrayList<ContratoInmuebleVO>();

		//Aclaracion: Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
		String sql = String.format("SELECT * FROM CONTRATOINMUEBLE WHERE ROWNUM <= 50", USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			contratos.add(convertResultSetToContratoInmueble(rs));
		}
		return contratos;
	}

	/**
	 * Metodo que obtiene la informacion del contrato en la Base de Datos que tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/> 
	 * @param id el identificador del contrato
	 * @return la informacion del contrato que cumple con los criterios de la sentecia SQL
	 * 			Null si no existe el contrato conlos criterios establecidos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public ContratoInmuebleVO getContratoInmueble(String fechaI,String fechaF, String usuario) throws SQLException, Exception 
	{
		ContratoInmuebleVO contrato = null;

		String sql = String.format("SELECT * FROM CONTRATOINMUEBLE WHERE FECHAINICIO = '%2$s' AND FECHAFIN = '%3$s' AND USUARIO = '%4$s'", USUARIO, fechaI, fechaF, usuario ); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			contrato = convertResultSetToContratoInmueble(rs);
		}

		return contrato;
	}

	/**
	 * Metodo que agregar la informacion de un nuevo contrato en la Base de Datos a partir del parametro ingresado<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param contrato ContratoInmuebleVO que desea agregar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void addContratoInmueble(ContratoInmuebleVO contrato) throws SQLException, Exception {

		String sql = String.format("INSERT INTO CONTRATOINMUEBLE (FECHAINICIO, FECHAFIN, USUARIO, DUENO, DIRECCION) VALUES (%2$s, '%3$s', '%4$s', '%5$s', '%6$s')", 
				USUARIO, 
				contrato.getFechaInicio(),
				contrato.getFechaFinal(),
				contrato.getUsuario(), 
				contrato.getInmueble().getDueno().getCedula(),
				contrato.getInmueble().getDireccion());
		System.out.println(sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}

	/**
	 * Metodo que actualiza la informacion del contrato en la Base de Datos que tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param contrato ContratoInmuebleVO que desea actualizar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 
	public void updateContratoInmueble(ContratoInmuebleVO contrato) throws SQLException, Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(String.format("UPDATE %s.CONTRATOSINMUEBLES SET ", RELACIONADO));
		sql.append(String.format("FECHAI = '%1$s' AND FECHAF = '%2$s' AND CUARTO = '%3$s' AND RELACIONADO = '%4$s' ", contrato.getFechaI(), contrato.getFechaF(), contrato.getCuarto()));

		System.out.println(sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	*/

	/**
	 * Metodo que actualiza la informacion del contrato en la Base de Datos que tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param contrato ContratoInmuebleVO que desea actualizar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void deleteContratoInmueble(ContratoInmuebleVO contrato) throws SQLException, Exception {

		String sql = String.format("DELETE FROM CONTRATOINMUEBLE WHERE FECHAINICIO = %2$d AND FECHAFIN = %3$d AND USUARIO = %4$d", USUARIO, contrato.getFechaInicio(),contrato.getFechaFinal(),contrato.getUsuario().getCedula());

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
	 * Metodo que transforma el resultado obtenido de una consulta SQL (sobre la tabla CONTRATOSINMUEBLES) en una instancia de la clase ContratoInmuebleVO.
	 * @param resultSet ResultSet con la informacion de un contrato que se obtuvo de la base de datos.
	 * @return ContratoInmuebleVO cuyos atributos corresponden a los valores asociados a un registro particular de la tabla CONTRATOSINMUEBLES.
	 * @throws SQLException Si existe algun problema al extraer la informacion del ResultSet.
	 */
	public ContratoInmuebleVO convertResultSetToContratoInmueble(ResultSet resultSet) throws SQLException {

		DAOInmueble dao = new DAOInmueble();
		DAOUsuario ldao = new DAOUsuario();
		InmuebleVO inmueble;
		try {
			String fechaI = resultSet.getString("FECHAINICIO");
			String fechaF = resultSet.getString("FECHAFIN");
			String dueno = resultSet.getString("DUENO");
			String direccion = resultSet.getString("DIRECCION");
			String cedula = resultSet.getString("USUARIO");
			dao.setConn(conn);
			inmueble = dao.getInmueble(dueno, direccion);
			ldao.setConn(conn);
			UsuarioVO ussr = ldao.getUsuario(cedula);

			ContratoInmuebleVO beb = new ContratoInmuebleVO(fechaI, fechaF, ussr, inmueble);
			return beb;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
