package com.bobcat4848.essentials;

import org.bukkit.plugin.java.JavaPlugin;

public class Essentials extends JavaPlugin {

	public static Essentials plugin;
	public configFile;
	
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
		configFile = new File(Bukkit.getServer().getPluginManager().getPlugin("YourPlugin").getDataFolder(), "config.yml");

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
}
