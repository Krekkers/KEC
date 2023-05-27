package krekks.easyparkour.command.level.admin;

import krekks.easyparkour.manager.levelmanager.LevelData;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static krekks.easyparkour.manager.levelmanager.LevelHandler.*;

public class DeleteLevelCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //removes a level
        if(args.length < 2){
            sender.sendMessage(ChatColor.RED + "Please confirm that you are 100% sure!");
            return false;
        }
        LevelData ld =  levelList.get(Integer.parseInt(args[0]));
        if(args[1].equals("confirm")){
            sender.sendMessage(ChatColor.RED + "You have deleted : " + ChatColor.GREEN + ld.getLevelName());
            levelList.remove(Integer.parseInt(args[0]));
            saveLevels();
        }else if(args[1].equals("all") && args[2].equals("confirm")){
            sender.sendMessage(ChatColor.RED + "You have deleted : " + ChatColor.GREEN + ld.getLevelName());
            levelList.clear();
            saveLevels();
        }else{
            sender.sendMessage(ChatColor.RED + "You should say 'confirm' ");
            return false;
        }

        return true;
    }
}
