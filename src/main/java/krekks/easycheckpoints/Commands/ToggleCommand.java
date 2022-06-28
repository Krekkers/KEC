package krekks.easycheckpoints.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import static krekks.easycheckpoints.EasyCheckpoints.Toggle;
import static krekks.easycheckpoints.EasyCheckpoints.plugin;

public class ToggleCommand implements CommandExecutor {

    public static int sec;

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Toggle = !Toggle;
        sender.sendMessage("Checkpoint plugin = " + Toggle);
        if(Toggle){
            new BukkitRunnable() {
                @Override
                public void run() {
                    sec += 1;
                }
            }.runTaskTimer(plugin, 0,20);
        }else { sec = 0; }

        return false;
    }
}
