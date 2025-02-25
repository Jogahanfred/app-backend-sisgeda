package pe.mil.fap.dto.helpers;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.method.annotation.HandlerMethodValidationException.Visitor;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import pe.mil.fap.entity.VisitEntity;
import pe.mil.fap.entity.VisitorEntity;
import pe.mil.fap.utils.enums.SegmentTypeEnum;

public class VisitScheduleByVisitorDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private VisitorEntity visitor;

	@Enumerated(EnumType.STRING)
	private SegmentTypeEnum coSituationVisitor;

	private List<VisitEntity> visits;

	public VisitScheduleByVisitorDTO() {
		super();
	}

	public VisitScheduleByVisitorDTO(VisitorEntity visitor, SegmentTypeEnum coSituationVisitor,
			List<VisitEntity> visits) {
		super();
		this.visitor = visitor;
		this.coSituationVisitor = coSituationVisitor;
		this.visits = visits;
	}

	public VisitorEntity getVisitor() {
		return visitor;
	}

	public void setVisitor(VisitorEntity visitor) {
		this.visitor = visitor;
	}

	public SegmentTypeEnum getCoSituationVisitor() {
		return coSituationVisitor;
	}

	public void setCoSituationVisitor(SegmentTypeEnum coSituationVisitor) {
		this.coSituationVisitor = coSituationVisitor;
	}

	public List<VisitEntity> getVisits() {
		return visits;
	}

	public void setVisits(List<VisitEntity> visits) {
		this.visits = visits;
	}

}
