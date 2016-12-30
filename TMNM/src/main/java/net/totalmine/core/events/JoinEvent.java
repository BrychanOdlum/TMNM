package net.totalmine.core.events;


import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import net.totalmine.core.TMNM;
import net.totalmine.permissions.rankHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JoinEvent implements Listener {

	
	@EventHandler
	public void PlayerLogin(PlayerLoginEvent event) {		
		
		if (TMNM.serverLoading) {
			event.disallow(PlayerLoginEvent.Result.KICK_OTHER, ChatColor.RED+"(ERROR) This server is currently loading");
		}

	}

	@EventHandler
	public void PlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();



		if (TMNM.maintenanceMode) {
			if (!player.hasPermission("tmnm.maintenance.bypass")) {
				player.kickPlayer(ChatColor.RED+"(ERROR) This server is in maintenance mode");
			}
		}

		event.setJoinMessage("");















		TMNM.keepConnection();


		Boolean empty = true;
		try {
			Statement statement = TMNM.connection.createStatement();
			ResultSet res = statement.executeQuery("SELECT `id` FROM `players` WHERE uuid = '" + player.getUniqueId() + "'");
			if (res.next())
				empty = false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (empty) {
			try {
				Statement statement = TMNM.connection.createStatement();
				statement.executeUpdate("INSERT INTO `players` (`uuid`) VALUES ('" + player.getUniqueId() + "');");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			Statement statement = TMNM.connection.createStatement();
			ResultSet res = statement.executeQuery("SELECT `username`, `rank` FROM `players` WHERE uuid = '" + player.getUniqueId() + "'");
			if (res.next()) {
				rankHandler.registerPlayer(player.getUniqueId(), res.getInt(2));
				if (!player.getName().equals(res.getString(1)))
					statement.executeUpdate("UPDATE `players` SET `username` = '" + player.getName() + "' WHERE `uuid` = '" + player.getUniqueId() + "'");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
