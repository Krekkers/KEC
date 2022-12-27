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

import static krekks.easycheckpoints.Config.Toggle;
import static krekks.easycheckpoints.Config.boost;
import static krekks.easycheckpoints.Config.boostSound;
import static krekks.easycheckpoints.Config.boostText;
import static krekks.easycheckpoints.Config.boostVal;
import static krekks.easycheckpoints.Config.checkpoint;
import static krekks.easycheckpoints.Config.checkpointOnly;
import static krekks.easycheckpoints.Config.finish;
import static krekks.easycheckpoints.Config.jump;
import static krekks.easycheckpoints.Config.jumpSound;
import static krekks.easycheckpoints.Config.jumpText;
import static krekks.easycheckpoints.Config.jumpVal;
import static krekks.easycheckpoints.misc.PlayerBoost.boost;
import static krekks.easycheckpoints.playerdata.PlayerDataHandler.setCheckpointOf;
import static krekks.easycheckpoints.system.levelsystem.LevelHandler.playerSetNextLevel;

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
        //This will trigger the player to go to the next level
        if(block == finish && !e.getPlayer().hasPermission("krekks.perms")){
            playerSetNextLevel(e.getPlayer());
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
        /*else if(block == elytra){
            elytraBoost(e.getPlayer(),elytraVal / 10,elytraSound , elytraText);
        }
         */
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
