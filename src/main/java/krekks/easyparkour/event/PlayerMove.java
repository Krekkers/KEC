package krekks.easyparkour.event;

import krekks.easyparkour.Config;
import krekks.easyparkour.playerdata.PlayerDataHandler;
import org.bukkit.*;
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

        if(e.getTo().getBlockX() == e.getFrom().getBlockX() && e.getTo().getBlockY() == e.getFrom().getBlockY() && e.getTo().getBlockZ() == e.getFrom().getBlockZ())
            return;
        Player p = e.getPlayer();
        //might work
        Location loc = e.getTo().clone().add(0,-1,0).getBlock().getLocation();
        Material block = loc.getBlock().getType();
        //checkpointhgsdfdfgrswrgfhwerhtw
        if(block == Config.checkpoint)
            setCheckpointOf(p, loc);
        //This will trigger the player to go to the next level
        if(block == Config.nextLevel)
            PlayerDataHandler.getPlayerDataFromList(p).finishLevel();
        //boost
        if(p.isSneaking()) return; //thank me later ;)
        if(block == Config.boost)
            boost(new Vector(0,Config.boostAmount / 10,0), e.getPlayer(), Sound.ENTITY_DOLPHIN_DEATH, Config.primary + "WOOSH");
        //boost in direction
        if(block == Material.IRON_BLOCK)
            boost(new Vector(p.getLocation().getDirection().getX(),
                    Config.boostAmount / 10,
                    p.getLocation().getDirection().getZ()),
                    e.getPlayer(),
                    Sound.ENTITY_ENDER_DRAGON_FLAP, Config.secondary + "WOOSH!");

    }

}
