package net.totalmine.chat;

import net.totalmine.core.TMNM;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 * Created by Brychan on 06/07/14.
 */
public class nickNameHandler implements Listener {



	public static HashMap<String, String> nicknames = new HashMap<String, String>();



	@EventHandler(priority = EventPriority.MONITOR)
	public void loginControl(PlayerJoinEvent event) {
		try {
			Statement statement = TMNM.connection.createStatement();
			ResultSet res = statement.executeQuery("SELECT `nickname` FROM `players` WHERE `uuid` = '" + event.getPlayer().getUniqueId().toString() + "' AND `nickname` != ''");
			//res.next();
			//ResultSet ra;
			// run query on database
			if (res.next()) {
				event.getPlayer().setDisplayName("*"+res.getString(1)+"§r");
				nicknames.put(event.getPlayer().getName(), res.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}








	public static boolean setNickname(String player, String nickname) {
		nickname = nickname.replaceAll("[^a-zA-Z0-9_&$-]+", "");

		if (removeFormatting(nickname).length() == 0)
			nickname = player;

		//if ((removeFormatting(nickname).toLowerCase().equals("totalmine")) && (!Bukkit.getPlayer(player).hasPermission("tcn.chat.imposter")))
		//	nickname = "IMPOSTER";

		if (nicknames.containsKey(player))
			nicknames.remove(player);

		if (Bukkit.getPlayer(player).hasPermission("tcn.chat.color"))
			nickname = addColor(nickname);

		TMNM.keepConnection();
		try {
			String sql = "UPDATE `players` SET `nickname` = ? WHERE `uuid` = ?";
			PreparedStatement statement = TMNM.connection.prepareStatement(sql);
			statement.setString(1, nickname);
			statement.setString(2, Bukkit.getPlayer(player).getUniqueId().toString());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Bukkit.getPlayer(player).setDisplayName("*" + nickname + "§r");
		nicknames.put(player, nickname);
		Bukkit.getPlayer(player).sendMessage(TMNM.prefix + "Your nickname has been changed");
		return true;
	}











	public static boolean removeNickname(String player) {
		if (nicknames.containsKey(player))
			nicknames.remove(player);
		TMNM.keepConnection();
		try {
			String sql = "UPDATE `players` SET `nickname` = '' WHERE `uuid` = ?";
			PreparedStatement statement = TMNM.connection.prepareStatement(sql);
			statement.setString(1, Bukkit.getPlayer(player).getUniqueId().toString());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Bukkit.getPlayer(player).setDisplayName("");
		return true;
	}









	public static String removeFormatting(String input) {
		input.replace("§1", "");
		input.replace("§2", "");
		input.replace("§3", "");
		input.replace("§4", "");
		input.replace("§5", "");
		input.replace("§6", "");
		input.replace("§7", "");
		input.replace("§8", "");
		input.replace("§9", "");
		input.replace("§0", "");
		input.replace("§a", "");
		input.replace("§b", "");
		input.replace("§c", "");
		input.replace("§d", "");
		input.replace("§e", "");
		input.replace("§l", "");
		input.replace("§k", "");
		input.replace("§o", "");
		input.replace("§n", "");
		input.replace("§m", "");
		input.replace("§r", "");
		input.replace("§f", "");
		input.replace("&1", "");
		input.replace("&2", "");
		input.replace("&3", "");
		input.replace("&4", "");
		input.replace("&5", "");
		input.replace("&6", "");
		input.replace("&7", "");
		input.replace("&8", "");
		input.replace("&9", "");
		input.replace("&0", "");
		input.replace("&a", "");
		input.replace("&b", "");
		input.replace("&c", "");
		input.replace("&d", "");
		input.replace("&e", "");
		input.replace("&f", "");
		input.replace("&l", "");
		input.replace("&k", "");
		input.replace("&o", "");
		input.replace("&n", "");
		input.replace("&m", "");
		input.replace("&r", "");
		return input;
	}


	public static String addColor(String content) {
		String coloredMsg = "";
		for (int i = 0; i < content.length(); i++) {
			if (content.charAt(i) == '&')
				coloredMsg = coloredMsg + '§';
			else
				coloredMsg = coloredMsg + content.charAt(i);
		}
		coloredMsg = coloredMsg.replaceAll("(?i)§l", "");
		coloredMsg = coloredMsg.replaceAll("(?i)§n", "");
		coloredMsg = coloredMsg.replaceAll("(?i)§o", "");
		coloredMsg = coloredMsg.replaceAll("(?i)§k", "");
		coloredMsg = coloredMsg.replaceAll("(?i)§m", "");
		return coloredMsg;
	}


	@EventHandler
	public void PlayerKicked(PlayerKickEvent event) {
		nicknames.remove(event.getPlayer().getName());
	}

	@EventHandler
	public void PlayerLeft(PlayerQuitEvent event) {
		nicknames.remove(event.getPlayer().getName());
	}

}