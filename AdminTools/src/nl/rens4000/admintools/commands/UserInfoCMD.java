package nl.rens4000.admintools.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nl.rens4000.admintools.AdminTools;
import nl.rens4000.admintools.utils.ChatUtilities;
import nl.rens4000.admintools.utils.ChatUtils;
import nl.rens4000.admintools.utils.User;

public class UserInfoCMD implements CommandExecutor{

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
		if(args.length != 1) {
			p.sendMessage(ChatUtils.INVALID_USAGE.getMessage() + "/userinfo <player>");
			return false;
		}
		
		Player t = Bukkit.getPlayer(args[0]);
		
		if(t == null) {
			p.sendMessage(ChatUtils.PLAYER_OFFLINE.getMessage());
			return false;
		}
		
		User u = AdminTools.getAdminTools().getUser(t.getUniqueId());
		
		p.sendMessage(ChatUtilities.PREFIX + "-========================================-");
		p.sendMessage(ChatUtilities.PREFIX + "Name: " + ChatColor.AQUA + t.getName());
		p.sendMessage(ChatUtilities.PREFIX + "UUID: " + ChatColor.AQUA + t.getUniqueId());
		p.sendMessage(ChatUtilities.PREFIX + "Gamemode: " + ChatColor.AQUA + t.getGameMode().toString());
		p.sendMessage(ChatUtilities.PREFIX + "X:" + ChatColor.AQUA + t.getLocation().getX());
		p.sendMessage(ChatUtilities.PREFIX + "Y:" + ChatColor.AQUA + t.getLocation().getY());
		p.sendMessage(ChatUtilities.PREFIX + "Z:" + ChatColor.AQUA + t.getLocation().getZ());
		p.sendMessage(ChatUtilities.PREFIX + "Logins: " + ChatColor.AQUA + u.getLogins());
		p.sendMessage(ChatUtilities.PREFIX + "Frozen: " + ChatColor.AQUA + u.isFrozen());
		p.sendMessage(ChatUtilities.PREFIX + "Times been reported: " + ChatColor.AQUA + u.getTimesBeenReported());
		p.sendMessage(ChatUtilities.PREFIX + "-========================================-");
		
		return false;
	}

}
