package net.totalmine.core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

import net.totalmine.core.commands.Close;
import net.totalmine.core.commands.CoreModules;
import net.totalmine.core.commands.SubCommand;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;




public class CommandHandler implements CommandExecutor {
	private HashMap <String, SubCommand> commands;
	public CommandHandler(Plugin plugin) {
		commands = new HashMap <String, SubCommand>();
		loadCommands();
	}

	private void loadCommands() {
		commands.put("close", new Close());
		commands.put("modules", new CoreModules());
	}


	@Override
	public boolean onCommand(CommandSender sender, Command cmd1, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only in-game players can use TMNM commands");
			return true;
		}

		Player player = (Player) sender;

		if (cmd1.getName().equalsIgnoreCase("tmnm")) {
			if (args == null || args.length < 1) {

				/*
				 * MAKE AN ADMIN COMMAND
				 * WHICH HAS ALL THE MAIN VARIABLES
				 * SUCH AS "hubTools:Enable, stophunger:enable, maintenancemode: enable
				 */

				player.sendMessage(ChatColor.GOLD+"--------------------------------------------------");
				player.sendMessage(ChatColor.RED.toString() + " TMN" + ChatColor.WHITE.toString() + " - " + ChatColor.GOLD.toString() + "Manager");
				player.sendMessage(ChatColor.YELLOW.toString()+"TotalMine Network Manager");
				player.sendMessage(ChatColor.YELLOW.toString()+"Version: " + TMNM.getPlugin().getDescription().getVersion() + ", developed by " + TMNM.getPlugin().getDescription().getAuthors().toString());
				player.sendMessage(ChatColor.GOLD+"--------------------------------------------------");


				return true;
			}

			if (args[0].equalsIgnoreCase("help")) {

				return true;
			}

			String sub = args[0];
			Vector < String > l = new Vector < String > ();
			l.addAll(Arrays.asList(args));
			l.remove(0);
			args = (String[]) l.toArray(new String[0]);
			if (!commands.containsKey(sub.toLowerCase())) {
				player.sendMessage(ChatColor.RED + "That sub command does not exist");
				return true;
			}
			try {
				commands.get(sub).onCommand(player, args);
			} catch (Exception e) {
				e.printStackTrace();
				player.sendMessage(ChatColor.RED+"TMNM Command Error");
			}
			return true;
		}
		return false;
	}


}
