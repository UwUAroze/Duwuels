package me.aroze.duwuels.duels;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class SumoDuel {

    public static ArrayList<UUID> queue = new ArrayList<>();

    public static boolean start() {
        if (!(queue.size() % 2 == 0)) return false;

        Player Player1 = Bukkit.getPlayer(queue.get(0));
        Player Player2 = Bukkit.getPlayer(queue.get(1));

        queue.remove(Player1.getUniqueId());
        queue.remove(Player2.getUniqueId());

        Player1.teleport(new Location(Bukkit.getWorld("Duel-Sumo-1"), 5,65,0));
        Player2.teleport(new Location(Bukkit.getWorld("Duel-Sumo-1"), -5,65,0));

        return true;
    }

}
