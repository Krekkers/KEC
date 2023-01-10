package krekks.easyparkour.playerdata;

import krekks.easyparkour.Config;
import krekks.easyparkour.system.levelsystem.LevelData;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import static krekks.easyparkour.Config.nextLevelSound;
import static krekks.easyparkour.system.levelsystem.LevelHandler.levelList;

public class PlayerData {
    Player player;
    Location checkpointLocation;
    Block checkpointBlock;
    int level;
    int points;
    int pointsMultiplier = 1;
    int goBackCounter = 0;

    /**
     * Player data to collect.
     */
    public PlayerData(Player _p, Location _l){
        checkpointLocation = _l;
        player = _p;
        setLevel(0);
    }

    public void addGoBackCounter(int s){
        goBackCounter += s;
    }
    public void addPoints(int points) {
        this.points += points;
    }


    public void setPoints(int points) {
        this.points = points;
    }
    public void setCheckpointBlock(Block b) { checkpointBlock = b; }
    public void setCheckpointLocation(Location _l){
        checkpointLocation = _l;
    }
    public void setPlayer(Player _p){
        player = _p;
    }
    public void setGoBackCounter(int b) { goBackCounter = b; }
    public int getPoints() {
        return points;
    }
    public Player getPlayer(){
        return player;
    }
    public int getGoBackCounter(){
        return goBackCounter;
    }
    public Block getCheckpointBlock() { return checkpointBlock; }
    public LevelData getLevelData(){ return levelList.get(level); }
    public int getLevel() {
        return level;
    }
    public Location getCheckpointLocation(){
        return checkpointLocation;
    }

    /**
     * set level
     * @param l level int
     */
    public void setLevel(int l) {
        level = l;
        LevelData ld = levelList.get(level);
        setCheckpointLocation(ld.getLevelSpawn());
        player.teleport(ld.getLevelSpawn());
    }

    //checks
    boolean isSameCheckpoint(Location loc){
        return loc.equals(getCheckpointLocation());
    }
    //misc functions

    /**
     * teleports player to checkpoint
     * @param loc
     */
    public void teleportPlayerToCheckpoint(Location loc){
        if(isSameCheckpoint(loc))
            return;
        //if its not the same checkpoint
        setCheckpointLocation(loc);
        setCheckpointBlock(loc.getBlock());
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.checkpointText));
        player.playSound(loc, Config.checkpointSound, 1 , 1);

    }

    /**
     * Finish level
     */
    public void finishLevel() {
        LevelData ld = levelList.get(level);
        //set data
        setLevel(ld.getLevelID());
        setCheckpointLocation(ld.getLevelSpawn());
        //success
        player.sendMessage(ChatColor.GREEN + "You finished!");
        player.sendMessage(ChatColor.GREEN + "You earned : " + ChatColor.RED + ld.getReward() + ChatColor.GREEN + " Points.");
        player.sendMessage(ChatColor.GREEN + "The difficulty was : " + ChatColor.RED + ld.getDifficulty());
        //teleports
        player.teleport(ld.getLevelSpawn());
        addPoints(ld.getReward() * pointsMultiplier);
        player.playSound(player,nextLevelSound, 2f,1f);
    }



}
