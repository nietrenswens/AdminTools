package nl.rens4000.admintools.events;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import nl.rens4000.admintools.AdminTools;
import nl.rens4000.admintools.managers.ConfigManager;
import nl.rens4000.admintools.utils.ChatUtilities;

public class ChatEvent implements Listener {
	
	public Map<String, Integer> cooldown = new HashMap<String, Integer>();
	
	ConfigManager cm = new ConfigManager(AdminTools.getAdminTools());
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		if(e.getMessage().startsWith("!")) {
			if(e.getPlayer().hasPermission("Admintools.StaffChat")) {
				e.setCancelled(true);
				for(Player p : Bukkit.getOnlinePlayers()) {
					if(p.hasPermission("Admintools.StaffChat")) {
						p.sendMessage(ChatColor.AQUA + "AdminChat: " + ChatColor.GREEN + e.getPlayer().getName() + "" + e.getMessage().replace('!', ' '));
					}
				return;
				}
			}
		}
		if(AdminTools.getAdminTools().getSlowchat()) {
			if(e.getPlayer().hasPermission("Admintools.bypass.SlowChat")) return;
			if(cooldown.containsKey(e.getPlayer().getName())) {
				e.getPlayer().sendMessage(ChatUtilities.PREFIX + "Slow chat is activated! Please, wait: " + cooldown.get(e.getPlayer().getName()) + " seconds.");
				e.setCancelled(true);
				return;
			}
			cooldown.put(e.getPlayer().getName(), cm.getConfig().getInt("chatmanager.slowchattime"));
			new BukkitRunnable() {

				@Override
				public void run() {
					int i = cooldown.get(e.getPlayer().getName());
					cooldown.put(e.getPlayer().getName(), i-1);
					if(cooldown.get(e.getPlayer().getName()) <= 0) {
						cooldown.remove(e.getPlayer().getName());
						cancel();
					}
				}
				
			}.runTaskTimerAsynchronously(AdminTools.getAdminTools(), 0, 20);
		}
	}
	
	@EventHandler
	public void swearCheck(AsyncPlayerChatEvent e) {
		for(String s : e.getMessage().split(" ")) {
			if(AdminTools.getAdminTools().getSwear().contains(s)) {
				e.setCancelled(true);
				e.getPlayer().sendMessage(ChatUtilities.PREFIX + "" + ChatColor.RED + "Swearing is not allowed!");
			}
		}
	}

}
