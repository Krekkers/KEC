package krekks.easyparkour.command.leaderboard;

import krekks.easyparkour.manager.leaderboardmanager.Leaderboard;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static krekks.easyparkour.manager.leaderboardmanager.LeaderboardHandler.leaderboard_List;

public class CreateLeaderboardCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p  = (Player) sender;
        if(args.length < 2)
            return false;

        Leaderboard newlb = new Leaderboard(1,args[0], p.getLocation(),Integer.parseInt(args[1]),"Type");
        leaderboard_List.add(newlb);
        newlb.CreateWorldObject();

        return false;
    }
}
