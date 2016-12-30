package net.totalmine.hub.misc;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class SpawnJoin implements Listener {

	@EventHandler
	public void PlayerJoin(PlayerJoinEvent event) {
		event.getPlayer().teleport(event.getPlayer().getWorld().getSpawnLocation().add(0.5, 0.0, 0.5));
	}
}
