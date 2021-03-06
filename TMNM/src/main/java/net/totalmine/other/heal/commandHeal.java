package net.totalmine.other.feed;


import net.totalmine.core.TMNM;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;


public class commandHeal implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd1, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only in-game players can use this command");
			return true;
		}
		Player player = (Player)sender;
		if (!player.hasPermission("tmnm.other.heal")) {
			player.sendMessage(TMNM.prefixManager + "You do not have the required permission to perform this command");
			return true;
		}

		player.setHealth(player.getMaxHealth());
		player.sendMessage(TMNM.prefix + "You have replenished your health");
		return true;
	}
}
