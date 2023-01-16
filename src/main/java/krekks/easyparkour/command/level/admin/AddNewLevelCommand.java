package krekks.easyparkour.command.level.admin;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static krekks.easyparkour.system.levelsystem.LevelHandler.createNewLevel;

public class AddNewLevelCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //"Usage </command> <level name> <creator> <x> <y> <z> <difficulty> <icon> <required points> <reward>"
        if(!(sender instanceof Player))
            return false;
        Player p = (Player) sender;
        sender.sendMessage("Dummy level has been made");
        String lname = args[0];
        String creator = args[1];
        double x = Double.parseDouble(args[2]);
        double y = Double.parseDouble(args[3]);
        double z = Double.parseDouble(args[4]);
        String difficulty = args[5];
        Material icon = Material.matchMaterial(args[6]);
        int points = Integer.parseInt(args[7]);
        int reward = Integer.parseInt(args[8]);
        createNewLevel(lname,creator, p.getLocation() ,difficulty, icon,points,reward);
        return false;
    }
}
