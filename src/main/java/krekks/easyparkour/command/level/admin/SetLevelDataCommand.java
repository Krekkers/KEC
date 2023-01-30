package krekks.easyparkour.command.level.admin;

import krekks.easyparkour.misc.KrekkMessages;
import krekks.easyparkour.system.levelsystem.LevelData;
import krekks.easyparkour.system.levelsystem.LevelHandler;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static krekks.easyparkour.KEP.gameWorld;

public class SetLevelDataCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args[0].equals("help")){
            KrekkMessages.krekkSendMessageArray((Player) sender,
                    ChatColor.GREEN + "You can use any of these commands!",
                    ChatColor.GREEN +"/setleveldata points 5",
                    ChatColor.GREEN +"/setleveldata reward 5",
                    ChatColor.GREEN +"/setleveldata location x y z",
                    ChatColor.GREEN +"/setleveldata name yourlevelname",
                    ChatColor.GREEN +"/setleveldata creator username",
                    ChatColor.GREEN +"/setleveldata icon GRASS_BLOCK",
                    ChatColor.GREEN +"/setleveldata difficulty 1 (this can be 0 to 10)");
            return false;
        }
        int levelID = Integer.parseInt(args[0]);
        LevelData ld = LevelHandler.levelList.get(levelID);
        switch (args[1]) {
            case "points" -> {
                ld.setPoints(Integer.parseInt(args[2]));
                sender.sendMessage(ChatColor.GREEN + "Successfully set required points to : " + ChatColor.RED + ld.getPoints());
            }
            case "location" -> {
                ld.setLevelSpawn(new Location(gameWorld, Double.parseDouble(args[2]), Double.parseDouble(args[3]), Double.parseDouble(args[4]) , 90 ,0));
                sender.sendMessage(ChatColor.GREEN + "Successfully set location to : " + ChatColor.RED + ld.getLevelSpawn().getX() + ", " + ld.getLevelSpawn().getY() + ", " + ld.getLevelSpawn().getZ());
            }
            case "reward" -> {
                ld.setReward(Integer.parseInt(args[2]));
                sender.sendMessage(ChatColor.GREEN + "Successfully set reward to : " + ChatColor.RED + ld.getReward());
            }
            case "name" -> {
                ld.setLevelName(args[2]);
                sender.sendMessage(ChatColor.GREEN + "Successfully set name to : " + ChatColor.RED + ld.getLevelName());
            }
            case "icon" -> {
                ld.setIcon(Material.matchMaterial(args[2]));
                sender.sendMessage(ChatColor.GREEN + "Successfully set icon to : " + ChatColor.RED + ld.getIcon());
            }
            case "creator" -> {
                ld.setCreator(args[2]);
                sender.sendMessage(ChatColor.GREEN + "Successfully set creator to : " + ChatColor.RED + ld.getCreator());
            }
            case "difficulty" -> {
                ld.setDifficulty(Integer.parseInt(args[2]));
                sender.sendMessage(ChatColor.GREEN + "Successfully set difficulty to : " + ChatColor.RED + ld.getDifficulty());
            }
            default -> sender.sendMessage(ChatColor.RED + "Invalid field");
        }

        return true;
    }
}
