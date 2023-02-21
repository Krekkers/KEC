package krekks.easyparkour.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class LeaderboardCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        /*
        LeaderboardMenu menu = new LeaderboardMenu(getMenuUtility((Player) sender));
        menu.menuOwner_PD = PlayerDataHandler.playerList.get((Player) sender);
        menu.openMenu();
         */
        return false;
    }
}
