package krekks.easyparkour.command.level.admin;

import krekks.easyparkour.misc.KrekkMessages;
import krekks.easyparkour.manager.levelmanager.LevelData;
import krekks.easyparkour.manager.levelmanager.LevelHandler;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static krekks.easyparkour.KEP.config;
import static krekks.easyparkour.KEP.gameWorld;

public class SetLevelDataCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args[0].equals("help")){
            KrekkMessages.krekkSendMessageArray((Player) sender,
                    config.primary + "You can use any of these commands!",
                    config.primary + "/setleveldata points 5",
                    config.primary + "/setleveldata reward 5",
                    config.primary + "/setleveldata location x y z",
                    config.primary + "/setleveldata name yourlevelname",
                    config.primary + "/setleveldata creator username",
                    config.primary +"/setleveldata icon GRASS_BLOCK",
                    config.primary  +"/setleveldata difficulty 1 (this can be 0 to 10)");
            return false;
        }
        int levelID = Integer.parseInt(args[0]);
        LevelData ld = LevelHandler.levelList.get(levelID);
        switch (args[1]) {
            case "points" -> {
                ld.setPoints(Integer.parseInt(args[2]));
                sender.sendMessage(config.primary  + "Successfully set required points to : " + config.secondary + ld.getPoints());
            }
            case "location" -> {
                ld.setLevelSpawn(new Location(gameWorld, Double.parseDouble(args[2]), Double.parseDouble(args[3]), Double.parseDouble(args[4]) , 90 ,0));
                sender.sendMessage(config.primary  + "Successfully set location to : " + config.secondary + ld.getLevelSpawn().getX() + ", " + ld.getLevelSpawn().getY() + ", " + ld.getLevelSpawn().getZ());
            }
            case "reward" -> {
                ld.setReward(Integer.parseInt(args[2]));
                sender.sendMessage(config.primary  + "Successfully set reward to : " + config.secondary  + ld.getReward());
            }
            case "name" -> {
                ld.setLevelName(args[2]);
                sender.sendMessage(config.primary + "Successfully set name to : " + config.secondary + ld.getLevelName());
            }
            case "icon" -> {
                ld.setIcon(Material.matchMaterial(args[2]));
                sender.sendMessage(config.primary  + "Successfully set icon to : " + config.secondary  + ld.getIcon());
            }
            case "creator" -> {
                ld.setCreator(args[2]);
                sender.sendMessage(config.primary + "Successfully set creator to : " + config.secondary  + ld.getCreator());
            }
            case "difficulty" -> {
                ld.setDifficulty(Integer.parseInt(args[2]));
                sender.sendMessage(config.primary  + "Successfully set difficulty to : " + config.secondary + ld.getDifficulty());
            }
            default -> sender.sendMessage(config.primary  + "Invalid field");
        }

        return true;
    }
}
