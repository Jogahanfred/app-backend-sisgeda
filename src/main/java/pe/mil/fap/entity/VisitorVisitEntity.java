package pe.mil.fap.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table; 
import pe.mil.fap.configuration.auditor.AuditorConfig;
import pe.mil.fap.utils.enums.PersonalSituationEnum; 

@Table(name = "TBL_VISITOR_VISIT")
@Entity(name = "VisitorVisitEntity")
public class VisitorVisitEntity extends AuditorConfig<Long> {

	@Id
	@Column(name = "ID_VISITOR_VISIT")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idVisitorVisit;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_visit", nullable = false, foreignKey = @ForeignKey(name = "FK_VISIT_DETAIL"))
	private VisitEntity visit;

	@ManyToOne
	@JoinColumn(name = "id_visitor", nullable = false)
	private VisitorEntity visitor;

	@Column(name = "CO_SITUATION", nullable = true) 
	@Enumerated(EnumType.STRING)
	private PersonalSituationEnum coSituation;
	

	public VisitorVisitEntity() {
		super();
	}

 
	public VisitorVisitEntity(Long idVisitorVisit, VisitEntity visit, VisitorEntity visitor,
			PersonalSituationEnum coSituation) {
		super();
		this.idVisitorVisit = idVisitorVisit;
		this.visit = visit;
		this.visitor = visitor;
		this.coSituation = coSituation;
	}


	public Long getIdVisitorVisit() {
		return idVisitorVisit;
	}

	public void setIdVisitorVisit(Long idVisitorVisit) {
		this.idVisitorVisit = idVisitorVisit;
	}

	public VisitEntity getVisit() {
		return visit;
	}

	public void setVisit(VisitEntity visit) {
		this.visit = visit;
	}

	public VisitorEntity getVisitor() {
		return visitor;
	}

	public void setVisitor(VisitorEntity visitor) {
		this.visitor = visitor;
	}

	public PersonalSituationEnum getCoSituation() {
		return coSituation;
	}

	public void setCoSituation(PersonalSituationEnum coSituation) {
		this.coSituation = coSituation;
	}

}
