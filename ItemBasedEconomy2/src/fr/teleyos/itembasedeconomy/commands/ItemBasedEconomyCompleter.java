package fr.teleyos.itembasedeconomy.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class ItemBasedEconomyCompleter implements TabCompleter{

	@Override
	public List<String> onTabComplete(CommandSender sender,Command cmd,String label,String[] args) {
		if(sender instanceof Player) {
			if(label.equalsIgnoreCase("putshop")) {
				if(args.length == 1) {
					List<String> list = new ArrayList<String>();
					list.add("buying");
					list.add("selling");
					list.add("help");
					return list;
				}else if(args.length == 3){
					String typed = args[2];
					List<String> list = new ArrayList<String>();
					if(typed != null) {
						for(Material material : Material.values()) {
							if(("minecraft:"+material.toString()).toLowerCase().contains(typed)) {
								list.add("minecraft:"+material.toString().toLowerCase());
							}
						}
					}else {
						for(Material material : Material.values()) {
							list.add("minecraft:"+material.toString().toLowerCase());
						}
					}
					return list;
				}else {
					return null;
				}
			}
			
		}
		
		
		return null;
	}

}
