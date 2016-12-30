package net.totalmine.core;

import org.bukkit.configuration.file.FileConfiguration;
import net.totalmine.core.TMNM;

public class ConfigManager {

	private static ConfigManager instance = new ConfigManager();

	FileConfiguration config;

	public ConfigManager() {
	}

	public static ConfigManager getInstance() {
		return instance;
	}

	public void setup() {

		config = TMNM.getPlugin().getConfig();
		config.options().header("TMNM Data File, do not manually edit this");

		config.addDefault("MySQL.host", "127.0.0.1");
		config.addDefault("MySQL.port", "3306");
		config.addDefault("MySQL.database", "database");
		config.addDefault("MySQL.username", "username");
		config.addDefault("MySQL.password", "password");

		config.addDefault("maintenancemode", "enable");

		config.addDefault("module.permissions", "enable");

		config.addDefault("module.hub.tools", "enable");
		config.addDefault("module.hub.nodamage", "enable");
		config.addDefault("module.hub.sidebar", "enable");
		config.addDefault("module.api.enabled", "enable");
		config.addDefault("module.api.key", "password");
		config.addDefault("module.api.port", 25001);
		config.addDefault("module.chat.enabled", "enable");
		config.addDefault("module.chat.nicknames", "enable");
		config.addDefault("module.chat.format", "&7<prefix>&r <name>&r <suffix>➟ §7<message>");
		config.addDefault("module.verificationKick", "disable");


		config.addDefault("module.other.weather.lock", "enable");
		config.addDefault("module.other.weather.command", "enable");

		config.addDefault("module.other.fly", "enable");
		config.addDefault("module.other.feed", "enable");
		config.addDefault("module.other.heal", "enable");

		config.options().copyDefaults(true);

	}

	public void load() {

		TMNM.SQLHOST = config.getString("MySQL.host");
		TMNM.SQLPORT = config.getString("MySQL.port");
		TMNM.SQLDATABASE = config.getString("MySQL.database");
		TMNM.SQLUSERNAME = config.getString("MySQL.username");
		TMNM.SQLPASSWORD = config.getString("MySQL.password");

		if (config.getString("maintenancemode").equals("enable"))
			TMNM.maintenanceMode = true;

		if (config.getString("module.permissions").equals("enable"))
			TMNM.modules.put("permissions", true);

		if (config.getString("module.hub.tools").equals("enable"))
			TMNM.modules.put("HubTools", true);
		if (config.getString("module.hub.nodamage").equals("enable"))
			TMNM.modules.put("HubNoDamage", true);
		if (config.getString("module.other.weather.lock").equals("enable"))
			TMNM.modules.put("WeatherLock", true);
		if (config.getString("module.chat.enabled").equals("enable")) {
			TMNM.modules.put("ChatFormatter", true);
			TMNM.chatFormat = config.getString("module.chat.format");
		}
		if (config.getString("module.chat.nicknames").equals("enable"))
			TMNM.modules.put("NickNames", true);
		if (config.getString("module.verificationKick").equals("enable"))
			TMNM.modules.put("verificationKick", true);


		if (config.getString("module.other.weather.command").equals("enable"))
			TMNM.modules.put("c_Weather", true);
		if (config.getString("module.other.fly").equals("enable"))
			TMNM.modules.put("c_Fly", true);
		if (config.getString("module.other.feed").equals("enable"))
			TMNM.modules.put("c_Feed", true);
		if (config.getString("module.other.heal").equals("enable"))
			TMNM.modules.put("c_Heal", true);



		if (config.getString("module.api.enabled").equals("enable")) {
			TMNM.modules.put("HTTPAPI", true);
			TMNM.webAPIPort = config.getInt("module.api.port");
			TMNM.webAPIKey = config.getString("module.api.key");
		}

		
		
		
	}

}
