package krekks.easyparkour.event;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import static krekks.easyparkour.Config.checkpoint;
import static krekks.easyparkour.Config.nextLevel;
import static krekks.easyparkour.playerdata.PlayerDataHandler.setCheckpointOf;
import static krekks.easyparkour.system.levelsystem.LevelHandler.finishLevel;

public class PlayerMove implements Listener {

    @EventHandler
    void MoveCheck(PlayerMoveEvent e){
        if(e.getFrom().getBlockX() == e.getTo().getBlockX() && e.getFrom().getBlockY() == e.getTo().getBlockY() && e.getFrom().getBlockZ() == e.getTo().getBlockZ()) return;
        Player p = e.getPlayer();
        Material block = p.getLocation().add(0,-1,0).getBlock().getType();
        Block b = p.getLocation().add(0,-1,0).getBlock();
        //checkpoint
        if(block == checkpoint){
            Location loc = p.getLocation().add(0,-1,0).getBlock().getLocation();
            setCheckpointOf(p, loc);
        }
        //This will trigger the player to go to the next level
        if(block == nextLevel) {
            finishLevel(e.getPlayer());
        }
    }
    //prevents falldamage
    @EventHandler
    void fallDamage(EntityDamageEvent e){
        if (e.getCause() == EntityDamageEvent.DamageCause.FALL) e.setCancelled(true);
    }
}
