package krekks.easycheckpoints.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import static krekks.easycheckpoints.EasyCheckpoints.Toggle;

public class InventoryEvents implements Listener {
    @EventHandler
    void inventoryUpdater(InventoryClickEvent e){
        //making sure
        if(Toggle && !e.getWhoClicked().hasPermission("krekks.perms")){
            if(e.getSlot() < 0) return;             //avoids big scary error
            e.setCancelled(true);                   //cancel the action
        }
    }
    //prevents people from dropping the item before the game starts
    @EventHandler
    void itemDropEvent(PlayerDropItemEvent e){
        if(Toggle && !e.getPlayer().hasPermission("krekks.perms")) return;
        e.setCancelled(true);
    }


}
