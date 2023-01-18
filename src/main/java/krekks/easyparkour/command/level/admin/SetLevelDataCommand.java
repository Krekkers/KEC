package krekks.easyparkour.command.level.admin;

import krekks.easyparkour.system.levelsystem.LevelData;
import krekks.easyparkour.system.levelsystem.LevelHandler;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static krekks.easyparkour.KEP.gameWorld;

public class SetLevelDataCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length < 3){
            sender.sendMessage("Please provide data");
            return false;
        }
        int levelID = Integer.parseInt(args[0]);
        LevelData ld = LevelHandler.levelList.get(levelID);
        switch (args[1]) {
            case "points" :
                    ld.setPoints(Integer.parseInt(args[2]));
                sender.sendMessage(ChatColor.GREEN + "Successfully set required points to : " + ChatColor.RED + ld.getPoints());
                    break;
            case "location" : 
                ld.setLevelSpawn(new Location(gameWorld, Double.parseDouble(args[2]), Double.parseDouble(args[3]), Double.parseDouble(args[4])));
                sender.sendMessage(ChatColor.GREEN + "Successfully set location to : " + ChatColor.RED + ld.getLevelSpawn().getX() + ", " + ld.getLevelSpawn().getY() + ", " + ld.getLevelSpawn().getZ());
                break;
            case "reward" :
                ld.setReward(Integer.parseInt(args[2]));
                sender.sendMessage(ChatColor.GREEN + "Successfully set reward to : " + ChatColor.RED + ld.getReward());
                break;
            case "name" :
                ld.setLevelName(args[2]);
                sender.sendMessage(ChatColor.GREEN + "Successfully set name to : " + ChatColor.RED + ld.getLevelName());
                break;
            case "icon" :
                ld.setIcon(Material.matchMaterial(args[2]));
                sender.sendMessage(ChatColor.GREEN + "Successfully set icon to : " + ChatColor.RED + ld.getIcon());
                break;
            case "creator":
                ld.setCreator(args[2]);
                sender.sendMessage(ChatColor.GREEN + "Successfully set creator to : " + ChatColor.RED + ld.getCreator());
                break;
            case "difficulty" :
                ld.setDifficulty(args[2]);
                sender.sendMessage(ChatColor.GREEN + "Successfully set difficulty to : " + ChatColor.RED + ld.getDifficulty());
                break;
            default:
                sender.sendMessage(ChatColor.RED + "Invalid field");
        }

        return false;
    }
}
