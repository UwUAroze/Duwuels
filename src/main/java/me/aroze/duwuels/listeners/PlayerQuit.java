package me.aroze.duwuels.listeners;

import me.aroze.duwuels.duels.SumoDuel;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        SumoDuel.queue.remove(e.getPlayer().getUniqueId());
    }

}
