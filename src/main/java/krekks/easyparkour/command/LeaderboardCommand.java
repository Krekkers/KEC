package krekks.easyparkour.command;

import krekks.easyparkour.playerdata.PlayerDataHandler;
import krekks.easyparkour.system.menusystem.menu.LeaderboardMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static krekks.easyparkour.system.menusystem.MenuManager.getMenuUtility;

public class LeaderboardCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        LeaderboardMenu menu = new LeaderboardMenu(getMenuUtility((Player) sender));
        menu.menuOwner_PD = PlayerDataHandler.playerList.get((Player) sender);
        menu.openMenu();
        return false;
    }
}
