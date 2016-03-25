package com.bobcat4848.essentials;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Enderchest implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("enderchest")) {
			if (args.length == 0) {
				if (player.hasPermission("be.enderchest") || player.isOp()) {
					player.openInventory(player.getEnderChest());
				} else {
					return false;
				}
			}
			if (args.length == 1) {
				if (player.hasPermission("be.enderchest.others")) {
					Player playerTarget = Bukkit.getPlayer(args[0]);
					
					if (playerTarget == null) {
						sender.sendMessage(ChatColor.RED + "Player is not online!");
						return false;
					}
					
					playerTarget.openInventory(playerTarget.getEnderChest());
					sender.sendMessage(ChatColor.GREEN + "You are looking through " + ChatColor.GOLD +  playerTarget.getDisplayName() + ChatColor.GREEN +  "'s stuff!");
				}
			}
		}

		return true;
	}

}
