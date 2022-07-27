package krekks.easycheckpoints.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static krekks.easycheckpoints.playerdata.PlayerDataHandler.setCheckpointOf;

public class SetCheckpointCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Location loc = Bukkit.getPlayer(args[0]).getLocation();
        loc.setX(Double.parseDouble(args[1]));
        loc.setY(Double.parseDouble(args[2]));
        loc.setZ(Double.parseDouble(args[3]));

        setCheckpointOf(Bukkit.getPlayer(args[0]), loc);
        sender.sendMessage(ChatColor.RED + args[0] + ChatColor.YELLOW + " His checkpoint has been manually changed to : " + ChatColor.RED + args[1] + " " + args[2] + " " + args[3]);

        return true;
    }
}
