package krekks.easyparkour.command.leaderboard;

import krekks.easyparkour.Config;
import krekks.easyparkour.system.leaderboardsystem.Leaderboard;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import static krekks.easyparkour.system.leaderboardsystem.LeaderboardHandler.leaderboard_List;

public class ListAllLeaderboardsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        sender.sendMessage(Config.primary + "Board");
        for(Leaderboard lb : leaderboard_List){
            //show data
            sender.sendMessage(Config.gray + "--------------------");
            sender.sendMessage(Config.primary + "Name : " + Config.secondary + lb.getName());
            sender.sendMessage(Config.primary + "ID   : " + Config.secondary + lb.getId());
            sender.sendMessage(Config.primary + "location   : " + Config.secondary + "X : " + lb.getLoc().getX() + "Y : " + lb.getLoc().getY() + "Z : " + lb.getLoc().getZ());
            sender.sendMessage(Config.gray + "--------------------");
        }


        return false;
    }
}
