package com.bobcat4848.essentials;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;

public class Utility implements CommandExecutor {

	public static List<String> toggled = new ArrayList<String>();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("heal")) {
			if (args.length == 0) {
				if (player.hasPermission("be.heal") || player.isOp()) {
					player.setHealth(player.getMaxHealth());
					player.setFoodLevel(20);

					for (PotionEffect effect : player.getActivePotionEffects()) {
						player.removePotionEffect(effect.getType());
					}

					sender.sendMessage(ChatColor.GREEN + "You have been healed!");
				}
			}
			if (args.length == 1) {
				if (player.hasPermission("be.heal.others")) {
					Player playerTarget = Bukkit.getPlayer(args[0]);

					if (playerTarget == null) {
						sender.sendMessage(ChatColor.RED + "Player is not online!");
						return true;
					}
					// Setting target players health and food to maximum values
					playerTarget.setHealth(playerTarget.getMaxHealth());
					playerTarget.setFoodLevel(20);

					for (PotionEffect effect : player.getActivePotionEffects()) {
						playerTarget.removePotionEffect(effect.getType());
					}

					player.sendMessage(ChatColor.GREEN + "You have healed " + ChatColor.GOLD + playerTarget.getDisplayName() + ChatColor.GREEN + "!");
					playerTarget.sendMessage(ChatColor.GREEN + "You have been healed!");
				}

			}
		}

		if (cmd.getName().equalsIgnoreCase("feed")) {
			if (args.length == 0) {
				if (player.hasPermission("be.feed") || player.isOp()) {
					player.setFoodLevel(20);
					sender.sendMessage(ChatColor.GREEN + "You have been fed!");
				}
			}
			if (args.length == 1) {
				if (player.hasPermission("be.feed.others")) {
					Player playerTarget = Bukkit.getPlayer(args[0]);

					if (playerTarget == null) {
						sender.sendMessage(ChatColor.RED + "Player is not online!");
						return true;
					}
					// Setting target players health and food to maximum values
					playerTarget.setFoodLevel(20);

					player.sendMessage(ChatColor.GREEN + "You have fed " + ChatColor.GOLD + playerTarget.getDisplayName() + ChatColor.GREEN + "!");
					playerTarget.sendMessage(ChatColor.GREEN + "You have been fed!");
				}

			}
		}

		if (cmd.getName().equalsIgnoreCase("god")) {
			if (args.length == 0) {
				if (player.hasPermission("be.god") || player.isOp()) {
					if (toggled.contains(player.getName())) {
						toggled.remove(player.getName());
						sender.sendMessage("God Mode Toggled Off!");
					} else {
						toggled.add(player.getName());
						player.setHealth(player.getMaxHealth());
						player.setFoodLevel(20);
						sender.sendMessage("God Mode Toggled On!");
					}
				}
			}
		}

		if (cmd.getName().equalsIgnoreCase("clearinventory")) {
			if (args.length == 0) {
				if (player.hasPermission("be.clearinventory") || player.isOp()) {
					player.getInventory().setStorageContents(null);

					sender.sendMessage(ChatColor.GREEN + "Your inventory has been cleared!");
				}
			}
			if (args.length == 1) {
				if (player.hasPermission("be.clearinventory.others")) {
					Player playerTarget = Bukkit.getPlayer(args[0]);
					if (playerTarget == null) {
						sender.sendMessage(ChatColor.RED + "Player is not online!");
						return true;
					}

					playerTarget.getInventory().setStorageContents(null);

					sender.sendMessage(ChatColor.GOLD + playerTarget.getDisplayName() + ChatColor.GREEN + "'s invetory has been cleared!");
					playerTarget.sendMessage(ChatColor.GREEN + "Your inventory has been cleared!");
				}

			}

			return true;
		}

		if (cmd.getName().equalsIgnoreCase("fly")) {
			if (args.length == 0) {
				if (player.hasPermission("be.fly") || player.isOp()) {
					if (player.isFlying()) {
						player.setAllowFlight(false);
						player.setFlying(false);
						sender.sendMessage(ChatColor.GREEN + "You can no longer fly!");
					} else {
						player.setAllowFlight(true);
						player.setFlying(true);
						sender.sendMessage(ChatColor.GREEN + "You can now fly!");
					}
				}
			}
			if (args.length == 1) {
				if (player.hasPermission("be.fly.others")) {
					Player playerTarget = Bukkit.getPlayer(args[0]);

					if (playerTarget == null) {
						sender.sendMessage(ChatColor.RED + "Player is not online!");
						return true;
					}

					if (playerTarget.isFlying()) {
						playerTarget.setAllowFlight(false);
						playerTarget.setFlying(false);
						sender.sendMessage(ChatColor.GOLD + playerTarget.getDisplayName() + ChatColor.GREEN + " can no longer fly!");
						playerTarget.sendMessage(ChatColor.GREEN + "You can no longer fly!");
					} else {
						playerTarget.setAllowFlight(true);
						playerTarget.setFlying(true);
						sender.sendMessage(ChatColor.GOLD + playerTarget.getDisplayName() + ChatColor.GREEN + " can now fly!");
						playerTarget.sendMessage(ChatColor.GREEN + "You can now fly!");
					}
				}
			}
			return true;
		}
		return true;
	}
}
