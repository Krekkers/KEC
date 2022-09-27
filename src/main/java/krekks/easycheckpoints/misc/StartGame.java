package krekks.easycheckpoints.misc;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.time.Instant;

import static krekks.easycheckpoints.EasyCheckpoints.*;


public class StartGame {



    public static void startGame(CommandSender sender){
        Toggle = !Toggle;
        sender.sendMessage(ChatColor.YELLOW + "KEC toggled state = " + ChatColor.RED + Toggle);
        if(Toggle){
            time = Instant.now();
        }else { sec = 0; }

    }

}
