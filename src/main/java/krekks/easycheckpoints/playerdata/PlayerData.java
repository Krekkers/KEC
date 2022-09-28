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
    Player player;
    Location checkpointLocation;
    int goBackCounter = 0;
    boolean finished = false;
    int secondsToFinish = 0;

    /**
     * Player data to collect.
     */
    public PlayerData(Player _p, Location _l){
        checkpointLocation = _l;
        player = _p;
    }

    public void addGoBackCounter(int s){
        goBackCounter += s;
    }
    public void addSecondsToFinish(){
        secondsToFinish += 1;
    }

    public void setFinished(boolean s){
        finished = s;
    }
    public void setSecondsToFinish(int s){
        secondsToFinish = s;
    }
    public void setCheckpointLocation(Location _l){
        checkpointLocation = _l;
    }
    public void setPlayer(Player _p){
        player = _p;
    }
    public void setGoBackCounter(int b) { goBackCounter = b; }

    public Player getPlayer(){
        return player;
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
    public Location getCheckpointLocation(){
        return checkpointLocation;
    }



}
