package com.bobcat4848.essentials;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Time implements CommandExecutor {

	public static String parseTime(long time) {
		
		long gameTime = time;
		long hours = gameTime / 1000 + 6;
		long minutes = (gameTime % 1000) * 60 / 1000;
		String amPm = "AM";
		if (hours >= 12) {
			hours = hours - 12; amPm = "PM";
		}
		
		if (hours >= 12) {
			hours = hours - 12; amPm = "AM";
		}
		
		if (hours == 0) hours = 12;
		
		String mm = "0" + minutes;
		mm = mm.substring(mm.length() - 2, mm.length());
		
		return hours + ":" + mm + " " + amPm;
	}
	
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("timebe")) {
			if (args.length == 0) {
				sender.sendMessage("Invalid Syntax! Do /time timeofday");
			}
			if (args.length == 1) {
				if (player.hasPermission("be.time") || player.isOp()) {
					if (args[0].equalsIgnoreCase("day")) {
						player.getWorld().setTime(0);
						sender.sendMessage(ChatColor.GREEN + "Time set to " + ChatColor.GOLD + "DAY" + ChatColor.GREEN + ", " + ChatColor.GOLD + "0 TICK(S)" + ChatColor.GREEN + ", " + ChatColor.GOLD + "or 6:00 AM" + ChatColor.GREEN + "!");
					} else if (args[0].equalsIgnoreCase("night")) {
						player.getWorld().setTime(18000);
						sender.sendMessage(ChatColor.GREEN + "Time set to " + ChatColor.GOLD + "NIGHT" + ChatColor.GREEN + ", " + ChatColor.GOLD + "18000 TICK(S)" + ChatColor.GREEN + ", " + ChatColor.GOLD + "or 12:00 AM" + ChatColor.GREEN + "!");
					} else {
						int time = Integer.parseInt(args[0]);

						player.getWorld().setTime(time);
						sender.sendMessage(ChatColor.GREEN + "Time set to " + ChatColor.GOLD + time + " TICK(S)" + ChatColor.GREEN + ", " + ChatColor.GOLD + parseTime(time) + ChatColor.GREEN + " !");
					}
				}
			}
		}

		return true;
	}

}
