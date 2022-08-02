package krekks.easycheckpoints.misc;

import krekks.easycheckpoints.playerdata.PlayerData;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import static krekks.easycheckpoints.playerdata.PlayerDataHandler.getCheckpointOf;
import static krekks.easycheckpoints.playerdata.PlayerDataHandler.getFromList;

public class GoBack extends JavaPlugin {
    //goes back to the checkpoint
    /**
     * This make the player go back to the checkpoint
     * @param p player to get back to his checkpoint
     */
    public static void goToCheckPoint(Player p){
        Location newLoc = getCheckpointOf(p);
        if(newLoc == null)return;
        //setting location values to be teleported to
        newLoc.add(0.5f,1f,0.5f);
        newLoc.setPitch(p.getLocation().getPitch());
        newLoc.setYaw(p.getLocation().getYaw());
        //teleports
        p.teleport(newLoc);
        //setting back the values so it won't set the checkpoint again
        newLoc.add(-0.5f,-1f,-0.5f);
        newLoc.setPitch(0);
        newLoc.setYaw(0);
        PlayerData d = getFromList(p.getName());
        if(!d.getFinished()){
            d.addGoBackCounter(1);
        }

    }

}
