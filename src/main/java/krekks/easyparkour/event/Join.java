package krekks.easyparkour.event;

import krekks.easyparkour.Config;
import krekks.easyparkour.misc.item.ItemMaker;
import krekks.easyparkour.playerdata.PlayerDataHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static krekks.easyparkour.playerdata.PlayerDataHandler.AddToList;

public class Join implements Listener {
    @EventHandler
    void joinEvent(PlayerJoinEvent e){
        //Made to decrease the likelihood of there being a leak
        if(!PlayerDataHandler.isInListPlayer(e.getPlayer())) {
            //adds player to the player list and sets his checkpoint
            AddToList(e.getPlayer(), Config.spawn);
        }
        //add checkpoint item and level selector to inventory
        e.getPlayer().getInventory().setItem(8, ItemMaker.checkpointItem);
        e.getPlayer().getInventory().setItem(0, ItemMaker.levelSelector);
    }
}
