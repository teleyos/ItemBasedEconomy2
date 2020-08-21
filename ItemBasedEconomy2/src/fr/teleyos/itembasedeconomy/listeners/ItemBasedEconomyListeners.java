package fr.teleyos.itembasedeconomy.listeners;
//TODO si left click et sneak et admin alors détruire panneau
import java.util.List;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import fr.teleyos.itembasedeconomy.GetMainClass;
import fr.teleyos.itembasedeconomy.money.BankManagement;
import fr.teleyos.itembasedeconomy.money.MoneyItems;
import fr.teleyos.itembasedeconomy.money.ShopManager;
import net.milkbowl.vault.economy.Economy;


public class ItemBasedEconomyListeners implements Listener{
	
	private static Economy economy = GetMainClass.getEconomy();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if(!economy.hasAccount(player)) {
			economy.createPlayerAccount(player);
		}else {
			player.sendMessage("You have §a"+economy.getBalance(player)+"$");
		}
		
	}
	
	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		Block block = e.getClickedBlock();
		Action action = e.getAction();
		if(block != null) {
			if(block.getState() instanceof Sign) {
				Sign sign = (Sign)block.getState();
				if(sign.getPersistentDataContainer().has(new NamespacedKey(GetMainClass.get(), "ibm_isshop"),PersistentDataType.BYTE)) {
					if(action == Action.RIGHT_CLICK_BLOCK) {
						if(!sign.getPersistentDataContainer().has(new NamespacedKey(GetMainClass.get(), "ibm_error"),PersistentDataType.BYTE)) {
							boolean selling ;
							if(sign.getPersistentDataContainer().has(new NamespacedKey(GetMainClass.get(), "ibm_selling"),PersistentDataType.BYTE)) {
								selling = true;
							}else {
								selling = false;//it's buying
							}
							Material article = Material.getMaterial(sign.getPersistentDataContainer().get(new NamespacedKey(GetMainClass.get(), "ibm_article"), PersistentDataType.STRING));
							int amount = sign.getPersistentDataContainer().get(new NamespacedKey(GetMainClass.get(), "ibm_amount"), PersistentDataType.INTEGER);
							int price = sign.getPersistentDataContainer().get(new NamespacedKey(GetMainClass.get(), "ibm_price"), PersistentDataType.INTEGER);
							ShopManager.signInteraction(player,selling,article,amount,price);
						}else {
							player.sendMessage("§c This sign doesn't work");
							if(player.hasPermission("signerror.see")) {
								player.sendMessage("See the doc for valid article names");
								player.sendMessage("https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html");
							}
						}
					}else if(action == Action.LEFT_CLICK_BLOCK){
						if(player.hasPermission("sign.destroy")) {
							if(player.isSneaking()) {
							}
						}else {
							e.setCancelled(true);
							player.sendMessage("You can't do that!");
						}
					}
				}
			}
		}
	}
	
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		InventoryView inv = e.getView();
		if(inv.getTitle().equalsIgnoreCase("§8Withdraw money")) {
			Player player = (Player) e.getWhoClicked();
			ItemStack it = e.getCurrentItem();
			moneyWithdraw(it,player,e);
		}
		if(inv.getTitle().equalsIgnoreCase("§8Deposit money")) {
			Player player = (Player) e.getWhoClicked();
			ItemStack it = e.getCurrentItem();
			moneyDeposit(it,player,e);
		}
		
	}
	
	private static ItemStack makeItem(Material material,String name) {
		ItemStack it = new ItemStack(material,1);
		ItemMeta itM = it.getItemMeta();
		itM.setDisplayName(name);
		it.setItemMeta(itM);
		return it;
	}
	
	private static void moneyWithdraw(ItemStack it,Player player,InventoryClickEvent e) {
		if(it != null) {
			List<String> itLore = it.getItemMeta().getLore();
			if(itLore != null) {
				if(itLore.get(0).equalsIgnoreCase("§8shop item")) {
					e.setCancelled(true);
					int nb=0;
					switch(it.getItemMeta().getDisplayName()) {
						case "§aOne Dollard" :
							nb=1;
							if(BankManagement.get(player, player)-nb>0) {
								BankManagement.take(player,nb, player);
								player.getInventory().addItem(makeItem(MoneyItems.getOneDollardItem(),"§aOne Dollard"));
							}else {
								player.sendMessage("§c Not enought money");
							}
							break;
						case "§aTwo Dollard" :
							nb=2;
							if(BankManagement.get(player, player)-nb>0) {
								BankManagement.take(player,nb, player);
								player.getInventory().addItem(makeItem(MoneyItems.getTwoDollardItem(),"§aTwo Dollard"));
							}else {
								player.sendMessage("§c Not enought money");
							}
							break;
						case "§aFive Dollard" :
							nb=5;
							if(BankManagement.get(player, player)-nb>0) {
								BankManagement.take(player,nb, player);
								player.getInventory().addItem(makeItem(MoneyItems.getFiveDollardItem(),"§aFive Dollard"));
							}else {
								player.sendMessage("§c Not enought money");
							}
							break;
						case "§aTen Dollard" :
							nb=10;
							if(BankManagement.get(player, player)-nb>0) {
								BankManagement.take(player,nb, player);
								player.getInventory().addItem(makeItem(MoneyItems.getTenDollardItem(),"§aTen Dollard"));
							}else {
								player.sendMessage("§c Not enought money");
							}
							break;
						case "§aTwenty Dollard" :
							nb=20;
							if(BankManagement.get(player, player)-nb>0) {
								BankManagement.take(player,nb, player);
								player.getInventory().addItem(makeItem(MoneyItems.getTwentyDollardItem(),"§aTwenty Dollard"));
							}else {
								player.sendMessage("§c Not enought money");
							}
							break;
						default://Fifty
							nb=50;
							if(BankManagement.get(player, player)-nb>0) {
								BankManagement.take(player,nb, player);
								player.getInventory().addItem(makeItem(MoneyItems.getFiftyDollardItem(),"§aFifty Dollard"));
							}else {
								player.sendMessage("§c Not enought money");
							}
							break;
					}
					
					
				}
			}
		}else {
			return;
		}
	}
	
	private static void moneyDeposit(ItemStack it,Player player,InventoryClickEvent e) {
		if(it != null) {
			List<String> itLore = it.getItemMeta().getLore();
			if(itLore != null) {
				if(itLore.get(0).equalsIgnoreCase("§8shop item")) {
					e.setCancelled(true);
					boolean taken = false;
					switch(it.getItemMeta().getDisplayName()) {
						case "§aOne Dollard" :
							takeItemIfAllowed(player,1,taken,it);
							break;
						case "§aTwo Dollard" :
							takeItemIfAllowed(player,2,taken,it);
							break;
						case "§aFive Dollard" :
							takeItemIfAllowed(player,5,taken,it);
							break;
						case "§aTen Dollard" :
							takeItemIfAllowed(player,10,taken,it);
							break;
						case "§aTwenty Dollard" :
							takeItemIfAllowed(player,20,taken,it);
							break;
						default://Fifty
							takeItemIfAllowed(player,50,taken,it);
							break;
					}
					
					
				}
			}
		}else {
			return;
		}
	}
	
	
	// I didn't know how to name this one but basically it checks if the player can deposit more at the bank
	// and it takes away an item named the same as in the shop and add the amount of money accordingly
	// It's based on the name so if you have old money you can still deposit it
	private static void takeItemIfAllowed(Player player,int nb,boolean taken,ItemStack it) {
		if(BankManagement.get(player, player)+nb<GetMainClass.get().getConfig().getInt("money.virtual.max")) {
			for(ItemStack item : player.getInventory().getContents()) {
				if (item != null) {
					if(item.getItemMeta().getDisplayName().equalsIgnoreCase(it.getItemMeta().getDisplayName())) {
						BankManagement.add(player, nb, player);
						taken = true;
						if(item.getAmount()>1) {
							item.setAmount(item.getAmount()-1);
						}else {
							player.getInventory().remove(item);
						}
						break;
					}
				}
			}
			if(!taken)player.sendMessage("§cYou don't have such money item!");
		}else {
			player.sendMessage("§c You can't have more money in bank!");
		}
	}

}
