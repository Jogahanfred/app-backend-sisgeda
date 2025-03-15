package pe.mil.fap.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import pe.mil.fap.configuration.auditor.AuditorConfig;
import pe.mil.fap.utils.enums.DocumentVerificationEnum;

@Table(name = "TBL_DOCUMENT")
@Entity(name = "DocumentEntity")
public class DocumentEntity extends AuditorConfig<Long> {

	@Id
	@Column(name = "ID_DOCUMENT")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idDocument;

	@NotEmpty(message = "La referencia no debe ser vacio")
	@Size(max = 70, message = "Código: máximo {max}")
	@Column(name = "NO_REFERENCE", nullable = false, length = 70)
	private String noReference;

	@NotNull(message = "La fecha de emisión no debe ser vacio")
	@Column(name = "FE_EMISSION", nullable = false)
	private LocalDate feEmission;

	@NotNull(message = "La fecha de expiración no debe ser vacio")
	@Column(name = "FE_EXPIRATION", nullable = false)
	private LocalDate feExpiration;

	@Transient
	private DocumentVerificationEnum txVerificationDescription;

	public DocumentEntity() {
		super();
	}

	public DocumentEntity(Long idDocument,
			@NotEmpty(message = "La referencia no debe ser vacio") @Size(max = 70, message = "Código: máximo {max}") String noReference,
			@NotNull(message = "La fecha de emisión no debe ser vacio") LocalDate feEmission,
			@NotNull(message = "La fecha de expiración no debe ser vacio") LocalDate feExpiration,
			DocumentVerificationEnum txVerificationDescription) {
		super();
		this.idDocument = idDocument;
		this.noReference = noReference;
		this.feEmission = feEmission;
		this.feExpiration = feExpiration;
		this.txVerificationDescription = txVerificationDescription;
	}

	public boolean isFeExpirationValid() {
	    return feExpiration != null && !LocalDate.now().isAfter(feExpiration); 
	}

	public boolean isFeEmissionBeforeOrEqualFeExpiration() {
		return feEmission != null && feExpiration != null && !feEmission.isAfter(feExpiration);
	}

	public Long getIdDocument() {
		return idDocument;
	}

	public void setIdDocument(Long idDocument) {
		this.idDocument = idDocument;
	}

	public String getNoReference() {
		return noReference;
	}

	public void setNoReference(String noReference) {
		this.noReference = noReference;
	}

	public LocalDate getFeEmission() {
		return feEmission;
	}

	public void setFeEmission(LocalDate feEmission) {
		this.feEmission = feEmission;
	}

	public LocalDate getFeExpiration() {
		return feExpiration;
	}

	public void setFeExpiration(LocalDate feExpiration) {
		this.feExpiration = feExpiration;
	}

	public DocumentVerificationEnum getTxVerificationDescription() {
		return txVerificationDescription;
	}

	public void setTxVerificationDescription(DocumentVerificationEnum txVerificationDescription) {
		this.txVerificationDescription = txVerificationDescription;
	}

}
