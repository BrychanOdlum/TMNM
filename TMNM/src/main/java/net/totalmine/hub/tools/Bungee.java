package net.totalmine.hub.tools;

import java.io.DataOutputStream;

import java.io.ByteArrayOutputStream;
import net.totalmine.core.TMNM;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Bungee implements Listener{
	
	public static void sendToServer(Player player, String targetServer){
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		@SuppressWarnings("resource")
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("Connect");
			out.writeUTF(targetServer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		player.sendPluginMessage(TMNM.getPlugin(), "BungeeCord", b.toByteArray());
	}
	
}
