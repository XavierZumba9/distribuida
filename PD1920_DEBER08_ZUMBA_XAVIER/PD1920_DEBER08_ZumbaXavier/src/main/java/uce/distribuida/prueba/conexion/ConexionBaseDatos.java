package uce.distribuida.prueba.conexion;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

@ApplicationScoped
public class ConexionBaseDatos {

    static BasicDataSource poolConexiones= null;	
    static final String DRIVER= "org.postgresql.Driver";
    static final String USUARIO= "postgres";
	static final String CLAVE = "root";
	static final String URL = "jdbc:postgresql://localhost:5432/distribuida";
	
	@ApplicationScoped
	@Produces
	public DataSource conexionBaseDatos () {	
		if(null==poolConexiones) {
			poolConexiones.setDriverClassName(DRIVER);
			poolConexiones.setUsername(USUARIO);
			poolConexiones.setPassword(CLAVE);
			poolConexiones.setUrl(URL);
			
			poolConexiones.setMinIdle(10);
			poolConexiones.setMaxIdle(50);
		}
		return poolConexiones;
		
	}
	
	
	public DataSource getDataSource() {
		return poolConexiones;
	}
}
