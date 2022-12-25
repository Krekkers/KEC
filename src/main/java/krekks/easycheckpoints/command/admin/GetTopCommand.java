package krekks.easycheckpoints.command.admin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static krekks.easycheckpoints.playerdata.PlayerDataHandler.finishedList;
import static krekks.easycheckpoints.playerdata.PlayerDataHandler.playerList;

public class GetTopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("Printing All finished players in order!");
        for(int i = 0; i < 10; i++){
            if(i > finishedList.size()) return true;
                Player p = finishedList.get(i);
                sender.sendMessage(ChatColor.GREEN + "Player in position : " + ChatColor.DARK_GREEN + (i + 1) + ChatColor.GREEN + " is : "  + ChatColor.DARK_GREEN + p.getName());
                sender.sendMessage(ChatColor.GREEN + "They took : " + ChatColor.DARK_GREEN + playerList.get(p).getSecondsToFinish() + " seconds");
        }
        return true;
    }
}
