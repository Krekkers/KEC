package krekks.easyparkour.command.admin;

import krekks.easyparkour.playerdata.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static krekks.easyparkour.misc.KrekkMessages.krekkSendMessageArray;
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
        String checkpoint = "&a No checkpoint available...";
        if(d.getCheckpointLocation() != null){
                checkpoint = "&aCheckpoint location : &c" +
                        d.getCheckpointLocation().getX()
                        + " "
                        + d.getCheckpointLocation().getY()
                        + " "
                        + d.getCheckpointLocation().getZ();
        }
        krekkSendMessageArray((Player) sender,
                "---------------------",
                "&a&lPlayer : &c" + d.getPlayer().getName(),
                "> " + checkpoint,
                "> " + "&aLevel : &c" + d.getLevel(),
                "> " + "&aPoints : &c" + d.getPoints(),
                "---------------------"
                );
        return true;
    }
}
