package krekks.easycheckpoints.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static krekks.easycheckpoints.playerdata.PlayerDataHandler.RemoveFromList;
import static org.bukkit.Bukkit.getLogger;

public class Leave implements Listener {
    @EventHandler
    void LeaveEvent(PlayerQuitEvent e){
        //removes player from list!
        RemoveFromList(e.getPlayer());
        getLogger().info("Player : " + e.getPlayer().getName() + " Has been removed from the list");
    }
}
