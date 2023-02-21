package krekks.easyparkour.command.leaderboard;

import krekks.easyparkour.system.leaderboardsystem.LeaderboardHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
public class RemoveLeaderboardCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        LeaderboardHandler.removeLeaderboard(Integer.parseInt(args[0]));
        return false;
    }
}
