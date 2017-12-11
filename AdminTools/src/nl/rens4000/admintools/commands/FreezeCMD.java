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

public class FreezeCMD implements CommandExecutor {

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
			p.sendMessage(ChatUtils.INVALID_USAGE.getMessage() + "/freeze <player>");
			return false;
		}
		
		Player t = Bukkit.getPlayer(args[0]);
		
		if(t == null) {
			p.sendMessage(ChatUtils.PLAYER_OFFLINE.getMessage());
			return false;
		}
		
		User u = AdminTools.getAdminTools().getUser(t.getUniqueId());
		
		Block b = t.getLocation().getWorld().getHighestBlockAt(t.getLocation());
		
		t.teleport(new Location(t.getLocation().getWorld(), t.getLocation().getX(), b.getLocation().getY(), t.getLocation().getZ()));

		u.setFrozen(!u.isFrozen());
		
		if(u.isFrozen()) {
			p.sendMessage(ChatUtilities.PREFIX + "You have frozen: " + t.getName() + ".");
			t.sendMessage(ChatUtilities.PREFIX + "You have been frozen by an admin!");
			t.sendTitle(ChatColor.RED + "You have been frozen!", ChatColor.AQUA + "By an admin!");
		} else {
			p.sendMessage(ChatUtilities.PREFIX + "You have unfrozen: " + t.getName() + ".");
			t.sendMessage(ChatUtilities.PREFIX + "You have been unfrozen by an admin!");
			t.sendTitle(ChatColor.RED + "You have been unfrozen!", ChatColor.AQUA + "By an admin!");
		}
		
		return false;
	}

}
