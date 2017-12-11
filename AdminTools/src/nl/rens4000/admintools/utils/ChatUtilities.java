package nl.rens4000.admintools.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatUtilities {
	
	public static String PREFIX = ChatColor.AQUA + "AdminTools " + ChatColor.RESET;
	
	@SuppressWarnings("deprecation")
	public static void broadcastTitleThroughAdmins(String title, String title2) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(p.hasPermission("Admintools.Admin")) {
				p.sendTitle(title, title2);
			}
		}
	}
	
	public static void broadcastThroughAdmins(String s) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(p.hasPermission("Admintools.Admin")) {
				p.sendMessage(PREFIX + s);
			}
		}
	}

	public static void broadcastNoPrefix(String message) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.sendMessage(message);
		}
	}

}
