package nl.rens4000.admintools.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import nl.rens4000.admintools.AdminTools;
import nl.rens4000.admintools.managers.ConfigManager;
import nl.rens4000.admintools.managers.ReportManager;
import nl.rens4000.admintools.utils.ChatUtilities;
import nl.rens4000.admintools.utils.ChatUtils;
import nl.rens4000.admintools.utils.User;

public class ReportCMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatUtils.NOT_PLAYER.getMessage());
			return false;
		}
		if(args.length < 2) {
			sender.sendMessage(ChatUtilities.PREFIX + ChatColor.RED + "Wrong usage of command! Usage: /report <player> <reason of the report>");
			return false;
		}
		
		Player pl = Bukkit.getPlayer(args[0]);
		
		if(pl == null) {
			sender.sendMessage(ChatUtils.PLAYER_OFFLINE.getMessage());
			return false;
		}
		Player p = (Player) sender;
		
		User u = AdminTools.getAdminTools().getUser(p.getUniqueId());
		
		if(u.getReportDelay() != 0) {
			sender.sendMessage(ChatUtilities.PREFIX + ChatColor.RED + "You still have a delay of: " + u.getReportDelay() + " seconds!");
			return false;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i < args.length; i++) {
			sb.append(args[i]).append(" ");
		}
		
		String reason = sb.toString().trim();
		
		int id = ReportManager.getReportManager().createReport(sender.getName(), args[0], reason, p);
		
		sender.sendMessage(ChatUtilities.PREFIX + "Report has been created by " + ChatColor.AQUA + sender.getName() + ChatColor.RESET
				+ " about " + ChatColor.AQUA + args[0] + ChatColor.RESET + "! The id of the report is " + ChatColor.AQUA + id);
		
		u.setReportDelay(ConfigManager.getConfigManager().getConfig().getInt("reports.delaybetweenreport"));
		
		ReportManager.getReportManager().runDelayTimer(u);
		
		return false;
	}

}
