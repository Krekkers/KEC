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

    /**
     * logger info multiline
     * @param line
     */
    public static void krekksLoggerInfo(String... line){
        StringBuilder full = new StringBuilder();
        for (String s : line) {
         full.append(s).append("\n");
        }
        Bukkit.getLogger().info(full.toString());
    }
    /**
     * logger warn multiline
     * @param line
     */
    public static void krekksLoggerWarn(String... line){
        for (String s : line) {
            Bukkit.getLogger().warning(s);
        }
    }
    /**
     * logger fine multiline
     * @param line
     */
    public static void krekksLoggerFine(String... line){
        for (String s : line) {
            Bukkit.getLogger().fine(s);
        }
    }

}
