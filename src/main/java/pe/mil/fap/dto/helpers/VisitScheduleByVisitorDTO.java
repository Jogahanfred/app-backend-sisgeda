package pe.mil.fap.dto.helpers;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.method.annotation.HandlerMethodValidationException.Visitor;

import pe.mil.fap.entity.VisitEntity;
import pe.mil.fap.entity.VisitorEntity;

public class VisitScheduleByVisitorDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private VisitorEntity visitor;

	private List<VisitEntity> visits;

	public VisitScheduleByVisitorDTO() {
		super();
	}

	public VisitScheduleByVisitorDTO(VisitorEntity visitor, List<VisitEntity> visits) {
		super();
		this.visitor = visitor;
		this.visits = visits;
	}

	public VisitorEntity getVisitor() {
		return visitor;
	}

	public void setVisitor(VisitorEntity visitor) {
		this.visitor = visitor;
	}

	public List<VisitEntity> getVisits() {
		return visits;
	}

	public void setVisits(List<VisitEntity> visits) {
		this.visits = visits;
	}

}
