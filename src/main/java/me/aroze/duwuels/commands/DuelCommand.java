package me.aroze.duwuels.commands;

import me.aroze.duwuels.util.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DuelCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length > 1) {
            sender.sendMessage(ChatUtils.color("\n &6⚠ &eDuels &7» &eSyntax:\n  &f/duel &7[Public Duel Queues] \n  &f/duel <player> &7[Private Duel]\n "));
            return true;
        }

        if (args.length == 1) {
            if (!(Bukkit.getOfflinePlayer(args[0]).isOnline())) {
                sender.sendMessage(ChatUtils.color("\n &6⚠ &eDuels &7» &eSyntax:\n  &f/duel &7[Public Duel Queues] \n  &f/duel <player> &7[Private Duel]\n "));
                sender.sendMessage(ChatUtils.color("\n &7You seem to have specified a player: &e " + args[0] + "&7,\n &7But they are &enot online &7;c\n "));
                return true;
            }
            if (sender.getName().equalsIgnoreCase(args[0])) {
                sender.sendMessage(ChatUtils.color("\n &6⚠ &eDuels &7» &eSyntax:\n  &f/duel &7[Public Duel Queues] \n  &f/duel <player> &7[Private Duel]\n "));
                sender.sendMessage(ChatUtils.color("\n &7You didd technicallyyy follow the syntax...\n &7But you cannot duel &eyourself&7, &7self harm is &eNOT the answer&7!\n "));
                return true;
            }
        }

        return true;
    }
}
