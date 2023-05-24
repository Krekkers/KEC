package krekks.easyparkour.command.admin;

import krekks.easyparkour.playerdata.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static krekks.easyparkour.KEP.config;
import static krekks.easyparkour.misc.KrekkMessages.krekkSendMessageArray;
import static krekks.easyparkour.playerdata.PlayerDataHandler.getPlayerDataFromList;
public class GetPlayerInListCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args[0] == null){
            sender.sendMessage(config.error + "Please provide a username");
            return true;
        }
        PlayerData d = getPlayerDataFromList(Bukkit.getPlayer(args[0]));
        if(d == null){
            sender.sendMessage(config.error + "This player does not exist in the data list");
            return true;
        }
        String checkpoint = "&a No checkpoint available...";
        if(d.getCheckpointLocation() != null){
                checkpoint = config.primary + "Checkpoint location : "+ config.secondary +
                        d.getCheckpointLocation().getX()
                        + ", "
                        + d.getCheckpointLocation().getY()
                        + ", "
                        + d.getCheckpointLocation().getZ();
        }
        krekkSendMessageArray((Player) sender,
                "---------------------",
                config.primary + "&lPlayer : &c" + d.getPlayer().getName(),
                config.gray + "> " + checkpoint,
                config.gray + "> " + "&aLevel : " + config.secondary +  d.getLevel(),
                config.gray + "> " + config.primary + "Points : " + config.secondary + d.getPoints(),
                "---------------------"
                );
        return true;
    }
}
