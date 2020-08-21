package fr.teleyos.itembasedeconomy.money;

import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import fr.teleyos.itembasedeconomy.GetMainClass;
import fr.teleyos.itembasedeconomy.ItemBasedEconomy;

public class BankManagement {

	private static ItemBasedEconomy main = GetMainClass.get();
	
	public static int get(Player player,CommandSender sender){
		return player.getPersistentDataContainer().get(new NamespacedKey(GetMainClass.get(),"IBE_Money"),PersistentDataType.INTEGER);
	}
	
	public static void set(Player player, int amount,CommandSender sender) {
		player.getPersistentDataContainer().set(new NamespacedKey(GetMainClass.get(),"IBE_Money"),PersistentDataType.INTEGER,amount);
	}
	
	public static boolean add(Player player, int amount, CommandSender sender) {
		
		if(get(player,sender)+amount < main.getConfig().getInt("money.virtual.max")) {
			
			set(player,get(player,sender)+amount,sender);
			sender.sendMessage(player.getName()+" gets "+amount+"$. Adding up to "+get(player,sender)+"$.");
			return true;
		}else{
			
			player.sendMessage("You can't have more than "+main.getConfig().getInt("money.virtual.max")+"$ in bank!");
			return false;
		}
	}
	
	public static boolean take(Player player,int amount,CommandSender sender) {
		
		if(get(player,sender)-amount > 0) {
			
			set(player,get(player,sender)-amount,sender);
			sender.sendMessage(player.getName()+" loses "+amount+"$. Leaving "+get(player,sender)+"$ on your account.");
			return true;
		}else{
			
			player.sendMessage("You can't have less than 0$ in bank!");
			return false;
		}
	}
}
