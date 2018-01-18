package nl.rens4000.admintools.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nl.rens4000.admintools.AdminTools;
import nl.rens4000.admintools.utils.ChatUtilities;
import nl.rens4000.admintools.utils.ChatUtils;
import nl.rens4000.admintools.utils.User;

public class CommandSpy implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatUtils.NOT_PLAYER.getMessage());
			return false;
		}
		
		Player p = (Player) sender;
		
		if(!p.hasPermission("Admintools.CommandSpy")) {
			p.sendMessage(ChatUtils.NO_PERM.getMessage());
			return false;
		}
		
		User u = AdminTools.getAdminTools().getUser(p.getUniqueId());
		
		u.setCommandSpy(!u.isCommandSpy());
		
		if(u.isCommandSpy()) {
			p.sendMessage(ChatUtilities.PREFIX + "You have enabled commandspy! You are now able to see every executed command!");
		} else {
			p.sendMessage(ChatUtilities.PREFIX + "You have disabled commandspy!");
		}
		
		return false;
	}
	
	

}
