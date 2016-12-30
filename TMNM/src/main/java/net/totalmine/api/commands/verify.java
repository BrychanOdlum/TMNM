package net.totalmine.api.commands;


import net.totalmine.core.TMNM;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;


public class verify implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd1, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only in-game players can use this command");
			return true;
		}

		Player player = (Player) sender;
		if (cmd1.getName().equalsIgnoreCase("verify")) {
			if (args == null || args.length < 1) {

				player.sendMessage(ChatColor.GRAY +"[" +ChatColor.RED + "Verify" + ChatColor.GRAY+ "] " + ChatColor.WHITE + "Please use the verification");

			}

		}


		return true;
	}
}
