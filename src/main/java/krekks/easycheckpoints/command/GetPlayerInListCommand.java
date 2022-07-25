package krekks.easycheckpoints.command;

import krekks.easycheckpoints.playerdata.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static krekks.easycheckpoints.playerdata.PlayerDataHandler.getFromList;
public class GetPlayerInListCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        PlayerData d = getFromList(args[0]);
        if(d == null){
            sender.sendMessage("Data = null");
            return true;
        }
        sender.sendMessage("---------------------");
        //sending playerdata
        sender.sendMessage("> " + ChatColor.YELLOW + "Player Data");
        sender.sendMessage("> " + ChatColor.YELLOW + "Player : " + ChatColor.RED + d.getP().getName());
        if(d.getCheckpointLocation() != null){
            sender.sendMessage("> " + ChatColor.YELLOW + "CheckPointLocation : " + ChatColor.RED + d.getCheckpointLocation().getX() + " " + d.getCheckpointLocation().getY() + " " + d.getCheckpointLocation().getZ());
        }
        sender.sendMessage("> " + ChatColor.YELLOW + "Go back counter : " + ChatColor.RED + d.getGoBackCounter());
        if(d.getFinished()){
            sender.sendMessage("> " + ChatColor.YELLOW + "Finished = " + ChatColor.RED + d.getFinished());
            sender.sendMessage("> " + ChatColor.YELLOW + "It took them : " + ChatColor.RED + d.getSecondsToFinish() + " Seconds");
        }
        return true;
    }
}
