package me.aroze.duwuels.duels;

import org.bukkit.*;
import org.bukkit.entity.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class SumoDuel {

    public static ArrayList<UUID> queue = new ArrayList<>();

    // Integer refers to arena number
    public static HashMap<Integer, ArrayList<UUID>> playing = new HashMap<>();

    public static boolean start() {
        if (!(queue.size() % 2 == 0)) return false;

        Player Player1 = Bukkit.getPlayer(queue.get(0));
        Player Player2 = Bukkit.getPlayer(queue.get(1));

        queue.remove(Player1.getUniqueId());
        queue.remove(Player2.getUniqueId());

        playing.put(1, new ArrayList<UUID>(){
            {
                add(Player1.getUniqueId());
                add(Player2.getUniqueId());
            }
        } );

        Player1.setGameMode(GameMode.SPECTATOR);
        Player2.setGameMode(GameMode.SPECTATOR);

        Bukkit.broadcastMessage("a");

        World arenaWorld = Player1.getWorld();
        Location arenaMiddle = new Location(Player1.getWorld(), 500000, 64, 500000);

        Bukkit.broadcastMessage("b");

        new Thread(() -> {
            while (!(arenaMiddle.getBlock().getType().isAir())) {
                arenaMiddle.add(500, 0, 0);
            }
        }).start();

        Bukkit.broadcastMessage("c");

        Location loc1 = arenaMiddle.clone().add(5, 0, -1);
        loc1.setYaw(90);
        loc1.setPitch(0);

        Location loc2 = arenaMiddle.clone().add(-5, 0, 1);
        loc2.setYaw(-90);
        loc2.setPitch(0);

        Player1.teleport(loc1);
        Player2.teleport(loc2);

        Bukkit.broadcastMessage("d");

        Location start = arenaMiddle.clone().add(-4, 0, -4);


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


        return true;
    }

}
