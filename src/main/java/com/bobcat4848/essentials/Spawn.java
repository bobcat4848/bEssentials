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
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;

public class Spawn implements CommandExecutor {

	private List<String> teleporting = new ArrayList<>();
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if (teleporting.contains(e.getPlayer().getName())) {
			if (e.getTo().getX() == e.getFrom().getX() || e.getTo().getY() == e.getFrom().getY() ||
					e.getTo().getZ() == e.getFrom().getZ()) {
				return;
			}
			teleporting.remove(e.getPlayer().getName());
			e.getPlayer().sendMessage("Teleportation Cancelled.");
		}
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("spawn")) {
			FileConfiguration config = Essentials.plugin.getConfig();
			if (!config.contains("Spawn")) {
				sender.sendMessage("The spawn has not been set.");
				return true;
			}
			World w = Bukkit.getWorld("world");
			double x = config.getDouble("Spawn.x");
			double y = config.getDouble("Spawn.y");
			double z = config.getDouble("Spawn.z");
			float pitch = config.getInt("Spawn.pitch");
			float yaw = config.getInt("Spawn.yaw");
			
			player.teleport(new Location(w, x, y, z, yaw, pitch));
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("setspawn")) {
			double x = player.getLocation().getX();
			double y = player.getLocation().getY();
			double z = player.getLocation().getZ();
			float pitch = player.getLocation().getPitch();
			float yaw = player.getLocation().getYaw();
			
			FileConfiguration config = Essentials.plugin.getConfig();
			config.set("Spawn.World", player.getWorld().getName());
			config.set("Spawn.x", x);
			config.set("Spawn.y", y);
			config.set("Spawn.z", z);
			config.set("Spawn.yaw", yaw);
			config.set("Spawn.pitch", pitch);
			Essentials.plugin.saveConfig();
			sender.sendMessage(ChatColor.GREEN + "Spawn set.");
			

		}
		return false;
	}
}
