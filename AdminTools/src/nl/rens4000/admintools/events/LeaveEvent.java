package nl.rens4000.admintools.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import nl.rens4000.admintools.AdminTools;
import nl.rens4000.admintools.managers.ConfigManager;
import nl.rens4000.admintools.utils.User;

public class LeaveEvent implements Listener {
	
	@EventHandler
	public void onLeaveEvent(PlayerQuitEvent e) {
		User u = AdminTools.getAdminTools().getUser(e.getPlayer().getUniqueId());
		ConfigManager cm = ConfigManager.getConfigManager();
		cm.getUsers().set("users." + e.getPlayer().getUniqueId() + ".frozen", u.isFrozen());
		cm.getUsers().set("users." + e.getPlayer().getUniqueId() + ".logins", u.getLogins());
		cm.getUsers().set("users." + e.getPlayer().getUniqueId() + ".timesBeenReported", u.getTimesBeenReported());
		cm.getUsers().set("users." + e.getPlayer().getUniqueId() + ".vanished", u.isVanished());
		cm.getUsers().set("users." + e.getPlayer().getUniqueId() + ".reportDelay", u.getReportDelay());
		cm.getUsers().set("users." + e.getPlayer().getUniqueId() + ".commandspy", u.isCommandSpy());
		AdminTools.getAdminTools().getLogger().info("Saved info of: " + u.getPlayer().getName());
		cm.save();
	}

}
