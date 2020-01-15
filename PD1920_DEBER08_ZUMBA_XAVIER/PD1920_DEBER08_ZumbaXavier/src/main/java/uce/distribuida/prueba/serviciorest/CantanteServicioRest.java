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
import uce.distribuida.prueba.pojo.Cantante;
import uce.distribuida.prueba.servicio.ServidorCantanteInterface;

@Path("/cantante")
@ApplicationScoped
public class CantanteServicioRest {

	
	@Inject
	private ServidorCantanteInterface singerInterface;
	
	Cantante singer = null;
	
	@POST
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	@Path("/insertarCantante")
	public void insertar(Cantante cantante) {
		
		try {
			 singerInterface.ingresarCantante(cantante);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("listarPorId/{id}")
	public Cantante cantante(@PathParam("id") Integer id) {
		
		//DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
		
	try {
		//serializar Oj a St
		singer = singerInterface.buscarCantantePorId(id);		
		}catch(Exception e) {
			e.getMessage();
		}
		return singer;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/listarTodos")
	public Response listar(){		
		List<Cantante> cantante = singerInterface.mostrarTodos();
		return Response.ok(cantante).build(); 
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_XML)
	@Path("/eliminarCantante/{id}")
	public Response eliminar(@PathParam("id") Integer id) {
		 singerInterface.eliminarCantante(id);
		return Response.ok("cantante eliminado").build();
	}
	
	@PUT
	@Path("/actualizarCantante")
	@Consumes(MediaType.APPLICATION_XML)
	public Response actualizar(Cantante cantante) {
		singerInterface.actualizarCantante(cantante);
		return Response.ok("cantante actualizado").build();
	}
}
