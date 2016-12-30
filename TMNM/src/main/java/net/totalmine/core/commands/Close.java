package net.totalmine.core.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.totalmine.core.TMNM;


public class Close implements SubCommand {
	
	public boolean onCommand(Player player, String[] args) {
		if (!player.hasPermission(permission()) && !player.isOp()) {
			player.sendMessage(ChatColor.RED+"You do not have the required permissions");
				return true;
		}

		if (!TMNM.maintenanceMode) {
			player.sendMessage(ChatColor.YELLOW + "The server is now in maintenance mode");
			TMNM.getPlugin().getConfig().set("maintenancemode", "disable");


			for (Player user : Bukkit.getOnlinePlayers()) {
				if (user.hasPermission("tmnm.maintenanceBypass"))
					continue;
				user.kickPlayer(ChatColor.RED + "(ERROR) This server has been put into maintenance mode");
			}

			TMNM.maintenanceMode = true;
		} else {

			TMNM.maintenanceMode = false;
			player.sendMessage(ChatColor.YELLOW + "The server is no longer in maintenance mode");


		}
		return true;
	}
	
	@Override
	public String permission() {
		return "tmnm.admin.close";
	}
	
}