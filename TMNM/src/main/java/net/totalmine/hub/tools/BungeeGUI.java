package net.totalmine.hub.tools;


import net.totalmine.core.TMNM;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BungeeGUI implements Listener{
	
	String VIP = (ChatColor.GOLD + "[VIP] ");
	private static HashMap<Integer, ItemStack> servers = new HashMap<Integer, ItemStack>();

	public BungeeGUI() {


		ItemStack FactionsIcon = new ItemStack (Material.CHEST);
		ItemMeta FactionsMeta = FactionsIcon.getItemMeta();
		FactionsMeta.setDisplayName("§dFactions (§cComing Soon§d)");
		FactionsMeta.setLore(Arrays.asList("§fThe best, most thrilling, action packed", "§fPvP experience since... EVER!", "", "§a  > Click to connect", ""));
		FactionsIcon.setItemMeta(FactionsMeta);
		servers.put(10, FactionsIcon);

		ItemStack SkyBlockIcon = new ItemStack (Material.GRASS);
		ItemMeta SkyBlockMeta = SkyBlockIcon.getItemMeta();
		SkyBlockMeta.setDisplayName("§dSkyBlock");
		SkyBlockMeta.setLore(Arrays.asList("§fA challenging survival experience,", "§fsee how long you can last", "", "§a  > Click to connect", ""));
		SkyBlockIcon.setItemMeta(SkyBlockMeta);
		servers.put(12, SkyBlockIcon);

		ItemStack CreativeIcon = new ItemStack (Material.REDSTONE_BLOCK);
		ItemMeta CreativeMeta = CreativeIcon.getItemMeta();
		CreativeMeta.setDisplayName("§dCreative");
		CreativeMeta.setLore(Arrays.asList("§fBuild whatever your heart desires-", "§fwithin a 64x64 plot of course", "", "§a  > Click to connect", ""));
		CreativeIcon.setItemMeta(CreativeMeta);
		servers.put(14, CreativeIcon);

		ItemStack VanillaIcon = new ItemStack (Material.DIAMOND_PICKAXE);
		ItemMeta VanillaMeta = VanillaIcon.getItemMeta();
		VanillaMeta.setDisplayName("§dSurvival (§5Original§d)");
		VanillaMeta.setLore(Arrays.asList("§fNo pvp, no factions, no nonsense-", "§fjust pure bonafide Survival", "", "§a  > Click to connect", ""));
		VanillaIcon.setItemMeta(VanillaMeta);
		servers.put(16, VanillaIcon);

		ItemStack KitPvPIcon = new ItemStack (Material.IRON_SWORD);
		ItemMeta KitPvPMeta = KitPvPIcon.getItemMeta();
		KitPvPMeta.setDisplayName("§dSGPvP");
		KitPvPMeta.setLore(Arrays.asList("§fFocus all your efforts in victory as", "§fyou dominate all opponents", "", "§a  > Click to connect", ""));
		KitPvPIcon.setItemMeta(KitPvPMeta);
		servers.put(22, KitPvPIcon);

		ItemStack QuitIcon = new ItemStack (Material.NETHER_STAR);
		ItemMeta QuitMeta = QuitIcon.getItemMeta();
		QuitMeta.setDisplayName("§6<< quit");
		QuitIcon.setItemMeta(QuitMeta);
		servers.put(27, QuitIcon);
		servers.put(35, QuitIcon);
	}

	
	public static void openServerGUI(Player player) {
		/*
		Inventory inv = Bukkit.createInventory(null, 9, TMNM.prefix + "Servers");
		ItemStack grass = new ItemStack (Material.GRASS);
		ItemMeta grassMeta = grass.getItemMeta();
		ItemStack DIAMOND_PICK = new ItemStack (Material.DIAMOND_PICKAXE);
		ItemMeta DIAMOND_PICKMeta = DIAMOND_PICK.getItemMeta();
		ItemStack WOOD = new ItemStack (Material.WOOD);
		ItemMeta WOODMeta = DIAMOND_PICK.getItemMeta();
		ItemStack NETHERSTAR = new ItemStack (Material.NETHER_STAR);
		ItemMeta NETHERSTARMeta = NETHERSTAR.getItemMeta();
		ItemStack DIAMOND_SWORD = new ItemStack (Material.DIAMOND_SWORD);
		ItemMeta DIAMOND_SWORDMeta = DIAMOND_SWORD.getItemMeta();
		
		grassMeta.setDisplayName("§aSkyBlock");
		grass.setItemMeta(grassMeta);
		DIAMOND_PICKMeta.setDisplayName("§aSurvival");
		DIAMOND_PICK.setItemMeta(DIAMOND_PICKMeta);
		WOODMeta.setDisplayName("§aCreative");
		WOOD.setItemMeta(WOODMeta);
		NETHERSTARMeta.setDisplayName("§aArcade");
		NETHERSTAR.setItemMeta(NETHERSTARMeta);
		DIAMOND_SWORDMeta.setDisplayName("§aKitPvP");
		DIAMOND_SWORD.setItemMeta(DIAMOND_SWORDMeta);
		// 0 1 2 3 4 5 6 7 8
		// 9 10 11 12 13 14 15 16 17
		// 18 19 20 21 22 23 24 25 26
		// 27 28 29 30 31 32 33 34 35
		inv.setItem(0, DIAMOND_PICK);
		inv.setItem(2, grass);
		inv.setItem(4, WOOD);
		inv.setItem(6, NETHERSTAR);
		inv.setItem(8, DIAMOND_SWORD);
		player.openInventory(inv);
		*/

		// 0 1 2 3 4 5 6 7 8
		// 9 10 11 12 13 14 15 16 17
		// 18 19 20 21 22 23 24 25 26
		// 27 28 29 30 31 32 33 34 35

		Inventory inv = Bukkit.createInventory(null, 36, TMNM.prefix + ChatColor.BLACK.toString() + " Servers");


		for(Map.Entry<Integer, ItemStack> entry : servers.entrySet()) {
			inv.setItem(entry.getKey(), entry.getValue());
		}

		player.openInventory(inv);
	}
}
