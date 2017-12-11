package nl.rens4000.admintools.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import nl.rens4000.admintools.AdminTools;
import nl.rens4000.admintools.utils.ChatUtils;
import nl.rens4000.admintools.utils.User;

public class MoveEvent implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		User u = AdminTools.getAdminTools().getUser(e.getPlayer().getUniqueId());
		if(u.isFrozen()) {
			e.setTo(e.getFrom());
			e.getPlayer().sendMessage(ChatUtils.FROZEN.getMessage());
			e.getPlayer().sendTitle(ChatColor.RED + "You have been frozen!", ChatColor.AQUA + "By an admin!");
		}
	}

}
