package krekks.easyparkour.misc;

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



}
