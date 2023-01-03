package krekks.easyparkour.system.levelsystem;

import krekks.easyparkour.playerdata.PlayerData;
import krekks.easyparkour.playerdata.PlayerDataHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.LinkedHashMap;

import static krekks.easyparkour.Config.nextLevelSound;
import static krekks.easyparkour.KEP.config;
import static org.bukkit.Bukkit.getLogger;

public class LevelHandler {

    public static final HashMap<Integer, LevelData> levelList = new HashMap<>();

    /**
     * Also works as reloading
     */
    public static void loadParkourLevels(){
        levelList.clear();
        getLogger().info("" +
                "------------------------ \n" +
                "Loading levels!\n" +
                "------------------------");
        for (int i = 0; i < config.getList("levels").size(); i++){
            LinkedHashMap<String, Object> levelObj = (LinkedHashMap<String, Object>) config.getList("levels").get(i);
            Location levelSpawn = new Location(Bukkit.getWorld("world"), (Double) levelObj.get("x"),(Double) levelObj.get("y"),(Double) levelObj.get("z"));
            Material icon =  Material.matchMaterial((String) levelObj.get("icon"));
            LevelData ld = new LevelData(i,levelSpawn, (String) levelObj.get("name"), (String) levelObj.get("difficulty"),(String) levelObj.get("creator"), icon, (int) levelObj.get("points"),(int) levelObj.get("reward"));
            getLogger().info("Loaded level : " + (String) levelObj.get("name"));
            levelList.put(i,ld);
        }
        getLogger().info("" +
                "------------------------ \n" +
                "Finished loading levels!\n" +
                "------------------------");
    }

    public static void playerSetNextLevel(Player p){
        //set the parkour level and teleport player to new level
        PlayerData pd = PlayerDataHandler.getFromList(p);
        if(pd.getLevel() + 1 > levelList.size()){
            p.sendMessage("You have finished the last level");
            return;
        }
        LevelData ld = levelList.get(pd.getLevel() + 1);
        //reward points
        int reward = (int) (ld.getPoints() / 1.5f);

        pd.addPoints(reward);
        //set data
        pd.setLevel(ld.getLevelID());
        pd.setCheckpointLocation(ld.getLevelSpawn());
        p.teleport(ld.getLevelSpawn());
        //success
        p.sendMessage(ChatColor.GREEN + "You finished!");
        p.sendMessage(ChatColor.GREEN + "You earned : " + ChatColor.RED + reward + ChatColor.GREEN + " Points.");
        p.sendMessage(ChatColor.GREEN + "The difficulty was : " + ChatColor.RED + ld.getDifficulty());
        p.playSound(p,nextLevelSound, 2f,1f);

    }
    public static void finishLevel(Player p){
        //set the parkour level and teleport player to new level
        PlayerData pd = PlayerDataHandler.getFromList(p);

        LevelData ld = levelList.get(pd.getLevel());
        pd.addPoints(ld.getReward());
        //set data
        pd.setLevel(ld.getLevelID());
        pd.setCheckpointLocation(ld.getLevelSpawn());
        p.teleport(ld.getLevelSpawn());
        //success
        p.sendMessage(ChatColor.GREEN + "You finished!");
        p.sendMessage(ChatColor.GREEN + "You earned : " + ChatColor.RED + ld.getReward() + ChatColor.GREEN + " Points.");
        p.sendMessage(ChatColor.GREEN + "The difficulty was : " + ChatColor.RED + ld.getDifficulty());
        p.playSound(p,nextLevelSound, 2f,1f);

    }
    public static void playerSetParkourLevel(Player p, int id){
        //set the parkour level and teleport player to new level
        PlayerData pd = PlayerDataHandler.getFromList(p);
        LevelData ld = levelList.get(id);
        //set data
        pd.setLevel(ld.getLevelID());
        pd.setCheckpointLocation(ld.getLevelSpawn());
        ld.getLevelSpawn().setYaw(90f);
        p.teleport(ld.getLevelSpawn());
        //success
        p.playSound(p,nextLevelSound, 1f,1f);

    }




}
