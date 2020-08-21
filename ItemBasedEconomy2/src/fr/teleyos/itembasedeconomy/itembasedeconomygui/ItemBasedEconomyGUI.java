package fr.teleyos.itembasedeconomy.itembasedeconomygui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.teleyos.itembasedeconomy.money.MoneyItems;

public class ItemBasedEconomyGUI {
	public static Inventory createWithdrawGUI() {
		Inventory inv = Bukkit.createInventory(null, 9, "§8Withdraw money");
		inv.addItem(makeItem(MoneyItems.getOneDollardItem(),"§aOne Dollard"));
		inv.addItem(makeItem(MoneyItems.getTwoDollardItem(),"§aTwo Dollard"));
		inv.addItem(makeItem(MoneyItems.getFiveDollardItem(),"§aFive Dollard"));
		inv.addItem(makeItem(MoneyItems.getTenDollardItem(),"§aTen Dollard"));
		inv.addItem(makeItem(MoneyItems.getTwentyDollardItem(),"§aTwenty Dollard"));
		inv.addItem(makeItem(MoneyItems.getFiftyDollardItem(),"§aFifty Dollard"));
		return inv;
	}
	
	public static Inventory createDepositGUI() {
		Inventory inv = Bukkit.createInventory(null, 9, "§8Deposit money");
		inv.addItem(makeItem(MoneyItems.getOneDollardItem(),"§aOne Dollard"));
		inv.addItem(makeItem(MoneyItems.getTwoDollardItem(),"§aTwo Dollard"));
		inv.addItem(makeItem(MoneyItems.getFiveDollardItem(),"§aFive Dollard"));
		inv.addItem(makeItem(MoneyItems.getTenDollardItem(),"§aTen Dollard"));
		inv.addItem(makeItem(MoneyItems.getTwentyDollardItem(),"§aTwenty Dollard"));
		inv.addItem(makeItem(MoneyItems.getFiftyDollardItem(),"§aFifty Dollard"));
		return inv;
	}
	
	private static ItemStack makeItem(Material material,String name) {
		ItemStack it = new ItemStack(material,1);
		ItemMeta itM = it.getItemMeta();
		itM.setDisplayName(name);
		List<String> lore = new ArrayList<String>();
		lore.add("§8shop item");
		itM.setLore(lore);
		it.setItemMeta(itM);
		return it;
	}
}
