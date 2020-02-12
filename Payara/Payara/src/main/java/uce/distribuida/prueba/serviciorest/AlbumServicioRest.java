package uce.distribuida.prueba.serviciorest;


import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import uce.distribuida.prueba.pojo.Album;
import uce.distribuida.prueba.servicio.ServidorAlbumInterface;

@Path("/album")
@ApplicationScoped
public class AlbumServicioRest {

	
	@Inject
	private ServidorAlbumInterface albumInterface;
	
	Album album = null;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/insertarAlbum")
	public void insertar(Album album) {
		
		try {
			 albumInterface.ingresarAlbum(album);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("listarPorId/{id}")
	public Album album(@PathParam("id") Integer id) {
		
		//DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
		
	try {
		//serializar Oj a St
		album = albumInterface.buscarAlbumPorId(id);		
		}catch(Exception e) {
			e.getMessage();
		}
		return album;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/listarTodos")
	public Response listar(){		
		List<Album> album= albumInterface.mostrarTodos();
		return Response.ok(album).build(); 
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/eliminarAlbum/{id}")
	public Response eliminar(@PathParam("id") Integer id) {
		 albumInterface.eliminarAlbum(id);
		return Response.ok("album eliminado").build();
	}
	
	@PUT
	@Path("/actualizarAlbum")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response actualizar(Album album) {
		albumInterface.actualizarAlbum(album);
		return Response.ok("album actualizado").build();
	}
}
