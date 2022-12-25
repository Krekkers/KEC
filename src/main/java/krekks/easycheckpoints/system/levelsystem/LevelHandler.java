package krekks.easycheckpoints.system.levelsystem;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.LinkedHashMap;

import static krekks.easycheckpoints.EasyCheckpoints.config;

public class LevelHandler {

    public static final HashMap<Integer, LevelData> levelList = new HashMap<>();

    public static void loadParkourLevels(){
        for (int i = 0; i < config.getList("levels").size(); i++){
            LinkedHashMap<String, Object> levelObj = (LinkedHashMap<String, Object>) config.getList("levels").get(i);
            Location levelSpawn = new Location(Bukkit.getWorld("world"), (Double) levelObj.get("x"),(Double) levelObj.get("y"),(Double) levelObj.get("z"));
            LevelData ld = new LevelData(i,levelSpawn, (String) levelObj.get("name"), (String) levelObj.get("difficulty"));
            levelList.put(i,ld);

        }
    }



}
