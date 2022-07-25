package krekks.easycheckpoints.event;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import static krekks.easycheckpoints.EasyCheckpoints.Toggle;
import static krekks.easycheckpoints.GoBack.GoToCheckPoint;

public class Interact implements Listener {
    /*
    //might be interesting to get working in the future
    @EventHandler
    void Launch(PlayerInteractEvent e){
    }

     */
    @EventHandler
    void Back(PlayerInteractEvent e){
        if(!Toggle) return;
        if (e.getPlayer().getItemInHand().getType() == Material.REDSTONE) {
            GoToCheckPoint(e.getPlayer());
            e.setCancelled( true);
        }
    }
    @EventHandler
    void NoPlace(BlockPlaceEvent e){
        if(Toggle && !e.getPlayer().hasPermission("krekks.perms")){
            e.setCancelled(true);
        }
    }
}
