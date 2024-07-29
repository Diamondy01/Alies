package me.diamondy.alies;

import me.diamondy.alies.commands.AcceptDuelCommand;
import me.diamondy.alies.commands.DenyDuelCommand;
import me.diamondy.alies.commands.duel;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

import java.util.Objects;


public final class Alies extends JavaPlugin implements Listener {

    public static class AnsiColor {
        public static final String RESET = "\u001B[0m";
        public static final String BLACK = "\u001B[30m";
        public static final String RED = "\u001B[31m";
        public static final String GREEN = "\u001B[32m";
        public static final String YELLOW = "\u001B[33m";
        public static final String BLUE = "\u001B[34m";
        public static final String PURPLE = "\u001B[35m";
        public static final String CYAN = "\u001B[36m";
        public static final String WHITE = "\u001B[37m";
    }


    @Override
    public void onEnable() {
        // Plugin startup logic

        PluginDescriptionFile pdfFile = getDescription();
        String Version = pdfFile.getVersion();

        getLogger().info(AnsiColor.BLUE + "Alies Loaded" + AnsiColor.RESET);
        getLogger().info(AnsiColor.WHITE + "-------------------------------------------------" + AnsiColor.RESET);
        getLogger().info(AnsiColor.RED + " " + AnsiColor.RESET);
        getLogger().info(AnsiColor.WHITE + "Author: Diamondy" + AnsiColor.RESET);
        getLogger().info(AnsiColor.WHITE + "Server Version: " + getServer().getVersion() + AnsiColor.RESET);
        getLogger().info(AnsiColor.WHITE + "Plugin Version: " + Version + AnsiColor.RESET);
        getLogger().info(AnsiColor.RED + " " + AnsiColor.RESET);
        getLogger().info(AnsiColor.WHITE + "-------------------------------------------------" + AnsiColor.RESET);
        getServer().getPluginManager().registerEvents(this, this);

        if (getCommand("duel") != null) {
            Objects.requireNonNull(getCommand("duel")).setExecutor(new duel(this));
        } else {
            getLogger().severe("Command 'duel' not found in plugin.yml");
        }

        if (getCommand("acceptduel") != null) {
            Objects.requireNonNull(getCommand("acceptduel")).setExecutor(new AcceptDuelCommand());
        } else {
            getLogger().severe("Command 'acceptduel' not found in plugin.yml");
        }

        if (getCommand("denyduel") != null) {
            Objects.requireNonNull(getCommand("denyduel")).setExecutor(new DenyDuelCommand());
        } else {
            getLogger().severe("Command 'denyduel' not found in plugin.yml");
        }

    }


    // Player Join Message
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage("&a&lWelcome to AC Practice, " + player.getName() + "!");
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info(AnsiColor.RED + "AstralClub Duels Addon Unloaded" + AnsiColor.RESET);

    }
}