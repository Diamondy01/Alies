// src/main/java/commands/DenyDuelCommand.java
package me.diamondy.alies.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DenyDuelCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {
                Player challenger = player.getServer().getPlayer(args[0]);
                if (challenger != null) {
                    player.sendMessage(ChatColor.RED + "You have declined the duel request from " + challenger.getName());
                    challenger.sendMessage(ChatColor.RED + player.getName() + " has declined your duel request.");
                } else {
                    player.sendMessage(ChatColor.RED + "The player who challenged you is no longer online.");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Usage: /denyduel <player>");
            }
        }
        return true;
    }
}
