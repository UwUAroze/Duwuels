package me.aroze.duwuels.listeners;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {

    public void onJoin(PlayerJoinEvent e) {
        e.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(100);
        e.getPlayer().teleport(Bukkit.getWorld("originallobby").getSpawnLocation());
    }

}
