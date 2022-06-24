package krekks.easycheckpoints.Commands;

import krekks.easycheckpoints.PlayerData.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.Console;

import static org.bukkit.ChatColor.translateAlternateColorCodes;
import static krekks.easycheckpoints.PlayerData.PlayerDataHandler.*;

public class GetListCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        for(PlayerData d : data){
            p.sendMessage(ChatColor.YELLOW + "Player Data >");
            p.sendMessage(ChatColor.YELLOW + "Player : " + ChatColor.RED + d.getP().getName());
            p.sendMessage(ChatColor.YELLOW + "CheckPointLocation : " + ChatColor.RED + d.getCheckpointLocation());
        }

        return false;
    }
}
