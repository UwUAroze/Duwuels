package me.aroze.duwuels.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlaceBreak implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (!(e.getPlayer().hasPermission("duwuels.build"))) {
            e.setCancelled(true);
            e.getPlayer().sendMessage("§c⚠ &7Smh! You can't break blocks!");
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if (!(e.getPlayer().hasPermission("duwuels.build"))) {
            e.setCancelled(true);
            e.getPlayer().sendMessage("§c⚠ &7Smh! You can't place blocks!");
        }
    }

}
