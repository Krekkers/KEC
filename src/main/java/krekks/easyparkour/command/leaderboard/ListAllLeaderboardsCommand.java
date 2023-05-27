package krekks.easyparkour.command.leaderboard;


import krekks.easyparkour.manager.leaderboardmanager.Leaderboard;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import static krekks.easyparkour.KEP.config;
import static krekks.easyparkour.manager.leaderboardmanager.LeaderboardHandler.leaderboard_List;

public class ListAllLeaderboardsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        sender.sendMessage(config.primary + "Board");
        for(Leaderboard lb : leaderboard_List){
            //show data
            sender.sendMessage(config.gray + "--------------------");
            sender.sendMessage(config.primary + "Name : " + config.secondary + lb.getName());
            sender.sendMessage(config.primary + "ID   : " + config.secondary + lb.getId());
            sender.sendMessage(config.primary + "location   : " + config.secondary + "X : " + lb.getLoc().getX() + "Y : " + lb.getLoc().getY() + "Z : " + lb.getLoc().getZ());
            sender.sendMessage(config.gray + "--------------------");
        }


        return false;
    }
}
