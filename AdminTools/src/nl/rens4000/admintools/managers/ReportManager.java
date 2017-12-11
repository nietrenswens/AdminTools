package nl.rens4000.admintools.managers;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import nl.rens4000.admintools.AdminTools;
import nl.rens4000.admintools.utils.ChatUtilities;
import nl.rens4000.admintools.utils.Report;
import nl.rens4000.admintools.utils.ReportState;
import nl.rens4000.admintools.utils.User;

public class ReportManager {
	
	private static ReportManager reportManager;
	
	private ConfigManager cm;
	
	public Map<Integer, Report> reports = new HashMap<Integer, Report>();
	
	public Map<String, ReportState> states = new HashMap<String, ReportState>();
	
	public Report getReport(int id) {
		for(int ri : reports.keySet()) {
			if(ri == id) {
				return reports.get(id);
			}
		}
		return null;
	}
	
	public int getHeighestId() {
		int result = 0;
		if(reports.size() == 0) return result;
		for(int i : reports.keySet()) {
			if(i > result) result = i;
		}
		return result;
	}
	
	public int createReport(String reporter, String name, String reason, Player p) {
		int newId = getHeighestId() + 1;
		Report r = new Report(newId, name, reporter, reason, ReportState.OPEN);
		reports.put(newId, r);
		ChatUtilities.broadcastTitleThroughAdmins(ChatColor.AQUA + reporter, ChatColor.GREEN + "has made a report!");
		ChatUtilities.broadcastThroughAdmins(ChatColor.AQUA + reporter + ChatColor.WHITE + " has made a report about: " + ChatColor.AQUA + name + "(" + newId + ")");
		
		User u = AdminTools.getAdminTools().getUser(p.getUniqueId());
		u.setTimesBeenReported(u.getTimesBeenReported() + 1);
		
		return newId;
	}
	
	private ReportState getStateFromString(String s) {
		for(String key : states.keySet()) {
			if(key.equals(s)) return states.get(s);
		}
		return ReportState.UNKNOWN;
	}
	
	public void loadAllReports() {
		if(!cm.getReports().contains("reports")) return;
		for(String key : cm.getReports().getConfigurationSection("reports").getKeys(false)) {
			int ids = Integer.parseInt(key);
			String id = key;
			String name = cm.getReports().getString("reports." + id + ".name");
			String reporter = cm.getReports().getString("reports." + id + ".reporter");
			String reason = cm.getReports().getString("reports." + id + ".reason");
			String state = cm.getReports().getString("reports." + id + ".state");
			loadReport(ids, name, reporter, reason, state);
		}
	}
	
	public void loadReport(int id, String name, String reporter, String reason, String state) {
		ReportState s = getStateFromString(state);
		Report r = new Report(id, name, reporter, reason, s);
		reports.put(id, r);
	}
	
	public void saveAllReports() {
		for(Report r : reports.values()) {
			saveReport(r);
		}
	}
	
	/*
	 What do we need to save?
	 - Id
	 - Name
	 - Reporter
	 - Reason
	 - State
	 */
	
	public void saveReport(Report r) {
		FileConfiguration rep = cm.getReports();
		String state = r.getReportState().getMessage();
		int id1 = r.getId();
		String id = Integer.toString(id1);
		String name = r.getName();
		String reporter = r.getReporter();
		String reason = r.getReason();
		rep.set("reports." + id + ".name", name);
		rep.set("reports." + id + ".reporter", reporter);
		rep.set("reports." + id + ".reason", reason);
		rep.set("reports." + id + ".state", state);
		cm.save();
	}
	
	public ReportManager() {
		reportManager = this;
		cm = ConfigManager.getConfigManager();
		loadReportStates();
	}
	
	private void loadReportStates() {
		states.put("OPEN", ReportState.OPEN);
		states.put("IN_TREATMENT", ReportState.IN_TREATMENT);
		states.put("ACCEPTED", ReportState.ACCEPTED);
		states.put("DENIED", ReportState.DENIED);
		states.put("UNKNOWN", ReportState.UNKNOWN);
	}

	public static ReportManager getReportManager() {
		return reportManager;
	}

	public void runDelayTimer(User u) {
		new BukkitRunnable() {

			@Override
			public void run() {
				if(u == null) {
					cancel();
					return;
				}
				u.setReportDelay(u.getReportDelay() - 1);
				if(u.getReportDelay() <= 0) {
					u.setReportDelay(0);
					cancel();
				}
			}
			
		}.runTaskTimer(AdminTools.getAdminTools(), 0, 20);
	}

}
