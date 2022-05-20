package me.aroze.duwuels;

import me.aroze.duwuels.commands.DuelCommand;
import me.aroze.duwuels.listeners.EntityDamageByEntity;
import me.aroze.duwuels.listeners.InventoryClick;
import me.aroze.duwuels.listeners.InventoryClose;
import org.bukkit.plugin.java.JavaPlugin;

public final class Duwuels extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("duel").setExecutor(new DuelCommand());

        getServer().getPluginManager().registerEvents(new InventoryClick(), this);
        getServer().getPluginManager().registerEvents(new InventoryClose(), this);
        getServer().getPluginManager().registerEvents(new EntityDamageByEntity(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Duwuels getInstance() {
        return getPlugin(Duwuels.class);
    }

}