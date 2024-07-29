// src/main/java/me/diamondy/alies/commands/AcceptDuelCommand.java
package me.diamondy.alies.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AcceptDuelCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {
                Player challenger = player.getServer().getPlayer(args[0]);
                if (challenger != null) {
                    player.sendMessage(ChatColor.GREEN + "You have accepted the duel request from " + challenger.getName());
                    challenger.sendMessage(ChatColor.GREEN + player.getName() + " has accepted your duel request!");
                    // Add logic to start the duel
                } else {
                    player.sendMessage(ChatColor.RED + "The player who challenged you is no longer online.");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Usage: /acceptduel <player>");
            }
        }
        return true;
    }
}