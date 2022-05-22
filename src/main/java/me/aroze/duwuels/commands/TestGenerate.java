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

        middle.clone().add(0,-2,0).getBlock().setType(Material.BEDROCK);





        return true;
    }
}
