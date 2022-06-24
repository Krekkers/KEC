package krekks.easycheckpoints.Events;

import static krekks.easycheckpoints.PlayerData.PlayerDataHandler.*;
import static org.bukkit.Bukkit.getLogger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Leave implements Listener {
    @EventHandler
    void LeaveEvent(PlayerQuitEvent e){
        //removes player from list!
        RemoveFromList(e.getPlayer());
        getLogger().info("Player : " + e.getPlayer().getName() + " Has been removed from the list");
    }
}
