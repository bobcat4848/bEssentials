package com.bobcat4848.essentials;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class UtilityListener implements Listener {

	@EventHandler
	public void entityDamageEvent(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			if (Utility.toggled.contains(event.getEntity().getName())) {
				event.setCancelled(true);
			}
		}
	}
}
