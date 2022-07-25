package krekks.easycheckpoints.playerdata;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * This class is made to collect player data
 * This data includes :
 * Player,
 * Checkpoint,
 * Go back Counter,
 * Finished ,
 * Seconds it took for someone to finish
 */
public class PlayerData {
    Player p;
    Location checkpointLocation;
    int goBackCounter = 0;
    boolean finished = false;
    int secondsToFinish = 0;

    /**
     * Player data to collect.
     */
    public PlayerData(Player _p, Location _l){
        checkpointLocation = _l;
        p = _p;
    }

    public Player getP(){
        return p;
    }
    public void addGoBackCounter(int s){
        goBackCounter += s;
    }
    public void setFinished(boolean s){
        finished = s;
    }
    public void addSecondsToFinish(){
        secondsToFinish += 1;
    }
    public void setSecondsToFinish(int s){
        secondsToFinish = s;
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
    public int getGoBackCounter(){
        return goBackCounter;
    }
    public boolean getFinished(){
        return finished;
    }
    public int getSecondsToFinish(){
        return secondsToFinish;
    }



}
