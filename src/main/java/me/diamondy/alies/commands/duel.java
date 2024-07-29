package me.diamondy.alies.commands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class duel implements CommandExecutor {

    private final JavaPlugin plugin;

    public duel(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String label, String[] args) {
        if (command.getName().equalsIgnoreCase("duel")) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Usage: /duel <player>");
                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(ChatColor.RED + "Player not found");
                return true;
            }
            if (target == sender) {
                sender.sendMessage(ChatColor.RED + "You can't duel yourself");
                return true;
            }
            sender.sendMessage(ChatColor.GREEN + "You have sent a duel request to " + target.getName());
            target.sendMessage(ChatColor.GREEN + "You have received a duel request from " + sender.getName());

            TextComponent acceptMessage = new TextComponent(ChatColor.GREEN + "Click here to accept the duel");
            acceptMessage.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/acceptduel " + sender.getName()));

            TextComponent declineMessage = new TextComponent(ChatColor.RED + "Click here to decline the duel");
            declineMessage.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/denyduel " + sender.getName()));

            target.spigot().sendMessage(acceptMessage);
            target.spigot().sendMessage(declineMessage);

            target.sendMessage(ChatColor.RED + "The duel request will expire in 30 seconds");
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (target.isOnline()) {
                        target.sendMessage(ChatColor.RED + "The duel request has expired");
                    }
                }
            }.runTaskLater(plugin, 600);
            return true;
        }
        return false;
    }
}