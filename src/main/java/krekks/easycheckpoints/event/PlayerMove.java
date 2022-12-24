package krekks.easycheckpoints.event;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;
import org.w3c.dom.Entity;

import static krekks.easycheckpoints.Config.*;
import static krekks.easycheckpoints.misc.PlayerBoost.boost;
import static krekks.easycheckpoints.misc.PlayerBoost.elytraBoost;
import static krekks.easycheckpoints.playerdata.PlayerDataHandler.setCheckpointOf;

public class PlayerMove implements Listener {

    @EventHandler
    void MoveCheck(PlayerMoveEvent e){
        if(!Toggle) return;
        if(e.getFrom().getBlockX() == e.getTo().getBlockX() && e.getFrom().getBlockY() == e.getTo().getBlockY() && e.getFrom().getBlockZ() == e.getTo().getBlockZ()) return;
        Player p = e.getPlayer();
        Material block = p.getLocation().add(0,-1,0).getBlock().getType();
        Block b = p.getLocation().add(0,-1,0).getBlock();
        //this is the part for the checkpoint

        if(block == checkpoint){
            Location loc = p.getLocation().add(0,-1,0).getBlock().getLocation();
            setCheckpointOf(p, loc, b);
        }
        //this is the part for the finishline
        if(block == finish && !e.getPlayer().hasPermission("krekks.perms")){
            /*
            Location l = p.getLocation();
            l.setX(finishX); l.setY(finishY); l.setZ(finishZ);
            p.sendMessage(ChatColor.YELLOW + "YOU " + ChatColor.RED + "FINISHED!");
            Bukkit.broadcastMessage("> " + ChatColor.YELLOW + p.getName() + ChatColor.RED + " is number " + (finishedList.size() + 1) + " to finish!");
            setCheckpointOf(p, l, b);
            p.teleport(l);
            for(PlayerData d : data){
                if(d.getPlayer() == p && !d.getFinished()){
                    d.setFinished(true);        //sets the finish of the player
                    addToFinished(p);           //add that user to the finished list.
                    d.setSecondsToFinish((int) Duration.between(time, Instant.now()).toMillis() / 1000);  //sets the seconds it took to finish
                }
            }
             */

        }
        if(checkpointOnly) return;
        //jump boost
        if(block == jump){
            boost(new Vector(0,jumpVal / 10,0), e.getPlayer(),jumpSound, jumpText);
        }
        //forward boost
        else if(block == boost){
            boost(new Vector(e.getPlayer().getLocation().getDirection().getX(),boostVal / 10,e.getPlayer().getLocation().getDirection().getZ()), e.getPlayer(),boostSound , boostText);
        }
        //elytra boost
        else if(block == elytra){
            elytraBoost(e.getPlayer(),elytraVal / 10,elytraSound , elytraText);
        }
    }
    //prevents falldamage
    @EventHandler
    void fallDamage(EntityDamageEvent e){
        if (e.getCause() == EntityDamageEvent.DamageCause.FALL && Toggle) e.setCancelled(true);
    }
    //removes the elytra when player lands.
    //to prevent cheating
    @EventHandler
    void landing(EntityToggleGlideEvent e){
        if(e.isGliding()) return;
        if(e.getEntity() instanceof Entity) return;
            Player p = (Player) e.getEntity();
            p.getInventory().setChestplate(null);
    }
}
