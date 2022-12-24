package krekks.easycheckpoints.misc;

import krekks.easycheckpoints.playerdata.PlayerData;
import org.bukkit.Location;

import java.time.Instant;

import static krekks.easycheckpoints.Config.*;
import static krekks.easycheckpoints.playerdata.PlayerDataHandler.data;
import static krekks.easycheckpoints.playerdata.PlayerDataHandler.removeFromList;

public class ResetGame {


    public static void RestartGame(){
        for(PlayerData d : data){
            if(!d.getPlayer().isOnline()){
                removeFromList(d.getPlayer());
            }
            d.setFinished(false);
            d.setSecondsToFinish(0);
            d.setCheckpointLocation(new Location(d.getPlayer().getWorld(), spawnX,spawnY,spawnZ));
            d.getPlayer().teleport(d.getCheckpointLocation());
            d.setGoBackCounter(0);
            time = Instant.now();
            Toggle = false;
        }
    }

}
