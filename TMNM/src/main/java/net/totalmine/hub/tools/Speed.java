package net.totalmine.hub.tools;

import java.util.HashSet;
import java.util.Set;

import net.totalmine.core.TMNM;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Speed implements Listener{
	
	Set<String> speed = new HashSet<String>();
	
	PotionEffect SPEED = PotionEffectType.SPEED.createEffect(999999999, 3);

	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e){
		Action a = e.getAction();
		ItemStack is = e.getItem();
		if(a == Action.PHYSICAL || is == null || is.getType() == Material.AIR)
			return;
		
		if (is.getType() == Material.QUARTZ){
			if(!speed.contains(e.getPlayer().getName())){
				e.getPlayer().addPotionEffect(SPEED, true);
				e.getPlayer().sendMessage(TMNM.prefix + "Speed has now been enabled!");
				speed.add(e.getPlayer().getName());
				return;
			}
		    if(speed.contains(e.getPlayer().getName())){
				e.getPlayer().removePotionEffect(PotionEffectType.SPEED);
				e.getPlayer().sendMessage(TMNM.prefix + "Speed has now been disabled!");
				speed.remove(e.getPlayer().getName());
				return;
			} 
		}
	}
	
}
