package nl.rens4000.admintools.utils;

import org.bukkit.ChatColor;

public enum ChatUtils {
	
	NO_PERM(ChatUtilities.PREFIX + ChatColor.RED + "You don't have permission to perform that command!"),
	FROZEN(ChatUtilities.PREFIX + ChatColor.RED + "You can't move because you're frozen by an admin!"),
	NOT_PLAYER(ChatUtilities.PREFIX + ChatColor.RED + "You have to be a player to execute this command!"),
	INVALID_USAGE(ChatUtilities.PREFIX + ChatColor.RED + "Invalid usage! Usage: " + ChatColor.AQUA),
	NOT_INTEGER(ChatUtilities.PREFIX + ChatColor.RED + "The given input is not a number!"),
	PLAYER_OFFLINE(ChatUtilities.PREFIX + ChatColor.RED + "The player isn't online or doesn't exist!"), 
	COMMAND_NOT_FOUND(ChatUtilities.PREFIX + ChatColor.RED + "The command couldn't been found!");
	
	
	private String message;
	
	ChatUtils(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

}
