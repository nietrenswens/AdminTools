package nl.rens4000.admintools.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import nl.rens4000.admintools.AdminTools;
import nl.rens4000.admintools.managers.ConfigManager;
import nl.rens4000.admintools.utils.ChatUtilities;
import nl.rens4000.admintools.utils.ChatUtils;
import nl.rens4000.admintools.utils.User;

public class VanishCMD implements CommandExecutor {
	
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
		
		User u  = AdminTools.getAdminTools().getUser(p.getUniqueId());
		
		u.setVanished(!u.isVanished());
		
		if(u.isVanished()) {
			p.sendMessage(ChatUtilities.PREFIX + "You've been made invisible!");
			for(Player pl : Bukkit.getOnlinePlayers()) {
				if(pl.getName() != p.getName()) {
					pl.hidePlayer(p);
				}
			}
			ChatUtilities.broadcastNoPrefix(ChatColor.translateAlternateColorCodes('&', ConfigManager.getConfigManager().getConfig().getString("messages.vanishleavemessage").replaceAll("%player%", p.getName())));
		} else {
			for(Player pl : Bukkit.getOnlinePlayers()) {
				if(pl.getName() != p.getName()) {
					pl.showPlayer(p);
				}
			}
			p.sendMessage(ChatUtilities.PREFIX + "You've been made visible again!");
			ChatUtilities.broadcastNoPrefix(ChatColor.translateAlternateColorCodes('&', ConfigManager.getConfigManager().getConfig().getString("messages.vanishjoinmessage").replaceAll("%player%", p.getName())));
		}
		return false;
	}

}
