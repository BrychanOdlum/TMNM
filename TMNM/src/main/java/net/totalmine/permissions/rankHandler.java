package net.totalmine.permissions;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachment;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Brychan on 17/07/14.
 */
public class rankHandler implements Listener {


	public static HashMap<UUID, Integer> uRanks = new HashMap<UUID, Integer>();
	public static HashMap<Integer, String> rPrefixes = new HashMap<Integer, String>();
	public static HashMap<Integer, String> rSuffixes = new HashMap<Integer, String>();

	public static void registerPlayer(UUID playersUUID, Integer rank) {
		uRanks.put(playersUUID, rank);

		if (!rPrefixes.containsKey(rank))
			rPrefixes.put(rank, permissionHandler.permissionsConfig.getString("ranks." + rankHandler.getRank(playersUUID).toString() + ".prefix"));

		if (!rSuffixes.containsKey(rank))
			rSuffixes.put(rank, permissionHandler.permissionsConfig.getString("ranks." + rankHandler.getRank(playersUUID).toString() + ".suffix"));

		permissionHandler.initiatePermissions(playersUUID);
	}



	public static Integer getRank(UUID playersUUID) {
		return uRanks.get(playersUUID);
	}

	public static String getPrefix(UUID playersUUID) {
		return rPrefixes.get(uRanks.get(playersUUID));
	}
	public static String getSuffix(UUID playersUUID) {
		return rSuffixes.get(uRanks.get(playersUUID));
	}
	public static String getPrefix(Integer rankID) {
		return rPrefixes.get(rankID);
	}
	public static String getSuffix(Integer rankID) {
		return rSuffixes.get(rankID);
	}



	@EventHandler
	public void PlayerKicked(PlayerKickEvent event) {
		uRanks.remove(event.getPlayer().getUniqueId());
	}

	@EventHandler
	public void PlayerLeft(PlayerQuitEvent event) {
		uRanks.remove(event.getPlayer().getUniqueId());
	}

}
