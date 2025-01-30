package pe.mil.fap.dto.generic;

import java.io.Serializable;

public class ParameterDataTableDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int iDisplayStart;
	private int iDisplayLength;
	private String sSearch;

	public int getStart() {
		return iDisplayStart;
	}

	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	public int getSize() {
		return iDisplayLength;
	}

	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	public String getFilter() {
		return sSearch;
	}

	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}
}
