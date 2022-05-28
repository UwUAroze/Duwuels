package me.aroze.duwuels.listeners;

import me.aroze.duwuels.commands.DuelCommand;
import me.aroze.duwuels.duels.QueueHandler;
import me.aroze.duwuels.duels.SumoDuel;
import me.aroze.duwuels.util.ChatUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        if (e.getWhoClicked().hasMetadata("duelGui")) {
            DuelCommand.queueGUI((Player) e.getWhoClicked());
            e.setCancelled(true);
            if (e.getSlot() == 4) {
                QueueHandler.addToQueue((Player) e.getWhoClicked(), "Sumo");
            }
        }

    }

}
