package fr.teleyos.itembasedeconomy.money;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.teleyos.itembasedeconomy.GetMainClass;

public class ShopManager {
	public static void signInteraction(Player player,boolean selling,Material article,int amount,int price) {
		boolean taken=false;
		if(selling) {
			if(BankManagement.take(player, price, player)) {
				player.getInventory().addItem(new ItemStack(article,amount));
			}
		}else {
			if(BankManagement.get(player, player)+price<GetMainClass.get().getConfig().getInt("money.virtual.max")) {
				for(ItemStack item : player.getInventory().getContents()) {
					if (item != null) {
						if(item.getType() == article && item.getAmount() >= amount) {
							if(item.getAmount()-amount >= 0) {
								item.setAmount(item.getAmount()-amount);
								BankManagement.add(player, price, player);
								taken =true;
								break;
							}
						}
					}
				}
				if(!taken)player.sendMessage("§cYou don't have such item! Stack them if you do...");
			}else {
				player.sendMessage("§c You can't have more money in bank!");
			}
		}
	}
}
