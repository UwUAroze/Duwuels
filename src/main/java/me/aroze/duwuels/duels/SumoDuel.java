package me.aroze.duwuels.duels;

import me.aroze.duwuels.Duwuels;
import me.aroze.duwuels.util.ChatUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class SumoDuel {

    public static ArrayList<UUID> queue = new ArrayList<>();

    // Integer refers to arena number
    public static HashMap<Integer, ArrayList<UUID>> playing = new HashMap<>();
    public static boolean start() {
        if (!(queue.size() % 2 == 0)) return false;

        Player player1 = Bukkit.getPlayer(queue.get(0));
        Player player2 = Bukkit.getPlayer(queue.get(1));

        queue.remove(player1.getUniqueId());
        queue.remove(player2.getUniqueId());

        playing.put(1, new ArrayList<UUID>(){
            {
                add(player1.getUniqueId());
                add(player2.getUniqueId());
            }
        } );

        player1.setGameMode(GameMode.SPECTATOR);
        player2.setGameMode(GameMode.SPECTATOR);

        Location arenaMiddle = new Location(player1.getWorld(), 500000, 64, 500000);

        Bukkit.getScheduler().runTaskAsynchronously(Duwuels.getInstance(), () -> {
            while (!(arenaMiddle.getBlock().getType().isAir())) {
                arenaMiddle.add(500, 0, 0);
            }
            Bukkit.getScheduler().runTask(Duwuels.getInstance(), () -> continueSynchronously(arenaMiddle, player1, player2));
        });


        return true;
    }

    public static void continueSynchronously(Location arenaMiddle, Player player1, Player player2) {

        Location loc1 = arenaMiddle.clone().add(5, 2, -1);
        loc1.setYaw(90);
        loc1.setPitch(0);

        Location loc2 = arenaMiddle.clone().add(-5, 2, 1);
        loc2.setYaw(-90);
        loc2.setPitch(0);

        player1.teleport(loc1);
        player2.teleport(loc2);

        player1.sendTitle(ChatUtils.color("&7Found an opponent: &c" + player2.getName()), ChatUtils.color("&7The game will begin shortly. &aGood Luck!"), 5, 20, 10);
        player2.sendTitle(ChatUtils.color("&7Found an opponent: &c" + player1.getName()), ChatUtils.color("&7The game will begin shortly. &aGood Luck!"), 5, 20, 10);

        Bukkit.getScheduler().runTaskLater(Duwuels.getInstance(), () -> {
            player1.sendTitle(ChatUtils.color("&7Starting in &63..."), ChatUtils.color("&7Opponent: &c" + player2.getName()), 5, 30, 0);
            player2.sendTitle(ChatUtils.color("&7Starting in &63..."), ChatUtils.color("&7Opponent: &c" + player1.getName()), 5, 30, 0);
        }, 35);

        Bukkit.getScheduler().runTaskLater(Duwuels.getInstance(), () -> {
            player1.sendTitle(ChatUtils.color("&7Starting in &e2..."), ChatUtils.color("&7Opponent: &c" + player2.getName()), 0, 30, 0);
            player2.sendTitle(ChatUtils.color("&7Starting in &e2..."), ChatUtils.color("&7Opponent: &c" + player1.getName()), 0, 30, 0);
        }, 60);

        Bukkit.getScheduler().runTaskLater(Duwuels.getInstance(), () -> {
            player1.sendTitle(ChatUtils.color("&7Starting in &c1..."), ChatUtils.color("&7Opponent: &c" + player2.getName()), 0, 10, 10);
            player2.sendTitle(ChatUtils.color("&7Starting in &c1..."), ChatUtils.color("&7Opponent: &c" + player1.getName()), 0, 10, 10);
        }, 85);

        Bukkit.getScheduler().runTaskLater(Duwuels.getInstance(), () -> {
            player1.teleport(loc1);
            player2.teleport(loc2);
            player1.setGameMode(GameMode.ADVENTURE);
            player2.setGameMode(GameMode.ADVENTURE);
        }, 110);

        Location start = arenaMiddle.clone().add(-4, 0, -4);
        World arenaWorld = arenaMiddle.getWorld();


    AtomicInteger delay = new AtomicInteger();
        for (double x = 0; x <= 8; x++) {
            for (double z = 0; z <= 8; z++) {
                double finalX = x+0.5;
                double finalZ = z+0.5;
                delay.getAndIncrement();
                Bukkit.getScheduler().runTaskLater(Duwuels.getInstance(), () -> {
                    arenaWorld.getBlockAt(start.clone().add(finalX, 0, finalZ)).setType(Material.BARRIER);
                    arenaWorld.spawnFallingBlock(start.clone().add(finalX, 20, finalZ), Material.PINK_STAINED_GLASS.createBlockData());
                }, delay.get());
            }
        }

        delay = new AtomicInteger();
        Location start2 = arenaMiddle.clone().add(-3, 0, -5);
        for (int z = 0; z <= 10; z=z+10) {
            for (int x = 0; x < 7; x++) {
                double finalX = x+0.5;
                double finalZ = z+0.5;
                delay.getAndIncrement();
                Bukkit.getScheduler().runTaskLater(Duwuels.getInstance(), () -> {
                    arenaWorld.getBlockAt(start2.clone().add(finalX, 0, finalZ)).setType(Material.BARRIER);
                    arenaWorld.spawnFallingBlock(start2.clone().add(finalX, 20, finalZ), Material.PINK_STAINED_GLASS.createBlockData());
                }, 2L * delay.get());
            }
        }

        delay = new AtomicInteger();
        Location start3 = arenaMiddle.clone().add(-5, 0, -3);
        for (int x = 0; x <= 10; x=x+10) {
            for (int z = 0; z < 7; z++) {
                double finalX = x+0.5;
                double finalZ = z+0.5;
                delay.getAndIncrement();
                Bukkit.getScheduler().runTaskLater(Duwuels.getInstance(), () -> {
                    arenaWorld.getBlockAt(start3.clone().add(finalX, 0, finalZ)).setType(Material.BARRIER);
                    arenaWorld.spawnFallingBlock(start3.clone().add(finalX, 20, finalZ), Material.PINK_STAINED_GLASS.createBlockData());
                }, 2L * delay.get());
            }
        }
    }

}
