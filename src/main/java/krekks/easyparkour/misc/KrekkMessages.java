package krekks.easyparkour.misc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class KrekkMessages {

    /**
     * Sends a message array to the player
     * You can use the color code & to color the message
     * @param p
     * @param line
     */
    public static void krekkSendMessageArray(Player p, String... line){
        for (String s : line) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
        }
    }
    public static void krekksLoggerInfo(String... line){
        StringBuilder full = new StringBuilder();
        for (String s : line) {
         full.append(s).append("\n");
        }
        Bukkit.getLogger().info(full.toString());
    }
    public static void krekksLoggerWarn(String... line){
        for (String s : line) {
            Bukkit.getLogger().warning(s);
        }
    }
    public static void krekksLoggerFine(String... line){
        for (String s : line) {
            Bukkit.getLogger().fine(s);
        }
    }

}
