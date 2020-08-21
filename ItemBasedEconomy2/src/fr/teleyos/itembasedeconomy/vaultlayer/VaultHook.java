package fr.teleyos.itembasedeconomy.vaultlayer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;

import fr.teleyos.itembasedeconomy.GetMainClass;
import fr.teleyos.itembasedeconomy.ItemBasedEconomy;
import net.milkbowl.vault.economy.Economy;

public class VaultHook {
	private ItemBasedEconomy main = GetMainClass.get();
	private Economy provider;
	public void hook() {
		provider = main.economy;
		Bukkit.getServicesManager().register(Economy.class, this.provider, this.main, ServicePriority.High);
		Bukkit.getConsoleSender().sendMessage("VaultHooked");
	}
	public void unhook() {
		Bukkit.getServicesManager().unregister(Economy.class, this.provider);
		Bukkit.getConsoleSender().sendMessage("Vault unHooked");
	}
}
