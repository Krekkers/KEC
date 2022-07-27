package krekks.easycheckpoints.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RestartCommand implements CommandExecutor {
    //not implemented yet.
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        /*
        sender.sendMessage("Resetting PlayerData");

        sender.sendMessage("Readding PlayerData");
        for(Player p : Bukkit.getOnlinePlayers()){
            PlayerDataHandler.AddToList(p,null);
        }

         */
        return true;
    }
}
