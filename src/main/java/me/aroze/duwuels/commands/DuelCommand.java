package me.aroze.duwuels.commands;

import me.aroze.duwuels.Duwuels;
import me.aroze.duwuels.duels.QueueHandler;
import me.aroze.duwuels.duels.SumoDuel;
import me.aroze.duwuels.util.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.Arrays;


public class DuelCommand implements CommandExecutor {

    public static void queueGUI(Player p) {
        Inventory inv = Bukkit.createInventory(p, 9, ChatUtils.color("&cDuels &7&o» &cPublic Queues"));
        ItemStack sumo = new ItemStack(Material.SLIME_BALL);
        ItemMeta sumoMeta = sumo.getItemMeta();
        sumoMeta.setDisplayName(ChatUtils.color("&cSumo"));
        sumoMeta.setLore(Arrays.asList("\n", ChatUtils.color("&7Playing: &cum"), ChatUtils.color("&7Queued: &c" + SumoDuel.queue.size()), "\n\n&e&oClick here to join the queue"));
        sumo.setItemMeta(sumoMeta);
        inv.setItem(4, sumo);

        p.openInventory(inv);
        p.setMetadata("duelGui", new FixedMetadataValue(Duwuels.getInstance(), true));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        for (Integer key : SumoDuel.playing.keySet()) {
            if (SumoDuel.playing.get(key).contains(((Player) sender).getUniqueId())) {
                sender.sendMessage(ChatUtils.color("&c⚠ &7You're already in a duel!"));
                return true;
            }
        }


        if (args.length > 1) {
            sender.sendMessage(ChatUtils.color("\n &6⚠ &eDuels &7» &eSyntax:\n  &f/duel &7[Public Duel Queues] \n  &f/duel <player> &7[Private Duel]\n "));
            return true;
        }

        if (args.length == 1) {

            if (args[0].equalsIgnoreCase("sumo")) {
                QueueHandler.addToQueue(((Player) sender), args[0]);
                return true;
            }

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

        queueGUI((Player) sender);
        return true;
    }
}
