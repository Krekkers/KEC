package krekks.easyparkour.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import static krekks.easyparkour.misc.GoBack.goToCheckPoint;
import static krekks.easyparkour.misc.ItemMaker.checkpointItem;

public class Interact implements Listener {
    @EventHandler
    void interactEvent(PlayerInteractEvent e){
        if(e.getItem() == null) return;
        if (e.getItem().getItemMeta().equals(checkpointItem.getItemMeta())) {
            goToCheckPoint(e.getPlayer());
            e.setCancelled( true);
        }

    }
    @EventHandler
    void noPlace(BlockPlaceEvent e){
        if(!e.getPlayer().hasPermission("krekks.perms")){
            e.setCancelled(true);
        }
    }
}
