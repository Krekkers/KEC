package krekks.easycheckpoints.command.admin;

import krekks.easycheckpoints.playerdata.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static krekks.easycheckpoints.playerdata.PlayerDataHandler.getFromList;
public class GetPlayerInListCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        PlayerData d = getFromList(Bukkit.getPlayer(args[0]));
        if(d == null){
            sender.sendMessage("Data = null");
            return true;
        }
        sender.sendMessage("---------------------");
        //sending playerdata
        sender.sendMessage("> " + ChatColor.GREEN + "Player Data");
        sender.sendMessage("> " + ChatColor.GREEN + "Player : " + ChatColor.DARK_GREEN + d.getPlayer().getName());
        if(d.getCheckpointLocation() != null){
            sender.sendMessage("> " + ChatColor.GREEN + "CheckPointLocation : " + ChatColor.DARK_GREEN + d.getCheckpointLocation().getX() + " " + d.getCheckpointLocation().getY() + " " + d.getCheckpointLocation().getZ());
        }
        sender.sendMessage("> " + ChatColor.GREEN + "Go back counter : " + ChatColor.DARK_GREEN + d.getGoBackCounter());
        if(d.getFinished()){
            sender.sendMessage("> " + ChatColor.GREEN + "Finished = " + ChatColor.DARK_GREEN + d.getFinished());
            sender.sendMessage("> " + ChatColor.GREEN + "It took them : " + ChatColor.DARK_GREEN + d.getSecondsToFinish() + " Seconds");
        }
        return true;
    }
}
