package nl.rens4000.admintools.managers;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import nl.rens4000.admintools.AdminTools;

public class ConfigManager {
	
	private File configFile;
	private FileConfiguration config;
	
	private File reportsFile;
	private FileConfiguration reports;
	
	private File usersFile;
	private FileConfiguration users;
	
	private File swearWordsFile;
	private FileConfiguration swearWords;
	
	private static ConfigManager configManager;
	
	public ConfigManager(AdminTools at) {
		configManager = this;
		configFile = new File(at.getDataFolder(), "config.yml");
		config = YamlConfiguration.loadConfiguration(configFile);
		reportsFile = new File(at.getDataFolder(), "reports.yml");
		reports = YamlConfiguration.loadConfiguration(reportsFile);
		usersFile = new File(at.getDataFolder(), "users.yml");
		setUsers(YamlConfiguration.loadConfiguration(usersFile));
		swearWordsFile = new File(at.getDataFolder(), "swearwords.yml");
		swearWords = YamlConfiguration.loadConfiguration(swearWordsFile);
	}
	
	public void save() {
		try {
			config.save(configFile);
			reports.save(reportsFile);
			users.save(usersFile);
			swearWords.save(swearWordsFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public File getSwearWordsFile() {
		return swearWordsFile;
	}

	public FileConfiguration getSwearWords() {
		return swearWords;
	}

	public File getConfigFile() {
		return configFile;
	}

	public FileConfiguration getConfig() {
		return config;
	}
	
	public File getReportsFile() {
		return reportsFile;
	}
	
	public FileConfiguration getReports() {
		return reports;
	}
	
	public static ConfigManager getConfigManager() {
		return configManager;
	}

	public FileConfiguration getUsers() {
		return users;
	}

	public void setUsers(FileConfiguration users) {
		this.users = users;
	}
	
}
