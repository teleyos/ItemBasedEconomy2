package fr.teleyos.itembasedeconomy.commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.teleyos.itembasedeconomy.itembasedeconomygui.ItemBasedEconomyGUI;
import fr.teleyos.itembasedeconomy.itembasedeconomygui.ItemBasedEconomySignMaker;
import fr.teleyos.itembasedeconomy.money.BankManagement;

public class ItemBasedEconomyCommands implements CommandExecutor{
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args) {
		if(sender instanceof Player) {
			Player player = (Player)sender;
			if(label.equalsIgnoreCase("money")) {
				player.sendMessage("You have §a"+BankManagement.get(player, player)+"$§r on your account.");
				return true;
			}
			if(label.equalsIgnoreCase("withdraw")) {
				player.openInventory(ItemBasedEconomyGUI.createWithdrawGUI());
				return true;
			}
			if(label.equalsIgnoreCase("deposit")) {
				player.openInventory(ItemBasedEconomyGUI.createDepositGUI());
				return true;
			}
			if(label.equalsIgnoreCase("putshop")) {
				if(args.length>=4) {
					boolean selling=true;
					player.sendMessage(args[0].getClass()+" : "+args[0]);
					if(args[0].equalsIgnoreCase("selling")) {
						selling = true;
					}else if(args[0].equalsIgnoreCase("buying")) {
						selling = false;
					}else if(args[0].equalsIgnoreCase("help")){
						player.sendMessage("Usage : /putshop <selling/buying> <amount> <material> <price>");
					}else {
						player.sendMessage("Usage : /putshop <selling/buying> <amount> <material> <price>");
						return false;
					}
					
					
					
					int amount=0;
					if(Integer.parseInt(args[1])<=64) {
						amount = Integer.parseInt(args[1]);
					}else {
						player.sendMessage("amount can't be more than 64");
						return false;
					}
					
					String matstring = args[2];
					String material = matstring.replace("minecraft:", "").toUpperCase();
					
					if(Material.getMaterial(material)==null) {
						player.sendMessage("invalid article");
						return false;
					}
					
					int price = Integer.parseInt(args[3]);

					Sign sign = placeSign(player);
					ItemBasedEconomySignMaker.makeShop(sign, selling, material, amount, price);
					
					return true;
					
				}else {
					player.sendMessage("Usage : /putshop <selling/buying> <amount> <material> <price>");
					return false;
				}
				
			}
			return false;
		}
		return false;
	}
	
	@SuppressWarnings("deprecation")
	private static Sign placeSign(Player player) {
		Location blockL = player.getTargetBlock(null,200).getLocation();
		float playerdir = (float) (player.getLocation().getYaw());
		switch(getFacing(playerdir)) {
			case 2:
				blockL.subtract(0, 0, 1);
				break;
			case 3:
				blockL.add(0, 0, 1);
				break;
			case 4:
				blockL.subtract(1, 0, 0);
				break;
			default:
				blockL.add(1, 0, 0);
				break;
		}
		blockL.getBlock().setType(Material.OAK_WALL_SIGN);
		Sign sign = (Sign) blockL.getBlock().getState();
		//3 180 -x ; 4 270 +z; 5 90 -z; 2 0/360 +x
		Byte test = getFacing(playerdir);
		sign.setRawData(test);
		sign.update();
		return sign;
	}
	
	private static byte getFacing(float dir) {
		int fourtyfifth = (int) dir;
		
		if( (315 <= fourtyfifth && fourtyfifth < 360) || (0 <= fourtyfifth && fourtyfifth < 45) ){
			return 2;
		}else if(45 <= fourtyfifth && fourtyfifth < 135) {
			return 5;
		}else if(135 <= fourtyfifth && fourtyfifth < 225) {
			return 3;
		}else{ // 225 <= fourtyfifth && fourtyfifth < 315
			return 4;
		}
		
	}
}
