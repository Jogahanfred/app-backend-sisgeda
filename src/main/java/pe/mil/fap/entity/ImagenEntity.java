package pe.mil.fap.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "TBL_IMAGEN")
@Entity(name = "ImagenEntity")
public class ImagenEntity {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

	@Column(name = "NO_IMAGEN")
	private String noImagen;

	@Column(name = "TX_IMAGEN_URL")
	private String txImagenUrl;	
	
	@Column(name = "ID_IMAGEN")
	private String idImagen;
	
	

	public ImagenEntity() {
		super();
	}

	public ImagenEntity(String noImagen, String txImagenUrl, String idImagen) {
		super();
		this.noImagen = noImagen;
		this.txImagenUrl = txImagenUrl;
		this.idImagen = idImagen;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNoImagen() {
		return noImagen;
	}

	public void setNoImagen(String noImagen) {
		this.noImagen = noImagen;
	}

	public String getTxImagenUrl() {
		return txImagenUrl;
	}

	public void setTxImagenUrl(String txImagenUrl) {
		this.txImagenUrl = txImagenUrl;
	}

	public String getIdImagen() {
		return idImagen;
	}

	public void setIdImagen(String idImagen) {
		this.idImagen = idImagen;
	}
	
	
}
