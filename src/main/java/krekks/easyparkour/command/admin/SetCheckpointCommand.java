package krekks.easyparkour.command.admin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static krekks.easyparkour.playerdata.PlayerDataHandler.setCheckpointOf;

public class SetCheckpointCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //location setter
        if(sender instanceof Player){
            Player p = (Player) sender;
            Location loc = new Location(p.getWorld(), Double.parseDouble(args[1]),Double.parseDouble(args[2]),Double.parseDouble(args[3]));
            setCheckpointOf(Bukkit.getPlayer(args[0]), loc, loc.getBlock());
            sender.sendMessage(ChatColor.RED + args[0] + ChatColor.GREEN + " His checkpoint has been manually changed to : " + ChatColor.RED + args[1] + " " + args[2] + " " + args[3]);
        }else{
            sender.sendMessage("Sorry but only players can do this command due to the world needing to be a option as of now.");
            sender.sendMessage("I will fix this in the future.");
        }
        return true;
    }
}
