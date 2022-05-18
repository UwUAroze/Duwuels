package me.aroze.duwuels.commands;

import me.aroze.duwuels.util.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DuelCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length > 1) {
            sender.sendMessage(ChatUtils.color("\n &6⚠ &eDuels &6» &eSyntax:\n  &f/duel &7[Public Queues] \n  &f/duel <player> &7[Private Duel]\n "));
            return true;
        }

        return true;
    }
}
