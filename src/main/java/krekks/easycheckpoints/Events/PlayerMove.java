package krekks.easycheckpoints.Events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import static krekks.easycheckpoints.EasyCheckpoints.Toggle;
import static krekks.easycheckpoints.PlayerData.PlayerDataHandler.SetCheckpointOf;

public class PlayerMove implements Listener {

    @EventHandler
    void MoveCheck(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if(!Toggle)
            return;
        if(e.getPlayer().getLocation().add(0,-1,0).getBlock().getType() == Material.GOLD_BLOCK){
            Location loc = e.getPlayer().getLocation().add(0,-1,0).getBlock().getLocation();
            SetCheckpointOf(e.getPlayer(), loc);
        }
        //jump boost
        if(e.getPlayer().getLocation().add(0,-1,0).getBlock().getType() == Material.DIAMOND_BLOCK){
            Boost(new Vector(0,1f,0), p);
        }
    }



    void Boost(Vector velo, Player p){
        p.setVelocity(velo);
        p.playSound(p.getLocation(), Sound.BAT_TAKEOFF,1,1);
    }



}
