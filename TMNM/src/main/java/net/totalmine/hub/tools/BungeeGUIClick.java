package net.totalmine.hub.tools;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class BungeeGUIClick implements Listener{

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {

		//Cancel all events, just because we don't need any rearranging
		event.setCancelled(true);

		if(!ChatColor.stripColor(event.getInventory().getName()).contains("Servers"))
			return;
		Player player = (Player) event.getWhoClicked();
		
		if(event.getCurrentItem()==null || event.getCurrentItem().getType()==Material.AIR || !event.getCurrentItem().hasItemMeta()){
			player.closeInventory();
			return;
		}

		switch (event.getCurrentItem().getType()) {
			case DIAMOND_PICKAXE:
				Bungee.sendToServer(player.getPlayer(), "survival");
				player.closeInventory();
				break;

			case CHEST:
				Bungee.sendToServer(player.getPlayer(), "factions");
				player.closeInventory();
				break;

			case REDSTONE_BLOCK:
				Bungee.sendToServer(player.getPlayer(), "creative");
				player.closeInventory();
				break;

			case GRASS:
				Bungee.sendToServer(player.getPlayer(), "skyblock");
				player.closeInventory();
				break;

			case IRON_SWORD:
				Bungee.sendToServer(player.getPlayer(), "sgpvp");
				player.closeInventory();
				break;

		     default:
		         player.closeInventory();
		         break;
		}
	}
	
}
