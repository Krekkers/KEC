package krekks.easyparkour.command;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import static krekks.easyparkour.KEP.PLUGIN;
import static krekks.easyparkour.KEP.config;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        p.sendMessage(config.primary + "You are being teleported to spawn." + config.secondary + " Please wait 5 seconds!");
        Location location = p.getLocation().clone();
        new BukkitRunnable() {
            @Override
            public void run() {
                if(location.getX() != p.getLocation().getX() && location.getY() != p.getLocation().getY() &&  location.getZ() != p.getLocation().getZ()){
                    p.sendMessage(config.secondary + "You have moved. Don't move please.");
                    cancel();
                    return;
                }
                p.teleport(config.spawn);
                cancel();
            }
        }.runTaskTimer(PLUGIN, 100,0);
        return true;
    }
}
