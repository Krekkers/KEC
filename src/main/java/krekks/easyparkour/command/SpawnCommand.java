package krekks.easyparkour.command;

import krekks.easyparkour.Config;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

import static krekks.easyparkour.KEP.PLUGIN;

public class SpawnCommand implements CommandExecutor {
    HashMap<UUID, Long> active;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        p.sendMessage(Config.primary + "You are being teleported to spawn." + Config.secondary + " Please wait 5 seconds!");
        Location location = p.getLocation().clone();
        if(active.containsKey(p.getUniqueId()))
            return true;
        active.put(p.getUniqueId(),System.currentTimeMillis());
        new BukkitRunnable() {
            @Override
            public void run() {
                if(location.getX() != p.getLocation().getX() && location.getY() != p.getLocation().getY() &&  location.getZ() != p.getLocation().getZ()){
                    p.sendMessage(Config.secondary + "You have moved. Don't move please.");
                    cancel();
                    return;
                }
                p.teleport(Config.spawn);
                active.remove(p.getUniqueId());
                cancel();
            }
        }.runTaskTimer(PLUGIN, 100,0);
        return true;
    }
}
