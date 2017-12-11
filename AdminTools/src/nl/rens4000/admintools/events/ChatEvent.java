package nl.rens4000.admintools.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		if(e.getMessage().startsWith("!")) {
			if(e.getPlayer().hasPermission("Admintools.Admin")) {
				e.setCancelled(true);
				for(Player p : Bukkit.getOnlinePlayers()) {
					if(p.hasPermission("Admintools.Admin")) {
						p.sendMessage(ChatColor.AQUA + "AdminChat: " + ChatColor.GREEN + e.getPlayer().getName() + "" + e.getMessage().replace('!', ' '));
					}
				}
			}
		}
	}

}
