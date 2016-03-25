package com.bobcat4848.essentials;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Speed implements CommandExecutor {

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		
		
		if (cmd.getName().equalsIgnoreCase("speed")) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.RED + "Invalid Syntax! Type /speed 1-10!");
			}
			if (args.length == 1) {
				if (player.hasPermission("be.speed") || player.isOp()) {
					float speed = Integer.parseInt(args[0]);
					
					try {
						if (speed < 1 || speed > 10) {
							sender.sendMessage(ChatColor.RED + "Pick a speed from 1 to 10!");
							return true;
						}
						
						if (player.isOnGround()) {
							player.setWalkSpeed(speed / 10);
							sender.sendMessage(ChatColor.GREEN + "Your walking speed is now: " + ChatColor.GOLD + speed);
						} else {
							player.setFlySpeed(speed / 10);
							sender.sendMessage(ChatColor.GREEN + "Your flying speed is now: " + ChatColor.GOLD + speed);
						}
					} catch (NumberFormatException e) {
						player.sendMessage("Speed needs to be a number 1-10!");
					}
				}
				return true;
			}
			if (args.length == 2) {
				if (player.hasPermission("be.speed.others")) {
					float speed = Integer.parseInt(args[0]);
					Player playerTarget = Bukkit.getPlayer(args[1]);

					try {
						if (speed < 1 || speed > 10) {
							sender.sendMessage("Pick a speed from 1 to 10!");
							return true;
						}
						
						if (playerTarget == null) {
							sender.sendMessage(ChatColor.RED + "Player is not online!");
							return true;
						}

						if (playerTarget.isOnGround()) {
							playerTarget.setWalkSpeed(speed / 10);
							
							sender.sendMessage(ChatColor.GREEN + "You set " + ChatColor.GOLD + 
									playerTarget.getDisplayName() + ChatColor.GREEN + " walking speed to " +
									ChatColor.GOLD + speed);
							
							playerTarget.sendMessage(ChatColor.GREEN + "Your walking speed is now: " + 
									ChatColor.GOLD + speed);
						} else {
							playerTarget.setFlySpeed(speed / 10);
							
							sender.sendMessage(ChatColor.GREEN + "You set " + ChatColor.GOLD + 
									playerTarget.getDisplayName() + ChatColor.GREEN + " flying speed to " + 
									ChatColor.GOLD + speed);
							
							playerTarget.sendMessage(ChatColor.GREEN + "Your flying speed is now: " + 
									ChatColor.GOLD + speed);
						}
					} catch (NumberFormatException e) {
						player.sendMessage("Speed needs to be a number 1-10!");
					}
				}
			}
		}
		
		return true;
	}
}
