package nl.rens4000.admintools.events;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import nl.rens4000.admintools.AdminTools;
import nl.rens4000.admintools.managers.ConfigManager;
import nl.rens4000.admintools.managers.ReportManager;
import nl.rens4000.admintools.utils.User;

public class JoinEvent implements Listener {
	
	private ConfigManager cm = ConfigManager.getConfigManager();
	
	@EventHandler
	public void onJoinEvent(PlayerJoinEvent e) {
		AdminTools.getAdminTools().getLogger().info("JOIN EVENT CALLED!");
		if(!checkConfig(e.getPlayer().getUniqueId())) {
			AdminTools.getAdminTools().getLogger().info("CONFIG CHECKED");
			cm.getUsers().set("users." + e.getPlayer().getUniqueId() + ".frozen", false);
			cm.getUsers().set("users." + e.getPlayer().getUniqueId() + ".logins", 1);
			cm.getUsers().set("users." + e.getPlayer().getUniqueId() + ".timesBeenReported", 0);
			cm.getUsers().set("users." + e.getPlayer().getUniqueId() + ".vanished", false);
			cm.getUsers().set("users." + e.getPlayer().getUniqueId() + ".reportDelay", 0);
			cm.getUsers().set("users." + e.getPlayer().getUniqueId() + ".commandspy", false);
			cm.save();
		}
		User u = new User(e.getPlayer(), cm.getUsers().getBoolean("users." + e.getPlayer().getUniqueId() + ".frozen"), 
				cm.getUsers().getInt("users." + e.getPlayer().getUniqueId() + ".logins"), 
				cm.getUsers().getInt("users." + e.getPlayer().getUniqueId() + ".timesBeenReported"),
				cm.getUsers().getBoolean("users." + e.getPlayer().getUniqueId() + ".vanished"),
				cm.getUsers().getInt("users." + e.getPlayer().getUniqueId() + ".reportDelay"),
				cm.getUsers().getBoolean("users." + e.getPlayer().getUniqueId() + ".commandspy"));
				u.setLogins(u.getLogins() + 1);
				AdminTools.getAdminTools().users.put(e.getPlayer().getUniqueId(), u);
				AdminTools.getAdminTools().getLogger().info("Added user: " + e.getPlayer().getUniqueId());
	
		for(User user : AdminTools.getAdminTools().users.values()) {
			if(user.isVanished()) {
				for(Player p : Bukkit.getOnlinePlayers()) {
					p.hidePlayer(user.getPlayer());
				}
			}
		}
		
		if(!(u.getReportDelay() <= 0)) {
			ReportManager.getReportManager().runDelayTimer(u);
		}
		
	}

	private boolean checkConfig(UUID u) {
		return cm.getUsers().contains("users." + u + ".frozen");
	}

}
