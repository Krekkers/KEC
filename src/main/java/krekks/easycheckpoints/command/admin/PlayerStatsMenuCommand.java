package krekks.easycheckpoints.command.admin;

import krekks.easycheckpoints.playerdata.PlayerDataHandler;
import krekks.easycheckpoints.system.menusystem.menu.PlayerStatsMenu;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static krekks.easycheckpoints.system.menusystem.MenuManager.getMenuUtility;

public class PlayerStatsMenuCommand implements CommandExecutor {
    //DominikPro4252 made me think of this
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        if(args.length == 0) {
            sender.sendMessage("Please provide a player name.");
            return false;
        }
        if(!PlayerDataHandler.isInList(args[0])){
            sender.sendMessage("Please provide a username from a player that is online.");
            return false;
        }
        Player player = (Player) sender;
        PlayerStatsMenu menu = new PlayerStatsMenu(getMenuUtility(player));
        menu.playerData = PlayerDataHandler.getFromList(Bukkit.getPlayer(args[0]));
        menu.openMenu();
        return true;
    }
}
