package me.aroze.duwuels.listeners;

import me.aroze.duwuels.Duwuels;
import me.aroze.duwuels.util.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

public class Join implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(100);

        Location spawnLoc = Bukkit.getWorld("originallobby").getSpawnLocation();
        spawnLoc.setYaw(135);
        spawnLoc.setPitch(0);
        e.getPlayer().teleport(spawnLoc);

        BukkitTask yDeathCheck = Bukkit.getScheduler().runTaskTimer(Duwuels.getInstance(), () -> {
            double y = e.getPlayer().getLocation().getY();
            if ( e.getPlayer().getWorld().getName().equals("Duel-Sumo-1") && y < 65 && e.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
                e.getPlayer().setGameMode(GameMode.SPECTATOR);
                e.getPlayer().sendTitle(ChatUtils.color("&eYou died!"), ChatUtils.color("&6lol what an idiot"),10,60,10);
                Bukkit.getScheduler().runTaskLater(Duwuels.getInstance(), () -> {
                    e.getPlayer().setGameMode(GameMode.ADVENTURE);
                    e.getPlayer().teleport(spawnLoc);
                }, 60);
            }
        } , 0, 1);

    }

}
