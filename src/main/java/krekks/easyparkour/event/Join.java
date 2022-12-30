package krekks.easyparkour.event;

import krekks.easyparkour.misc.ItemMaker;
import krekks.easyparkour.playerdata.PlayerDataHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static krekks.easyparkour.Config.spawn;
import static krekks.easyparkour.playerdata.PlayerDataHandler.AddToList;
import static krekks.easyparkour.system.storage.PlayerSaveUtil.getPlayerFromDB;

public class Join implements Listener {
    @EventHandler
    void joinEvent(PlayerJoinEvent e){
        AddToList(e.getPlayer(), spawn);
        getPlayerFromDB(PlayerDataHandler.getFromList(e.getPlayer()));


        e.getPlayer().getInventory().setItem(8, ItemMaker.checkpointItem);
    }
}
