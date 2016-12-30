package net.totalmine.hub.tools;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Join implements Listener{

	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		
		e.getPlayer().getInventory().clear();
		
		ItemStack CP = new ItemStack(Material.COMPASS);
		ItemMeta CPMeta = CP.getItemMeta();
		CPMeta.setDisplayName("§aServer Selector");
		CP.setItemMeta(CPMeta);
		
		ItemStack SP = new ItemStack(Material.QUARTZ);
		ItemMeta SPMeta = SP.getItemMeta();
		SPMeta.setDisplayName("§aSpeed");
		SP.setItemMeta(SPMeta);	
		
		ItemStack W = new ItemStack(Material.WATCH);
		ItemMeta WMeta = W.getItemMeta();
		WMeta.setDisplayName("§aHide Players");
		W.setItemMeta(WMeta);
		
		e.getPlayer().getInventory().addItem(new ItemStack(CP));
		e.getPlayer().getInventory().addItem(new ItemStack(SP));
		e.getPlayer().getInventory().addItem(new ItemStack(W));
	}
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e){
		Action a = e.getAction();
		ItemStack is = e.getItem();
		if(a == Action.PHYSICAL || is == null || is.getType() == Material.AIR)
			return;
		
		if (is.getType() == Material.COMPASS)
			BungeeGUI.openServerGUI(e.getPlayer());
	}
}
