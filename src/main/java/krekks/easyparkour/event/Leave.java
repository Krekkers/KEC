package krekks.easyparkour.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static krekks.easyparkour.playerdata.PlayerDataHandler.removeFromList;
import static org.bukkit.Bukkit.getLogger;

public class Leave implements Listener {
    @EventHandler
    void leaveEvent(PlayerQuitEvent e){
        //removes player from list!
        removeFromList(e.getPlayer());
        getLogger().info("Player : " + e.getPlayer().getName() + " Has been removed from the list");
    }
}
