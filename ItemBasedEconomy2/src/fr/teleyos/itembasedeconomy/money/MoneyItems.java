package fr.teleyos.itembasedeconomy.money;

import org.bukkit.Material;

import fr.teleyos.itembasedeconomy.GetMainClass;
import fr.teleyos.itembasedeconomy.ItemBasedEconomy;

public class MoneyItems {
	
	private static ItemBasedEconomy main = GetMainClass.get();
	
	private static Material oneDollardItem = Material.SNOWBALL;
	private static Material twoDollardItem = Material.REDSTONE;
	private static Material fiveDollardItem = Material.LAPIS_LAZULI;
	private static Material tenDollardItem = Material.IRON_BARS;
	private static Material twentyDollardItem = Material.GOLD_INGOT;
	private static Material fiftyDollardItem = Material.EMERALD;
	
	public static void setAllFromConfig() {
		String one = main.getConfig().getString("money.items.onedollard");
		String two = main.getConfig().getString("money.items.twodollard");
		String five = main.getConfig().getString("money.items.fivedollard");
		String ten = main.getConfig().getString("money.items.tendollard");
		String twenty = main.getConfig().getString("money.items.twentydollard");
		String fifty = main.getConfig().getString("money.items.fiftydollard");
		setAll(one,two,five,ten,twenty,fifty);
	}
	
	public static void setAll(String one,String two, String five, String ten, String twenty, String fifty) {
		setOneDollardItem(Material.getMaterial(one));
		setTwoDollardItem(Material.getMaterial(two));
		setFiveDollardItem(Material.getMaterial(five));
		setTenDollardItem(Material.getMaterial(ten));
		setTwentyDollardItem(Material.getMaterial(twenty));
		setFiftyDollardItem(Material.getMaterial(fifty));
	}
	
	public static Material getOneDollardItem() {
		return oneDollardItem;
	}

	public static Material getTwoDollardItem() {
		return twoDollardItem;
	}

	public static void setTwoDollardItem(Material twoDollardItem) {
		MoneyItems.twoDollardItem = twoDollardItem;
	}

	public static Material getFiveDollardItem() {
		return fiveDollardItem;
	}

	public static void setFiveDollardItem(Material fiveDollardItem) {
		MoneyItems.fiveDollardItem = fiveDollardItem;
	}

	public static Material getTenDollardItem() {
		return tenDollardItem;
	}

	public static void setTenDollardItem(Material tenDollardItem) {
		MoneyItems.tenDollardItem = tenDollardItem;
	}

	public static Material getTwentyDollardItem() {
		return twentyDollardItem;
	}

	public static void setTwentyDollardItem(Material twentyDollardItem) {
		MoneyItems.twentyDollardItem = twentyDollardItem;
	}

	public static Material getFiftyDollardItem() {
		return fiftyDollardItem;
	}

	public static void setFiftyDollardItem(Material fiftyDollardItem) {
		MoneyItems.fiftyDollardItem = fiftyDollardItem;
	}

	public static void setOneDollardItem(Material oneDollardItem) {
		MoneyItems.oneDollardItem = oneDollardItem;
	}
	
	
}
