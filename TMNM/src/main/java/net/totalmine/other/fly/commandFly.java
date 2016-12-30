package net.totalmine.other.fly;


import net.totalmine.core.TMNM;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;


public class commandFly implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd1, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only in-game players can use this command");
			return true;
		}
		Player player = (Player) sender;

		if (!player.hasPermission("tmnm.other.fly")) {
			player.sendMessage(TMNM.prefixManager + "You do not have the required permission to perform this command");
			return true;
		}

		if (args.length < 1)
			togglePlayer(sender.getName(), null);
		else {
			boolean toggle = TMNM.matchToggleArgument(args[0]);
			togglePlayer(sender.getName(), toggle);
		}

		return true;
	}


	void togglePlayer(String player, Boolean enabled) {

		Player user = Bukkit.getPlayer(player);
		if (enabled == null) {
			enabled = Boolean.valueOf(!user.getAllowFlight());
		}

		user.setFallDistance(0.0F);
		user.setAllowFlight(enabled.booleanValue());

		if (!user.getAllowFlight()) {
			user.setFlying(false);
		}

		if (user.getAllowFlight())
			user.sendMessage(TMNM.prefix + "You may now fly");
		else
			user.sendMessage(TMNM.prefix + "You may no longer fly");
	}
}
