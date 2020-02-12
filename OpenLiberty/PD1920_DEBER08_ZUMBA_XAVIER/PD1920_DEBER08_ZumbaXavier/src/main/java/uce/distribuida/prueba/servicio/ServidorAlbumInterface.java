package uce.distribuida.prueba.servicio;

import java.util.List;
import uce.distribuida.prueba.pojo.Album;

public interface ServidorAlbumInterface {

	 void ingresarAlbum(Album album);
	 Album buscarAlbumPorId(int id);
	 void actualizarAlbum(Album album);
	 List<Album> mostrarTodos();
	 void eliminarAlbum(int id);
}
