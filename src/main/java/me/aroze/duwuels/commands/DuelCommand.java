package me.aroze.duwuels.commands;

import me.aroze.duwuels.util.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class DuelCommand implements CommandExecutor {

    static ArrayList<UUID> qSumo = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length > 1) {
            sender.sendMessage(ChatUtils.color("\n &6⚠ &eDuels &7» &eSyntax:\n  &f/duel &7[Public Duel Queues] \n  &f/duel <player> &7[Private Duel]\n "));
            return true;
        }

        if (args.length == 1) {
            if (!(Bukkit.getOfflinePlayer(args[0]).isOnline())) {
                sender.sendMessage(ChatUtils.color("\n &6⚠ &eDuels &7» &eSyntax:\n  &f/duel &7[Public Duel Queues] \n  &f/duel <player> &7[Private Duel]\n "));
                sender.sendMessage(ChatUtils.color("\n &7&oYou seem to have specified a player: &e&o" + args[0] + "&7&o,\n &7&oBut they are &e&onot online &7&o;c\n "));
                return true;
            }
            if (sender.getName().equalsIgnoreCase(args[0])) {
                sender.sendMessage(ChatUtils.color("\n &6⚠ &eDuels &7» &eSyntax:\n  &f/duel &7[Public Duel Queues] \n  &f/duel <player> &7[Private Duel]\n "));
                sender.sendMessage(ChatUtils.color("\n &7&oYou didd technicallyyy follow the syntax...\n &7&oBut you cannot duel &e&oyourself&7, &7&oself harm is &e&oNOT the answer&7&o!\n "));
                return true;
            }
            // private duel code
        }

        return true;
    }
}
