package krekks.easycheckpoints.command;

import krekks.easycheckpoints.system.menusystem.menu.GameStatusMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static krekks.easycheckpoints.system.menusystem.MenuManager.getMenuUtility;

public class GameManagerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        GameStatusMenu menu = new GameStatusMenu(getMenuUtility(player));
        menu.openMenu();
        return true;

    }
}
