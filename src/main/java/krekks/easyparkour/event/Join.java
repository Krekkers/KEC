package krekks.easyparkour.event;

import krekks.easyparkour.Config;
import krekks.easyparkour.misc.item.ItemMaker;
import krekks.easyparkour.playerdata.PlayerDataHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;

import static krekks.easyparkour.playerdata.PlayerDataHandler.AddToList;
import static krekks.easyparkour.system.storage.PlayerSaveUtil.savePlayer;

public class Join implements Listener {
    @EventHandler
    void joinEvent(PlayerJoinEvent e){
        //Made to decrease the likelihood of there being a leak
        if(!PlayerDataHandler.isInListPlayer(e.getPlayer())) {
            //adds player to the player list and sets his checkpoint
            AddToList(e.getPlayer(), Config.spawn);
            try {
                savePlayer(PlayerDataHandler.getPlayerDataFromList(e.getPlayer()));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        //add checkpoint item and level selector to inventory
        e.getPlayer().getInventory().setItem(8, ItemMaker.checkpointItem);
        e.getPlayer().getInventory().setItem(0, ItemMaker.levelSelector);
    }
}
