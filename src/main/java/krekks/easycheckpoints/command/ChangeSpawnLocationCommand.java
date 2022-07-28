package krekks.easycheckpoints.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

import static krekks.easycheckpoints.EasyCheckpoints.*;

public class ChangeSpawnLocationCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("Manually changing these settings will result in all comments being wiped from the config file.");
        List<Double> xyz = new ArrayList<>();
        xyz.add(Double.parseDouble(args[0]));
        xyz.add(Double.parseDouble(args[1]));
        xyz.add(Double.parseDouble(args[2]));
        config.set("spawnlocation",xyz);
        sender.sendMessage(ChatColor.YELLOW + "Spawn location has been set to : " + xyz);
        spawnX = config.getDoubleList("spawnlocation").get(0);
        spawnY = config.getDoubleList("spawnlocation").get(1);
        spawnZ = config.getDoubleList("spawnlocation").get(2);
        plugin.saveConfig();
        return true;
    }
}
