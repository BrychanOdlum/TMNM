package net.totalmine.hub.misc;

import net.totalmine.core.TMNM;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class Drop implements Listener{

	@EventHandler
	public void onPlayerDrop(PlayerDropItemEvent e){
		e.setCancelled(true);
		e.getPlayer().sendMessage(TMNM.prefix + "You may not drop items on the Hub.");
		return;
	}
	
}
