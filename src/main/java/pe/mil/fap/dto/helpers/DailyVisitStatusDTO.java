package pe.mil.fap.dto.helpers;

import java.io.Serializable;

public class DailyVisitStatusDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int totalScheduledVisits;
	private int totalScheduledVisitors;
	private int totalVisitorsRegisteredInProgress;
	private int totalVisitorsRegisteredExit;
	private int totalVisitorsRegisteredDenied;

	public DailyVisitStatusDTO() {
		super();
	}

	public DailyVisitStatusDTO(int totalScheduledVisits, int totalScheduledVisitors, int totalVisitorsRegisteredInProgress,
			int totalVisitorsRegisteredExit, int totalVisitorsRegisteredDenied) {
		super();
		this.totalScheduledVisits = totalScheduledVisits;
		this.totalScheduledVisitors = totalScheduledVisitors;
		this.totalVisitorsRegisteredInProgress = totalVisitorsRegisteredInProgress;
		this.totalVisitorsRegisteredExit = totalVisitorsRegisteredExit;
		this.totalVisitorsRegisteredDenied = totalVisitorsRegisteredDenied;
	}

	public int getTotalScheduledVisits() {
		return totalScheduledVisits;
	}

	public void setTotalScheduledVisits(int totalScheduledVisits) {
		this.totalScheduledVisits = totalScheduledVisits;
	}

	public int getTotalScheduledVisitors() {
		return totalScheduledVisitors;
	}

	public void setTotalScheduledVisitors(int totalScheduledVisitors) {
		this.totalScheduledVisitors = totalScheduledVisitors;
	}

	public int getTotalVisitorsRegisteredInProgress() {
		return totalVisitorsRegisteredInProgress;
	}

	public void setTotalVisitorsRegisteredInProgress(int totalVisitorsRegisteredInProgress) {
		this.totalVisitorsRegisteredInProgress = totalVisitorsRegisteredInProgress;
	}

	public int getTotalVisitorsRegisteredExit() {
		return totalVisitorsRegisteredExit;
	}

	public void setTotalVisitorsRegisteredExit(int totalVisitorsRegisteredExit) {
		this.totalVisitorsRegisteredExit = totalVisitorsRegisteredExit;
	}

	public int getTotalVisitorsRegisteredDenied() {
		return totalVisitorsRegisteredDenied;
	}

	public void setTotalVisitorsRegisteredDenied(int totalVisitorsRegisteredDenied) {
		this.totalVisitorsRegisteredDenied = totalVisitorsRegisteredDenied;
	}

}
