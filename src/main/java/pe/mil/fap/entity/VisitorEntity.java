package pe.mil.fap.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import pe.mil.fap.configuration.auditor.AuditorConfig;
import pe.mil.fap.utils.enums.DocumentTypeEnum;
import pe.mil.fap.utils.enums.GenderEnum;
import pe.mil.fap.utils.enums.NationalityEnum;

@Table(name = "TBL_VISITOR")
@Entity(name = "VisitorEntity")
public class VisitorEntity extends AuditorConfig<Long> {

	@Id
	@Column(name = "ID_VISITOR")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idVisitor;

	@NotEmpty(message = "El nombre no debe ser vacio")
	@Size(min = 2, max = 50, message = "Nombre: mínimo {min} - máximo {max}")
	@Column(name = "NO_NAMES", nullable = false, length = 50)
	private String noName;

	@NotEmpty(message = "El apellido no debe ser vacio")
	@Size(min = 2, max = 50, message = "Apellido: mínimo {min} - máximo {max}")
	@Column(name = "NO_SUR_NAMES", nullable = false, length = 50)
	private String noSurName;

	@NotEmpty(message = "El NRO de documento no debe ser vacio")
	@Size(min = 6, max = 20, message = "Nro Documento: mínimo {min} - máximo {max}")
	@Column(name = "NU_DOCUMENT", nullable = false, length = 20)
	private String nuDocument;

	@NotNull(message = "El tipo de documento no debe ser vacio")
	@Column(name = "NO_DOCUMENT_TYPE", nullable = false) 
	@Enumerated(EnumType.STRING)
	private DocumentTypeEnum noDocumentType;

	@NotNull(message = "El género no debe ser vacio")
	@Column(name = "NO_GENDER", nullable = false) 
	@Enumerated(EnumType.STRING)
	private GenderEnum noGender;

	@NotNull(message = "La nacionalidad no debe ser vacio")
	@Column(name = "NO_NATIONALITY", nullable = false) 
	@Enumerated(EnumType.STRING)
	private NationalityEnum noNationality;

	@Column(name = "NU_PHONE", nullable = true, length = 9)
	private Integer nuPhone;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_IMAGE", referencedColumnName = "ID")
	public ImageEntity image;
	
	@Transient
	public String txFullName;

	public VisitorEntity() {
		super();
	}
	
	public VisitorEntity(Long idVisitor,
			@NotEmpty(message = "El nombre no debe ser vacio") @Size(min = 2, max = 50, message = "Nombre: mínimo {min} - máximo {max}") String noName,
			@NotEmpty(message = "El apellido no debe ser vacio") @Size(min = 2, max = 50, message = "Apellido: mínimo {min} - máximo {max}") String noSurName,
			@NotEmpty(message = "El NRO de documento no debe ser vacio") @Size(min = 6, max = 20, message = "Nro Documento: mínimo {min} - máximo {max}") String nuDocument,
			@NotNull(message = "El tipo de documento no debe ser vacio") DocumentTypeEnum noDocumentType,
			@NotNull(message = "El género no debe ser vacio") GenderEnum noGender,
			@NotNull(message = "La nacionalidad no debe ser vacio") NationalityEnum noNationality, Integer nuPhone,
			ImageEntity image, String txFullName) {
		super();
		this.idVisitor = idVisitor;
		this.noName = noName;
		this.noSurName = noSurName;
		this.nuDocument = nuDocument;
		this.noDocumentType = noDocumentType;
		this.noGender = noGender;
		this.noNationality = noNationality;
		this.nuPhone = nuPhone;
		this.image = image;
		this.txFullName = txFullName;
	}

	public Long getIdVisitor() {
		return idVisitor;
	}

	public void setIdVisitor(Long idVisitor) {
		this.idVisitor = idVisitor;
	}

	public String getNoName() {
		return noName;
	}

	public void setNoName(String noName) {
		this.noName = noName;
	}

	public String getNoSurName() {
		return noSurName;
	}

	public void setNoSurName(String noSurName) {
		this.noSurName = noSurName;
	}

	public String getNuDocument() {
		return nuDocument;
	}

	public void setNuDocument(String nuDocument) {
		this.nuDocument = nuDocument;
	}

	public DocumentTypeEnum getNoDocumentType() {
		return noDocumentType;
	}

	public void setNoDocumentType(DocumentTypeEnum noDocumentType) {
		this.noDocumentType = noDocumentType;
	}

	public GenderEnum getNoGender() {
		return noGender;
	}

	public void setNoGender(GenderEnum noGender) {
		this.noGender = noGender;
	}

	public NationalityEnum getNoNationality() {
		return noNationality;
	}

	public void setNoNationality(NationalityEnum noNationality) {
		this.noNationality = noNationality;
	}

	public Integer getNuPhone() {
		return nuPhone;
	}

	public void setNuPhone(Integer nuPhone) {
		this.nuPhone = nuPhone;
	}

	public ImageEntity getImage() {
		return image;
	}

	public void setImage(ImageEntity image) {
		this.image = image;
	}

	public String getTxFullName() {
		return this.noSurName + " " + this.noName;
	}	

}
