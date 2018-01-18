package nl.rens4000.admintools.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import nl.rens4000.admintools.AdminTools;
import nl.rens4000.admintools.utils.ChatUtilities;
import nl.rens4000.admintools.utils.User;

public class CommandEvent implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onCommandEvent(PlayerCommandPreprocessEvent e) {
		User u = AdminTools.getAdminTools().getUser(e.getPlayer().getUniqueId());
		if(u.isFrozen()) {
			e.getPlayer().sendMessage(ChatUtilities.PREFIX + "You can't execute that command because you are frozen");
			e.getPlayer().sendTitle(ChatColor.RED + "You have been frozen!", ChatColor.AQUA + "By an admin!");
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void commandSpyCheck(PlayerCommandPreprocessEvent e) {
		for(User u : AdminTools.getAdminTools().users.values()) {
			if(u.isCommandSpy()) {
				User u1 = AdminTools.getAdminTools().getUser(e.getPlayer().getUniqueId());
				if(u.getPlayer().getName().equals(u1.getPlayer().getName())) return;
				u.getPlayer().sendMessage(ChatUtilities.PREFIX + "" + ChatColor.RED + "COMMANDSPY " + ChatColor.AQUA + e.getPlayer().getName() + ChatColor.GRAY + ": " + e.getMessage());
			}
		}
	}

}
