package uce.distribuida.prueba.pojo;

import java.io.Serializable;
import java.util.Date;


//@XmlRootElement
public class Cantante implements Serializable{

	private static final long serialVersionUID = 1L;
	/*
	@Getter @Setter private int idSinger;
	@Getter @Setter private String nombre;
	@Getter @Setter private String apellido;
	@Getter @Setter private Date fechaNacimiento;
	
*/
	private int idSinger;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	public int getIdSinger() {
		return idSinger;
	}
	public void setIdSinger(int idSinger) {
		this.idSinger = idSinger;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	

}
