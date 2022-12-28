package krekks.easyparkour.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static krekks.easyparkour.playerdata.PlayerDataHandler.removeFromList;

public class Leave implements Listener {
    @EventHandler
    void leaveEvent(PlayerQuitEvent e){
        //removes player from list!
        removeFromList(e.getPlayer());
    }
}
