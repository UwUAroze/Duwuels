package me.aroze.duwuels.duels;

import me.aroze.duwuels.util.ChatUtils;
import org.bukkit.entity.Player;

public class QueueHandler {

    public static boolean addToQueue(Player player, String duelType) {

        if (duelType.equalsIgnoreCase("Sumo")) {

            if (SumoDuel.queue.contains(player.getUniqueId())) {
                SumoDuel.queue.remove(player.getUniqueId());
                player.sendMessage(ChatUtils.color("&7You have been &cremoved &7from the &eSumo &7queue!"));
                player.closeInventory();
                return false;
            }

            SumoDuel.queue.add(player.getUniqueId());
            player.sendMessage(ChatUtils.color("&7You have been &aadded &7to the &eSumo &7queue!"));
            player.closeInventory();
            // queue/game logic
            SumoDuel.start();
            return true;

        }

        return false;
    }
}