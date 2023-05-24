package krekks.easyparkour.system.leaderboardsystem;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static krekks.easyparkour.KEP.PLUGIN;
import static krekks.easyparkour.KEP.config;

public class LeaderboardHandler {

    public static ArrayList<Leaderboard> leaderboard_List = new ArrayList<>();

    public static void loadLeaderboards(){
        if(leaderboard_List.size() > 0)
            RemoveAllLeaderBoards();
        leaderboard_List.clear();
        for (int i = 0; i < config.fileConfig.getList("leaderboards").size(); i++){
            LinkedHashMap<String, Object> LeaderboardOBJ = (LinkedHashMap<String, Object>) config.fileConfig.getList("leaderboards").get(i);

            Leaderboard lb = new Leaderboard(1,(String) LeaderboardOBJ.get("title"),
                    new Location(Bukkit.getWorld("world"), (Double) LeaderboardOBJ.get("x"), (Double) LeaderboardOBJ.get("y"), (Double) LeaderboardOBJ.get("z")),
                    (int) LeaderboardOBJ.get("limit"),
                    "Default");
            lb.CreateWorldObject(); // spawns the board in.
            leaderboard_List.add(lb);

        }
    }

    public static void refreshAllBoards(){
        for(Leaderboard lb : leaderboard_List){
            lb.removeEntities(true);
        }
    }

    public static void RemoveAllLeaderBoards(){
        for(Leaderboard lb : leaderboard_List){
            lb.removeEntities(false);
        }
    }

    public static void removeLeaderboard(int id){
        leaderboard_List.remove(id);
    }



    public static void saveBoards(){
        List<Object> saveList = new ArrayList<>();
        for(Leaderboard lb : leaderboard_List){
            Bukkit.getLogger().info("saved loc" + lb.getLoc());
            saveList.add(
                    savableLeaderboard(lb.getName(), lb.getType(),lb.getLimit(), lb.getLoc().getX(),lb.getLoc().getY(),lb.getLoc().getZ(),lb.getLineOffset())
            );
            Bukkit.getLogger().info("Saved board : " + lb.getName());
        }
        //setting level
        config.fileConfig.set("leaderboards", saveList);
        PLUGIN.saveConfig();
    }

    static Object savableLeaderboard(String title, String type, int limit, double x, double y, double z, double offset){
        LinkedHashMap<String, Object> levelObj = new LinkedHashMap<>();
        levelObj.put("title", title);
        levelObj.put("type", type);
        levelObj.put("limit", limit);
        levelObj.put("x", x);
        levelObj.put("y", y);
        levelObj.put("z", z);
        levelObj.put("yoffset", offset);
        return levelObj;
    }

}
