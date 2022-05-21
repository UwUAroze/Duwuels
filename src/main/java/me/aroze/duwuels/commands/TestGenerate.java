package me.aroze.duwuels.commands;

import org.bukkit.Bukkit;
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
        for (int a = 0; a <= 8; a++) {
            for (int b = 0; b <= 8; b++) {
                player.getWorld().getBlockAt(start.clone().add(a, 0, b)).setType(Material.WHITE_CONCRETE);
            }
        }

        Location start2 = middle.clone().add(5, 0, 3);
            for (int a = 0; a < 5; a++) {
                player.getWorld().getBlockAt(start2.clone().add(0, 0, a)).setType(Material.LIME_CONCRETE);
            }




        return true;
    }
}
