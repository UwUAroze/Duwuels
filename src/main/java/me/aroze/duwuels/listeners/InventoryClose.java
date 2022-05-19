package me.aroze.duwuels.listeners;

import me.aroze.duwuels.Duwuels;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryClose implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (e.getPlayer().hasMetadata("duelGui")){
            e.getPlayer().removeMetadata("duelGui", Duwuels.getInstance());
        }
    }

}
