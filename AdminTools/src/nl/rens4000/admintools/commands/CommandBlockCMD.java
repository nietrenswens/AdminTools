package nl.rens4000.admintools.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nl.rens4000.admintools.AdminTools;
import nl.rens4000.admintools.utils.ChatUtilities;
import nl.rens4000.admintools.utils.ChatUtils;
import nl.rens4000.admintools.utils.User;

public class CommandBlockCMD implements CommandExecutor {

	@SuppressWarnings("deprecation")
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
			p.sendMessage(ChatUtils.INVALID_USAGE.getMessage() + "/commandblock <player>");
			return false;
		}
		
		Player t = Bukkit.getPlayer(args[0]);
		
		if(t == null) {
			p.sendMessage(ChatUtils.PLAYER_OFFLINE.getMessage());
			return false;
		}
		
		User u = AdminTools.getAdminTools().getUser(t.getUniqueId());

		u.setCommandBlocked(!u.isCommandBlocked());
		
		if(u.isFrozen()) {
			p.sendMessage(ChatUtilities.PREFIX + "You have commandblocked: " + t.getName() + ".");
			t.sendMessage(ChatUtilities.PREFIX + "You have been commandblocked by an admin!");
			t.sendTitle(ChatColor.RED + "You have been commandblocked!", ChatColor.AQUA + "By an admin!");
		} else {
			p.sendMessage(ChatUtilities.PREFIX + "You have uncommandblocked: " + t.getName() + ".");
			t.sendMessage(ChatUtilities.PREFIX + "You have been uncommandblocked by an admin!");
			t.sendTitle(ChatColor.RED + "You have been uncommandblocked!", ChatColor.AQUA + "By an admin!");
		}
		
		return false;
	}
	
}
