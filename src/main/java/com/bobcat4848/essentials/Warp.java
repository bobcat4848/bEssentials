package com.bobcat4848.essentials;

import com.bobcat4848.essentials.Essentials;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import static org.bukkit.Bukkit.getLogger;

public class Warp {

    public static FileConfiguration warpConfig = null;
    public static File warps = null;

    // Custom Config Methods
    public static void reloadCustomConfig() {
        if (warps == null) {
            warps = new File(Essentials.plugin.getDataFolder(), "warps.yml");
            if (!warps.exists()) {
                try {
                    warps.createNewFile();
                } catch (Exception e) {
                    System.out.println("Couldn't create new file!");
                }
            }
        }
        warpConfig = YamlConfiguration.loadConfiguration(warps);
        Essentials.plugin.saveResource(warps.getName(), false);
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