package fr.teleyos.itembasedeconomy.itembasedeconomygui;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Sign;
import org.bukkit.persistence.PersistentDataType;

import fr.teleyos.itembasedeconomy.GetMainClass;

public class ItemBasedEconomySignMaker {
	public static void makeShop(Sign sign,boolean selling,String article,int amount,int price) {
		
		sign.getPersistentDataContainer().set(new NamespacedKey(GetMainClass.get(),"ibm_isshop"),PersistentDataType.BYTE,(byte)1);
		
		if(selling) {
			sign.getPersistentDataContainer().set(new NamespacedKey(GetMainClass.get(),"ibm_selling"),PersistentDataType.BYTE,(byte)1);
		}else {
		}
		
		sign.getPersistentDataContainer().set(new NamespacedKey(GetMainClass.get(),"ibm_article"),PersistentDataType.STRING,article);
		sign.getPersistentDataContainer().set(new NamespacedKey(GetMainClass.get(),"ibm_amount"),PersistentDataType.INTEGER,amount);
		sign.getPersistentDataContainer().set(new NamespacedKey(GetMainClass.get(),"ibm_price"),PersistentDataType.INTEGER,price);
		
		if(Material.getMaterial(article) != null ) {
			if(sign.getPersistentDataContainer().has(new NamespacedKey(GetMainClass.get(),"ibm_error"),PersistentDataType.BYTE)){
				sign.getPersistentDataContainer().remove(new NamespacedKey(GetMainClass.get(),"ibm_error"));
			}
			if(selling) {
				sign.setLine(0, "Selling");
			}else {
				sign.setLine(0, "Buying");
			}
			sign.setLine(1, "§f"+Integer.toString(amount));
			sign.setLine(2,"§6"+article.toLowerCase().replace("_", " "));
			sign.setLine(3, "for §a"+Integer.toString(price)+"§r$");
		}else {
			sign.setLine(0,"§c ERROR");
			sign.setLine(1,"§c Wrong article name");
			sign.setLine(2, "§c click to get the");
			sign.setLine(3,"§cdoc link");
			sign.getPersistentDataContainer().set(new NamespacedKey(GetMainClass.get(),"ibm_error"),PersistentDataType.BYTE,(byte)1);
		}
		sign.update();
		
	}
}
