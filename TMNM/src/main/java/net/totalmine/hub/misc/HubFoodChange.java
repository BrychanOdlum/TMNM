package net.totalmine.hub.misc;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class HubFoodChange implements Listener{

	@EventHandler
	public void onPlayerDrop(FoodLevelChangeEvent e){
		e.setCancelled(true);
	}
	
}
