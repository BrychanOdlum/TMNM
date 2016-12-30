package net.totalmine.permissions;

import net.totalmine.core.TMNM;
import net.totalmine.permissions.rankHandler;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachment;

import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;




public class permissionHandler implements Listener {

	static HashMap<UUID, PermissionAttachment> pAttachments = new HashMap<UUID, PermissionAttachment>();
	static File permissionsFile;
	static FileConfiguration permissionsConfig;



	public permissionHandler() {
		permissionsFile = new File(TMNM.getPlugin().getDataFolder(), "permissions.yml");
		if (!permissionsFile.exists()) {
			try {
				permissionsFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		permissionsConfig = YamlConfiguration.loadConfiguration(permissionsFile);
	}



	public static void initiatePermissions(UUID playersUUID) {
		PermissionAttachment attachment = Bukkit.getPlayer(playersUUID).addAttachment(TMNM.getPlugin());
		pAttachments.put(Bukkit.getPlayer(playersUUID).getUniqueId(), attachment);
		injectUsersPerms(Bukkit.getPlayer(playersUUID).getUniqueId());
	}




	public static void injectUsersPerms(UUID playersUUID) {
		for (Integer i= 0; i<=rankHandler.getRank(playersUUID); i++) {
			List<String>perms = permissionsConfig.getStringList("ranks." + i + ".permissions");
			for (String perm : perms) {
				if (perm.startsWith("-"))
					pAttachments.get(playersUUID).setPermission(perm.substring(1), false);
				else
					pAttachments.get(playersUUID).setPermission(perm, true);
			}
		}
	}



	@EventHandler(priority = EventPriority.MONITOR)
	public void PlayerKicked(PlayerKickEvent event) {
		pAttachments.remove(event.getPlayer().getUniqueId());
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void PlayerLeft(PlayerQuitEvent event) {
		pAttachments.remove(event.getPlayer().getUniqueId());
	}


}