package krekks.easycheckpoints.command.admin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static krekks.easycheckpoints.playerdata.PlayerDataHandler.finishedList;
import static krekks.easycheckpoints.playerdata.PlayerDataHandler.playerList;

public class DisplayTopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        for(int i = 0; i < 10; i++){
            if(i >= finishedList.size()) return true;
            Player p = finishedList.get(i);
            Bukkit.broadcastMessage(ChatColor.YELLOW + "Player in position : " + ChatColor.RED + (i + 1) + " is : " + p.getName());
            Bukkit.broadcastMessage(ChatColor.YELLOW + "They took : " + ChatColor.RED + playerList.get(p).getSecondsToFinish() + " seconds");
        }
        return true;
    }
}
