package krekks.easyparkour.event;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import static krekks.easyparkour.misc.GoBack.goToCheckPoint;

public class Interact implements Listener {
    /*
    //might be interesting to get working in the future
    @EventHandler
    void Launch(PlayerInteractEvent e){
    }

     */
    @EventHandler
    void interactEvent(PlayerInteractEvent e){
        if(e.getItem() == null) return;
        if (e.getItem().getType() == Material.REDSTONE) {
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
