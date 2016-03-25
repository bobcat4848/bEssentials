package com.bobcat4848.essentials;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.libs.org.ibex.nestedvm.util.Seekable;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Warps implements CommandExecutor {

    private List<String> warpNames = new ArrayList<>();
    private List<Location> warpLocation = new ArrayList<>();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;


        if (cmd.getName().equalsIgnoreCase("warp")) {
            if (args.length == 0) {
                if (player.hasPermission("be.warp") || player.isOp()) {
                    sender.sendMessage("Warps: " + warpNames);
                }
            }
            if (args.length == 1) {
                if (player.hasPermission("be.warp." + warpNames)) {
                    sender.sendMessage("You have been warped to " + warpNames);
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

                    FileConfiguration warps = loadConfiguration();
                    loadConfiguration(File file)
                    warps.set("Warps.World", player.getWorld().getName());
                    warps.set("Warps.warpName", warpName);
                    warps.set("Warps.x", x);
                    warps.set("Warps.y", y);
                    warps.set("Warps.z", z);
                    warps.set("Warps.yaw", yaw);
                    warps.set("Warps.pitch", pitch);
                    Essentials.plugin.saveConfig();
                    sender.sendMessage("Your warp " + warpName + " has been set!");

                }
            }
        }

        return true;
    }

}
