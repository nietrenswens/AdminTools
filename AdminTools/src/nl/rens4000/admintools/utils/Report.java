package nl.rens4000.admintools.utils;

public class Report {
	
	private int id;
	private String name;
	private String reporter;
	private String reason;
	private ReportState reportState;
	
	public Report(int id, String name, String reporter, String reason, ReportState reportState) {
		this.id = id;
		this.name = name;
		this.reporter = reporter;
		this.reason = reason;
		this.reportState = reportState;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReporter() {
		return reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public ReportState getReportState() {
		return reportState;
	}

	public void setReportState(ReportState reportState) {
		this.reportState = reportState;
	}

}
