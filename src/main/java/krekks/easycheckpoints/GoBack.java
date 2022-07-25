package krekks.easycheckpoints;

import krekks.easycheckpoints.playerdata.PlayerData;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import static krekks.easycheckpoints.playerdata.PlayerDataHandler.GetCheckpointOf;
import static krekks.easycheckpoints.playerdata.PlayerDataHandler.GetFromList;

public class GoBack extends JavaPlugin {

    public static void GoToCheckPoint(Player p){
        Location newLoc = GetCheckpointOf(p);
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
        PlayerData d = GetFromList(p.getName());
        if(!d.getFinished()){
            d.addGoBackCounter(1);
        }

    }

}
