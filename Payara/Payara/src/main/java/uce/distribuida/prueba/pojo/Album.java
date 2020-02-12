package uce.distribuida.prueba.pojo;

import java.io.Serializable;
import java.util.Date;



//@XmlRootElement
public class Album implements Serializable{

	private static final long serialVersionUID = 1L;

	//@Getter @Setter
	
	private int idAlbum;
	private int idSinger;
	private String title;
	private Date release;
	
	// metodos getter y setter
	public int getIdAlbum() {
		return idAlbum;
	}
	public void setIdAlbum(int idAlbum) {
		this.idAlbum = idAlbum;
	}
	public int getIdSinger() {
		return idSinger;
	}
	public void setIdSinger(int idSinger) {
		this.idSinger = idSinger;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getRelease() {
		return release;
	}
	public void setRelease(Date release) {
		this.release = release;
	}
	
	
	
}
