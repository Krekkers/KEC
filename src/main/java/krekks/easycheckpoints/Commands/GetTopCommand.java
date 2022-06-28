package krekks.easycheckpoints.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static krekks.easycheckpoints.PlayerData.PlayerDataHandler.finishedList;

public class GetTopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("Printing All finished players in order!");
        for(int i = 0; i < 10; i++){
            if(finishedList.size() <= i ){
                Player p = finishedList.get(i);
                sender.sendMessage("Player in position " + (i + 1) + " is : " + p.getName());
            }
        }


        return true;
    }
}
