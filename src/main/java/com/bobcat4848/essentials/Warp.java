package com.bobcat4848.essentials;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.jline.internal.InputStreamReader;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;

import static org.bukkit.Bukkit.getLogger;

public class Warp {

    protected static FileConfiguration warpConfig = null;
    protected static File warps = null;

    // Custom Config Methods
    public static void reloadCustomConfig() {
        if (warps == null) {
            //warps = new File(getDataFolder(), "warps.yml");
            if (!warps.exists()) {
                try {
                    warps.createNewFile();
                } catch (Exception e) {
                    System.out.println("Couldn't create new file!");
                }
            }
        }
        warpConfig = YamlConfiguration.loadConfiguration(warps);

        // Look for defaults in the jar
        try {
            Reader defConfigStream = new InputStreamReader(Essentials.plugin.getResource("warps.yml"), "UTF8");

            if (defConfigStream != null) {
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                warpConfig.setDefaults(defConfig);
            }
        } catch (Exception e) {

        }
    }

    public static FileConfiguration getWarpConfig() {
        if (warpConfig == null) {
            reloadCustomConfig();
        }
        return warpConfig;
    }

    public static void saveCustomConfig() {
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
