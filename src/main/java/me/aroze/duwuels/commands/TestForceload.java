package me.aroze.duwuels.commands;

import me.aroze.duwuels.Duwuels;
import me.aroze.duwuels.util.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestForceload implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if (!sender.hasPermission("duwuels.admin")) {
            sender.sendMessage(ChatUtils.color("&c⚠ &7You aren't allowed to do this! smh!"));
            return true;
        }

        Location arenaMiddle = new Location(player.getWorld(), 500000, 64, 500000);

            for (int i = 0; i < Integer.parseInt(args[0]); i++) {
                arenaMiddle.add(500, 0, 0);
                if ( !(arenaMiddle.getChunk().isForceLoaded()) ) {
                    arenaMiddle.getChunk().load();
                    arenaMiddle.getChunk().setForceLoaded(true);
                    arenaMiddle.getChunk().load();
                    sender.sendMessage(ChatUtils.color("&cLoaded chunk at " + arenaMiddle.getX()));
                }
            }

        return true;
    }

    public static void continueSynchronously(Location arenaMiddle) {

        if (arenaMiddle.getChunk().isLoaded()) return;

        arenaMiddle.getChunk().load();
        arenaMiddle.getChunk().setForceLoaded(true);
    }

}
