package pe.mil.fap.dto.helpers;

import java.io.Serializable;
import java.time.LocalDate;

import pe.mil.fap.utils.enums.VisitSituationEnum;

public class VisitorScheduleOnTheDayDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String noNameVisit;
	private String feStart;
	private String feEnd;
	private String noNameSquadron;
	private String noNameSchedule;
	private String nuDocumentVisitor;
	private String noSurNamesVisitor;
	private String noNamesVisitor;
	private String coSituation;

	public VisitorScheduleOnTheDayDTO() {
		super();
	}

	public VisitorScheduleOnTheDayDTO(String noNameVisit, String feStart, String feEnd, String noNameSquadron,
			String noNameSchedule, String nuDocumentVisitor, String noSurNamesVisitor, String noNamesVisitor,
			String coSituation) {
		super();
		this.noNameVisit = noNameVisit;
		this.feStart = feStart;
		this.feEnd = feEnd;
		this.noNameSquadron = noNameSquadron;
		this.noNameSchedule = noNameSchedule;
		this.nuDocumentVisitor = nuDocumentVisitor;
		this.noSurNamesVisitor = noSurNamesVisitor;
		this.noNamesVisitor = noNamesVisitor;
		this.coSituation = coSituation;
	}

	public String getNoNameVisit() {
		return noNameVisit;
	}

	public void setNoNameVisit(String noNameVisit) {
		this.noNameVisit = noNameVisit;
	}

	public String getFeStart() {
		return feStart;
	}

	public void setFeStart(String feStart) {
		this.feStart = feStart;
	}

	public String getFeEnd() {
		return feEnd;
	}

	public void setFeEnd(String feEnd) {
		this.feEnd = feEnd;
	}

	public String getNoNameSquadron() {
		return noNameSquadron;
	}

	public void setNoNameSquadron(String noNameSquadron) {
		this.noNameSquadron = noNameSquadron;
	}

	public String getNoNameSchedule() {
		return noNameSchedule;
	}

	public void setNoNameSchedule(String noNameSchedule) {
		this.noNameSchedule = noNameSchedule;
	}

	public String getNuDocumentVisitor() {
		return nuDocumentVisitor;
	}

	public void setNuDocumentVisitor(String nuDocumentVisitor) {
		this.nuDocumentVisitor = nuDocumentVisitor;
	}

	public String getNoSurNamesVisitor() {
		return noSurNamesVisitor;
	}

	public void setNoSurNamesVisitor(String noSurNamesVisitor) {
		this.noSurNamesVisitor = noSurNamesVisitor;
	}

	public String getNoNamesVisitor() {
		return noNamesVisitor;
	}

	public void setNoNamesVisitor(String noNamesVisitor) {
		this.noNamesVisitor = noNamesVisitor;
	}

	public String getCoSituation() {
		return coSituation;
	}

	public void setCoSituation(String coSituation) {
		this.coSituation = coSituation;
	}

}
