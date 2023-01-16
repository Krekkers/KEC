package krekks.easyparkour.command.level.admin;

import krekks.easyparkour.system.levelsystem.LevelData;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static krekks.easyparkour.system.levelsystem.LevelHandler.levelList;

public class DeleteLevelCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //removes a level
        if(args.length < 1){
            sender.sendMessage(ChatColor.RED + "Please confirm that you are 100% sure!");
            return false;
        }
        LevelData ld =  levelList.get(Integer.parseInt(args[0]));
        if(args[1].toLowerCase() == "confirm"){
            levelList.remove(Integer.parseInt(args[0]));
            sender.sendMessage(ChatColor.RED + "You have deleted : " + ChatColor.GREEN + ld.getLevelName());
        }else{
            sender.sendMessage(ChatColor.RED + "You should say 'confirm' ");
        }
        return true;
    }
}
