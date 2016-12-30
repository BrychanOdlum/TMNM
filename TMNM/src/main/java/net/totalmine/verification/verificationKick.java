package net.totalmine.verification;

import net.totalmine.core.TMNM;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class  verificationKick implements Listener {

	@EventHandler
	public void PlayerJoin(PlayerLoginEvent event) {
		Player player = event.getPlayer();


		TMNM.keepConnection();


		Boolean empty = true;


		try {
			Statement statement = TMNM.connection.createStatement();
			ResultSet res = statement.executeQuery("SELECT `key` FROM `verification` WHERE `uuid` = '" + player.getUniqueId() + "'");
			if (res.next()) {
				empty = false;
				event.setKickMessage(ChatColor.RED+"§7Your verification key is: §e" + res.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (empty)
			event.setKickMessage(ChatColor.RED+"§bSlow down there fella, please start the verification process first!\n§bGo to: §ehttp://TotalMine.net/register");
		event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
	}
}
