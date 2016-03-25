package com.bobcat4848.essentials;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kill implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("kill")) {
			if (args.length == 0) {
				if (player.hasPermission("be.kill") || player.isOp()) {
					player.setHealth(0);
					sender.sendMessage(ChatColor.RED + "You have been killed!");
				}
			}
			if (args.length == 1) {
				if (player.hasPermission("be.kill.others")) {
					Player playerTarget = Bukkit.getPlayer(args[0]);

					if (playerTarget == null) {
						sender.sendMessage(ChatColor.RED + "That player is not online!");
						return true;
					}

					playerTarget.setHealth(0);
					sender.sendMessage(ChatColor.GOLD + playerTarget.getDisplayName() + ChatColor.RED + " has been killed!");
					playerTarget.sendMessage(ChatColor.RED + "You have been killed!");
				}
			}
		}

		return true;
	}

}
