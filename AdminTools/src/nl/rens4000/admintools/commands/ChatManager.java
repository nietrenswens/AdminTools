package nl.rens4000.admintools.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nl.rens4000.admintools.utils.ChatUtilities;
import nl.rens4000.admintools.utils.ChatUtils;

public class ChatManager implements CommandExecutor{
	
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
			sender.sendMessage(ChatUtilities.PREFIX + ChatColor.AQUA + "ChatManager");
			sender.sendMessage(ChatUtilities.PREFIX + ChatColor.AQUA + "/chatmanager help" + ChatColor.BLUE + " - To show all the commands.");
			sender.sendMessage(ChatUtilities.PREFIX + "-========================================-");
			return false;
		}
		if(args[0].equalsIgnoreCase("help")) {
			sender.sendMessage(ChatUtilities.PREFIX + "-========================================-");
			sender.sendMessage(ChatUtilities.PREFIX + ChatColor.AQUA + "/chatmanager" + ChatColor.BLUE + " - Main command.");
			sender.sendMessage(ChatUtilities.PREFIX + ChatColor.AQUA + "/chatmanager clearchat" + ChatColor.BLUE + " - Clears the chat.");
			sender.sendMessage(ChatUtilities.PREFIX + "-========================================-");
			return false;
		} else if(args[0].equalsIgnoreCase("clearchat")) {
			for(int i = 0; i < 100; i++) {
				for(Player pl : Bukkit.getOnlinePlayers()) {
					pl.sendMessage("");
				}
			}
			p.sendMessage(ChatUtilities.PREFIX + "The chat has been cleared. Enjoy the emptyness :)");
		} else {
			sender.sendMessage(ChatUtils.COMMAND_NOT_FOUND.getMessage());
		}
		return false;
	}

}
