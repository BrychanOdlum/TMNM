package net.totalmine.core.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.totalmine.core.TMNM;

import java.util.Map;


public class CoreModules implements SubCommand {
	
	public boolean onCommand(Player player, String[] args) {
		if (!player.hasPermission(permission()) && !player.isOp()) {
			player.sendMessage(ChatColor.RED+"You do not have the required permissions");
				return true;
		}
		 
		

		player.sendMessage(ChatColor.GOLD+"--------------------------------------------------");
		player.sendMessage(ChatColor.RED.toString() + " TMNM" + ChatColor.WHITE.toString() + " - " + ChatColor.GOLD.toString() + "Modules");
		player.sendMessage("");
		
		String module;
		// Core Manager Module																		//
				player.sendMessage("  " + ChatColor.YELLOW.toString()+"Manager: " + ChatColor.GOLD.toString() + "CORE");
		//																							//
		for(Map.Entry<String, Boolean> entry : TMNM.modules.entrySet()) {
			module = ChatColor.RED.toString()+"DISABLED";
			if (entry.getValue()) module = ChatColor.GREEN.toString()+"ENABLED";
			player.sendMessage("  " + ChatColor.YELLOW.toString() + entry.getKey() + ": " + module);
		}

		
		player.sendMessage(ChatColor.GOLD+"--------------------------------------------------");
		
		
		return true;
	}
	
	@Override
	public String permission() {
		return "tmnm.admin";
	}
	
}