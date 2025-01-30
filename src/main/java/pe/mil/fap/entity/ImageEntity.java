package pe.mil.fap.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "TBL_IMAGE")
@Entity(name = "ImageEntity")
public class ImageEntity {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

	@Column(name = "NO_NAME_IMAGE")
	private String noNameImage;

	@Column(name = "TX_IMAGE_URL")
	private String txImageUrl;	
	
	@Column(name = "ID_IMAGE")
	private String idImage;
	
	

	public ImageEntity() {
		super();
	}

	public ImageEntity(String noNameImage, String txImageUrl, String idImage) {
		super();
		this.noNameImage = noNameImage;
		this.txImageUrl = txImageUrl;
		this.idImage = idImage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNoNameImage() {
		return noNameImage;
	}

	public void setNoNameImage(String noNameImage) {
		this.noNameImage = noNameImage;
	}

	public String getTxImageUrl() {
		return txImageUrl;
	}

	public void setTxImageUrl(String txImageUrl) {
		this.txImageUrl = txImageUrl;
	}

	public String getIdImage() {
		return idImage;
	}

	public void setIdImage(String idImage) {
		this.idImage = idImage;
	}
 
	
}
