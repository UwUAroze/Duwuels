package me.aroze.duwuels.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.util.Vector;

public class Join implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(100);

        Location spawnLoc = Bukkit.getWorld("originallobby").getSpawnLocation();
        spawnLoc.setYaw(135);
        spawnLoc.setPitch(0);

    }

}
