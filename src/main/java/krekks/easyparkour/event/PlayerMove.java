package krekks.easyparkour.event;

import krekks.easyparkour.playerdata.PlayerDataHandler;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import static krekks.easyparkour.KEP.config;
import static krekks.easyparkour.misc.movement.PlayerBoost.boost;
import static krekks.easyparkour.playerdata.PlayerDataHandler.setCheckpointOf;

public class PlayerMove implements Listener {

    @EventHandler
    void MoveCheck(PlayerMoveEvent e){
        if(e.getTo().getBlockX() == e.getFrom().getBlockX() && e.getTo().getBlockY() == e.getFrom().getBlockY() && e.getTo().getBlockZ() == e.getFrom().getBlockZ())
            return;
        Player p = e.getPlayer();
        Location loc = e.getTo().clone().add(0,-1,0).getBlock().getLocation();
        Material blockMaterial = loc.getBlock().getType();

        //checkpoint
        if(blockMaterial == config.checkpoint){
            setCheckpointOf(p, loc);
            return;
        }
        //This will trigger the player to go to the next level
        if(blockMaterial == config.nextLevel){
            PlayerDataHandler.getPlayerDataFromList(p).finishLevel();
            return;
        }

        //boost
        if(p.isSneaking()) return; //thank me later ;)
        if(blockMaterial == config.boost){
            boost(new Vector(0,config.boostAmount / 10,0), e.getPlayer(), Sound.ENTITY_DOLPHIN_DEATH, ChatColor.translateAlternateColorCodes('&', "&bWOOSH!"));
            return;
        }
        //boost in direction
        if(blockMaterial == Material.IRON_BLOCK){
            boost(new Vector(p.getLocation().getDirection().getX(),
                            config.boostAmount / 10,
                            p.getLocation().getDirection().getZ()),
                    e.getPlayer(),
                    Sound.ENTITY_ENDER_DRAGON_FLAP, config.secondary + "WOOSH!");
            return;
        }

        if(blockMaterial == config.floatblock){
            //give player effect
            p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 5, 2));
        }
    }

}
