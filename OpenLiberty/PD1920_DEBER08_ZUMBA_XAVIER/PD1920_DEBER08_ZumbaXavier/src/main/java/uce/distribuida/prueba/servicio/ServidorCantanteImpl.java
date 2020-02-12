package uce.distribuida.prueba.servicio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;

import uce.distribuida.prueba.conexion.ConexionBaseDatos;
import uce.distribuida.prueba.pojo.Cantante;

@ApplicationScoped
public class ServidorCantanteImpl implements ServidorCantanteInterface{

	
	PreparedStatement preparedStatement=null;
	ResultSet resultSet =null;
	Connection usuarioConexion;
	@Inject private DataSource conexionBase;
	
	private final String INSERT = "INSERT INTO singer (id_singer, first_name, last_name, birth_date) values (?, ?, ?, ?)";
	private final String FIND = "SELECT id_singer,first_name,last_name,birth_date FROM singer WHERE id_singer = ?";
	private final String SELECT = "SELECT * FROM singer ORDER BY id_singer";    
	private final String UPDATE = "UPDATE singer SET first_name = ?, last_name = ?, birth_date = ? WHERE id_singer = ?";
	private final String DELETE = "DELETE from singer where id_singer=?";
	

	@Override
	public void ingresarCantante(Cantante cantante) {
		try {
			usuarioConexion = conexionBase.getConnection();
    		preparedStatement = usuarioConexion.prepareStatement(INSERT);
    		int index=1;
    		preparedStatement.setInt(index++, cantante.getIdSinger());
    		preparedStatement.setString(index++, cantante.getNombre());
    		preparedStatement.setString(index++, cantante.getApellido());
    		preparedStatement.setDate(index++,  (Date) cantante.getFechaNacimiento());
    		System.out.println("Ejecutando query: "+INSERT);
    		preparedStatement.executeUpdate();
    		
    		usuarioConexion.close();
    		preparedStatement.close();  	
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
		
	}

	@Override
	public Cantante buscarCantantePorId(int id) {
		Cantante cantante = new Cantante();
		try {
			usuarioConexion = conexionBase.getConnection();
    		preparedStatement = usuarioConexion.prepareStatement(FIND);
    		 preparedStatement.setInt(1, id);
    		 resultSet= preparedStatement.executeQuery();
    		 while (resultSet.next()) {
                 int id2 = resultSet.getInt("id_singer");
                 String firstName = resultSet.getString("first_name");
                 String lastName = resultSet.getString("last_name");
                 Date birthDate = resultSet.getDate("birth_date");
                 //System.out.println(id2 + "," + firstName + "," + lastName + "," + birthDate);
           
                 	cantante.setIdSinger(id2);
                 	cantante.setNombre(firstName);
                 	cantante.setApellido(lastName);
                 	cantante.setFechaNacimiento(birthDate);
                 	
                 	usuarioConexion.close();
            		preparedStatement.close();  
            		resultSet.close();
    		 }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		return cantante;
	}

	@Override
	public void actualizarCantante(Cantante cantante) {
		try {
			usuarioConexion = conexionBase.getConnection();
    		//System.out.println("Ejecutando query: "+ SQL_UPDATE);
    		preparedStatement = usuarioConexion.prepareStatement(UPDATE);
    		int index=1;
    		preparedStatement.setInt( index, cantante.getIdSinger());
    		preparedStatement.setString(index++, cantante.getNombre());
    		preparedStatement.setString(index++, cantante.getApellido());
    		preparedStatement.setDate(index++, (Date) cantante.getFechaNacimiento());
    		preparedStatement.setInt( index, cantante.getIdSinger());
    		int rows = preparedStatement.executeUpdate();
    		System.out.println("Registro afectados: " + rows); 
    		
    		usuarioConexion.close();
    		preparedStatement.close(); 
    	} catch(SQLException e) {
			e.printStackTrace();
		}
    }		
		
	

	@Override
	public List<Cantante> mostrarTodos() {
		List<Cantante> singerlist = new ArrayList<Cantante>();
		try {
			 usuarioConexion = conexionBase.getConnection();
			 preparedStatement = usuarioConexion.prepareStatement(SELECT);
			 resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id_singer");
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				Date birthDate = resultSet.getDate("birth_date");

				Cantante cantante = new Cantante();
				cantante.setIdSinger(id);
				cantante.setNombre(firstName);
				cantante.setApellido(lastName);
				cantante.setFechaNacimiento(birthDate);

				singerlist.add(cantante);
				
			
					usuarioConexion.close();
		    		preparedStatement.close();  
		    	
			}
			return singerlist;
		} catch (SQLException ex) {
			ex.printStackTrace();
		} 
		return singerlist;
	}

	@Override
	public void eliminarCantante(int id) {
		 try{
			 usuarioConexion = conexionBase.getConnection();
			 preparedStatement = usuarioConexion.prepareStatement(DELETE);
			 preparedStatement.setInt(1, id);
			 resultSet = preparedStatement.executeQuery();
			 
			 	usuarioConexion.close();
	    		preparedStatement.close();  
	    		resultSet.close();
        }catch(Exception e){
            e.printStackTrace();
     	}
	}

}
