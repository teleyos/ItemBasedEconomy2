package fr.teleyos.itembasedeconomy;

import net.milkbowl.vault.economy.Economy;

public class GetMainClass {
	
	private static ItemBasedEconomy main;
	private static Economy economy;
	
	public GetMainClass(ItemBasedEconomy mainClass) {
		main = mainClass;
	}
	public GetMainClass(Economy econ) {
		economy = econ;
	}
	
	public static ItemBasedEconomy get() {
		return main;
	}
	public static Economy getEconomy() {
		return economy;
	}
	
	

}
