package krekks.easyparkour.system.leaderboardsystem;

import krekks.easyparkour.Config;
import krekks.easyparkour.system.storage.PlayerSaveUtil;
import org.bukkit.Bukkit;

import java.util.ArrayList;

import static krekks.easyparkour.KEP.PLUGIN;

public class LeaderboardLoader {
    public static final ArrayList<LeaderboardObj> lb_List = new ArrayList<>();

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
        }, 0, Config.LB_refreshRate);

        Bukkit.getScheduler().runTaskAsynchronously(PLUGIN, new Runnable() {
            @Override
            public void run() {
                Bukkit.getLogger().info("This message was printed to the console asynchronously");
                //Bukkit.broadcastMessage is not thread-safe
            }
        });
    }

}
