package com.bobcat4848.essentials;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class Lightning implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("lightning")) {
			if (player.hasPermission("be.lightning") || player.isOp()) {
				player.getWorld().strikeLightning(player.getTargetBlock((Set<Material>) null, 64).getLocation());
			}

		}
		return true;

	}
}
