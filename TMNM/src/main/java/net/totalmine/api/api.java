package net.totalmine.api;

import net.minecraft.util.org.apache.commons.codec.binary.Base64;
import net.minecraft.util.org.apache.commons.codec.binary.StringUtils;
import net.totalmine.core.TMNM;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.bukkit.ChatColor;


public class api extends Thread {
	public void run() {

		if (TMNM.modules.get("HTTPAPI")) {
			try {
				HttpServer server = HttpServer.create(new InetSocketAddress(TMNM.webAPIPort), 0); //25789
				server.createContext("/api", new WebApiHandler());
				server.setExecutor(null); // creates a default executor
				server.start();
			} catch (IOException ie) {
				ie.printStackTrace();
			}
		}
	}



	static class WebApiHandler implements HttpHandler {
		public void handle(HttpExchange t) throws IOException {

			String request[] = t.getRequestURI().toString().split("/");

			String response;

			if (!request[2].equals(TMNM.webAPIKey)) {
				Bukkit.broadcastMessage("§cAn API request was generated using an §lINVALID§r§c API key");
				response = "key_invalid";

			} else {



				if (request[3].equals("command")) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Base64Decode(request[4]));
					response = "command_dispatched";
				} else if (request[3].equals("broadcast")) {
					Bukkit.broadcastMessage(Base64Decode(request[4]).replace("&", "§"));
					response = "broadcast_dispatched";
				} else
					response = "bad_method";

			}



			t.sendResponseHeaders(200, response.length());
			OutputStream os = t.getResponseBody();

			os.write(response.getBytes());
			os.close();
		}
	}


	public static String Base64Decode(String s) {
		return StringUtils.newStringUtf8(Base64.decodeBase64(s));
	}

}