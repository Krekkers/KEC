package krekks.easycheckpoints.PlayerData;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerData {
    Player p;
    Location checkpointLocation;

    public PlayerData(Player _p, Location _l){
        checkpointLocation = _l;
        p = _p;
    }

    public Player getP(){
        return p;
    }
    public Location getCheckpointLocation(){
        return checkpointLocation;
    }

    public void setCheckpointLocation(Location _l){
        checkpointLocation = _l;
    }
    public void setP(Player _p){
        p = _p;
    }



}
