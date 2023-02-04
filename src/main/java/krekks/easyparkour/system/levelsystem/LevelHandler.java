package krekks.easyparkour.system.levelsystem;

import krekks.easyparkour.playerdata.PlayerData;
import krekks.easyparkour.playerdata.PlayerDataHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static krekks.easyparkour.Config.nextLevelSound;
import static krekks.easyparkour.KEP.PLUGIN;
import static krekks.easyparkour.KEP.config;
import static org.bukkit.Bukkit.getLogger;

public class LevelHandler {

    public static final ArrayList<LevelData> levelList = new ArrayList<>();

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
            LevelData ld = new LevelData(i,levelSpawn, (String) levelObj.get("name"), (int) levelObj.get("difficulty"),(String) levelObj.get("creator"), icon, (int) levelObj.get("points"),(int) levelObj.get("reward"));
            getLogger().info("Loaded level : " + levelObj.get("name"));
            levelList.add(ld);
        }
        getLogger().info("" +
                "------------------------ \n" +
                "Finished loading levels!\n" +
                "------------------------");
    }

    /**
    this function is depricated but will set the next level.
    A better option is to use the function : playerSetParkourLevel
     */
    @Deprecated
    public static void playerSetNextLevel(Player p){
        //set the parkour level and teleport player to new level
        PlayerData pd = PlayerDataHandler.getPlayerDataFromList(p);
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
        //pd.setCheckpointLocation(ld.getLevelSpawn());
        p.teleport(ld.getLevelSpawn());
        //success
        p.sendMessage(ChatColor.GREEN + "You finished!");
        p.sendMessage(ChatColor.GREEN + "You earned : " + ChatColor.RED + reward + ChatColor.GREEN + " Points.");
        p.sendMessage(ChatColor.GREEN + "The difficulty was : " + ChatColor.RED + ld.getDifficulty());
        p.playSound(p,nextLevelSound, 2f,1f);

    }
    @Deprecated
    public static void finishLevel(Player p){
        //set the parkour level and teleport player to new level

    }

    /**
     * You can use this. But its just does : PlayerDataHandler.getPlayerDataFromList(p).setLevel(id);
     * @param p
     * @param id
     */
    public static void playerSetParkourLevel(Player p, int id){
        PlayerDataHandler.getPlayerDataFromList(p).setLevel(id);
    }

    /**
     * Creates a new level object and saves it to config
     * @param name name of the level
     * @param creator the creator of the level
     * @param location the location will be split up into x y and z values
     * @param difficulty difficulty
     * @param icon material icon for the level in the level menu
     * @param points points required to play
     * @param reward reward points
     */
    public static void createNewLevel(String name, String creator, Location location, int difficulty, Material icon, int points, int reward){
        //create level data
        List<Object> saveList = new ArrayList<>();
        for (LevelData levelData : levelList) {
            saveList.add(levelObjectCreator(
                    levelData.levelName
                    , levelData.creator
                    , levelData.getLevelSpawn()
                    , levelData.getDifficulty()
                    , levelData.getIcon()
                    , levelData.getPoints()
                    , levelData.getReward()));
        }

        saveList.add(levelObjectCreator(name,creator,location,difficulty,icon,points,reward));

        config.set("levels", saveList);
        PLUGIN.saveConfig();
        loadParkourLevels();
    }

    public static void saveLevels(){
        List<Object> saveList = new ArrayList<>();
        for(LevelData ld : levelList){
            saveList.add(levelObjectCreator(
                    ld.levelName
                    ,ld.creator
                    ,ld.getLevelSpawn()
                    ,ld.getDifficulty()
                    ,ld.getIcon()
                    ,ld.getPoints()
                    ,ld.getReward()));
            Bukkit.getLogger().info("Saved Level : " + ld.getLevelName());
        }
        //setting level
        config.set("levels", saveList);
        PLUGIN.saveConfig();
        loadParkourLevels();

    }


    /**
     * Creates levelObject... This is only used for saving data
     * It is not wise to use this on adding levelData
     * @param name
     * @param creator
     * @param location
     * @param difficulty
     * @param icon
     * @param points
     * @param reward
     * @return An object reperesenting all the params
     */
    static Object levelObjectCreator(String name, String creator, Location location, int difficulty, Material icon, int points, int reward){
        LinkedHashMap<String, Object> levelObj = new LinkedHashMap<>();
        levelObj.put("name", name);
        levelObj.put("creator", creator);
        levelObj.put("x",location.getX());
        levelObj.put("y",location.getY());
        levelObj.put("z",location.getZ());
        levelObj.put("difficulty", difficulty);
        levelObj.put("icon", icon.toString());
        levelObj.put("points", points);
        levelObj.put("reward", reward);
        return levelObj;
    }




}
