package me.aroze.duwuels.commands;

import me.aroze.duwuels.Duwuels;
import me.aroze.duwuels.util.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeleteAllArenas implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        if (!sender.hasPermission("duwuels.admin")) {
            sender.sendMessage(ChatUtils.color("&c⚠ &7You aren't allowed to do this! smh!"));
            return true;
        }

        player.sendMessage(ChatUtils.color("&c⚠ &7Deleting all arenas..."));

        Location arenaMiddle = new Location(player.getWorld(), 500000, 64, 500000);

        Bukkit.getScheduler().runTask(Duwuels.getInstance(), () -> continueSynchronously(arenaMiddle, player));

        return true;
    }

    public static void continueSynchronously(Location arenaMiddle, Player player) {

        for (int i = 0; i <= 100; i++) {

            Location block = arenaMiddle.clone().add(i * 500, 0, 0);
            player.sendMessage(ChatUtils.color("&7⚠ Checking arena location #" + i));

            if (!(block.getBlock().getType().isAir())) {

                player.sendMessage(ChatUtils.color("&c⚠ &7Found arena: &a#" + i));

                int finalI = i;

                Bukkit.getScheduler().runTask(Duwuels.getInstance(), () ->
                player.sendMessage(ChatUtils.color("&c⚠ &7Started deletion of arena: &a#" + finalI + " &7[" + block.getX() + ", " + block.getY() + ", " + block.getZ() + "&7]")));

                Location start = block.clone().add(-8, 0, -8);

                for (int y = 0; y <= 1; y++) {
                    for (int x = 0; x <= 20; x++) {
                        for (int z = 0; z <= 20; z++) {
                            start.clone().add(x, y, z).getBlock().setType(Material.AIR);
                        }
                    }
                }

                player.sendMessage(ChatUtils.color("&a⚠ &aFinished deletion of arena: &a#" + finalI));

            }
        }
    }
}
