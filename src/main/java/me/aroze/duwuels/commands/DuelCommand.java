package me.aroze.duwuels.commands;

import me.aroze.duwuels.util.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DuelCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length > 1) {
            sender.sendMessage(ChatUtils.color("&c⚠ Syntax:\n &7/duel &8[Public queues] \n &7/duel <player> &8[private duel]"));
        }

        return true;
    }
}
