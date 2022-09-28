package krekks.easycheckpoints.command;

import krekks.easycheckpoints.playerdata.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.time.Instant;

import static krekks.easycheckpoints.EasyCheckpoints.*;
import static krekks.easycheckpoints.playerdata.PlayerDataHandler.data;
import static krekks.easycheckpoints.playerdata.PlayerDataHandler.removeFromList;

public class RestartCommand implements CommandExecutor {
    //not implemented yet.
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //data to reset.
        /*
        -finished
        -seconds
         */
        sender.sendMessage(ChatColor.RED + "Resetting Data");
        sender.sendMessage(ChatColor.RED + "This feature should be used with precaution.");
        //the resetting part.
        for(PlayerData d : data){
            if(!d.getPlayer().isOnline()){
                removeFromList(d.getPlayer());
            }
            d.setFinished(false);
            d.setSecondsToFinish(0);
            d.setCheckpointLocation(new Location(d.getPlayer().getWorld(), spawnX,spawnY,spawnZ));
            d.getPlayer().teleport(d.getCheckpointLocation());
            d.setGoBackCounter(0);
            time = Instant.now();
        }
        //gotta toggle the game off when resetting
        Toggle = false;
        return true;
    }
}
