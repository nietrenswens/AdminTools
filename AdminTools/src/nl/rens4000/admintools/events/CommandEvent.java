package nl.rens4000.admintools.events;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import nl.rens4000.admintools.AdminTools;
import nl.rens4000.admintools.managers.ConfigManager;
import nl.rens4000.admintools.utils.ChatUtilities;
import nl.rens4000.admintools.utils.User;

public class CommandEvent implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onCommandEvent(PlayerCommandPreprocessEvent e) {
		User u = AdminTools.getAdminTools().getUser(e.getPlayer().getUniqueId());
		if(u.isFrozen()) {
			List<String> cmds = ConfigManager.getConfigManager().getConfig().getStringList("frozen.whitelistedcommand");
			for (String command : cmds) {
				if (e.getMessage().equalsIgnoreCase("/" + command)) {
					return;
				}
			}
			e.getPlayer().sendMessage(ChatUtilities.PREFIX + "You can't execute that command because you are frozen");
			e.getPlayer().sendTitle(ChatColor.RED + "You have been frozen!", ChatColor.AQUA + "By an admin!");
			e.setCancelled(true);
		}
	}

}
