package krekks.easyparkour.event;

import krekks.easyparkour.playerdata.PlayerDataHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;

import static krekks.easyparkour.playerdata.PlayerDataHandler.removeFromList;
import static krekks.easyparkour.storage.PlayerSaveUtil.savePlayer;

public class Leave implements Listener {
    @EventHandler
    void leaveEvent(PlayerQuitEvent e){
        //removes player from list!
        try {
            savePlayer(PlayerDataHandler.getPlayerDataFromList(e.getPlayer()));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        removeFromList(e.getPlayer());
    }
}
