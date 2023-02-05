package krekks.easyparkour.system.leaderboardsystem;

import krekks.easyparkour.Config;
import krekks.easyparkour.system.storage.PlayerSaveUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.ArrayList;

import static krekks.easyparkour.KEP.PLUGIN;

public class LeaderboardLoader {
    public static final ArrayList<LeaderboardObj> lb_List = new ArrayList<>();

    public static void initLeaderboard(){
        PlayerSaveUtil.loadLeaderboard();
        //repeat task!
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(PLUGIN, () -> {
            Bukkit.broadcastMessage(ChatColor.RED + "Leaderboard will be updated.");
            Bukkit.broadcastMessage(ChatColor.RED + "Lag is expected.");
            Bukkit.getLogger().info("ATTEMPTING TO LOAD IN LEADERBOARD DATA");
            lb_List.clear();
            Bukkit.getLogger().info("-------------------------");
            Bukkit.getLogger().info("LIST HAS BEEN CLEARED");
            PlayerSaveUtil.loadLeaderboard();
            Bukkit.getLogger().info("-------------------------");
            Bukkit.getLogger().info("LIST HAS BEEN LOADED!");
            Bukkit.broadcastMessage(ChatColor.RED + "Leaderboard " + ChatColor.GREEN + "succesfully updated.");
        }, 0, Config.LB_refreshRate);

    }

}
