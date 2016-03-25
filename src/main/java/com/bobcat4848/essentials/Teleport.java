package com.bobcat4848.essentials;

import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Teleport implements CommandExecutor, Listener {

	Map<String, String> tpa = new HashMap<>();
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if (tpa.containsKey(e.getPlayer().getName()) || tpa.containsValue(e.getPlayer().getName())) {
			if (e.getTo().getX() == e.getFrom().getX() || e.getTo().getY() == e.getFrom().getY() ||
					e.getTo().getZ() == e.getFrom().getZ()) {
				return;
			}
			for (Entry <String, String> entry : tpa.entrySet()) {
				if (entry.getKey() == e.getPlayer().getName()) {
					tpa.remove(e.getPlayer().getName());
				}
				if (entry.getValue() == e.getPlayer().getName()) {
					tpa.remove(entry.getKey());
				}
			}
			e.getPlayer().sendMessage("Teleportation Cancelled.");
		}
	}

	
	public Entity getNearestMob(Player p, double radius) {
		Map<Double, Entity> map = new HashMap<>();
		for (Entity e : p.getNearbyEntities(radius, radius, radius)) {
			map.put(p.getLocation().distance(e.getLocation()), e);
		}
		double closest = Collections.min(map.keySet());
		return map.get(closest);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		final Player player = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("teleport")) {
			if (player.hasPermission("be.teleport")) {
				if (args.length == 1) {
					Player playerTarget = Bukkit.getPlayer(args[0]);
					if (playerTarget == null) {
						sender.sendMessage(ChatColor.RED + "Player is not online!");

						return true;
					}
					player.teleport(playerTarget.getLocation());
					sender.sendMessage(ChatColor.GREEN + "You have been teleported to " + ChatColor.GOLD + playerTarget.getName() + ChatColor.GREEN + ".");
				} else if (args.length == 2) {
					Player playerTarget = Bukkit.getPlayer(args[0]);
					Player fromPlayer = Bukkit.getPlayer(args[1]);
					if (fromPlayer == null || playerTarget == null) {
						sender.sendMessage(ChatColor.RED + "Player is not online!");

						return true;
					}
					fromPlayer.teleport(playerTarget.getLocation());
					sender.sendMessage(ChatColor.GREEN + "You teleported " + ChatColor.GOLD + fromPlayer.getName() + ChatColor.GREEN + " to " + ChatColor.GOLD + playerTarget.getName() + ChatColor.GREEN + ".");
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("teleporttomob")) {
			if (player.hasPermission("be.tptomob")) {
				Entity loc = getNearestMob(player, 50);
				player.teleport(loc.getLocation());
				sender.sendMessage("You have been teleported to a " + WordUtils.capitalizeFully(loc.getType().name().replace("_", " ")) + "!");

			}
		}
		
		if (cmd.getName().equalsIgnoreCase("tpa")) {
			if (player.hasPermission("be.tpa") || player.isOp()) {
				if (args[0].equalsIgnoreCase("accept")) {
					for (Entry <String, String> entry : tpa.entrySet()) {
						if (entry.getValue() == player.getName()) {
							Player p = Bukkit.getPlayer(entry.getKey());
							player.teleport(p.getLocation());
							tpa.remove(entry.getKey());
						} else {
							player.sendMessage("You do not have any pending teleport requests!");
						}
					}
					return true;
				}
				if (args.length == 1) {
					final Player playerTarget = Bukkit.getPlayer(args[0]);
					if (playerTarget == null) {
						sender.sendMessage(ChatColor.RED + "Player is not online!");

						return true;
					}
					if (tpa.containsKey(playerTarget.getName()) || tpa.containsValue(playerTarget.getName())) {
						sender.sendMessage("That player already has a teleport request.");
						return true;
					}
					if (tpa.containsKey(player.getName())) {
						player.sendMessage("You already have a pending teleport request!");
						return true;
					}
					tpa.put(player.getName(), playerTarget.getName());
					new BukkitRunnable() {
						public void run() {
							for (Entry <String, String> entry : tpa.entrySet()) {
								if (entry.getKey() == player.getName() && entry.getValue() == playerTarget.getName()) {
									tpa.remove(player.getName());
								}
							}
							player.sendMessage("Your tpa request has timed out!");
							playerTarget.sendMessage("Your tpa request has timed out!");
							
						}
					}.runTaskLater(Essentials.plugin, 10*20);
					player.sendMessage("Your tpa request has been sent to " + playerTarget.getName());
					playerTarget.sendMessage(player.getName() + " has sent a tpa request!\n" + "/tpa accept to accept\n" + "/tpa deny to deny");
				}
			}
		}
		
		
		
		
		
		
		
		if (cmd.getName().equalsIgnoreCase("tpahere")) {
			if (player.hasPermission("be.tpahere") || player.isOp()) {
				if (args[0].equalsIgnoreCase("accept")) {
					for (Entry <String, String> entry : tpa.entrySet()) {
						if (entry.getValue() == player.getName()) {
							Player p = Bukkit.getPlayer(entry.getKey());
							player.teleport(p.getLocation());
							tpa.remove(entry.getKey());
						} else {
							player.sendMessage("You do not have any pending teleport requests!");
						}
					}
					return true;
				}
				if (args.length == 1) {
					final Player playerTarget = Bukkit.getPlayer(args[0]);
					if (playerTarget == null) {
						sender.sendMessage(ChatColor.RED + "Player is not online!");

						return true;
					}
					if (tpa.containsKey(playerTarget.getName()) || tpa.containsValue(playerTarget.getName())) {
						sender.sendMessage("That player already has a teleport request.");
						return true;
					}
					if (tpa.containsKey(player.getName())) {
						player.sendMessage("You already have a pending teleport request!");
						return true;
					}
					tpa.put(player.getName(), playerTarget.getName());
					new BukkitRunnable() {
						public void run() {
							for (Entry <String, String> entry : tpa.entrySet()) {
								if (entry.getKey() == player.getName() && entry.getValue() == playerTarget.getName()) {
									tpa.remove(player.getName());
								}
							}
							player.sendMessage("Your tpa request has timed out!");
							playerTarget.sendMessage("Your tpa request has timed out!");
							
						}
					}.runTaskLater(Essentials.plugin, 30*20);
					player.sendMessage("Your tpahere request has been sent to " + playerTarget.getName());
					playerTarget.sendMessage(player.getName() + " has sent a tpahere request!\n" + "/tpa accept to accept\n" + "/tpa deny to deny");
				}
			}
		}
		
		return false;
	}

}