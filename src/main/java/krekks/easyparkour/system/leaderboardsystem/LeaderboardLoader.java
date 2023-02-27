package krekks.easyparkour.system.leaderboardsystem;

import krekks.easyparkour.Config;
import krekks.easyparkour.system.storage.PlayerSaveUtil;
import org.bukkit.Bukkit;

import java.util.ArrayList;

import static krekks.easyparkour.KEP.PLUGIN;

public class LeaderboardLoader {
    public static final ArrayList<LeaderboardPlayer> lb_List = new ArrayList<>();

    public static void initLeaderboard(){
        PlayerSaveUtil.loadLeaderboard();
        //repeat task!
        Bukkit.getScheduler().runTaskTimerAsynchronously(PLUGIN, () -> {
            Bukkit.getLogger().info("ATTEMPTING TO LOAD IN LEADERBOARD DATA");
            lb_List.clear();
            Bukkit.getLogger().info("-------------------------");
            Bukkit.getLogger().info("LIST HAS BEEN CLEARED");
            PlayerSaveUtil.loadLeaderboard();
            Bukkit.getLogger().info("-------------------------");
            Bukkit.getLogger().info("LIST HAS BEEN LOADED!");
            Bukkit.getLogger().info("BOARDS GOT UPDATED!");
            Bukkit.getScheduler().runTask(PLUGIN, LeaderboardHandler::refreshAllBoards);
        }, 0, Config.LB_refreshRate);
        //handles refreshrate
        /*
        Bukkit.getScheduler().runTaskTimer(PLUGIN, () -> {
        }, 60, Config.LB_refreshRate);

         */
    }

}
