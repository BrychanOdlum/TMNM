package net.totalmine.other.weather;


import net.totalmine.core.TMNM;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;


public class weatherCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd1, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only in-game players can use this command");
			return true;
		}
		Player player = (Player) sender;

		if (!player.hasPermission("tmnm.other.weather")) {
			player.sendMessage(TMNM.prefixManager + "You do not have the required permission to perform this command");
			return true;
		}

		//if (cmd1.getName().equalsIgnoreCase("verify")) {
		if (args == null || args.length < 1) {
			player.sendMessage(TMNM.prefixManager + "Usage: /weather [storm/sun]");
			return true;
		}
		boolean isStorm = false;
		if (args[0].equalsIgnoreCase("storm"))
			isStorm = true;

		player.getWorld().setStorm(isStorm);


		player.sendMessage(TMNM.prefixManager + "Weather value set");

		//}


		return true;
	}
}