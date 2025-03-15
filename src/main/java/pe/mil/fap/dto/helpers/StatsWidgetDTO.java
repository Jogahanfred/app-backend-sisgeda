package pe.mil.fap.dto.helpers;

import java.io.Serializable;

public class StatsWidgetDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int totalVisits;
	private int totalPendingVisits;

	private int visitorsDetained;
	private int visitorsDetainedLastMonth;

	private int totalVisitors;
	private int totalForeignVisitors;

	private int totalUnexpectedVisits;

	public StatsWidgetDTO() {
		super();
	}

	public StatsWidgetDTO(int totalVisits, int totalPendingVisits, int visitorsDetained, int visitorsDetainedLastMonth,
			int totalVisitors, int totalForeignVisitors, int totalUnexpectedVisits) {
		super();
		this.totalVisits = totalVisits;
		this.totalPendingVisits = totalPendingVisits;
		this.visitorsDetained = visitorsDetained;
		this.visitorsDetainedLastMonth = visitorsDetainedLastMonth;
		this.totalVisitors = totalVisitors;
		this.totalForeignVisitors = totalForeignVisitors;
		this.totalUnexpectedVisits = totalUnexpectedVisits;
	}

	public int getTotalVisits() {
		return totalVisits;
	}

	public void setTotalVisits(int totalVisits) {
		this.totalVisits = totalVisits;
	}

	public int getTotalPendingVisits() {
		return totalPendingVisits;
	}

	public void setTotalPendingVisits(int totalPendingVisits) {
		this.totalPendingVisits = totalPendingVisits;
	}

	public int getVisitorsDetained() {
		return visitorsDetained;
	}

	public void setVisitorsDetained(int visitorsDetained) {
		this.visitorsDetained = visitorsDetained;
	}

	public int getVisitorsDetainedLastMonth() {
		return visitorsDetainedLastMonth;
	}

	public void setVisitorsDetainedLastMonth(int visitorsDetainedLastMonth) {
		this.visitorsDetainedLastMonth = visitorsDetainedLastMonth;
	}

	public int getTotalVisitors() {
		return totalVisitors;
	}

	public void setTotalVisitors(int totalVisitors) {
		this.totalVisitors = totalVisitors;
	}

	public int getTotalForeignVisitors() {
		return totalForeignVisitors;
	}

	public void setTotalForeignVisitors(int totalForeignVisitors) {
		this.totalForeignVisitors = totalForeignVisitors;
	}

	public int getTotalUnexpectedVisits() {
		return totalUnexpectedVisits;
	}

	public void setTotalUnexpectedVisits(int totalUnexpectedVisits) {
		this.totalUnexpectedVisits = totalUnexpectedVisits;
	}

}
