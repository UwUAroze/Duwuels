package me.aroze.duwuels.listeners;

import me.aroze.duwuels.Duwuels;
import me.aroze.duwuels.duels.SumoDuel;
import me.aroze.duwuels.util.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
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


        Location spawnLoc = Bukkit.getWorld("lobby").getSpawnLocation();
        spawnLoc.setYaw(135);
        spawnLoc.setPitch(0);
        e.getPlayer().teleport(spawnLoc);

        BukkitTask yDeathCheck = Bukkit.getScheduler().runTaskTimer(Duwuels.getInstance(), () -> {
            double y = e.getPlayer().getLocation().getY();
            if ( y < 65 && !(e.getPlayer().getGameMode().equals(GameMode.SPECTATOR))) {

                Player loser = null;
                Player winner = null;
                Integer arenaNum = null;

                for (Integer key : SumoDuel.playing.keySet()) {
                    if (SumoDuel.playing.get(key).contains(e.getPlayer().getUniqueId())) {
                        loser = e.getPlayer();
                        SumoDuel.playing.get(key).remove(loser.getUniqueId());
                        winner = Bukkit.getPlayer(SumoDuel.playing.get(key).get(0));
                        SumoDuel.playing.get(key).remove(winner.getUniqueId());
                        arenaNum = key;
                    }
                }

                if (loser == null || winner == null) return;

                loser.setGameMode(GameMode.SPECTATOR);
                winner.setGameMode(GameMode.SPECTATOR);

                loser.sendTitle(ChatUtils.color("&cYou died!"), ChatUtils.color("&7Better luck next time :p"), 5, 15, 5);
                Bukkit.getScheduler().runTaskLater(Duwuels.getInstance(), () -> {
                    e.getPlayer().setGameMode(GameMode.ADVENTURE);
                    e.getPlayer().teleport(spawnLoc);
                }, 25);

                winner.sendTitle(ChatUtils.color("&aYou won!"), ChatUtils.color("&7good job."), 5, 15, 5);
                Player finalWinner = winner;
                Bukkit.getScheduler().runTaskLater(Duwuels.getInstance(), () -> {
                    finalWinner.setGameMode(GameMode.ADVENTURE);
                    finalWinner.teleport(spawnLoc);
                }, 25);

                Location arenaMiddle = new Location(winner.getWorld(), (500*arenaNum) + 500000, 64, 500000);
                Location start = arenaMiddle.clone().add(-8, 0, -8);

                for (int y2 = 0; y2 <= 1; y2++) {
                    for (int x = 0; x <= 20; x++) {
                        for (int z = 0; z <= 20; z++) {
                            start.clone().add(x, y2, z).getBlock().setType(Material.AIR);
                        }
                    }
                }



            }
        } , 0, 1);

    }

}
