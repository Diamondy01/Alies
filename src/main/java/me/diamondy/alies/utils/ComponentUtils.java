package me.diamondy.alies.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;



public class ComponentUtils {
    public static TextComponent fromLegacy(String input){
        return LegacyComponentSerializer.legacyAmpersand().deserialize(input);
    }

    public static String toPlainString(Component component){
        return PlainTextComponentSerializer.plainText().serialize(component);
    }

    public static void sendMessage(Player player, String message) {
        player.sendMessage(fromLegacy(message));
    }
}

