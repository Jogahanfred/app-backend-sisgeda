package pe.mil.fap.dto.helpers;

import java.io.Serializable;

import pe.mil.fap.entity.VisitorEntity;

public class DatasetNumberEntriesPerPersonDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private VisitorEntity visitor;
	private Integer nuCount;

	public DatasetNumberEntriesPerPersonDTO() {
		super();
	}

	public DatasetNumberEntriesPerPersonDTO(VisitorEntity visitor, Integer nuCount) {
		super();
		this.visitor = visitor;
		this.nuCount = nuCount;
	}

	public VisitorEntity getVisitor() {
		return visitor;
	}

	public void setVisitor(VisitorEntity visitor) {
		this.visitor = visitor;
	}

	public Integer getNuCount() {
		return nuCount;
	}

	public void setNuCount(Integer nuCount) {
		this.nuCount = nuCount;
	}

}
