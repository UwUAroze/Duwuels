package me.aroze.duwuels.commands;

import me.aroze.duwuels.Duwuels;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestGenerate implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        Location middle = player.getLocation();


        Location start = middle.clone().add(4, 0, 4);


        for (int x = 0; x <= 8; x++) {
            for (int z = 0; z <= 8; z++) {
                player.getWorld().getBlockAt(start.clone().add(x, 0, z)).setType(Material.BARRIER);
                player.getWorld().spawnFallingBlock(start.clone().add(x, 15, z), Material.PINK_STAINED_GLASS, (byte) 0);
            }
        }

        Location start2 = middle.clone().add(5, 0, 3);
        for (int z = 0; z <= 10; z=z+10) {
            for (int x = 0; x < 7; x++) {
                player.getWorld().getBlockAt(start2.clone().add(x, 0, z)).setType(Material.BARRIER);
                player.getWorld().spawnFallingBlock(start2.clone().add(x, 15, z), Material.PINK_STAINED_GLASS, (byte) 0);
            }
        }

        Location start3 = middle.clone().add(3, 0, 5);
        for (int x = 0; x <= 10; x=x+10) {
            for (int z = 0; z < 7; z++) {
                player.getWorld().getBlockAt(start3.clone().add(x, 0, z)).setType(Material.BARRIER);
                player.getWorld().spawnFallingBlock(start3.clone().add(x, 15, z), Material.PINK_STAINED_GLASS, (byte) 0);
            }
        }





        return true;
    }
}
