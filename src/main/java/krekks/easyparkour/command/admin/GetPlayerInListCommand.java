package krekks.easyparkour.command.admin;

import krekks.easyparkour.playerdata.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static krekks.easyparkour.playerdata.PlayerDataHandler.getFromList;
public class GetPlayerInListCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args[0] == null){
            sender.sendMessage("Please provide a username");
            return true;
        }
        PlayerData d = getFromList(Bukkit.getPlayer(args[0]));
        if(d == null){
            sender.sendMessage("This player does not exist in the data list");
            return true;
        }
        sender.sendMessage("---------------------");
        //sending playerdata
        sender.sendMessage("> " + ChatColor.GREEN + "Player Data");
        sender.sendMessage("====================");
        sender.sendMessage("> " + ChatColor.GREEN + "Player : " + ChatColor.DARK_GREEN + d.getPlayer().getName());
        if(d.getCheckpointLocation() != null){
            sender.sendMessage("> " + ChatColor.GREEN + "CheckPointLocation : " +
                    ChatColor.RED + d.getCheckpointLocation().getX() +
                    ", " + d.getCheckpointLocation().getY() + ", " +
                    d.getCheckpointLocation().getZ());
        }
        sender.sendMessage("> " + ChatColor.GREEN + "Go back counter : " + ChatColor.RED + d.getGoBackCounter());
        sender.sendMessage("> " + ChatColor.GREEN + "Points : " + ChatColor.RED + d.getPoints());
        sender.sendMessage("> " + ChatColor.GREEN + "Level : " + ChatColor.RED + d.getLevel());
        sender.sendMessage("---------------------");
        return true;
    }
}
