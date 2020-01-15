package uce.distribuida.prueba.serviciorest;


import java.util.List;


import uce.distribuida.prueba.pojo.Album;
import uce.distribuida.prueba.servicio.ServidorAlbumInterface;

@Path("/album")
@ApplicationScoped
public class AlbumServicioRest {

	
	@Inject
	private ServidorAlbumInterface albumInterface;
	
	Album album = null;
	
	@POST
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	@Path("/insertarAlbum")
	public void insertar(Album album) {
		
		try {
			 albumInterface.ingresarAlbum(album);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
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
	@Produces(MediaType.APPLICATION_XML)
	@Path("/listarTodos")
	public Response listar(){		
		List<Album> album= albumInterface.mostrarTodos();
		return Response.ok(album).build(); 
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_XML)
	@Path("/eliminarAlbum/{id}")
	public Response eliminar(@PathParam("id") Integer id) {
		 albumInterface.eliminarAlbum(id);
		return Response.ok("album eliminado").build();
	}
	
	@PUT
	@Path("/actualizarAlbum")
	@Consumes(MediaType.APPLICATION_XML)
	public Response actualizar(Album album) {
		albumInterface.actualizarAlbum(album);
		return Response.ok("album actualizado").build();
	}
}
