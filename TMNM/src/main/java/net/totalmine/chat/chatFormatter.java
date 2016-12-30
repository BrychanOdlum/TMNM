package net.totalmine.chat;

import net.milkbowl.vault.chat.Chat;
import net.totalmine.core.TMNM;
import net.totalmine.permissions.rankHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.Map;
import java.util.HashMap;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 * Created by Brychan on 05/07/14.
 */
public class chatFormatter implements Listener {

	private final Map<String, String> nicknames = new HashMap<String, String>();





	public String colorize(String input) {
		return ChatColor.translateAlternateColorCodes('&', input);
	}

	public String colorizeNoSpecial(String input) {
		String coloredMsg = ChatColor.translateAlternateColorCodes('&', input);

		coloredMsg = coloredMsg.replaceAll("(?i)§l", "");
		coloredMsg = coloredMsg.replaceAll("(?i)§n", "");
		coloredMsg = coloredMsg.replaceAll("(?i)§o", "");
		coloredMsg = coloredMsg.replaceAll("(?i)§k", "");
		coloredMsg = coloredMsg.replaceAll("(?i)§m", "");

		URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + user.getName());
		String uuid = (String)((JSObject)new JsonParser().parse(new InputStreamReader(url.openStream()))).get("id");
		String realUUID = uuid.substring(0, 8) + "-" + uuid.substring(8, 12) + "-" + uuid.substring(12, 16) + "-" + uuid.substring(16, 20) + "-" + uuid.substring(20, 32);

		return coloredMsg;
	}


	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		String suffix = "";
		String pname;
		if (!event.getPlayer().getDisplayName().isEmpty()) {
			pname = event.getPlayer().getDisplayName();
		} else {
			pname = event.getPlayer().getName();
		}

		String message = event.getMessage();

		if (event.getPlayer().hasPermission("tcn.chat.color"))
			message = colorize(message);
		String rawFormat = TMNM.chatFormat;


		rawFormat = colorize(rawFormat);
		rawFormat = rawFormat.replace("<prefix>",  colorize(rankHandler.getPrefix(event.getPlayer().getUniqueId()))  );
		rawFormat = rawFormat.replace("<suffix>",  colorize(rankHandler.getSuffix(event.getPlayer().getUniqueId()))  );
		rawFormat = rawFormat.replace("<name>", pname).replace("%", "");
		rawFormat = rawFormat.replace("<message>", message).replace("%", "");


		event.setFormat(rawFormat);
	}


}
