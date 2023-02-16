package krekks.easyparkour.event;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import static krekks.easyparkour.playerdata.PlayerDataHandler.getCheckpointOf;

public class DamageEvent implements Listener {

    @EventHandler
    void damage(EntityDamageEvent e){
        if(e.getEntity() instanceof Player p){
            //gets the player
            if(p.getFireTicks() > 1) p.setFireTicks(0);
            if((p.getHealth() - e.getFinalDamage()) < 1 ){     //if player health is lower than 0;
                e.setCancelled(true);
                Location l = getCheckpointOf(p);
                assert l != null;
                l.add(0, 1, 0);
                p.sendMessage(ChatColor.GREEN + "You got saved from " + ChatColor.RED + "Death!");
                p.teleport(l);
                p.setHealth(20);

            }
        }
    }
    //prevents fall damage
    @EventHandler
    void fallDamage(EntityDamageEvent e){
        if (e.getCause() == EntityDamageEvent.DamageCause.FALL) e.setCancelled(true);
    }

}
