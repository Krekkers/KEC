package krekks.easyparkour.command.admin.level;

import krekks.easyparkour.system.levelsystem.LevelData;
import krekks.easyparkour.system.levelsystem.LevelHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetLevelDataCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        int levelID = Integer.parseInt(args[0]);
        LevelData ld = LevelHandler.levelList.get(levelID);

        switch (args[2]){
            case "points":
                break;
            case "location":

                break;
            case "reward":

                break;
            case "name":

                break;
            case "icon":

                break;
            case "creator":

                break;
        }
        sender.sendMessage("not working yet");
        return true;
    }
}
