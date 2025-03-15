package pe.mil.fap.dto.helpers;

import java.io.Serializable;

public class ReportNumberEntriesPerPersonDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idVisitor;
	private Long nuCount;

	public ReportNumberEntriesPerPersonDTO() {
		super();
	}

	public ReportNumberEntriesPerPersonDTO(Long idVisitor, Long nuCount) {
		super();
		this.idVisitor = idVisitor;
		this.nuCount = nuCount;
	}

	public Long getIdVisitor() {
		return idVisitor;
	}

	public void setIdVisitor(Long idVisitor) {
		this.idVisitor = idVisitor;
	}

	public Long getNuCount() {
		return nuCount;
	}

	public void setNuCount(Long nuCount) {
		this.nuCount = nuCount;
	}

}
