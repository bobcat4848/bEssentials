package com.bobcat4848.essentials;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Rename implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("rename")) {
			ItemStack itemStack = player.getInventory().getItemInMainHand();

			if (itemStack.getType() == Material.AIR) {
				sender.sendMessage(ChatColor.RED + "That's not an item!");

				return true;
			}

			ItemMeta itemMeta = itemStack.getItemMeta();

			String rename = "";
			for (int i = 0; i < args.length; i++) {
				rename += args[i] + " ";
			}

			itemMeta.setDisplayName(rename.replaceAll("&", "�"));
			player.getInventory().getItemInMainHand().setItemMeta(itemMeta);
			sender.sendMessage(ChatColor.GREEN + "Your item has been renamed to " + ChatColor.GOLD + rename);

		}

		return false;
	}

}
