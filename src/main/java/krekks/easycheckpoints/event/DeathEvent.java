package krekks.easycheckpoints.event;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import static krekks.easycheckpoints.Config.*;
import static krekks.easycheckpoints.playerdata.PlayerDataHandler.getCheckpointOf;

public class DeathEvent implements Listener {

    //for the future
    @EventHandler
    void playerDeath(EntityDamageEvent e){
        if(e.getEntity() instanceof Player){
            Player p = (Player) e.getEntity();                  //gets the player
            if(Toggle) p.setFireTicks(0);                       //sets firetick to 0 so it doesnt happen
            if((p.getHealth() - e.getFinalDamage()) <= 0 ){     //if player health is lower then 0;
                if(getCheckpointOf(p) == null) return;
                e.setCancelled(true);
                Location l = getCheckpointOf(p);
                l.add(0, 1, 0);
                p.sendMessage(ChatColor.YELLOW + "You got saved from " + ChatColor.RED + "Death!");
                p.teleport(l);
                p.setHealth(20);

            }
        }
    }

}
