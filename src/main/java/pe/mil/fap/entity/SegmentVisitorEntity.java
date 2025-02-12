package pe.mil.fap.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import pe.mil.fap.configuration.auditor.AuditorConfig; 
import pe.mil.fap.utils.enums.SegmentTypeEnum;

@Table(name = "TBL_SEGMENT_VISITOR")
@Entity(name = "SegmentVisitorEntity")
public class SegmentVisitorEntity extends AuditorConfig<Long> {

	@Id
	@Column(name = "ID_SEGMENT_VISITOR")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idSegmentVisitor;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_visit", foreignKey = @ForeignKey(name = "FK_SEGMENT_VISIT"))
	private VisitEntity segment;

	@ManyToOne
	@JoinColumn(name = "id_visitor", foreignKey = @ForeignKey(name = "FK_SEGMENT_VISITOR"))
	private VisitorEntity visitor;
	
	@NotNull(message = "El tipo de segmento no debe ser vacio")
	@Column(name = "CO_SEGMENT_TYPE", nullable = false) 
	@Enumerated(EnumType.STRING)
	private SegmentTypeEnum coSegmentType;

	@NotNull(message = "La fecha de inicio no debe ser vacio")
	@Column(name = "FE_START_SEGMENT", nullable = false) 
	private LocalDateTime feStartSegment; 
 
	@Column(name = "FE_END_SEGMENT", nullable = true) 
    private LocalDateTime feEndSegment;
	
	@Size(max = 100, message = "El detalle del segmento debe ser máximo {max} caracteres")
	@Column(name = "TX_SEGMENT_DETAIL", nullable = true, length = 100)
	private String txSegmentDetail;

	public SegmentVisitorEntity() {
		super();
	}

	public SegmentVisitorEntity(Long idSegmentVisitor, VisitEntity segment, VisitorEntity visitor,
			@NotNull(message = "El tipo de segmento no debe ser vacio") SegmentTypeEnum coSegmentType,
			@NotNull(message = "La fecha de inicio no debe ser vacio") LocalDateTime feStartSegment,
			LocalDateTime feEndSegment,
			@Size(max = 100, message = "El detalle del segmento debe ser máximo {max} caracteres") String txSegmentDetail) {
		super();
		this.idSegmentVisitor = idSegmentVisitor;
		this.segment = segment;
		this.visitor = visitor;
		this.coSegmentType = coSegmentType;
		this.feStartSegment = feStartSegment;
		this.feEndSegment = feEndSegment;
		this.txSegmentDetail = txSegmentDetail;
	}

	public Long getIdSegmentVisitor() {
		return idSegmentVisitor;
	}

	public void setIdSegmentVisitor(Long idSegmentVisitor) {
		this.idSegmentVisitor = idSegmentVisitor;
	}

	public VisitEntity getSegment() {
		return segment;
	}

	public void setSegment(VisitEntity segment) {
		this.segment = segment;
	}

	public VisitorEntity getVisitor() {
		return visitor;
	}

	public void setVisitor(VisitorEntity visitor) {
		this.visitor = visitor;
	}

	public SegmentTypeEnum getCoSegmentType() {
		return coSegmentType;
	}

	public void setCoSegmentType(SegmentTypeEnum coSegmentType) {
		this.coSegmentType = coSegmentType;
	}

	public LocalDateTime getFeStartSegment() {
		return feStartSegment;
	}

	public void setFeStartSegment(LocalDateTime feStartSegment) {
		this.feStartSegment = feStartSegment;
	}

	public LocalDateTime getFeEndSegment() {
		return feEndSegment;
	}

	public void setFeEndSegment(LocalDateTime feEndSegment) {
		this.feEndSegment = feEndSegment;
	}

	public String getTxSegmentDetail() {
		return txSegmentDetail;
	}

	public void setTxSegmentDetail(String txSegmentDetail) {
		this.txSegmentDetail = txSegmentDetail;
	} 
	
	
}
