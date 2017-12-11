package nl.rens4000.admintools.utils;

public enum ReportState {
	
	OPEN("OPEN", false), IN_TREATMENT("IN_TREATMENT", false), ACCEPTED("ACCEPTED", true), DENIED("DENIED", true), UNKNOWN("UNKNOWN", false);
	
	private String message;
	private boolean closed;
	
	ReportState(String message, boolean closed) {
		this.message = message;
		this.closed = closed;
	}
	
	public String getMessage() {
		return message;
	}
	
	public boolean isClosed() {
		return closed;
	}

}
