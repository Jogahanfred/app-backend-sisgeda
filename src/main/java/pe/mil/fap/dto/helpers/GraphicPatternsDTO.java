package pe.mil.fap.dto.helpers;

import java.io.Serializable;

public class GraphicPatternsDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String noDay;
	private Long nuCount;

	public GraphicPatternsDTO() {
		super();
	}

	public GraphicPatternsDTO(String noDay, Long nuCount) {
		super();
		this.noDay = noDay;
		this.nuCount = nuCount;
	}

	public String getNoDay() {
		return noDay;
	}

	public void setNoDay(String noDay) {
		this.noDay = noDay;
	}

	public Long getNuCount() {
		return nuCount;
	}

	public void setNuCount(Long nuCount) {
		this.nuCount = nuCount;
	}

}
