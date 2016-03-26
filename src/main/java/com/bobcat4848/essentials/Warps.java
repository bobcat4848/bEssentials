package com.bobcat4848.essentials;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class Warps implements CommandExecutor {

    private static Map<String, Location> warpData = new HashMap<String, Location>();
    //private FileConfiguration warps = Essentials.plugin.getWarpConfig();
    private FileConfiguration warps = Warp.getWarpConfig();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;


        if (cmd.getName().equalsIgnoreCase("warp")) {
            if (args.length == 0) {
                if (player.hasPermission("be.warp") || player.isOp()) {
                    for (Map.Entry<String, Location> entry : warpData.entrySet()) {
                        String warpName = entry.getKey();
                        sender.sendMessage("Warp: " + warpName);
                    }
                }
            }
            if (args.length == 1) {
                String warp = args[0];
                if (warpData.containsKey(warp)) {
                    if (player.hasPermission("be.warp." + warp)) {
                        player.teleport(warpData.get(warp));
                        sender.sendMessage(ChatColor.GREEN + "You have been warped to " + warp);
                    } else {
                        sender.sendMessage(ChatColor.RED + "You do not have access to warp here!");
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "That warp does not exist!");
                }
            }
        }
        if (cmd.getName().equalsIgnoreCase("setwarp")) {
            if (args.length == 0) {
                sender.sendMessage("Invalid Syntax! Do /setwarp nameofwarp!");
            }
            if (args.length == 1) {
                if (player.hasPermission("be.setwarp") || player.isOp()) {
                    String warpName = args[0];


                    double x = player.getLocation().getX();
                    double y = player.getLocation().getY();
                    double z = player.getLocation().getZ();
                    float pitch = player.getLocation().getPitch();
                    float yaw = player.getLocation().getYaw();


                    warps.set("Warps." + warpName + ".World", player.getWorld().getName());
                    warps.set("Warps." + warpName + ".x", x);
                    warps.set("Warps." + warpName + ".y", y);
                    warps.set("Warps." + warpName + ".z", z);
                    warps.set("Warps." + warpName + ".yaw", yaw);
                    warps.set("Warps." + warpName + ".pitch", pitch);
                    //Essentials.plugin.saveCustomConfig();
                    Warp.saveCustomConfig();
                    loadWarp();
                    sender.sendMessage("Your warp " + warpName + " has been set!");

                }
            }
        }

        return true;
    }

    public static void loadWarp() {
        warpData.clear();
        for (String s : Warp.getWarpConfig().getConfigurationSection("Warps").getKeys(false)) {
            //World w = Bukkit.getWorld(Essentials.plugin.getWarpConfig().getString("Warps." + s + ".World"));
            //double x = Essentials.plugin.getWarpConfig().getDouble("Warps." + s + ".x");
            //double y = Essentials.plugin.getWarpConfig().getDouble("Warps." + s + ".y");
            //double z = Essentials.plugin.getWarpConfig().getDouble("Warps." + s + ".z");
            //float yaw = (float) Essentials.plugin.getWarpConfig().getDouble("Warps." + s + ".yaw");
            //float pitch = (float) Essentials.plugin.getWarpConfig().getDouble("Warps." + s + ".pitch");

            World w = Bukkit.getWorld(Warp.getWarpConfig().getString("Warps." + s + ".World"));
            double x = Warp.getWarpConfig().getDouble("Warps." + s +".x");
            double y = Warp.getWarpConfig().getDouble("Warps." + s +".y");
            double z = Warp.getWarpConfig().getDouble("Warps." + s +".z");
            float yaw = (float) Warp.getWarpConfig().getDouble("Warps." + s +".yaw");
            float pitch = (float) Warp.getWarpConfig().getDouble("Warps." + s +".pitch");

            warpData.put(s, new Location(w, x, y, z, yaw, pitch));
        }
    }
}
