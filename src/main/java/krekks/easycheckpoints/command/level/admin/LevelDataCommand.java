package krekks.easycheckpoints.command.level.admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static krekks.easycheckpoints.system.levelsystem.LevelHandler.levelList;

public class LevelDataCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        for(int i = 0; i < levelList.size(); i++){
            sender.sendMessage("Level data of : " + i);
            sender.sendMessage("Level id : " + levelList.get(i).getLevelID());
            sender.sendMessage("Level name : " + levelList.get(i).getLevelName());
            sender.sendMessage("Level location : " + levelList.get(i).getLevelSpawn());
            sender.sendMessage("Level difficulty : " + levelList.get(i).getDifficulty());
        }

        return true;
    }
}
