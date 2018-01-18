package nl.rens4000.admintools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;

import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import nl.rens4000.admintools.commands.AdminToolsCMD;
import nl.rens4000.admintools.commands.ChatManager;
import nl.rens4000.admintools.commands.CommandSpy;
import nl.rens4000.admintools.commands.FreezeCMD;
import nl.rens4000.admintools.commands.ReportCMD;
import nl.rens4000.admintools.commands.ReportsCMD;
import nl.rens4000.admintools.commands.UserInfoCMD;
import nl.rens4000.admintools.commands.VanishCMD;
import nl.rens4000.admintools.events.ChatEvent;
import nl.rens4000.admintools.events.CommandEvent;
import nl.rens4000.admintools.events.JoinEvent;
import nl.rens4000.admintools.events.LeaveEvent;
import nl.rens4000.admintools.events.MoveEvent;
import nl.rens4000.admintools.managers.ConfigManager;
import nl.rens4000.admintools.managers.ReportManager;
import nl.rens4000.admintools.utils.User;

public class AdminTools extends JavaPlugin {
	
	public Map<UUID, User> users;
	
	private static AdminTools adminTools;
	
	List<String> sweartemp = new ArrayList<String>();
	
	List<String> swear = new ArrayList<String>();
	
	public List<String> getSwear() {
		return swear;
	}
	
	private boolean slowchat = false;
	
	@Override
	public void onEnable() {
		users = new HashMap<UUID, User>();
		PluginManager pm = Bukkit.getPluginManager();
		adminTools = this;
		
		//Initialize all constructors
		new ConfigManager(this);
		new ReportManager();
		
		//Generate default config
		generateDefaultConfig();
		
		//Load commands
		getCommand("report").setExecutor(new ReportCMD());
		getCommand("reports").setExecutor(new ReportsCMD());
		getCommand("freeze").setExecutor(new FreezeCMD());
		getCommand("vanish").setExecutor(new VanishCMD());
		getCommand("userinfo").setExecutor(new UserInfoCMD());
		getCommand("admintools").setExecutor(new AdminToolsCMD());
		getCommand("chatmanager").setExecutor(new ChatManager());
		getCommand("commandspy").setExecutor(new CommandSpy());
		
		//Load events
		pm.registerEvents(new CommandEvent(), this);
		pm.registerEvents(new LeaveEvent(), this);
		pm.registerEvents(new JoinEvent(), this);
		pm.registerEvents(new MoveEvent(), this);
		pm.registerEvents(new ChatEvent(), this);
		
		//Load all reports
		ReportManager.getReportManager().loadAllReports();
		
		//Load all swear words into list
		List<String> temp = ConfigManager.getConfigManager().getSwearWords().getStringList("swearwords");
		swear = temp; //Not 1 of the best ways to do it but it will do the trick i guess
		
		 // All you have to do is adding this line in your onEnable method:
        Metrics metrics = new Metrics(this);

        metrics.addCustomChart(new Metrics.SingleLineChart("players_amount", new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                // (This is useless as there is already a player chart by default.)
                return Bukkit.getOnlinePlayers().size();
            }
        }));
	}
	
	private void generateDefaultConfig() {
		FileConfiguration config = ConfigManager.getConfigManager().getConfig();
		config.addDefault("messages.vanishleavemessage", "&e%player% left the game.");
		config.addDefault("messages.vanishjoinmessage", "&e%player% joined the game.");
		config.addDefault("reports.delaybetweenreport", 60);
		config.addDefault("reports.allowreporterstoseestatechanges", true);
		config.addDefault("chatmanager.slowchattime", 5);
		
		config.options().copyDefaults(true);
		
		//swear list default
		FileConfiguration swearWords = ConfigManager.getConfigManager().getSwearWords();
		initializeSwearDefault();
		
		swearWords.addDefault("swearwords", sweartemp);
		
		swearWords.options().copyDefaults(true);
		
		ConfigManager.getConfigManager().save();
	}
	
	private void initializeSwearDefault() {
		sweartemp.add("cunt");
		sweartemp.add("fuck");
		sweartemp.add("cancer");
		sweartemp.add("aids");
		sweartemp.add("motherfucker");
		sweartemp.add("shit");
		//Lets keep the server family friendly ;)
	}

	public boolean getSlowchat() {
		return slowchat;
	}
	
	public void setSlowchat(boolean b) {
		this.slowchat = b;
	}

	public User getUser(UUID u) {
		return users.get(u);
	}
	
	@Override
	public void onDisable() {
		ReportManager.getReportManager().saveAllReports();
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(p.hasPermission("Admintools.admin")) {
				p.kickPlayer(ChatColor.RED + "The server is being restarted/shutdown. \n NOTE: RELOADS AREN'T SUPPORTED! THIS WILL SCREW UP THE CONFIG OF: " + ChatColor.AQUA + "AdminTools." + ChatColor.RED + "\n If this is a reload, make sure to restart the server.");
				return;
			}
			p.kickPlayer(ChatColor.RED + "The server is being restarted/shutdown!");
		}
		
	}
	
	public static AdminTools getAdminTools() {
		return adminTools;
	}

}
