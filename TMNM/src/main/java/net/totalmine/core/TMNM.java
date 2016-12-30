package net.totalmine.core;

import java.sql.*;
import java.util.HashMap;
import java.util.logging.Logger;

import net.totalmine.chat.chatFormatter;
import net.totalmine.chat.nickNameHandler;
import net.totalmine.hub.misc.SpawnJoin;
import net.totalmine.other.feed.commandFeed;
import net.totalmine.other.feed.commandHeal;
import net.totalmine.other.fly.commandFly;
import net.totalmine.other.weather.weatherCommand;
import net.totalmine.other.weather.weatherLock;
import net.totalmine.permissions.permissionHandler;
import net.totalmine.verification.verificationKick;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


import net.totalmine.core.events.*;
import net.totalmine.hub.misc.Drop;
import net.totalmine.hub.misc.HubFoodChange;
import net.totalmine.hub.misc.HubHealthChange;
import net.totalmine.hub.test.Maps;
import net.totalmine.hub.tools.Bungee;
import net.totalmine.hub.tools.BungeeGUI;
import net.totalmine.hub.tools.BungeeGUIClick;
import net.totalmine.hub.tools.Hide;
import net.totalmine.hub.tools.Join;
import net.totalmine.hub.tools.Speed;

import net.totalmine.api.api;
import net.totalmine.api.commands.*;

import net.totalmine.chat.nickNameCommands;

import net.totalmine.sql.MySQL;

public class TMNM extends JavaPlugin implements Listener {
	public Logger logger;

	private static Plugin p;


	public api api;

	public static String prefix = "§6[§e§lTM§6] §f";
	public static String prefixManager = "§6[§e§lTM§6] §f";
	public static boolean maintenanceMode = false;
	public static boolean serverLoading = true;

	public static Integer webAPIPort = 80;
	public static String webAPIKey = "password";

	PluginDescriptionFile pdfFile = this.getDescription();


	public static String SQLHOST;
	public static String SQLPORT;
	public static String SQLDATABASE;
	public static String SQLUSERNAME;
	public static String SQLPASSWORD;

	public static String chatFormat;

	public static MySQL MySQL;
	public static Connection connection = null;

	//        {START}         //
	//   Initialize modules   //
	//                        //

	public static HashMap<String, Boolean> modules = new HashMap<String, Boolean>();


//	private boolean HubSideBar			= false;

	//                        //
	//   Initialize modules   //
	//         {END}          //



	@Override
	public void onDisable() {
		serverLoading = true;
		for (Player player : Bukkit.getOnlinePlayers())
			player.kickPlayer(ChatColor.RED+"TMNM (" + pdfFile.getVersion()+") has been unloaded, the server is rebooting.");
	}

	@Override
	public void onEnable() {
		initializeModules();
		getServer().getScheduler().scheduleSyncDelayedTask(this, new Startup(), 10);
		p = this;
		logger = this.getLogger();
		getServer().getPluginManager().registerEvents(this, this);
		getLogger().info("TMNM initiated");



		//Load config before setting commands and events
		loadConfig();

		//Initialize MySQL connections
		MySQL = new MySQL(SQLHOST, SQLPORT, SQLDATABASE, SQLUSERNAME, SQLPASSWORD);
		connection = null;
		connection = MySQL.open();

		//Initialize web server
		if (modules.get("HTTPAPI")) {
			new api().start();
		}


		setCommands();
		setEvents();


		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
	}

	class Startup implements Runnable {
		public void run() {
			serverLoading = false;
		}
	}

	public static Plugin getPlugin() {
		return p;
	}

	public static boolean getMaintenanceMode() {
		return maintenanceMode;
	}

	public void loadConfig() {
		ConfigManager.getInstance().setup();
		saveConfig();
		ConfigManager.getInstance().load();
	}

	public void initializeModules() {
		modules.put("permissions", false);
		modules.put("HTTPAPI", false);
		modules.put("HubTools", false);
		modules.put("HubNoDamage", false);
		modules.put("WeatherLock", false);
		modules.put("ChatFormatter", false);
		modules.put("NickNames", false);
		modules.put("c_Weather",false);
		modules.put("c_Fly", false);
		modules.put("c_Feed", false);
		modules.put("c_Heal", false);
		modules.put("verificationKick", false);
	}


	public void setCommands() {
		//Core Management commands
		getCommand("tmnm").setExecutor(new CommandHandler(p));

		//All other commands
		getCommand("verify").setExecutor(new verify());
		//getCommand("gui").setExecutor(new BungeeGUI());

		if (modules.get("c_Weather"))
			getCommand("weather").setExecutor(new weatherCommand());
		if (modules.get("c_Fly"))
			getCommand("fly").setExecutor(new commandFly());
		if (modules.get("c_Feed"))
			getCommand("feed").setExecutor(new commandFeed());
		if (modules.get("c_Heal"))
			getCommand("heal").setExecutor(new commandHeal());
		if (modules.get("NickNames")) {
			getCommand("nickname").setExecutor(new nickNameCommands());
			getCommand("realname").setExecutor(new nickNameCommands());
		}
	}

	public void setEvents() {
		//Core Events
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new JoinEvent(), p);
		pm.registerEvents(new LeaveEvent(), p);

		//if (modules.get("permissions"))
			pm.registerEvents(new permissionHandler(), p);

		if (modules.get("ChatFormatter"))
			pm.registerEvents(new chatFormatter(), p);
		if (modules.get("verificationKick"))
			pm.registerEvents(new verificationKick(), p);
		if (modules.get("NickNames")) {
			pm.registerEvents(new nickNameHandler(), p);
		}
		//Hub Events
		if (modules.get("HubTools")) {
			pm.registerEvents(new Bungee(), p);
			pm.registerEvents(new BungeeGUI(), p);
			pm.registerEvents(new BungeeGUIClick(), p);
			pm.registerEvents(new Join(), p);
			pm.registerEvents(new Speed(), p);
			pm.registerEvents(new Drop(), p);
			pm.registerEvents(new Hide(), p);
			pm.registerEvents(new SpawnJoin(), p);
		}
		if (modules.get("HubNoDamage")) {
			pm.registerEvents(new HubFoodChange(), p);
			pm.registerEvents(new HubHealthChange(), p);
		}
		if (modules.get("WeatherLock")) {
			pm.registerEvents(new weatherLock(), p);
		}


		pm.registerEvents(new Maps(), p);
	}


	public String colorize(String input) {
		return ChatColor.translateAlternateColorCodes('&', input);
	}



	public static Boolean matchToggleArgument(String arg) {
		if ((arg.equalsIgnoreCase("on")) || (arg.startsWith("ena")) || (arg.equalsIgnoreCase("1"))) {
			return true;
		}
		if ((arg.equalsIgnoreCase("off")) || (arg.startsWith("dis")) || (arg.equalsIgnoreCase("0"))) {
			return false;
		}
		return false;
	}


	public static void keepConnection() {

		try {
			Statement statement = TMNM.connection.createStatement();
			ResultSet res = statement.executeQuery("SELECT 1");
			if (!res.next()) {
				Bukkit.broadcastMessage("My SQL CONNECTION REOPENED");
				connection = MySQL.open();
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			Bukkit.broadcastMessage("My SQL CONNECTION REOPENED");
			connection = MySQL.open();
		}
	}



	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
		String m = event.getMessage().toLowerCase().split(" ")[0];
		if (m.contains(":")) {
			event.setCancelled(true);
			event.getPlayer().sendMessage("Sorry human, we computers do not allow it when you touch us like that.");
			return;
		}
	}

}
