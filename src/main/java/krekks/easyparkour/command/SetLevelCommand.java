package krekks.easyparkour.command;

import krekks.easyparkour.misc.KrekksString;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static krekks.easyparkour.system.levelsystem.LevelHandler.playerSetParkourLevel;

public class SetLevelCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args[1] == null || args[2] == null){
            sender.sendMessage("Please add context");
            return false;
        }
        //levels
        int level = KrekksString.getIntFromString(args[2]);
        playerSetParkourLevel(Bukkit.getPlayer(args[1]),level);
        sender.sendMessage(ChatColor.GREEN + "Success!");
        return true;
    }
}
