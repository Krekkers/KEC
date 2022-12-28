package krekks.easyparkour.playerdata;

import org.bukkit.Location;
import org.bukkit.block.Block;
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
    Block checkpointBlock;
    int level;
    int points;
    int goBackCounter = 0;

    /**
     * Player data to collect.
     */
    public PlayerData(Player _p, Location _l){
        checkpointLocation = _l;
        player = _p;
        level = 0;
    }

    public void addGoBackCounter(int s){
        goBackCounter += s;
    }

    public void setLevel(int l) {
        level = l;
    }
    public void setCheckpointBlock(Block b) { checkpointBlock = b; }
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
    public Block getCheckpointBlock() { return checkpointBlock; }
    public int getLevel() {
        return level;
    }
    public Location getCheckpointLocation(){
        return checkpointLocation;
    }



}