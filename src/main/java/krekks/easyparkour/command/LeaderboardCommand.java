package krekks.easyparkour.command;

import krekks.easyparkour.system.leaderboardsystem.Leaderboard;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LeaderboardCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p  = (Player) sender;

        Leaderboard newlb = new Leaderboard(1,"hello", p.getLocation(),5,"test");

        newlb.CreateWorldObject();

        /*
        LeaderboardMenu menu = new LeaderboardMenu(getMenuUtility((Player) sender));
        menu.menuOwner_PD = PlayerDataHandler.playerList.get((Player) sender);
        menu.openMenu();
         */
        return false;
    }
}
