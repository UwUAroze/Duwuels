package me.aroze.duwuels.listeners;

import me.aroze.duwuels.commands.DuelCommand;
import me.aroze.duwuels.handlers.DuelQueue;
import me.aroze.duwuels.util.ChatUtils;
import org.bukkit.Bukkit;
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
                if (DuelQueue.qSumo.contains(e.getWhoClicked().getUniqueId())) {
                    DuelQueue.qSumo.remove(e.getWhoClicked().getUniqueId());
                    e.getWhoClicked().sendMessage(ChatUtils.color("&eYou have been &cremoved &7from the &eSumo &7queue!"));
                    return;
                }
                DuelQueue.qSumo.add(e.getWhoClicked().getUniqueId());
                e.getWhoClicked().sendMessage(ChatUtils.color("&7You have been &aadded &7to the &eSumo &7queue!"));
                // queue/game logic
                return;
            }
        }

    }

}
