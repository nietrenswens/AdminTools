package nl.rens4000.admintools.utils;

import org.bukkit.entity.Player;

public class User {
	
	private Player p;
	private boolean frozen;
	private int logins;
	private int timesBeenReported;
	private boolean vanished;
	private int reportDelay;
	private boolean commandSpy;
	private boolean commandBlocked;

	public User(Player p, boolean frozen, int logins, int timesBeenReported, boolean vanished, int reportDelay, boolean commandSpy, boolean commandBlocked) {
		this.p = p;
		this.frozen = frozen;
		this.logins = logins;
		this.timesBeenReported = timesBeenReported;
		this.vanished = vanished;
		this.reportDelay = reportDelay;
		this.commandSpy = commandSpy;
		this.commandBlocked = commandBlocked;
	}
	
	public boolean isCommandSpy() {
		return commandSpy;
	}

	public void setCommandSpy(boolean commandSpy) {
		this.commandSpy = commandSpy;
	}

	public int getReportDelay() {
		return reportDelay;
	}

	public void setReportDelay(int reportDelay) {
		this.reportDelay = reportDelay;
	}

	public boolean isVanished() {
		return vanished;
	}

	public void setVanished(boolean vanished) {
		this.vanished = vanished;
	}

	public Player getPlayer() {
		return p;
	}
	
	public boolean isFrozen() {
		return frozen;
	}
	
	public int getLogins() {
		return logins;
	}
	
	public int getTimesBeenReported() {
		return timesBeenReported;
	}

	public void setPlayer(Player p) {
		this.p = p;
	}

	public void setFrozen(boolean frozen) {
		this.frozen = frozen;
	}

	public void setLogins(int logins) {
		this.logins = logins;
	}

	public void setTimesBeenReported(int timesBeenReported) {
		this.timesBeenReported = timesBeenReported;
	}
	
	public boolean isCommandBlocked() {
		return commandBlocked;
	}
	
	public void setCommandBlocked(boolean commandBlocked) {
		this.commandBlocked = commandBlocked;
	}

}
