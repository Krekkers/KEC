package krekks.easyparkour.command.level.admin;

import krekks.easyparkour.misc.KrekkMessages;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static krekks.easyparkour.KEP.gameWorld;
import static krekks.easyparkour.manager.levelmanager.LevelHandler.createNewLevel;

public class AddNewLevelCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //"Usage </command> <level name> <creator> <x> <y> <z> <difficulty> <icon> <required points> <reward>"
        if(!(sender instanceof Player))
            return false;
        if(args[0].equals("help")){
            KrekkMessages.krekkSendMessageArray((Player) sender,
                    ChatColor.GREEN + "Here is how to use this command!",
                    ChatColor.GREEN + "/createlevel <level name> <creator> <x> <y> <z> <difficulty> <icon> <required points> <reward> | " + ChatColor.RED + "Or /createlevel clean level",
                    ChatColor.GREEN + "level name = name of the level",
                    ChatColor.GREEN + "creator = the creator of the level",
                    ChatColor.GREEN + "x y z = location coordinates",
                    ChatColor.GREEN + "difficulty = 0 / 10 difficulty rating",
                    ChatColor.GREEN + "icon = the item displayed on the level menu",
                    ChatColor.GREEN + "required points = the points required to play the level(can be 0)",
                    ChatColor.GREEN + "reward = the points reward on completing a level"
                    );
            return false;
       }
        if(args.length < 2)
            return false;
        if(args[0].equals("clean") && args[1].equals("level")){
            createNewLevel("Krekks Easy Parkour","Krekkers", ((Player) sender).getLocation() ,1, Material.matchMaterial("bedrock"),0,0);
            sender.sendMessage(ChatColor.GREEN + "A clean level has been generated.");
            sender.sendMessage(ChatColor.GREEN + "You can edit this level using : " + ChatColor.RED + "/setleveldata");
            return true;
        }
        if(args.length < 8)
            return false;
        Player p = (Player) sender;
        String lname = args[0];
        String creator = args[1];
        double x = Double.parseDouble(args[2]);
        double y = Double.parseDouble(args[3]);
        double z = Double.parseDouble(args[4]);
        Location loc = new Location(gameWorld, x,y,z);
        int difficulty = Integer.parseInt(args[5]);
        Material icon = Material.matchMaterial(args[6]);
        int points = Integer.parseInt(args[7]);
        int reward = Integer.parseInt(args[8]);
        createNewLevel(lname,creator, loc ,difficulty, icon,points,reward);
        sender.sendMessage(ChatColor.GREEN + "You have created a new level named : " + ChatColor.RED + lname);
        return true;
    }
}
