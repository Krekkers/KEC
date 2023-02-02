package krekks.easyparkour.event;

import krekks.easyparkour.Config;
import krekks.easyparkour.playerdata.PlayerDataHandler;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import static krekks.easyparkour.playerdata.PlayerDataHandler.setCheckpointOf;

public class PlayerMove implements Listener {

    @EventHandler
    void MoveCheck(PlayerMoveEvent e){
        if(e.getTo().getBlock() == e.getFrom().getBlock()) return;
        Player p = e.getPlayer();
        Location loc = p.getLocation().add(0,-1,0);
        Material block = loc.getBlock().getType();
        //checkpoint
        if(block == Config.checkpoint)
            setCheckpointOf(p, p.getLocation().add(0,-1,0).getBlock().getLocation());
        //This will trigger the player to go to the next level
        if(block == Config.nextLevel)
            PlayerDataHandler.getPlayerDataFromList(p).finishLevel();
    }

}
