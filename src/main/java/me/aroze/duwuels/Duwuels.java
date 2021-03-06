package me.aroze.duwuels;

import me.aroze.duwuels.commands.DeleteAllArenas;
import me.aroze.duwuels.commands.DuelCommand;
import me.aroze.duwuels.commands.TestForceload;
import me.aroze.duwuels.commands.TestGenerate;
import me.aroze.duwuels.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Duwuels extends JavaPlugin {

    public static Location spawnLoc;

    @Override
    public void onEnable() {

        spawnLoc = Bukkit.getWorld("lobby").getSpawnLocation();

        spawnLoc.setYaw(135);
        spawnLoc.setPitch(0);


        addCommand("duel", new DuelCommand());
        addCommand("gen", new TestGenerate());
        addCommand("deleteallarenas", new DeleteAllArenas());
        addCommand("testforceload", new TestForceload());

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