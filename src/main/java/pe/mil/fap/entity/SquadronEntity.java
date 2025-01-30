package pe.mil.fap.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import pe.mil.fap.configuration.auditor.AuditorConfig;

@Table(name = "TBL_SQUADRON")
@Entity(name = "SquadronEntity")
public class SquadronEntity extends AuditorConfig<Long>{
 
	@Id
	@Column(name = "ID_SQUADRON")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idSquadron;

	@NotEmpty(message = "El código no debe ser vacio")
	@Size(min = 3, max = 10, message = "Código: mínimo {min} - máximo {max}") 
	@Column(name = "CO_IDENTIFIER", nullable = false, length = 10)
	private String coIdentifier;

	@NotEmpty(message = "El nombre no debe ser vacio")
	@Size(max = 50, message = "Nombre: máximo {max}") 
	@Column(name = "NO_LONG_NAME", nullable = false, length = 50)
	private String noLongName;
	
	@Column(name = "FL_STATE", nullable = false)
	@NotNull(message = "El estado no debe ser vacio")
	private Boolean flState;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_IMAGE", referencedColumnName = "ID")
	public ImageEntity image;

	public SquadronEntity() {
		super();
	}

	public SquadronEntity(Long idSquadron, String coIdentifier, String noLongName, Boolean flState, ImageEntity image) {
		super();
		this.idSquadron = idSquadron;
		this.coIdentifier = coIdentifier;
		this.noLongName = noLongName;
		this.flState = flState;
		this.image = image;
	}

	public Long getIdSquadron() {
		return idSquadron;
	}

	public void setIdSquadron(Long idSquadron) {
		this.idSquadron = idSquadron;
	}

	public String getCoIdentifier() {
		return coIdentifier;
	}

	public void setCoIdentifier(String coIdentifier) {
		this.coIdentifier = coIdentifier;
	}

	public String getNoLongName() {
		return noLongName;
	}

	public void setNoLongName(String noLongName) {
		this.noLongName = noLongName;
	}

	public Boolean getFlState() {
		return flState;
	}

	public void setFlState(Boolean flState) {
		this.flState = flState;
	}

	public ImageEntity getImage() {
		return image;
	}

	public void setImage(ImageEntity image) {
		this.image = image;
	} 
 
	
}