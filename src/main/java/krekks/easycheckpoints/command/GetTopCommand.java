package krekks.easycheckpoints.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static krekks.easycheckpoints.playerdata.PlayerDataHandler.data;
import static krekks.easycheckpoints.playerdata.PlayerDataHandler.finishedList;

public class GetTopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("Printing All finished players in order!");
        for(int i = 0; i < 10; i++){
            if(i > finishedList.size()) return true;
                Player p = finishedList.get(i);
                sender.sendMessage(ChatColor.YELLOW + "Player in position : " + ChatColor.RED + (i + 1) + ChatColor.YELLOW + " is : "  + ChatColor.RED + p.getName());
                sender.sendMessage(ChatColor.YELLOW + "They took : " + ChatColor.RED + data.get(i).getSecondsToFinish() + " seconds");
        }
        return true;
    }
}
