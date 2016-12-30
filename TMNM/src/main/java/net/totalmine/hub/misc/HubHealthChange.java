package net.totalmine.hub.misc;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class HubHealthChange implements Listener{
	
	@EventHandler
	public void onPlayerDrop(EntityDamageEvent e){
		if (e.getEntity() instanceof Player)
			e.setCancelled(true);
	}
	
}
