package krekks.easyparkour.misc.movement;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class PlayerBoost {
    /**
     * A function to boost players in a direction
     * @param velocity Is for the velocity direction
     * @param p Player that will have this velocity applied
     * @param sound Sound that will be played when boosted
     * @param message Messaged that will be displayed
     */
    public static void boost(Vector velocity, Player p, Sound sound, String message){
        p.setVelocity(velocity);
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        p.playSound(p.getLocation(), sound,1,1);
    }
    /**
     * Gives a player elytra + glide toggle true.
     * @param p Player that will have this velocity applied
     * @param sound Sound that will be played when boosted
     * @param message Messaged that will be displayed
     */
    @Deprecated
    public static void elytraBoost(Player p,double force, Sound sound, String message){
        boost(new Vector(p.getLocation().getDirection().getX(), force, p.getLocation().getDirection().getZ()), p, sound,message);
        p.getInventory().setChestplate(new ItemStack(Material.ELYTRA));
        p.setGliding(true);
    }

}
