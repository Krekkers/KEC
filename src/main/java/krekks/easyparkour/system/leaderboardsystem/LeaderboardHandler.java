package krekks.easyparkour.system.leaderboardsystem;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;

public class LeaderboardHandler {

    public static ArrayList<Leaderboard> leaderboard_List = new ArrayList<>();

    public static void loadLeaderboards(){
        Leaderboard lb = new Leaderboard(1,"Finish count", new Location(Bukkit.getWorld("world"), 0, 150 , 0), 5, "");
        leaderboard_List.add(lb);
    }

    public static void RemoveAllLeaderBoards(){
        for(Leaderboard lb : leaderboard_List){
            lb.removeEntities(false);
        }
    }



}
