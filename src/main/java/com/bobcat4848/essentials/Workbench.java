package com.bobcat4848.essentials;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Workbench implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("workbench")) {
			if (args.length == 0) {
				if (player.hasPermission("be.workbench") || player.isOp()) {
					player.openWorkbench(null, true);
				}
			}
			if (args.length == 1) {
				if (player.hasPermission("be.workbench.others")) {
					Player playerTarget = Bukkit.getPlayer(args[0]);
					
					if (playerTarget == null) {
						sender.sendMessage(ChatColor.RED + "Player is not online!");
						return true;
					}
					
					playerTarget.openWorkbench(null, true);
					sender.sendMessage(ChatColor.GREEN + "You opened a workbench for " + ChatColor.GOLD + playerTarget.getDisplayName() + ChatColor.GREEN + "!");
					playerTarget.sendMessage(ChatColor.GOLD + player.getDisplayName() + ChatColor.GREEN + " opened a workbench UI for you!");
				}
			}
		}
		return true;

	}
}
