package com.bobcat4848.essentials;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.jline.internal.InputStreamReader;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;

public class Essentials extends JavaPlugin {

	public static Essentials plugin;
	private FileConfiguration warpConfig = null;
	private File warps = null;

	public void onEnable() {
		getLogger().info("Plugin Enabled!");
		loadConfigs();
		getLogger().info("Config loaded!");
		registerCommands();
		getLogger().info("Commands loaded!");
		registerEvents();
		getLogger().info("Events loaded!");
		plugin = this;
	}
	
	private void loadConfigs() {
		saveDefaultConfig();
		saveCustomConfig();
	}
	
	private void registerCommands() {
		getCommand("teleport").setExecutor(new Teleport());
		getCommand("tpa").setExecutor(new Teleport());
		getCommand("tpahere").setExecutor(new Teleport());
		getCommand("spawn").setExecutor(new Spawn());
		getCommand("setspawn").setExecutor(new Spawn());
		getCommand("repair").setExecutor(new Repair());
		getCommand("rename").setExecutor(new Rename());
		getCommand("heal").setExecutor(new Utility());
		getCommand("feed").setExecutor(new Utility());
		getCommand("god").setExecutor(new Utility());
		getCommand("clearinventory").setExecutor(new Utility());
		getCommand("fly").setExecutor(new Utility());
		getCommand("speed").setExecutor(new Speed());
		getCommand("timebe").setExecutor(new Time());
		getCommand("enderchest").setExecutor(new Enderchest());
		getCommand("kill").setExecutor(new Kill());
		getCommand("lightning").setExecutor(new Lightning());
		getCommand("workbench").setExecutor(new Workbench());
		getCommand("warp").setExecutor(new Warps());
		getCommand("setwarp").setExecutor(new Warps());
	}
	
	private void registerEvents() {
		getServer().getPluginManager().registerEvents(new Teleport(), this);
		getServer().getPluginManager().registerEvents(new UtilityListener(), this);
	}


	// Custom Config Methods
	public void reloadCustomConfig() {
		if (warps == null) {
			warps = new File(getDataFolder(), "warps.yml");
		}
		warpConfig = YamlConfiguration.loadConfiguration(warps);

		// Look for defaults in the jar

		try {
			Reader defConfigStream = new InputStreamReader(this.getResource("warps.yml"), "UTF8");

			if (defConfigStream != null) {
				YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
				warpConfig.setDefaults(defConfig);
			}
		} catch (Exception e) {

		}
	}

	public FileConfiguration getWarpConfig() {
		if (warpConfig == null) {
			reloadCustomConfig();
		}
		return warpConfig;
	}

	public void saveCustomConfig() {
		if (warpConfig == null || warps == null) {
			return;
		}
		try {
			getWarpConfig().save(warps);
		} catch (IOException ex) {
			getLogger().log(Level.SEVERE, "Could not save config to " + warps, ex);
		}
	}


}
