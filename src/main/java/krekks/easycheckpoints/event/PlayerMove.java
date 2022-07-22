package krekks.easycheckpoints.event;

import krekks.easycheckpoints.playerdata.PlayerData;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import static krekks.easycheckpoints.command.ToggleCommand.sec;
import static krekks.easycheckpoints.EasyCheckpoints.*;
import static krekks.easycheckpoints.playerdata.PlayerDataHandler.SetCheckpointOf;
import static krekks.easycheckpoints.playerdata.PlayerDataHandler.*;

public class PlayerMove implements Listener {

    //tip from thiemo to not check in a onmove!
    //materials
    Material checkpoint = Material.matchMaterial(config.getString("checkpointblock"));
    Material boost = Material.matchMaterial(config.getString("jumpblock"));
    Material speed = Material.matchMaterial(config.getString("speedblock"));
    Material finish = Material.matchMaterial(config.getString("finishblock"));
    //text
    String BoostText = config.getString("boostmessage");
    String checkpointText = config.getString("checkpointmessage");
    String speedText = config.getString("speedtext");
    //sounds
    Sound boostSound = Sound.valueOf(config.getString("boostsound"));
    Sound checkpointSound = Sound.valueOf(config.getString("checkpointsound"));
    Sound speedSound = Sound.valueOf(config.getString("speedsound"));
    //values
    double boostval = config.getDouble("jumpvalue");
    double speedval = config.getDouble("speedvalue");
    //
    boolean checkpointOnly = config.getBoolean("checkpointonly");

    @EventHandler
    void MoveCheck(PlayerMoveEvent e){
        if(!Toggle)  return;
        if(e.getFrom().getBlockX() == e.getTo().getBlockX() && e.getFrom().getBlockY() == e.getTo().getBlockY() && e.getFrom().getBlockZ() == e.getTo().getBlockZ()) return;
        Player p = e.getPlayer();
        //this is the part for the checkpoint
        if(e.getPlayer().getLocation().add(0,-1,0).getBlock().getType() == checkpoint){
            Location loc = p.getLocation().add(0,-1,0).getBlock().getLocation();
            SetCheckpointOf(p, loc);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',checkpointText));
            p.playSound(p.getLocation(), checkpointSound,1,2);

        }
        //this is the part for the finishline
        if(p.getLocation().add(0,-1,0).getBlock().getType() == finish){
            Location l = p.getLocation();
            l.setX(finishX); l.setY(finishY); l.setZ(finishZ);
            p.sendMessage(ChatColor.YELLOW + "YOU " + ChatColor.RED + "FINISHED!");
            Bukkit.broadcastMessage("> " + ChatColor.YELLOW + p.getName() + ChatColor.RED + " is number " + (finishedList.size() + 1) + " to finish!");
            SetCheckpointOf(p, l);
            p.teleport(l);
            for(PlayerData d : data){
                if(d.getP() == p && !d.getfinished()){
                    d.setFinished(true);        //sets the finish of the player
                    AddToFinished(p);           //add that user to the finished list.
                    d.setSecondsToFinish(sec);  //sets the seconds it took to finish
                }
            }
        }
        if(checkpointOnly) return;
        //jump boost
        if(p.getLocation().add(0,-1,0).getBlock().getType() == boost){
            Boost(new Vector(0,boostval / 10,0), e.getPlayer());
        }
        /* to be forgotten about since i will likely not add it!
        //speed boost
        if(p.getLocation().add(0,-1,0).getBlock().getType() == speed){
            p.setWalkSpeed((float) speedval / 10);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', speedText));
            p.playSound(p.getLocation(), speedSound,1,1);
        }else{
            p.setWalkSpeed(0.2f);
        }
*/

    }

    @EventHandler
    void FallDamage(EntityDamageEvent e){
        if(!Toggle) return;
        if (e.getCause() == EntityDamageEvent.DamageCause.FALL && Toggle){
            e.setCancelled(true);
        }
    }


    void Boost(Vector velo, Player p){
        if(!Toggle) return;
        p.setVelocity(velo);
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', BoostText));
        p.playSound(p.getLocation(), boostSound,1,1);
    }



}
