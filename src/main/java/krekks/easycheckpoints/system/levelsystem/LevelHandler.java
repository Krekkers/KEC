package krekks.easycheckpoints.system.levelsystem;

import krekks.easycheckpoints.playerdata.PlayerData;
import krekks.easycheckpoints.playerdata.PlayerDataHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.LinkedHashMap;

import static krekks.easycheckpoints.EasyCheckpoints.config;
import static org.bukkit.Bukkit.getLogger;

public class LevelHandler {

    public static final HashMap<Integer, LevelData> levelList = new HashMap<>();

    /**
     * Also works as reloading
     */
    public static void loadParkourLevels(){
        levelList.clear();
        getLogger().info("------------------------");
        for (int i = 0; i < config.getList("levels").size(); i++){
            LinkedHashMap<String, Object> levelObj = (LinkedHashMap<String, Object>) config.getList("levels").get(i);
            Location levelSpawn = new Location(Bukkit.getWorld("world"), (Double) levelObj.get("x"),(Double) levelObj.get("y"),(Double) levelObj.get("z"));
            LevelData ld = new LevelData(i,levelSpawn, (String) levelObj.get("name"), (String) levelObj.get("difficulty"));
            getLogger().info("Loaded level : " + (String) levelObj.get("name"));
            levelList.put(i,ld);
        }
        getLogger().info("------------------------");
        getLogger().info("Finished loading levels!");
        getLogger().info("------------------------");
    }

    public static void playerSetNextLevel(Player p){
        //set the parkour level and teleport player to new level
        PlayerData pd = PlayerDataHandler.getFromList(p);
        LevelData ld = levelList.get(pd.getLevel() + 1);
        pd.setLevel(ld.getLevelID());
        p.teleport(ld.getLevelSpawn());
        p.sendMessage("Level " + ld.getLevelName() + " | Difficulty : " + ld.getDifficulty());

    }





}
