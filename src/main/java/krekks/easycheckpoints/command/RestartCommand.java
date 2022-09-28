package krekks.easycheckpoints.command;

import krekks.easycheckpoints.system.menusystem.menu.RestartMenu;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static krekks.easycheckpoints.system.menusystem.MenuManager.getMenuUtility;

public class RestartCommand implements CommandExecutor {
    //not implemented yet.
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //data to reset.
        /*
        -finished
        -seconds
         */
        sender.sendMessage(ChatColor.RED + "Resetting Data");
        sender.sendMessage(ChatColor.RED + "This feature should be used with precaution.");
        //the resetting part.
        Player player = (Player) sender;
        RestartMenu menu = new RestartMenu(getMenuUtility(player));
        menu.openMenu();
        return true;

    }
}
