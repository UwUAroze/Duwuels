package me.aroze.duwuels.duels;

import me.aroze.duwuels.Duwuels;
import me.aroze.duwuels.util.ChatUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

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

        Location loc1 = arenaMiddle.clone().add(5, 0, -1);
        loc1.setYaw(90);
        loc1.setPitch(0);

        Location loc2 = arenaMiddle.clone().add(-5, 0, 1);
        loc2.setYaw(-90);
        loc2.setPitch(0);

        player1.teleport(loc1);
        player2.teleport(loc2);

        player1.sendTitle(ChatUtils.color("&7Found an opponent: &c" + player2.getName()), ChatUtils.color("&7The game will begin shortly. &aGood Luck!"), 5, 10, 5);
        player2.sendTitle(ChatUtils.color("&7Found an opponent: &c" + player1.getName()), ChatUtils.color("&7The game will begin shortly. &aGood Luck!"), 5, 10, 5);

        Bukkit.getScheduler().runTaskLater(Duwuels.getInstance(), () -> {
            player1.sendTitle(ChatUtils.color("&7Starting in &63..."), ChatUtils.color("&7Opponent: &c" + player2.getName()), 5, 20, 0);
            player2.sendTitle(ChatUtils.color("&7Starting in &63..."), ChatUtils.color("&7Opponent: &c" + player1.getName()), 5, 20, 0);
        }, 20);

        Bukkit.getScheduler().runTaskLater(Duwuels.getInstance(), () -> {
            player1.sendTitle(ChatUtils.color("&7Starting in &e2..."), ChatUtils.color("&7Opponent: &c" + player2.getName()), 0, 20, 0);
            player2.sendTitle(ChatUtils.color("&7Starting in &e2..."), ChatUtils.color("&7Opponent: &c" + player1.getName()), 0, 20, 0);
        }, 35);

        Bukkit.getScheduler().runTaskLater(Duwuels.getInstance(), () -> {
            player1.sendTitle(ChatUtils.color("&7Starting in &c1..."), ChatUtils.color("&7Opponent: &c" + player2.getName()), 0, 5, 5);
            player2.sendTitle(ChatUtils.color("&7Starting in &c1..."), ChatUtils.color("&7Opponent: &c" + player1.getName()), 0, 5, 5);
        }, 50);

        Location start = arenaMiddle.clone().add(-4, 0, -4);
        World arenaWorld = arenaMiddle.getWorld();

        for (int x = 0; x <= 8; x++) {
            for (int z = 0; z <= 8; z++) {
                arenaWorld.getBlockAt(start.clone().add(x, 0, z)).setType(Material.BARRIER);
                arenaWorld.spawnFallingBlock(start.clone().add(x, 15, z), Material.PINK_STAINED_GLASS, (byte) 0);
            }
        }

        Location start2 = arenaMiddle.clone().add(-3, 0, -5);
        for (int z = 0; z <= 10; z=z+10) {
            for (int x = 0; x < 7; x++) {
                arenaWorld.getBlockAt(start2.clone().add(x, 0, z)).setType(Material.BARRIER);
                arenaWorld.spawnFallingBlock(start2.clone().add(x, 15, z), Material.PINK_STAINED_GLASS, (byte) 0);
            }
        }

        Location start3 = arenaMiddle.clone().add(-5, 0, -3);
        for (int x = 0; x <= 10; x=x+10) {
            for (int z = 0; z < 7; z++) {
                arenaWorld.getBlockAt(start3.clone().add(x, 0, z)).setType(Material.BARRIER);
                arenaWorld.spawnFallingBlock(start3.clone().add(x, 15, z), Material.PINK_STAINED_GLASS, (byte) 0);
            }
        }
    }

}
