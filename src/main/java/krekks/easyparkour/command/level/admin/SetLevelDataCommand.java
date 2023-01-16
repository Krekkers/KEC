package krekks.easyparkour.command.level.admin;

import krekks.easyparkour.system.levelsystem.LevelData;
import krekks.easyparkour.system.levelsystem.LevelHandler;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static krekks.easyparkour.KEP.gameWorld;

public class SetLevelDataCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        int levelID = Integer.parseInt(args[0]);
        LevelData ld = LevelHandler.levelList.get(levelID);

        switch (args[2]) {
            case "points" -> ld.setPoints(Integer.parseInt(args[3]));
            case "location" -> ld.setLevelSpawn(new Location(gameWorld, Double.parseDouble(args[3]), Double.parseDouble(args[4]), Double.parseDouble(args[5])));
            case "reward" -> ld.setReward(Integer.parseInt(args[3]));
            case "name" -> ld.setLevelName(args[3]);
            case "icon" -> ld.setIcon(Material.matchMaterial(args[3]));
            case "creator" -> ld.setCreator(args[3]);
            case "difficulty" -> ld.setDifficulty(args[3]);
        }
        return true;
    }
}
