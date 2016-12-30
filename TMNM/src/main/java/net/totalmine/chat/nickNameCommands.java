package net.totalmine.chat;


import net.totalmine.core.TMNM;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;


public class nickNameCommands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only in-game players can use this command");
			return true;
		}

		Player player = (Player)sender;



		if (command.getName().equals("nickname")) {
			if (args.length <1) {
				if (nickNameHandler.nicknames.containsKey(player.getName())) {
					nickNameHandler.removeNickname(player.getName());
					player.sendMessage(TMNM.prefix+"You have removed your nickname");
					return true;
				} else {
					player.sendMessage(TMNM.prefix+"Bad format, /nick [nickname]");
					return true;
				}
			} else if ((nickNameHandler.nicknames.containsKey(player.getName())) && (args[0].equalsIgnoreCase("off"))) {
				nickNameHandler.removeNickname(player.getName());
				player.sendMessage(TMNM.prefix+"You have removed your nickname");
				return true;
			} else if ((args.length > 1) && (player.hasPermission("tcn.chat.nickname.others"))) {
				if (Bukkit.getPlayer(args[1]).isOnline()) {
					if (args[1].length() > 24) {
						player.sendMessage(TMNM.prefix+"Nicknames may only be 24 characters");
						return true;
					}
					nickNameHandler.setNickname(Bukkit.getPlayer(args[0]).getName(), args[1]);
					player.sendMessage(TMNM.prefix+"You changed "+Bukkit.getPlayer(args[0]).getName()+"'s nickname.");
				} else
					player.sendMessage(TMNM.prefix+"The targeted player must be online");
				return true;
			} else {
				if (player.hasPermission("tcn.chat.nickname")) {
					if (args[0].length() > 24) {
						player.sendMessage(TMNM.prefix+"Nicknames may only be 24 characters");
						return true;
					}
					nickNameHandler.setNickname(player.getName(), args[0]);
				} else
					player.sendMessage(TMNM.prefix+"You don't have permission for this command");
			}
			return true;
		}
		if (command.getName().equals("realname")) {
			if (args.length < 1) {
				player.sendMessage(TMNM.prefix+"Bad format, /realname [nickname]");
				return true;
			}
			String input = nickNameHandler.removeFormatting(args[0]).toLowerCase();
			input = input.replaceAll("[^a-zA-Z0-9_$-]+", "");
			for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
				if (nickNameHandler.nicknames.containsKey(onlinePlayer)) {

					if (nickNameHandler.removeFormatting(nickNameHandler.nicknames.get(onlinePlayer)).toLowerCase().contains(input)) {
						player.sendMessage(TMNM.prefix+onlinePlayer.getName()+" is "+onlinePlayer.getDisplayName());
						return true;
					}
				}
			}
			player.sendMessage(TMNM.prefix+"No player could be found");
			return true;
		}
		return false;
	}
}
