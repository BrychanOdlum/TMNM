package net.totalmine.core.events;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class LeaveEvent implements Listener {


	@EventHandler
	public void PlayerKicked(PlayerKickEvent event) {
		event.setLeaveMessage("");
	}

	@EventHandler
	public void PlayerLeft(PlayerQuitEvent event) {
		event.setQuitMessage("");
	}
	
	
	
}
