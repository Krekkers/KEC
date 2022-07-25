package krekks.easycheckpoints.misc;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import static krekks.easycheckpoints.EasyCheckpoints.Toggle;

public class PlayerBoost {
    /**
     * A function to boost players in a direction
     * @param velocity Is for the velocity direction
     * @param p Player that will have this velocity applied
     * @param sound Sound that will be played when boosted
     * @param message Messaged that will be displayed
     */
    public static void Boost(Vector velocity, Player p, Sound sound, String message){
        if(!Toggle) return;
        p.setVelocity(velocity);
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        p.playSound(p.getLocation(), sound,1,1);
    }
}