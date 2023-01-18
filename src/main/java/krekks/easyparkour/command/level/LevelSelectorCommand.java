package krekks.easyparkour.command.level;

import krekks.easyparkour.playerdata.PlayerDataHandler;
import krekks.easyparkour.system.menusystem.menu.LevelSelectionMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static krekks.easyparkour.system.menusystem.MenuManager.getMenuUtility;

public class LevelSelectorCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        LevelSelectionMenu menu = new LevelSelectionMenu(getMenuUtility(player));
        menu.pd = PlayerDataHandler.getPlayerDataFromList(player);
        menu.openMenu();
        return true;
    }
}
