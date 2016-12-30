package net.totalmine.hub.test;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

public class Maps implements Listener {

	@EventHandler
	public void onMap(MapInitializeEvent e) {
		MapView m = e.getMap();
		for (MapRenderer r: m.getRenderers()) {
			m.removeRenderer(r);
		}
		 try {
			m.addRenderer(new ImageRenderer("http://chivebox.com/img/mc/news.png"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
