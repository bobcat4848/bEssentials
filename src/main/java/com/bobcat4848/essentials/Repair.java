package com.bobcat4848.essentials;

import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Repair implements CommandExecutor {

	public boolean checkItem(ItemStack i) {
		String[] items = { "SWORD", "AXE", "SHOVEL", "HOE", "BOW", "HELMET", "SHEARS", "ROD", "SHIELD", "STEEL", "CHESTPLATE", "LEGGINGS", "BOOTS" };
		for (String s : items) {
			if (i.getType().name().contains(s)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;

		ItemStack itemStack = player.getInventory().getItemInMainHand();
		if (cmd.getName().equalsIgnoreCase("repair")) {
			if (itemStack.getType() == Material.AIR) {
				sender.sendMessage(ChatColor.RED + "You can't repair this!");
				return true;
			}

			if (checkItem(itemStack)) {
				if (itemStack.getDurability() == 0) {
					sender.sendMessage(ChatColor.RED + "This item is already at max duribility.");
					return true;
				}
				if (player.hasPermission("be.repair.*") || player.isOp()) {
					player.getInventory().getItemInMainHand().setDurability((short) 0);
					sender.sendMessage(ChatColor.GREEN + "Your " + ChatColor.GOLD + WordUtils.capitalizeFully(itemStack.getType().name().replace("_", " ")) + ChatColor.GREEN + " has been repaired!");
					return true;
				} else if (player.hasPermission("be.repair.enchanted")) {
					if (itemStack.getEnchantments().size() > 0) {
						player.getInventory().getItemInMainHand().setDurability((short) 0);
						sender.sendMessage(ChatColor.GREEN + "Your enchanted " + ChatColor.GOLD + WordUtils.capitalizeFully(itemStack.getType().name().replace("_", " ")) + ChatColor.GREEN + " has been repaired!");
						return true;
					}
				} else if (player.hasPermission("be.repair")) {
					if (itemStack.getEnchantments().size() == 0) {
						player.getInventory().getItemInMainHand().setDurability((short) 0);
						sender.sendMessage(ChatColor.GREEN + "Your " + ChatColor.GOLD + WordUtils.capitalizeFully(itemStack.getType().name().replace("_", " ")) + ChatColor.GREEN + " has been repaired!");
						return true;
					}
				}
			} else {
				sender.sendMessage(ChatColor.RED + "Item can't be repaired!");
			}

		}

		return true;
	}

}
