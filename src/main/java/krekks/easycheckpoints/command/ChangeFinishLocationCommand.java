package krekks.easycheckpoints.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

import static krekks.easycheckpoints.Config.*;
import static krekks.easycheckpoints.EasyCheckpoints.*;

public class ChangeFinishLocationCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("Manually changing these settings will result in all comments being wiped from the config file.");
        List<Double> xyz = new ArrayList<>();
        xyz.add(Double.parseDouble(args[0]));
        xyz.add(Double.parseDouble(args[1]));
        xyz.add(Double.parseDouble(args[2]));
        config.set("finishlocation",xyz);
        sender.sendMessage(ChatColor.YELLOW + "Finish location has been set to : " + xyz);
        finishX = config.getDoubleList("finishlocation").get(0);
        finishY = config.getDoubleList("finishlocation").get(1);
        finishZ = config.getDoubleList("finishlocation").get(2);
        plugin.saveConfig();
        return true;
    }
}
