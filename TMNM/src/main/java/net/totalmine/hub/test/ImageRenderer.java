package net.totalmine.hub.test;

import java.awt.Image;
import java.net.URI;

import javax.imageio.ImageIO;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapPalette;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

public class ImageRenderer extends MapRenderer {

	private boolean hasRendered;
	private final Image theImg;
	private Thread renderImageThread;
	public final String theUrl;

	public ImageRenderer(String url) throws Exception {
		this.hasRendered = false;
		this.theImg = ImageIO.read(URI.create(url).toURL().openStream());
		this.theUrl = url;
	}

	@Override
	public void render(MapView view, final MapCanvas canvas, Player player) {
		if ((!this.hasRendered) && (this.theImg != null)
				&& (this.renderImageThread == null)) {
			this.renderImageThread = new Thread() {

				public void run() {
					Bukkit.broadcastMessage("TEST1");
					canvas.drawImage(0, 0,
							MapPalette.resizeImage(ImageRenderer.this.theImg));
				}

			};
			this.renderImageThread.start();
			this.hasRendered = true;
		}
	}

}