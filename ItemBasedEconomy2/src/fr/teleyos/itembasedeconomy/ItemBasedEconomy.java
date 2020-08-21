package fr.teleyos.itembasedeconomy;

import org.bukkit.plugin.java.JavaPlugin;

import fr.teleyos.itembasedeconomy.commands.ItemBasedEconomyCommands;
import fr.teleyos.itembasedeconomy.commands.ItemBasedEconomyCompleter;
import fr.teleyos.itembasedeconomy.listeners.ItemBasedEconomyListeners;
import fr.teleyos.itembasedeconomy.money.MoneyItems;
import fr.teleyos.itembasedeconomy.vaultlayer.EconomyImplementer;
import fr.teleyos.itembasedeconomy.vaultlayer.VaultHook;
import net.milkbowl.vault.economy.Economy;

public class ItemBasedEconomy extends JavaPlugin{
	
	public Economy economy = null;
	private VaultHook vaultHook;
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		economy = new EconomyImplementer();
		new GetMainClass(this);
		new GetMainClass(economy);
		vaultHook = new VaultHook();
		vaultHook.hook();
		MoneyItems.setAllFromConfig();
		getServer().getPluginManager().registerEvents(new ItemBasedEconomyListeners(), this);
		getServer().getPluginCommand("money").setExecutor(new ItemBasedEconomyCommands());
		getServer().getPluginCommand("withdraw").setExecutor(new ItemBasedEconomyCommands());
		getServer().getPluginCommand("deposit").setExecutor(new ItemBasedEconomyCommands());
		getServer().getPluginCommand("putshop").setExecutor(new ItemBasedEconomyCommands());
		getServer().getPluginCommand("putshop").setTabCompleter(new ItemBasedEconomyCompleter());
		System.out.println("[ItemBasedEconomy] Plugin Launched.");
	}

	@Override
	public void onDisable() {
		vaultHook.unhook();
		System.out.println("[ItemBasedEconomy] Plugin Stopped.");
	}
	
	
}
