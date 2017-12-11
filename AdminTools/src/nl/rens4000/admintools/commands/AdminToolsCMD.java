package nl.rens4000.admintools.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import nl.rens4000.admintools.AdminTools;
import nl.rens4000.admintools.utils.ChatUtilities;
import nl.rens4000.admintools.utils.ChatUtils;

public class AdminToolsCMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!sender.hasPermission("Admintools.Admin")) {
			sender.sendMessage(ChatUtils.NO_PERM.getMessage());
			return false;
		}
		if(args.length < 1) {
			sender.sendMessage(ChatUtilities.PREFIX + "-========================================-");
			sender.sendMessage(ChatUtilities.PREFIX + ChatColor.AQUA + "Admin Tools v" + AdminTools.getAdminTools().getDescription().getVersion());
			sender.sendMessage(ChatUtilities.PREFIX + ChatColor.AQUA + "/admintools help" + ChatColor.BLUE + " - To show all the commands.");
			sender.sendMessage(ChatUtilities.PREFIX + "-========================================-");
			return false;
		}
		if(args[0].equalsIgnoreCase("help")) {
			sender.sendMessage(ChatUtilities.PREFIX + "-========================================-");
			sender.sendMessage(ChatUtilities.PREFIX + ChatColor.AQUA + "/admintools" + ChatColor.BLUE + " - Main command of the plugin.");
			sender.sendMessage(ChatUtilities.PREFIX + ChatColor.AQUA + "/freeze <player>" + ChatColor.BLUE + " - Freezes a player.");
			sender.sendMessage(ChatUtilities.PREFIX + ChatColor.AQUA + "/report <player> <reason of report>" + ChatColor.BLUE + " - Player report command.");
			sender.sendMessage(ChatUtilities.PREFIX + ChatColor.AQUA + "/reports" + ChatColor.BLUE + " - Main command of the reports handling.");
			sender.sendMessage(ChatUtilities.PREFIX + ChatColor.AQUA + "/userinfo <user>" + ChatColor.BLUE + " - Shows you some information about the given player.");
			sender.sendMessage(ChatUtilities.PREFIX + ChatColor.AQUA + "/vanish" + ChatColor.BLUE + " - Become invisible.");
			sender.sendMessage(ChatUtilities.PREFIX + "-========================================-");
			return false;
		} else {
			sender.sendMessage(ChatUtils.COMMAND_NOT_FOUND.getMessage());
		}
		return false;
	}

}
