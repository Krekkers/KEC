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
        sender.sendMessage("> " + ChatColor.GREEN + "Points : " + ChatColor.RED + d.getPoints());
        sender.sendMessage("> " + ChatColor.GREEN + "Level : " + ChatColor.RED + d.getLevel());
        sender.sendMessage("---------------------");
        return true;
    }
}
