package pe.mil.fap.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import pe.mil.fap.configuration.auditor.AuditorConfig;
import pe.mil.fap.utils.enums.SegmentTypeEnum;
import pe.mil.fap.utils.enums.VisitSituationEnum;
import jakarta.persistence.ForeignKey;

@Table(name = "TBL_VISIT")
@Entity(name = "VisitEntity")
public class VisitEntity extends AuditorConfig<Long> {

	@Id
	@Column(name = "ID_VISIT")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idVisit;

	@NotEmpty(message = "El nombre no debe ser vacio")
	@Size(min = 2, max = 100, message = "Nombre: mínimo {min} - máximo {max}")
	@Column(name = "NO_NAME", nullable = false, length = 100)
	private String noName;

	@NotNull(message = "La fecha de inicio no debe ser vacio")
	@Column(name = "FE_START", nullable = true)
	private LocalDate feStart;

	@NotNull(message = "La fecha de termino no debe ser vacio")
	@Column(name = "FE_END", nullable = true)
	private LocalDate feEnd;

	@ManyToOne
	@JoinColumn(name = "id_schedule", nullable = false, foreignKey = @ForeignKey(name = "FK_VISIT_SCHEDULE"))
	private ScheduleEntity schedule;

	@ManyToOne
	@JoinColumn(name = "id_document", nullable = false, foreignKey = @ForeignKey(name = "FK_VISIT_DOCUMENT"))
	private DocumentEntity document;

	@ManyToOne
	@JoinColumn(name = "id_squadron", nullable = false, foreignKey = @ForeignKey(name = "FK_VISIT_SQUADRON"))
	private SquadronEntity squadron;

	@OneToMany(mappedBy = "visit", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<VisitorVisitEntity> visitorVisit;

	@OneToMany(mappedBy = "segment", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SegmentVisitorEntity> segmentVisitor;
	
	@Transient
	@Enumerated(EnumType.STRING)
	private VisitSituationEnum noVisitSituation;

	public VisitEntity() {
		super();
	}

	public VisitEntity(Long idVisit,
			@NotEmpty(message = "El nombre no debe ser vacio") @Size(min = 2, max = 100, message = "Nombre: mínimo {min} - máximo {max}") String noName,
			@NotNull(message = "La fecha de inicio no debe ser vacio") LocalDate feStart,
			@NotNull(message = "La fecha de termino no debe ser vacio") LocalDate feEnd, ScheduleEntity schedule,
			DocumentEntity document, SquadronEntity squadron, List<VisitorVisitEntity> visitorVisit,
			List<SegmentVisitorEntity> segmentVisitor,VisitSituationEnum noVisitSituation ) {
		super();
		this.idVisit = idVisit;
		this.noName = noName;
		this.feStart = feStart;
		this.feEnd = feEnd;
		this.schedule = schedule;
		this.document = document;
		this.squadron = squadron;
		this.visitorVisit = visitorVisit;
		this.segmentVisitor = segmentVisitor;
		this.noVisitSituation = noVisitSituation;
	}

	public boolean isFeStartValid() {
	    return feStart != null && !feStart.isBefore(LocalDate.now());
	}
	
	public boolean isDateWithinRange(LocalDate date) {
	    if (date == null || feStart == null || feEnd == null) {
	        return false;
	    }
	    return !date.isBefore(feStart) && !date.isAfter(feEnd);
	}

	public Long getIdVisit() {
		return idVisit;
	}

	public void setIdVisit(Long idVisit) {
		this.idVisit = idVisit;
	}

	public LocalDate getFeStart() {
		return feStart;
	}

	public void setFeStart(LocalDate feStart) {
		this.feStart = feStart;
	}

	public LocalDate getFeEnd() {
		return feEnd;
	}

	public void setFeEnd(LocalDate feEnd) {
		this.feEnd = feEnd;
	}

	public DocumentEntity getDocument() {
		return document;
	}

	public void setDocument(DocumentEntity document) {
		this.document = document;
	}

	public SquadronEntity getSquadron() {
		return squadron;
	}

	public void setSquadron(SquadronEntity squadron) {
		this.squadron = squadron;
	}

	public List<VisitorVisitEntity> getVisitorVisit() {
		return visitorVisit;
	}

	public void setVisitorVisit(List<VisitorVisitEntity> visitorVisit) {
		this.visitorVisit = visitorVisit;
	}

	public List<SegmentVisitorEntity> getSegmentVisitor() {
		return segmentVisitor;
	}

	public void setSegmentVisitor(List<SegmentVisitorEntity> segmentVisitor) {
		this.segmentVisitor = segmentVisitor;
	}

	public String getNoName() {
		return noName;
	}

	public void setNoName(String noName) {
		this.noName = noName;
	}

	public ScheduleEntity getSchedule() {
		return schedule;
	}

	public void setSchedule(ScheduleEntity schedule) {
		this.schedule = schedule;
	}

	public VisitSituationEnum getNoVisitSituation() {
		return noVisitSituation;
	}

	public void setNoVisitSituation(VisitSituationEnum noVisitSituation) {
		this.noVisitSituation = noVisitSituation;
	}

}
