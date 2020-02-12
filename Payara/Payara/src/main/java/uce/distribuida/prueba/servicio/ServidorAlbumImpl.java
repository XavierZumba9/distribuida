package uce.distribuida.prueba.servicio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import uce.distribuida.prueba.conexion.ConexionBaseDatos;
import uce.distribuida.prueba.pojo.Album;

public class ServidorAlbumImpl implements ServidorAlbumInterface{

	//ConexionBaseDatos conexionBase = new ConexionBaseDatos();
	PreparedStatement preparedStatement=null;
	ResultSet resultSet =null;
	Connection usuarioConexion;
	
	@Inject private DataSource conexionBase;
	
	private final String INSERT = "INSERT INTO album (id_album, id_singer, title, release_date) values (?, ?, ?, ?)";
	private final String FIND = "SELECT id_album,id_singer,title,release_date FROM album WHERE id_album = ?";
	private final String SELECT = "SELECT * FROM album ORDER BY id_album";    
	private final String UPDATE = "UPDATE album SET title = ?, release_date = ? WHERE id_album = ?";
	private final String DELETE = "DELETE from album where id_album=?";
	


	@Override
	public void ingresarAlbum(Album album) {
		try {
			usuarioConexion = conexionBase.getConnection();
    		preparedStatement = usuarioConexion.prepareStatement(INSERT);
    		int index=1;
    		preparedStatement.setInt(index++, album.getIdAlbum());
    		preparedStatement.setInt(index++, album.getIdSinger());
    		preparedStatement.setString(index++, album.getTitle());
    		preparedStatement.setDate(index++,  (Date) album.getRelease());
    		//System.out.println("Ejecutando query: "+INSERT);
    		preparedStatement.executeUpdate();
    		
    		usuarioConexion.close();
    		preparedStatement.close();  	
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
		
	}

	@Override
	public Album buscarAlbumPorId(int id) {
		Album album = new Album();
		try {
			usuarioConexion = conexionBase.getConnection();
    		preparedStatement = usuarioConexion.prepareStatement(FIND);
    		 preparedStatement.setInt(1, id);
    		 resultSet= preparedStatement.executeQuery();
    		 while (resultSet.next()) {
                 int id2 = resultSet.getInt("id_album");
                 int id3 = resultSet.getInt("id_singer");
                 String title = resultSet.getString("title");
                 Date releaseDate = resultSet.getDate("release_date");
                 //System.out.println(id2 + "," + firstName + "," + lastName + "," + birthDate);
           
                 	album.setIdAlbum(id2);
                 	album.setIdSinger(id3);
                 	album.setTitle(title);
                 	album.setRelease(releaseDate);
                 	usuarioConexion.close();
            		preparedStatement.close();  
            		resultSet.close();
    		 }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		return album;
	}

	@Override
	public void actualizarAlbum(Album album) {
		try {
			usuarioConexion = conexionBase.getConnection();
    		//System.out.println("Ejecutando query: "+ SQL_UPDATE);
    		preparedStatement = usuarioConexion.prepareStatement(UPDATE);
    		int index=1;
    		preparedStatement.setInt( index, album.getIdAlbum());
    		preparedStatement.setString(index++, album.getTitle());
    		preparedStatement.setDate(index++, (Date) album.getRelease());
    		preparedStatement.setInt( index, album.getIdAlbum());
    		int rows = preparedStatement.executeUpdate();
    		System.out.println("Registro afectados: " + rows); 
    		
    		usuarioConexion.close();
    		preparedStatement.close(); 
    	} catch(SQLException e) {
			e.printStackTrace();
		}
    }		
		
	

	@Override
	public List<Album> mostrarTodos() {
		List<Album> albumList = new ArrayList<Album>();
		try {
			 usuarioConexion = conexionBase.getConnection();
			 preparedStatement = usuarioConexion.prepareStatement(SELECT);
			 resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int idAlbum = resultSet.getInt("id_album");
				int idCantante = resultSet.getInt("id_singer");
				String title = resultSet.getString("title");
				Date releaseDate = resultSet.getDate("release_date");

				Album album = new Album();
				album.setIdAlbum(idAlbum);
				album.setIdSinger(idCantante);
				album.setTitle(title);
				album.setRelease(releaseDate);

				albumList.add(album);
				
			
					usuarioConexion.close();
		    		preparedStatement.close();  
		    	
			}
			return albumList;
		} catch (SQLException ex) {
			ex.printStackTrace();
		} 
		return albumList;
	}

	@Override
	public void eliminarAlbum(int id) {
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
