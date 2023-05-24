package krekks.easyparkour.system.leaderboardsystem;


import krekks.easyparkour.system.storage.PlayerSaveUtil;
import org.bukkit.Bukkit;

import java.util.ArrayList;

import static krekks.easyparkour.KEP.PLUGIN;
import static krekks.easyparkour.KEP.config;

public class LeaderboardLoader {
    public static final ArrayList<LeaderboardPlayer> lb_List = new ArrayList<>();

    public static void initLeaderboard(){

        Bukkit.getLogger().info("Leaderboard loading...");
        PlayerSaveUtil.loadLeaderboard();
        //repeat task!
        Bukkit.getScheduler().runTaskTimerAsynchronously(PLUGIN, () -> {
            long timeOfLoad = System.currentTimeMillis();
            Bukkit.getScheduler().runTask(PLUGIN, PlayerSaveUtil::SaveAllPlayers);
            Bukkit.getLogger().info("ATTEMPTING TO LOAD IN LEADERBOARD DATA");
            lb_List.clear();
            PlayerSaveUtil.loadLeaderboard();
            Bukkit.getScheduler().runTask(PLUGIN, LeaderboardHandler::refreshAllBoards);
            Bukkit.getLogger().info("Loading Success! It took : " + (System.currentTimeMillis() - timeOfLoad ) + " MS");

        }, 0, config.LB_refreshRate);
    }



}
