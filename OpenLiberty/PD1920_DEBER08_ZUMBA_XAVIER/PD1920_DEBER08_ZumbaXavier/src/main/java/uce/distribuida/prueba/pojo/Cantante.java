package uce.distribuida.prueba.pojo;

import java.io.Serializable;
import java.util.Date;


//@XmlRootElement
public class Cantante implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Getter @Setter private int idSinger;
	@Getter @Setter private String nombre;
	@Getter @Setter private String apellido;
	@Getter @Setter private Date fechaNacimiento;
	


}
