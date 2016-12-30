package net.totalmine.hub.tools;

import java.util.HashSet;
import java.util.Set;

import net.totalmine.core.TMNM;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Hide implements Listener{
	
	Set<String> hide = new HashSet<String>();

	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e){
		Action a = e.getAction();
		ItemStack is = e.getItem();
		if(a == Action.PHYSICAL || is == null || is.getType() == Material.AIR)
			return;
		
		if (is.getType() == Material.WATCH){
			if(!hide.contains(e.getPlayer().getName())){
				e.getPlayer().sendMessage(TMNM.prefix + "All players are hidden from you!");
				hide.add(e.getPlayer().getName());
				for(Player online : Bukkit.getOnlinePlayers()){
					if (online.hasPermission("tmnm.hidden.bypass"))
						continue;
					e.getPlayer().hidePlayer(online);
				}
				return;
			}
		    if(hide.contains(e.getPlayer().getName())){
				e.getPlayer().sendMessage(TMNM.prefix + "All players are now visible to you!");
				hide.remove(e.getPlayer().getName());
				for(Player online : Bukkit.getOnlinePlayers()){
					e.getPlayer().showPlayer(online);
				}
				return;
			} 
		}
	}
	
}
