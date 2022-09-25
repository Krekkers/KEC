package krekks.easycheckpoints.misc;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import static krekks.easycheckpoints.EasyCheckpoints.*;
import static krekks.easycheckpoints.EasyCheckpoints.sec;

public class StartGame {
    public static void startGame(CommandSender sender){
        Toggle = !Toggle;
        sender.sendMessage(ChatColor.YELLOW + "KEC toggled state = " + ChatColor.RED + Toggle);
        if(Toggle){
            new BukkitRunnable() {
                @Override
                public void run() {
                    sec += 1;
                    if(!Toggle){
                        this.cancel();
                    }
                }
            }.runTaskTimer(plugin, 0,20);
        }else { sec = 0; }

    }

}
