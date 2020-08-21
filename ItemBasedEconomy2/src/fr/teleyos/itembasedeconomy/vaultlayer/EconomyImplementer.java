package fr.teleyos.itembasedeconomy.vaultlayer;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import fr.teleyos.itembasedeconomy.GetMainClass;
import fr.teleyos.itembasedeconomy.money.BankManagement;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.economy.EconomyResponse.ResponseType;

public class EconomyImplementer implements Economy{
	
	@Override
	public EconomyResponse bankBalance(String arg0) {
		return null;
	}

	@Override
	public EconomyResponse bankDeposit(String arg0, double arg1) {
		return null;
	}

	@Override
	public EconomyResponse bankHas(String arg0, double arg1) {
		return null;
	}

	@Override
	public EconomyResponse bankWithdraw(String arg0, double arg1) {
		return null;
	}

	@Override
	public EconomyResponse createBank(String arg0, String arg1) {
		return null;
	}

	@Override
	public EconomyResponse createBank(String arg0, OfflinePlayer arg1) {
		return null;
	}

	@Override
	public boolean createPlayerAccount(String playerName) {
		if(Bukkit.getPlayer(playerName)==null) return false;
		Player player = Bukkit.getPlayer(playerName);
		player.getPersistentDataContainer().set(new NamespacedKey(GetMainClass.get(),"IBE_Money"),PersistentDataType.INTEGER,0);
		return true;
	}

	@Override
	public boolean createPlayerAccount(OfflinePlayer playerName) {
		if((Player)playerName==null) return false;
		Player player = (Player) playerName;
		player.getPersistentDataContainer().set(new NamespacedKey(GetMainClass.get(),"IBE_Money"),PersistentDataType.INTEGER,0);
		return true;
	}

	@Override
	public boolean createPlayerAccount(String playerName, String world) {
		return false;
	}

	@Override
	public boolean createPlayerAccount(OfflinePlayer playerName, String world) {
		return false;
	}

	@Override
	public String currencyNamePlural() {
		return "dollard";
	}

	@Override
	public String currencyNameSingular() {
		return "dollards";
	}

	@Override
	public EconomyResponse deleteBank(String arg0) {
		return null;
	}

	@Override
	public EconomyResponse depositPlayer(String playerName, double amount) {
		if(Bukkit.getPlayer(playerName)==null) return new EconomyResponse(amount, 0, ResponseType.FAILURE, playerName+" doesn't exist!");
		Player player = Bukkit.getPlayer(playerName);
		BankManagement.add(player,(int)Math.floor(amount), player);
		return new EconomyResponse(amount, BankManagement.get(player, player), ResponseType.SUCCESS, playerName+" credited with "+amount+"dollards");
	}

	@Override
	public EconomyResponse depositPlayer(OfflinePlayer playerOff, double amount) {
		if((Player)playerOff==null) return new EconomyResponse(amount, 0, ResponseType.FAILURE,"specified player doesn't exist!");
		Player player = (Player)playerOff;
		BankManagement.add(player,(int)Math.floor(amount), player);
		return new EconomyResponse(amount, BankManagement.get(player, player), ResponseType.SUCCESS, player.getName()+" credited with "+amount+"dollards");
	}

	@Override
	public EconomyResponse depositPlayer(String arg0, String arg1, double arg2) {
		return null;
	}

	@Override
	public EconomyResponse depositPlayer(OfflinePlayer arg0, String arg1, double arg2) {
		return null;
	}

	@Override
	public String format(double arg0) {
		return null;
	}

	@Override
	public int fractionalDigits() {
		return 0;
	}

	@Override
	public double getBalance(String playerName) {
		if(Bukkit.getPlayer(playerName)==null) return 0;
		Player player = Bukkit.getPlayer(playerName);
		return (double)BankManagement.get(player, player);
	}

	@Override
	public double getBalance(OfflinePlayer playerOff) {
		if((Player)playerOff==null) return 0;
		Player player = (Player)playerOff;
		return (double)BankManagement.get(player, player);
	}

	@Override
	public double getBalance(String arg0, String arg1) {
		return 0;
	}

	@Override
	public double getBalance(OfflinePlayer arg0, String arg1) {
		return 0;
	}

	@Override
	public List<String> getBanks() {
		return null;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public boolean has(String playerName, double amount) {
		if(Bukkit.getPlayer(playerName)==null)return false;
		if(BankManagement.get(Bukkit.getPlayer(playerName),Bukkit.getPlayer(playerName))>(int)Math.floor(amount)) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean has(OfflinePlayer playerOff, double amount) {
		if((Player)playerOff==null)return false;
		if(BankManagement.get((Player)playerOff,(Player)playerOff)>(int)Math.floor(amount)) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean has(String arg0, String arg1, double arg2) {
		return false;
	}

	@Override
	public boolean has(OfflinePlayer arg0, String arg1, double arg2) {
		return false;
	}

	@Override
	public boolean hasAccount(String playerName) {
		if(Bukkit.getPlayer(playerName)==null)return false;
		Player player = Bukkit.getPlayer(playerName);
		if(player.getPersistentDataContainer().has(new NamespacedKey(GetMainClass.get(),"IBE_Money"),PersistentDataType.INTEGER)){
			return true;
		}
		return false;
	}

	@Override
	public boolean hasAccount(OfflinePlayer playerOff) {
		if((Player)playerOff==null)return false;
		Player player =(Player)playerOff;
		if(player.getPersistentDataContainer().has(new NamespacedKey(GetMainClass.get(),"IBE_Money"),PersistentDataType.INTEGER)){
			return true;
		}
		return false;
	}

	@Override
	public boolean hasAccount(String arg0, String arg1) {
		return false;
	}

	@Override
	public boolean hasAccount(OfflinePlayer arg0, String arg1) {
		return false;
	}

	@Override
	public boolean hasBankSupport() {
		return false;
	}

	@Override
	public EconomyResponse isBankMember(String arg0, String arg1) {
		return null;
	}

	@Override
	public EconomyResponse isBankMember(String arg0, OfflinePlayer arg1) {
		return null;
	}

	@Override
	public EconomyResponse isBankOwner(String arg0, String arg1) {
		return null;
	}

	@Override
	public EconomyResponse isBankOwner(String arg0, OfflinePlayer arg1) {
		return null;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public EconomyResponse withdrawPlayer(String playerName, double amount) {
		if(Bukkit.getPlayer(playerName)==null)return new EconomyResponse(amount, 0, ResponseType.FAILURE, "specified player doesn't exist");
		Player player = Bukkit.getPlayer(playerName);
		BankManagement.take(player, (int)Math.floor(amount), player);
		return new EconomyResponse(amount, BankManagement.get(player,player), ResponseType.SUCCESS, playerName+" got "+amount+"taken away");
	}

	@Override
	public EconomyResponse withdrawPlayer(OfflinePlayer playerOff, double amount) {
		if((Player)playerOff==null)return new EconomyResponse(amount, 0, ResponseType.FAILURE, "specified player doesn't exist");
		Player player = (Player)playerOff;
		BankManagement.take(player, (int)Math.floor(amount), player);
		return new EconomyResponse(amount, BankManagement.get(player,player), ResponseType.SUCCESS, player.getName()+" got "+amount+"taken away");
	}

	@Override
	public EconomyResponse withdrawPlayer(String arg0, String arg1, double arg2) {
		return null;
	}

	@Override
	public EconomyResponse withdrawPlayer(OfflinePlayer arg0, String arg1, double arg2) {
		return null;
	}

}
