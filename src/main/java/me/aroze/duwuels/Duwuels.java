package me.aroze.duwuels;

import me.aroze.duwuels.commands.DuelCommand;
import me.aroze.duwuels.listeners.*;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Duwuels extends JavaPlugin {

    @Override
    public void onEnable() {

        addCommand("duel", new DuelCommand());

        addListener(new InventoryClick());
        addListener(new InventoryClose());
        addListener(new EntityDamageByEntity());
        addListener(new PlayerJoin());
        addListener(new PlayerQuit());
        addListener(new PlaceBreak());

    }

    @Override
    public void onDisable() {
        // Soon:tm:
    }

    public static Duwuels getInstance() {
        return getPlugin(Duwuels.class);
    }

    private void addCommand(String name, CommandExecutor executor) {
        getCommand(name).setExecutor(executor);
    }

    private void addListener(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }

}