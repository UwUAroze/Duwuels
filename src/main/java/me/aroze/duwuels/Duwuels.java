package me.aroze.duwuels;

import me.aroze.duwuels.commands.DuelCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Duwuels extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("duel").setExecutor(new DuelCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
