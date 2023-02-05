package krekks.easyparkour.playerdata;

import krekks.easyparkour.Config;
import krekks.easyparkour.system.levelsystem.LevelData;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import static krekks.easyparkour.Config.multipliers;
import static krekks.easyparkour.Config.nextLevelSound;
import static krekks.easyparkour.system.levelsystem.LevelHandler.levelList;
import static krekks.easyparkour.system.storage.PlayerSaveUtil.getPlayerFinishCountFromDB;
import static krekks.easyparkour.system.storage.PlayerSaveUtil.getPlayerPointsFromDB;

public class PlayerData {
    Player player;
    Location checkpointLocation;
    //Block checkpointBlock;
    int level;
    int points;
    double pointsMultiplier;
    int goBackCounter = 0;


    //other
    int finishCount = 0;

    /**
     * Player data to collect.
     */
    public PlayerData(Player _p){
        checkpointLocation = Config.spawn;
        player = _p;
        setLevel(0);
        points = getPlayerPointsFromDB(_p);
        finishCount = getPlayerFinishCountFromDB(_p);
        _p.setLevel(points);
        pointsMultiplier = getMultiplierPerm();
    }

    public void addGoBackCounter(int s){
        goBackCounter += s;
    }
    public void addPoints(int points) {
        this.points += points;
    }



    public void setFinishCount(int finishCount) {
        this.finishCount = finishCount;
    }
    public int getFinishCount() {
        return finishCount;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    //public void setCheckpointBlock(Block b) { checkpointBlock = b; }
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
    //public Block getCheckpointBlock() { return checkpointBlock; }
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
        checkpointLocation = ld.getLevelSpawn();
        player.teleport(ld.getLevelSpawn());
    }

    //checks
    boolean isSameCheckpoint(Location loc){
        return loc.equals(getCheckpointLocation());
    }
    //misc functions
    /**
     * teleports player to checkpoint
     * @param loc location to be set as checkpoint
     */
    public void setCheckpoint(Location loc){
        if(isSameCheckpoint(loc))
            return;
        //if its not the same checkpoint
        checkpointLocation = loc;
        //setCheckpointBlock(loc.getBlock());
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.checkpointText));
        player.playSound(loc, Config.checkpointSound, 1 , 1);
    }

    /**
     * player go to checkpoint
     */
    public void goToCheckPoint(){
        addGoBackCounter(1);
        Location checkpoint = getCheckpointLocation();
        Location newLoc = new Location(player.getWorld(),checkpoint.getX(),checkpoint.getY(),checkpoint.getZ());
        //setting location values to be teleported to
        newLoc.add(0.5f,1f,0.5f);
        newLoc.setPitch(player.getLocation().getPitch());
        newLoc.setYaw(player.getLocation().getYaw());
        //teleports
        player.teleport(newLoc);
    }
    /**
     * User will finish the level.
     * All is handled inside this function and will not be able to be edited.
     */
    public void finishLevel() {
        LevelData ld = levelList.get(level);
        //set data
        level = ld.getLevelID();
        checkpointLocation = ld.getLevelSpawn();
        //teleports
        int processedReward = (int) (ld.getReward() * pointsMultiplier);
        player.teleport(ld.getLevelSpawn());
        addPoints(processedReward);
        player.setLevel(points);
        setFinishCount(this.finishCount += 1);
        player.playSound(player,nextLevelSound, 2f,1f);
        //success
        player.sendMessage(ChatColor.GREEN + "You finished!");
        player.sendMessage(ChatColor.GREEN + "You earned : " + ChatColor.RED + processedReward + ChatColor.GREEN + " Points.");
        player.sendMessage(ChatColor.GREEN + "The difficulty was : " + ChatColor.RED + ld.getDifficulty());
    }

    /**
     * Returns the multiplier object. I could have done this easier but I have not.
     * @return permission object
     */
    public double getMultiplierPerm(){
        for(KrekksPermission kp : multipliers){
            if(player.hasPermission(kp.permissionName)){
                return kp.multiplier;
            }
        }
        return 1.0;
    }



}
