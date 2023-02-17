package krekks.easyparkour.event;

import krekks.easyparkour.Config;
import krekks.easyparkour.playerdata.PlayerDataHandler;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import static krekks.easyparkour.misc.movement.PlayerBoost.boost;
import static krekks.easyparkour.playerdata.PlayerDataHandler.setCheckpointOf;

public class PlayerMove implements Listener {

    @EventHandler
    void MoveCheck(PlayerMoveEvent e){
        //this
        if(e.getTo().getBlockX() == e.getFrom().getBlockX() && e.getTo().getBlockY() == e.getFrom().getBlockY() && e.getTo().getBlockZ() == e.getFrom().getBlockZ())
            return;
        Player p = e.getPlayer();
        //might work
        Location loc = e.getTo().clone().add(0,-1,0).getBlock().getLocation();
        Material block = loc.getBlock().getType();
        //checkpoint
        if(block == Config.checkpoint)
            setCheckpointOf(p, loc);
        //This will trigger the player to go to the next level
        if(block == Config.nextLevel)
            PlayerDataHandler.getPlayerDataFromList(p).finishLevel();
        //boost
        if(block == Config.boost)
            boost(new Vector(0,Config.boostAmount / 10,0), e.getPlayer(), Sound.ENTITY_DOLPHIN_DEATH, "WOOSH");
    }

}
