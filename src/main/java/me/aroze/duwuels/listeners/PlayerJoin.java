package me.aroze.duwuels.listeners;

import me.aroze.duwuels.Duwuels;
import me.aroze.duwuels.duels.SumoDuel;
import me.aroze.duwuels.util.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitTask;

import java.util.UUID;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(100);


        Location spawnLoc = Bukkit.getWorld("originallobby").getSpawnLocation();
        spawnLoc.setYaw(135);
        spawnLoc.setPitch(0);
        e.getPlayer().teleport(spawnLoc);

        BukkitTask yDeathCheck = Bukkit.getScheduler().runTaskTimer(Duwuels.getInstance(), () -> {
            double y = e.getPlayer().getLocation().getY();
            if ( e.getPlayer().getWorld().getName().equals("Duel-Sumo-1") && y < 65 && SumoDuel.playing.get(1).contains(e.getPlayer().getUniqueId())) {

                Bukkit.broadcastMessage("a");

                Player loser = e.getPlayer();
                SumoDuel.playing.get(1).remove(loser.getUniqueId());

                Player winner = Bukkit.getPlayer(SumoDuel.playing.get(1).get(0));
                SumoDuel.playing.get(1).remove(winner.getUniqueId());

                loser.setGameMode(GameMode.SPECTATOR);
                winner.setGameMode(GameMode.SPECTATOR);

                Bukkit.broadcastMessage("b");

                loser.sendTitle(ChatUtils.color("&cYou died!"), ChatUtils.color("&7Better luck next time :p"),5,15,5);
                Bukkit.getScheduler().runTaskLater(Duwuels.getInstance(), () -> {
                    e.getPlayer().setGameMode(GameMode.ADVENTURE);
                    e.getPlayer().teleport(spawnLoc);
                }, 25);

                winner.sendTitle(ChatUtils.color("&aYou won!"), ChatUtils.color("&7good job."),5,15,5);
                Bukkit.getScheduler().runTaskLater(Duwuels.getInstance(), () -> {
                    winner.setGameMode(GameMode.ADVENTURE);
                    winner.teleport(spawnLoc);
                }, 25);

            }
        } , 0, 1);

    }

}
