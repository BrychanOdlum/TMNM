package net.totalmine.other.weather;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class weatherLock implements Listener {

	@EventHandler
	public void weatherChange(WeatherChangeEvent event) {
		event.setCancelled(true);
	}
}
