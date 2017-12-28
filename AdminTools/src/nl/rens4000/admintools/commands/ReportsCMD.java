package nl.rens4000.admintools.commands;

import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nl.rens4000.admintools.managers.ReportManager;
import nl.rens4000.admintools.utils.ChatUtilities;
import nl.rens4000.admintools.utils.ChatUtils;
import nl.rens4000.admintools.utils.Report;
import nl.rens4000.admintools.utils.ReportState;

public class ReportsCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatUtils.NOT_PLAYER.getMessage());
			return false;
		}
		Player p = (Player) sender;
		if(!p.hasPermission("Admintools.Admin")) {
			p.sendMessage(ChatUtils.NO_PERM.getMessage());
			return false;
		}
		if(args.length < 1) {
			sender.sendMessage(ChatUtilities.PREFIX + "-========================================-");
			sender.sendMessage(ChatUtilities.PREFIX + ChatColor.AQUA + "Reports: " + ReportManager.getReportManager().reports.size());
			sender.sendMessage(ChatUtilities.PREFIX + ChatColor.AQUA + "/reports help" + ChatColor.BLUE + " - To show all the commands.");
			sender.sendMessage(ChatUtilities.PREFIX + "-========================================-");
			return false;
		}
		if(args[0].equalsIgnoreCase("help")) {
			sender.sendMessage(ChatUtilities.PREFIX + "-========================================-");
			sender.sendMessage(ChatUtilities.PREFIX + ChatColor.AQUA + "/reports" + ChatColor.BLUE + " - Main command.");
			sender.sendMessage(ChatUtilities.PREFIX + ChatColor.AQUA + "/reports all" + ChatColor.BLUE + " - Show all reports.");
			sender.sendMessage(ChatUtilities.PREFIX + ChatColor.AQUA + "/reports show <id>" + ChatColor.BLUE + " - Show the info of the given report.");
			sender.sendMessage(ChatUtilities.PREFIX + ChatColor.AQUA + "/reports take <id>" + ChatColor.BLUE + " - Change the state of a report to: IN_TREATMENT.");
			sender.sendMessage(ChatUtilities.PREFIX + ChatColor.AQUA + "/reports accept <id>" + ChatColor.BLUE + " - Change the state of a report to: ACCEPTED(closed).");
			sender.sendMessage(ChatUtilities.PREFIX + ChatColor.AQUA + "/reports deny <id>" + ChatColor.BLUE + " - Change the state of a report to: DENIED(closed).");
			sender.sendMessage(ChatUtilities.PREFIX + "-========================================-");
			return false;
		}else
		if(args[0].equalsIgnoreCase("all")) {
			return showAll(p);
		}else
		if(args[0].equalsIgnoreCase("show")) {
			return show(p, args);
		}else
		if(args[0].equalsIgnoreCase("take")) {
			return take(p, args);
		}else
		if(args[0].equalsIgnoreCase("accept")) {
			return accept(p, args);
		}else
		if(args[0].equalsIgnoreCase("deny")) {
			return deny(p, args);
		}else {
			sender.sendMessage(ChatUtils.COMMAND_NOT_FOUND.getMessage());
		}
		return false;
	}
	
	private boolean take(Player p, String[] args) {
		if(args.length != 2) {
			p.sendMessage(ChatUtils.INVALID_USAGE + "/reports take <id>");
			return false;
		}
		if(!isInteger(args[1])) {
			p.sendMessage(ChatUtils.NOT_INTEGER.getMessage());
			return false;
		}
		int id = Integer.parseInt(args[1]);
		if(!ReportManager.getReportManager().reports.keySet().contains(id)) {
			p.sendMessage(ChatUtilities.PREFIX + ChatColor.RED + "That report doesn't exist!");
			return false;
		}
		Report r = ReportManager.getReportManager().getReport(id);
		r.setReportState(ReportState.IN_TREATMENT);
		p.sendMessage(ChatUtilities.PREFIX + "Changed the state of report " + id + " to " + ChatColor.AQUA + r.getReportState().getMessage());
		
		Player t = Bukkit.getPlayer(r.getReporter());
		
		if(t == null) return false;
		
		t.sendMessage(ChatUtilities.PREFIX + "The state of your report(" + r.getId() + ") has been changed to: " + ChatColor.AQUA + r.getReportState().getMessage()); 
		
		
		return false;
	}
	
	private boolean accept(Player p, String[] args) {
		if(args.length != 2) {
			p.sendMessage(ChatUtils.INVALID_USAGE + "/reports accept <id>");
			return false;
		}
		if(!isInteger(args[1])) {
			p.sendMessage(ChatUtils.NOT_INTEGER.getMessage());
			return false;
		}
		int id = Integer.parseInt(args[1]);
		if(!ReportManager.getReportManager().reports.keySet().contains(id)) {
			p.sendMessage(ChatUtilities.PREFIX + ChatColor.RED + "That report doesn't exist!");
			return false;
		}
		Report r = ReportManager.getReportManager().getReport(id);
		r.setReportState(ReportState.ACCEPTED);
		p.sendMessage(ChatUtilities.PREFIX + "Changed the state of report " + id + " to " + ChatColor.AQUA + r.getReportState().getMessage());
		
		Player t = Bukkit.getPlayer(r.getReporter());
		
		if(t == null) return false;
		
		t.sendMessage(ChatUtilities.PREFIX + "The state of your report(" + r.getId() + ") has been changed to: " + ChatColor.AQUA + r.getReportState().getMessage()); 
		
		return false;
	}
	
	private boolean deny(Player p, String[] args) {
		if(args.length != 2) {
			p.sendMessage(ChatUtils.INVALID_USAGE + "/reports deny <id>");
			return false;
		}
		if(!isInteger(args[1])) {
			p.sendMessage(ChatUtils.NOT_INTEGER.getMessage());
			return false;
		}
		int id = Integer.parseInt(args[1]);
		if(!ReportManager.getReportManager().reports.keySet().contains(id)) {
			p.sendMessage(ChatUtilities.PREFIX + ChatColor.RED + "That report doesn't exist!");
			return false;
		}
		Report r = ReportManager.getReportManager().getReport(id);
		r.setReportState(ReportState.DENIED);
		p.sendMessage(ChatUtilities.PREFIX + "Changed the state of report " + id + " to " + ChatColor.AQUA + r.getReportState().getMessage());
		
		Player t = Bukkit.getPlayer(r.getReporter());
		
		if(t == null) return false;
		
		t.sendMessage(ChatUtilities.PREFIX + "The state of your report(" + r.getId() + ") has been changed to: " + ChatColor.AQUA + r.getReportState().getMessage()); 
		
		
		return false;
	}

	public boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    return true;
	}
	
	private boolean show(Player p, String[] args) {
		if(args.length != 2) {
			p.sendMessage(ChatUtils.INVALID_USAGE + "/reports show <id>");
			return false;
		}
		if(!isInteger(args[1])) {
			p.sendMessage(ChatUtils.NOT_INTEGER.getMessage());
			return false;
		}
		int id = Integer.parseInt(args[1]);
		if(!ReportManager.getReportManager().reports.keySet().contains(id)) {
			p.sendMessage(ChatUtilities.PREFIX + ChatColor.RED + "That report doesn't exist!");
			return false;
		}
		Report r = ReportManager.getReportManager().getReport(id);
		p.sendMessage(ChatUtilities.PREFIX + "-========================================-");
		p.sendMessage(ChatUtilities.PREFIX + "ID: " + id);
		p.sendMessage(ChatUtilities.PREFIX + "Name: " + r.getName());
		p.sendMessage(ChatUtilities.PREFIX + "Reporter: " + r.getReporter());
		p.sendMessage(ChatUtilities.PREFIX + "Reason: " + r.getReason());
		p.sendMessage(ChatUtilities.PREFIX + "State: " + r.getReportState().getMessage());
		p.sendMessage(ChatUtilities.PREFIX + "Date: " + new Date(((long)r.getDate())*1000L));
		p.sendMessage(ChatUtilities.PREFIX + "-========================================-");
		return false;
	}

	private boolean showAll(Player p) {
		if(ReportManager.getReportManager().reports.size() == 0) {
			p.sendMessage(ChatUtilities.PREFIX + "-========================================-");
			p.sendMessage(ChatUtilities.PREFIX + "No reports have been found!");
			p.sendMessage(ChatUtilities.PREFIX + "Enjoy the quietness ;)");
			p.sendMessage(ChatUtilities.PREFIX + "-========================================-");
			return false;
		}
		p.sendMessage(ChatUtilities.PREFIX + "-========================================-");
		for(Report r : ReportManager.getReportManager().reports.values()) {
			p.sendMessage(ChatUtilities.PREFIX + r.getId() + " " + r.getName() + ": " + ChatColor.AQUA + r.getReason());
		}
		p.sendMessage(ChatUtilities.PREFIX + "-========================================-");
		return false;
	}

}
