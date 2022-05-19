package me.aroze.duwuels.listeners;

import me.aroze.duwuels.commands.DuelCommand;
import me.aroze.duwuels.util.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick implements Listener {

    public void onInventoryClick(InventoryClickEvent e) {

        if (e.getView().getTitle().equals(ChatUtils.color("&6Duels &7&o» &ePublic Queues"))) {
            DuelCommand.queueGUI((Player) e.getWhoClicked());
            Bukkit.broadcastMessage("a");
        }

    }

}
