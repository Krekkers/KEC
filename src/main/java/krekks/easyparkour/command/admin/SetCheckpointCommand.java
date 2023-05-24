package krekks.easyparkour.command.admin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static krekks.easyparkour.KEP.config;
import static krekks.easyparkour.playerdata.PlayerDataHandler.setCheckpointOf;

public class SetCheckpointCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //location setter
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args[1] == null || args[2] == null || args[3] == null ){
                sender.sendMessage(config.error + "A location needs to be provided");
                sender.sendMessage(config.primary + "Example : " + config.secondary + "/setcheckpointof playername 100 50 100");
            }
            Location loc = new Location(p.getWorld(), Double.parseDouble(args[1]),Double.parseDouble(args[2]),Double.parseDouble(args[3]));
            setCheckpointOf(Bukkit.getPlayer(args[0]), loc);
            sender.sendMessage(ChatColor.RED + args[0] + config.primary + " His checkpoint has been manually changed to : " + config.secondary + args[1] + " " + args[2] + " " + args[3]);
        }else{
            sender.sendMessage(config.error + "Sorry but only players can do this command due to the world needing to be a option as of now.");
            sender.sendMessage(config.error + "I will fix this in the future.");
        }
        return true;
    }
}
